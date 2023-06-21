package cwh.hbnu.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cwh.hbnu.community.model.pojo.BmsTip;

public interface ITipService extends IService<BmsTip> {

    BmsTip getRandomTip();

}
