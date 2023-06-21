package cwh.hbnu.community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cwh.hbnu.community.common.api.ApiResult;
import cwh.hbnu.community.model.vo.PostVO;
import cwh.hbnu.community.service.IPostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/search")
public class SearchController extends BaseController {

    @Resource
    private IPostService postService;

    @GetMapping("")
    private ApiResult<Page<PostVO>> searchList(@RequestParam("keyword") String keyword,
                                               @RequestParam("pageNum") Integer pageNum,
                                               @RequestParam("pageSize") Integer pageSize){
        Page<PostVO> postVOPage = postService.searchByKey(keyword, new Page<>(pageNum, pageSize));
        return ApiResult.success(postVOPage);
    }

}
