package cwh.hbnu.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cwh.hbnu.community.common.api.ApiResult;
import cwh.hbnu.community.model.pojo.BmsBillboard;
import cwh.hbnu.community.service.IBillBoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/billboard")
public class BillBoardController extends BaseController{

    @Resource
    private IBillBoardService iBillBoardService;

    @GetMapping("/show") // 说明处理的是get 请求
    public ApiResult<BmsBillboard> getNotice(){
        List<BmsBillboard> list = iBillBoardService.list(new LambdaQueryWrapper<BmsBillboard>().eq(BmsBillboard::isShow,true)); //使用lambdaQuery的api，
//        for (BmsBillboard bmsBillboard : list) {
//            System.out.println("bmsBillboard = " + bmsBillboard);
//        }
        return ApiResult.success(list.get(list.size()-1)); // 获取最后一条记录
    }

}
