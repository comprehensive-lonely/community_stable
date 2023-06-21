package cwh.hbnu.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cwh.hbnu.community.mapper.PromotionMapper;
import cwh.hbnu.community.model.pojo.BmsPromotion;
import cwh.hbnu.community.service.IPromotionService;
import org.springframework.stereotype.Service;

@Service
public class IPromotionServiceImpl extends ServiceImpl<PromotionMapper, BmsPromotion> implements IPromotionService {

}
