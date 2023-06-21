package cwh.hbnu.community.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cwh.hbnu.community.common.api.ApiResult;
import cwh.hbnu.community.model.dto.LoginDTO;
import cwh.hbnu.community.model.dto.RegisterDTO;
import cwh.hbnu.community.model.pojo.BmsPost;
import cwh.hbnu.community.model.pojo.UmsUser;
import cwh.hbnu.community.service.IPostService;
import cwh.hbnu.community.service.IUserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static cwh.hbnu.community.jwt.JwtUtil.USER_NAME;

@RestController
@RequestMapping("/ums/user")
public class UserController extends BaseController{

    @Resource
    private IUserService iUserService;

    @Resource
    private IPostService postService;

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public ApiResult<Map<String,Object>> register(@Valid @RequestBody RegisterDTO registerDTO){
        UmsUser user = iUserService.executeRegister(registerDTO);
        if(ObjectUtil.isEmpty(user)){
            return ApiResult.failed("账号注册失败！");
        }
        Map<String,Object> map = new HashMap<>(16);
        map.put("user",user);
        return ApiResult.success(map);
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResult<Map<String, String>> login(@RequestBody LoginDTO loginDTO){
        String token = iUserService.executeLogin(loginDTO);
        if(ObjectUtil.isEmpty(token)){
            return ApiResult.failed("账号或密码错误！");
        }
        Map<String,String> map = new HashMap<>(16);
        map.put("token",token);
        return ApiResult.success(map, "登录成功！");
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ApiResult<UmsUser> getUser(@RequestHeader(value = USER_NAME) String userName){
        UmsUser user = iUserService.getUserByUsername(userName);
        return ApiResult.success(user);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ApiResult<Object> logout(){
        return ApiResult.success(null,"注销成功！");
    }

    @GetMapping("/{username}")
    public ApiResult<Map<String, Object>> getUserByName(@PathVariable("username") String username,
                                                        @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                                        @RequestParam(value = "size", defaultValue = "10") Integer size){
        Map<String, Object> map = new HashMap<>(16);
        UmsUser userByUsername = iUserService.getUserByUsername(username);
        Assert.notNull(userByUsername, "用户不存在");
        Page<BmsPost> page = postService.page(new Page<>(pageNo, size),
                new LambdaQueryWrapper<BmsPost>().eq(BmsPost::getUserId, userByUsername.getId()));
        map.put("user", userByUsername);
        map.put("topics", page);
        return ApiResult.success(map);
    }

    @PostMapping("/update")
    public ApiResult<UmsUser> updateUser(@RequestBody UmsUser user) {
        iUserService.updateById(user);
        return ApiResult.success(user);
    }

}
