package cwh.hbnu.community.controller;

import cwh.hbnu.community.common.api.ApiResult;
import cwh.hbnu.community.model.dto.CommentDTO;
import cwh.hbnu.community.model.pojo.BmsComment;
import cwh.hbnu.community.model.pojo.UmsUser;
import cwh.hbnu.community.model.vo.CommentVO;
import cwh.hbnu.community.service.CommentService;
import cwh.hbnu.community.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static cwh.hbnu.community.jwt.JwtUtil.USER_NAME;

/**
 * @author CaowenHao
 * @date 2023/6/17  21:45
 * @description
 */
@RestController
@RequestMapping("/comment")
public class CommentController extends BaseController{

    @Resource
    private CommentService commentService;
    @Resource
    private IUserService userService;

    @GetMapping("/get_comments")
    public ApiResult<List<CommentVO>> getCommentsByTopicID(@RequestParam(value = "topicid", defaultValue = "1") String topicid){
        List<CommentVO> list = commentService.getCommentsByTopicID(topicid);
        return ApiResult.success(list);
    }

    @PostMapping("/add_comment")
    public ApiResult<BmsComment> addComment(@RequestHeader(value = USER_NAME) String username,
                                            @RequestBody CommentDTO dto){
        UmsUser user = userService.getUserByUsername(username);
        BmsComment comment = commentService.create(dto, user);
        return ApiResult.success(comment);
    }
}
