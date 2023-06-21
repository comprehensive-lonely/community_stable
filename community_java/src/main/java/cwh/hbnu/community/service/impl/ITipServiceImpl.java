package cwh.hbnu.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cwh.hbnu.community.mapper.TipMapper;
import cwh.hbnu.community.model.pojo.BmsTip;
import cwh.hbnu.community.service.ITipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ITipServiceImpl extends ServiceImpl<TipMapper, BmsTip> implements ITipService {

    @Override
    public BmsTip getRandomTip() {
        BmsTip todayTip = null;
        try{
            todayTip = this.baseMapper.getRandomTip();
        }catch (Exception e){
            e.printStackTrace();
            log.info("tip转化失败！");
        }
        return todayTip;
    }
}
