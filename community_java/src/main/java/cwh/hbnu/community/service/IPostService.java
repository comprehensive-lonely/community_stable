package cwh.hbnu.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cwh.hbnu.community.model.dto.CreateTopicDTO;
import cwh.hbnu.community.model.pojo.BmsPost;
import cwh.hbnu.community.model.pojo.UmsUser;
import cwh.hbnu.community.model.vo.PostVO;

import java.util.List;
import java.util.Map;

public interface IPostService extends IService<BmsPost> {

    Page<PostVO> getList(Page<PostVO> page, String tab);

    BmsPost create(CreateTopicDTO dto, UmsUser principal);


    Map<String, Object> viewTopic(String id);

    List<BmsPost> getRecommend(String id);

    Page<PostVO> searchByKey(String keyword, Page<PostVO> page);

}
