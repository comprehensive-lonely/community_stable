package cwh.hbnu.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cwh.hbnu.community.model.dto.LoginDTO;
import cwh.hbnu.community.model.dto.RegisterDTO;
import cwh.hbnu.community.model.pojo.UmsUser;
import cwh.hbnu.community.model.vo.ProfileVO;

public interface IUserService extends IService<UmsUser> {

//    注册
    UmsUser executeRegister(RegisterDTO registerDTO);
//    根据username查用户
    UmsUser getUserByUsername(String username);

//    用户登录(使用JWT token 验证)
    String executeLogin(LoginDTO loginDto);

    ProfileVO getUserProfile(String userId);
}
