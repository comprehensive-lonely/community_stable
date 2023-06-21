package cwh.hbnu.community.controller;

import cwh.hbnu.community.common.api.ApiResult;
import cwh.hbnu.community.model.pojo.BmsTip;
import cwh.hbnu.community.service.ITipService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/tip")
public class TipController extends BaseController{

    @Resource
    private ITipService iTipService;

    @GetMapping("/today")
    public ApiResult<BmsTip> getRandomTip(){
        BmsTip tip = iTipService.getRandomTip();
        return ApiResult.success(tip);
    }

}
