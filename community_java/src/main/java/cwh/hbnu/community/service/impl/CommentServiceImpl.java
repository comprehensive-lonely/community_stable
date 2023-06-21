package cwh.hbnu.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cwh.hbnu.community.mapper.CommentMapper;
import cwh.hbnu.community.model.dto.CommentDTO;
import cwh.hbnu.community.model.pojo.BmsComment;
import cwh.hbnu.community.model.pojo.UmsUser;
import cwh.hbnu.community.model.vo.CommentVO;
import cwh.hbnu.community.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author CaowenHao
 * @date 2023/6/17  21:42
 * @description
 */

@Slf4j
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, BmsComment> implements CommentService {
    @Override
    public List<CommentVO> getCommentsByTopicID(String topicId) {
        List<CommentVO> list = new ArrayList<CommentVO>();
        try{
            list = this.baseMapper.getCommentsByTopicID(topicId);
        }catch (Exception e){
            log.info("根据帖子 ID 获取评论功能失败！");
            System.out.println("topicId = " + topicId);
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BmsComment create(CommentDTO dto, UmsUser user) {
        BmsComment comment = BmsComment.builder()
                .userId(user.getId())
                .content(dto.getContent())
                .topicId(dto.getTopic_id())
                .createTime(new Date())
                .build();
        this.baseMapper.insert(comment);
        return comment;
    }
}
