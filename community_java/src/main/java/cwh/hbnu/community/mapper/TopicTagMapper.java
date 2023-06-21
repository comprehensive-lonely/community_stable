package cwh.hbnu.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cwh.hbnu.community.model.pojo.BmsTopicTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TopicTagMapper extends BaseMapper<BmsTopicTag> {
    /**
     * 根据标签获取话题ID集合
     *
     * @param id
     * @return
     */
    Set<String> getTopicIdsByTagId(@Param("id") String id);
}
