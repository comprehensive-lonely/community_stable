package cwh.hbnu.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cwh.hbnu.community.common.api.ApiResult;
import cwh.hbnu.community.model.pojo.BmsPost;
import cwh.hbnu.community.model.pojo.BmsTag;
import cwh.hbnu.community.service.ITagService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tag")
public class TagController extends BaseController {

    @Resource
    private ITagService tagService;

    @GetMapping("/{name}")
    public ApiResult<Map<String, Object>> getTopicsByTag(@PathVariable("name") String tagName,
                                                         @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                         @RequestParam(value = "size", defaultValue = "5") Integer size){
        Map<String, Object> map = new HashMap<>();
        LambdaQueryWrapper<BmsTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BmsTag::getName, tagName);
        BmsTag one = tagService.getOne(wrapper);
        Assert.notNull(one, "话题不存在，或已被删除！");

        // 热门标签
        Page<BmsPost> topics = tagService.selectTopicsByTagId(new Page<>(page, size), one.getId());
        Page<BmsTag> hotTags = tagService.page(new Page<>(1, 5),
                new LambdaQueryWrapper<BmsTag>()
                        .notIn(BmsTag::getName, tagName)
                        .orderByDesc(BmsTag::getTopicCount));

        map.put("topics", topics);
        map.put("hotTags", hotTags);
        return ApiResult.success(map);
    }

}
