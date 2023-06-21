package cwh.hbnu.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cwh.hbnu.community.mapper.FollowMapper;
import cwh.hbnu.community.model.pojo.BmsFollow;
import cwh.hbnu.community.service.FollowService;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, BmsFollow> implements FollowService {
}
