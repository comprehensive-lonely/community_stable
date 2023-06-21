package cwh.hbnu.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cwh.hbnu.community.common.api.ApiResult;
import cwh.hbnu.community.common.exception.ApiAsserts;
import cwh.hbnu.community.model.pojo.BmsFollow;
import cwh.hbnu.community.model.pojo.UmsUser;
import cwh.hbnu.community.service.FollowService;
import cwh.hbnu.community.service.IUserService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static cwh.hbnu.community.jwt.JwtUtil.USER_NAME;

@RestController
@RequestMapping("/relationship")
public class RelationController {

    @Resource
    private FollowService followService;

    @Resource
    private IUserService userService;

    @GetMapping("/subscribe/{userId}")
    public ApiResult<Object> subscribe(@RequestHeader(value = USER_NAME) String userName,
                                       @PathVariable("userId")String parentId){
        UmsUser user = userService.getUserByUsername(userName);
        if(parentId.equals(user.getId())){
            ApiAsserts.fail("不可以关注自己！");
        }
        // 根据父id 的判断和 userID 的判断看是否存在这么一个关注的关系
        BmsFollow one = followService.getOne(
                new LambdaQueryWrapper<BmsFollow>()
                .eq(BmsFollow::getParentId, parentId)
                .eq(BmsFollow::getFollowerId, user.getId()));

        if(!ObjectUtils.isEmpty(one)){
            ApiAsserts.fail("已关注");
        }
        // 关注
        BmsFollow follow = new BmsFollow();
        follow.setParentId(parentId);
        follow.setFollowerId(user.getId());
        followService.save(follow);
        return ApiResult.success(null, "关注成功！");
    }

    @GetMapping("/unsubscribe/{userId}")
    public ApiResult<Object> Unsubscribe(@RequestHeader(value = USER_NAME) String userName,
                                         @PathVariable("userId")String parentId){
        UmsUser user = userService.getUserByUsername(userName);

        BmsFollow one = followService.getOne(
                new LambdaQueryWrapper<BmsFollow>()
                        .eq(BmsFollow::getParentId, parentId)
                        .eq(BmsFollow::getFollowerId, user.getId())
        );
        // 如果不存在关注的关系，就不能取消关注
        if(ObjectUtils.isEmpty(one)){
            ApiAsserts.fail("未关注");
        }
        // 根据up的id 移除他
        followService.remove(new LambdaQueryWrapper<BmsFollow>().eq(BmsFollow::getParentId, parentId)
        .eq(BmsFollow::getFollowerId, user.getId()));
        return ApiResult.success(null, "取消关注成功！");
    }

    @GetMapping("/validate/{topicUserId}")
    public ApiResult<Map<String, Object>> isSubscribed(@RequestHeader(value = USER_NAME)String username,
                                                       @PathVariable("topicUserId")String topicUserId){
        UmsUser user = userService.getUserByUsername(username);
        Map<String, Object> map = new HashMap<>(16);
        map.put("hasFollow", false);
        if(!ObjectUtils.isEmpty(user)){
            BmsFollow one = followService.getOne(new LambdaQueryWrapper<BmsFollow>()
                    .eq(BmsFollow::getParentId, topicUserId)
                    .eq(BmsFollow::getFollowerId, user.getId()));
            if(!ObjectUtils.isEmpty(one)){
                map.put("hasFollow", true);
            }
        }
        return ApiResult.success(map);
    }

}
