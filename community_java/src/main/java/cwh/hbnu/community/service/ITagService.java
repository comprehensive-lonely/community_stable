package cwh.hbnu.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cwh.hbnu.community.model.pojo.BmsPost;
import cwh.hbnu.community.model.pojo.BmsTag;

import java.util.List;


public interface ITagService extends IService<BmsTag> {
    List<BmsTag> insertTags(List<String> tags);

    Page<BmsPost> selectTopicsByTagId(Page<BmsPost> topicPage, String id);
}
