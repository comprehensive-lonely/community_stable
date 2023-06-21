package cwh.hbnu.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cwh.hbnu.community.mapper.BillBoardMapper;
import cwh.hbnu.community.model.pojo.BmsBillboard;
import cwh.hbnu.community.service.IBillBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IBillBoardServiceImpl extends ServiceImpl<BillBoardMapper, BmsBillboard> implements IBillBoardService {

}
