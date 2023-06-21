package cwh.hbnu.community.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vdurmont.emoji.EmojiParser;
import cwh.hbnu.community.common.api.ApiResult;
import cwh.hbnu.community.model.dto.CreateTopicDTO;
import cwh.hbnu.community.model.pojo.BmsPost;
import cwh.hbnu.community.model.pojo.UmsUser;
import cwh.hbnu.community.model.vo.PostVO;
import cwh.hbnu.community.service.IPostService;
import cwh.hbnu.community.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static cwh.hbnu.community.jwt.JwtUtil.USER_NAME;

@RestController
@RequestMapping("/post")
public class PostController extends BaseController {

    @Resource
    private IPostService iPostService;
    @Resource
    private IUserService iUserService;

    @GetMapping("/list")
    public ApiResult<Page<PostVO>> list(@RequestParam(value = "tab", defaultValue = "latest") String tab,
                                        @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        Page<PostVO> list = iPostService.getList(new Page<>(pageNo, pageSize), tab);
        return ApiResult.success(list);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ApiResult<BmsPost> create(@RequestHeader(value = USER_NAME) String username,
                                     @RequestBody CreateTopicDTO createTopicDTO){
        UmsUser userByUsername = iUserService.getUserByUsername(username);
        BmsPost topic = iPostService.create(createTopicDTO, userByUsername);
        return ApiResult.success(topic);
    }

    @GetMapping("")
    public ApiResult<Map<String, Object>> view(@RequestParam("id") String id){
        Map<String, Object> map = iPostService.viewTopic(id);
        return ApiResult.success(map);
    }
    
    @GetMapping("/recommend")
    public ApiResult<List<BmsPost>> getRecommend(@RequestParam("topicId") String id){
        List<BmsPost> topics = iPostService.getRecommend(id);
        return ApiResult.success(topics);
    }

    @PostMapping("/update")
    public ApiResult<BmsPost> update(@RequestHeader(value = USER_NAME)String username,
                                     @Valid @RequestBody BmsPost bmsPost){
        UmsUser user = iUserService.getUserByUsername(username);
        Assert.isTrue(user.getId().equals(bmsPost.getUserId()), "非本人没有修改权限！");
        bmsPost.setModifyTime(new Date());
        bmsPost.setContent(EmojiParser.parseToAliases(bmsPost.getContent()));
        iPostService.updateById(bmsPost);
        return ApiResult.success(bmsPost);
    }

    @DeleteMapping("/delete/{id}")  
    public ApiResult<String> delete(@RequestHeader(value = USER_NAME)String username,
                                    @PathVariable("id") String id){
        UmsUser user = iUserService.getUserByUsername(username);
        BmsPost byId = iPostService.getById(id);
        Assert.notNull(byId, "来晚一步，话题已不存在！");
        org.springframework.util.Assert.isTrue(byId.getUserId().equals(user.getId()), "你为什么可以删除别人的话题？？？");
        iPostService.removeById(id);
        return ApiResult.success(null, "删除成功！");
    }
}
