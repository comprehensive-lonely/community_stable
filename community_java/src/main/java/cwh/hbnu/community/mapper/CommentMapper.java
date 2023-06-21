package cwh.hbnu.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cwh.hbnu.community.model.pojo.BmsComment;
import cwh.hbnu.community.model.vo.CommentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends BaseMapper<BmsComment> {

    /**
     * getCommentsByTopicID
     * @Param topicId
     * return
     * */
    List<CommentVO> getCommentsByTopicID(@Param("topicId") String topicId);

}
