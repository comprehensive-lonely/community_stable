package cwh.hbnu.community.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cwh.hbnu.community.common.exception.ApiAsserts;
import cwh.hbnu.community.jwt.JwtUtil;
import cwh.hbnu.community.mapper.UserMapper;
import cwh.hbnu.community.model.dto.LoginDTO;
import cwh.hbnu.community.model.dto.RegisterDTO;
import cwh.hbnu.community.model.pojo.UmsUser;
import cwh.hbnu.community.model.vo.ProfileVO;
import cwh.hbnu.community.service.IUserService;
import cwh.hbnu.community.utils.MD5Utils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, UmsUser> implements IUserService {

    @Override
    public UmsUser executeRegister(RegisterDTO registerDTO) {
        LambdaQueryWrapper<UmsUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UmsUser::getUsername, registerDTO.getName()).or().eq(UmsUser::getEmail,registerDTO.getEmail());
        UmsUser user = baseMapper.selectOne(wrapper);
        if (!ObjectUtil.isEmpty(user)){
            ApiAsserts.fail("账号或邮箱已经存在！");
        }
        UmsUser addUser = UmsUser.builder()
                .username(registerDTO.getName())
                .alias(registerDTO.getName())
                .password(MD5Utils.getPwd(registerDTO.getPass()))
                .email(registerDTO.getEmail())
                .createTime(new Date())
                .status(true)
                .build();
        baseMapper.insert(addUser);
//        System.out.println(addUser.getPassword());
        return addUser;
    }

    @Override
    public UmsUser getUserByUsername(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<UmsUser>().eq(UmsUser::getUsername,username));
    }

    @Override
    public String executeLogin(LoginDTO loginDto) {
        String token = null;
        try{
            UmsUser user = getUserByUsername(loginDto.getUsername());
            String encodePwd = MD5Utils.getPwd(loginDto.getPassword());
            System.out.println("encodePwd = " + encodePwd);
            System.out.println("loginDto = " + loginDto.getPassword());
            if(!encodePwd.equals(user.getPassword())){
                throw new Exception("密码错误！");
            }
            token = JwtUtil.generateToken(String.valueOf(user.getUsername()));
        }catch (Exception e){
            log.warn("用户不存在，或密码验证失败！");
        }
        return token;
    }

    @Override
    public ProfileVO getUserProfile(String userId) {
        ProfileVO profile = new ProfileVO();
        UmsUser user = baseMapper.selectById(userId);
        BeanUtil.copyProperties(user, profile); // 把参数相同的赋值user 的参数赋值给 profileVo，非常好用！

        return profile;
    }
}
