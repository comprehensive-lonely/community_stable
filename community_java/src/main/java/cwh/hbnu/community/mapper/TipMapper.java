package cwh.hbnu.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cwh.hbnu.community.model.pojo.BmsTip;
import org.springframework.stereotype.Repository;

@Repository
public interface TipMapper extends BaseMapper<BmsTip> {BmsTip getRandomTip();}
