package cwh.hbnu.community.controller;

import cwh.hbnu.community.common.api.ApiResult;
import cwh.hbnu.community.model.pojo.BmsPromotion;
import cwh.hbnu.community.service.IPromotionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/promotion")
public class PromotionController extends BaseController {

    @Resource
    private IPromotionService iPromotionService;

    @GetMapping("/all")
    public ApiResult<List<BmsPromotion>> getPromotion(){
        List<BmsPromotion> list = iPromotionService.list();
        return ApiResult.success(list);
    }

}
