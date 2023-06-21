package cwh.hbnu.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cwh.hbnu.community.model.dto.CommentDTO;
import cwh.hbnu.community.model.pojo.BmsComment;
import cwh.hbnu.community.model.pojo.UmsUser;
import cwh.hbnu.community.model.vo.CommentVO;

import java.util.List;

public interface CommentService extends IService<BmsComment> {

    List<CommentVO> getCommentsByTopicID(String topicId);

    BmsComment create(CommentDTO dto, UmsUser user);
}
