package cwh.hbnu.community.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vdurmont.emoji.EmojiParser;
import cwh.hbnu.community.mapper.TagMapper;
import cwh.hbnu.community.mapper.TopicMapper;
import cwh.hbnu.community.mapper.UserMapper;
import cwh.hbnu.community.model.dto.CreateTopicDTO;
import cwh.hbnu.community.model.pojo.BmsPost;
import cwh.hbnu.community.model.pojo.BmsTag;
import cwh.hbnu.community.model.pojo.BmsTopicTag;
import cwh.hbnu.community.model.pojo.UmsUser;
import cwh.hbnu.community.model.vo.PostVO;
import cwh.hbnu.community.model.vo.ProfileVO;
import cwh.hbnu.community.service.IPostService;
import cwh.hbnu.community.service.ITagService;
import cwh.hbnu.community.service.ITopicTagService;
import cwh.hbnu.community.service.IUserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IPostServiceImpl extends ServiceImpl<TopicMapper, BmsPost> implements IPostService {
    @Resource
    private TagMapper tagMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private ITopicTagService iTopicTagService;
    @Resource
    private IUserService iUserService;

    @Resource
    @Lazy
    private ITagService iTagService;

    @Override
    public Page<PostVO> getList(Page<PostVO> page, String tab) {
        // 查询话题
        Page<PostVO> iPage = this.baseMapper.selectListAndPage(page, tab);
        // 查询该话题的标签
        iPage.getRecords().forEach(topic -> {
            List<BmsTopicTag> topicTags = iTopicTagService.selectByTopicId(topic.getId());
            if(!topicTags.isEmpty()){
                List<String> tagIds = topicTags.stream().map(BmsTopicTag::getTagId).collect(Collectors.toList());
                List<BmsTag> tags = tagMapper.selectBatchIds(tagIds);
                topic.setTags(tags);
            }
        });
        return iPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BmsPost create(CreateTopicDTO dto, UmsUser user) {
        BmsPost topic = this.baseMapper.selectOne(new LambdaQueryWrapper<BmsPost>().eq(BmsPost::getTitle, dto.getTitle()));
        Assert.isNull(topic,"话题已经存在！");

        // 封装话题
        BmsPost post = BmsPost.builder()
                .userId(user.getId())
                .title(dto.getTitle())
                .content(EmojiParser.parseToAliases(dto.getContent()))
                .createTime(new Date())
                .build();
        this.baseMapper.insert(post);

        // 增加积分
        int newScore = user.getScore() + 1;
        userMapper.updateById(user.setScore(newScore));

        if (!ObjectUtils.isEmpty(dto.getTags())) {
            // 保存标签
            List<BmsTag> tags = iTagService.insertTags(dto.getTags());
            // 处理标签与话题的关联
            iTopicTagService.createTopicTag(post.getId(), tags);
        }
        return post;
    }

    @Override
    public Map<String, Object> viewTopic(String id) {
        Map<String, Object> map = new HashMap<>(16);
        BmsPost topic = this.baseMapper.selectById(id);
        org.springframework.util.Assert.notNull(topic, "当前话题不存在,或已被作者删除");
        // 查询话题详情
        topic.setView(topic.getView() + 1);
        this.baseMapper.updateById(topic);
        // emoji转码
        topic.setContent(EmojiParser.parseToUnicode(topic.getContent()));
        map.put("topic", topic);
        // 标签
        QueryWrapper<BmsTopicTag> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(BmsTopicTag::getTopicId, topic.getId());
        Set<String> set1 = new HashSet<>();

        for (BmsTopicTag articleTag : iTopicTagService.list(wrapper)) {
            set1.add(articleTag.getTagId());
        }

        List<BmsTopicTag> list = iTopicTagService.list(wrapper);
        for (BmsTopicTag bmsTopicTag : list) {
            System.out.println("bmsTopicTag = " + bmsTopicTag);
        }
        if(!set1.isEmpty()){
            List<BmsTag> tags = iTagService.listByIds(set1);
            map.put("tags", tags);
        }

        // 作者
        ProfileVO user = iUserService.getUserProfile(topic.getUserId());
        map.put("user", user);

        return map;
    }



    @Override
    public List<BmsPost> getRecommend(String id) {
        return this.baseMapper.selectRecommend(id);
    }

    @Override
    public Page<PostVO> searchByKey(String keyword, Page<PostVO> page) {
        // 查询话题
        Page<PostVO> iPage = this.baseMapper.searchByKey(page, keyword);
        // 查询话题的标签
        setTopicTags(iPage);
        return iPage;
    }
 
    private void setTopicTags(Page<PostVO> iPage) {
        iPage.getRecords().forEach(topic -> {
            List<BmsTopicTag> topicTags = iTopicTagService.selectByTopicId(topic.getId());
            if (!topicTags.isEmpty()) {
                List<String> tagIds = topicTags.stream().map(BmsTopicTag::getTagId).collect(Collectors.toList());
                List<BmsTag> tags = tagMapper.selectBatchIds(tagIds);
                topic.setTags(tags);
            }
        });
    }
}
