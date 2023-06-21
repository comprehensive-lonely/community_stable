package cwh.hbnu.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cwh.hbnu.community.model.pojo.BmsTag;
import cwh.hbnu.community.model.pojo.BmsTopicTag;

import java.util.List;
import java.util.Set;

public interface ITopicTagService extends IService<BmsTopicTag> {

    List<BmsTopicTag> selectByTopicId(String topicId);

    void createTopicTag(String id, List<BmsTag> tags);

    Set<String> selectTopicIdsByTagId(String id);

}
