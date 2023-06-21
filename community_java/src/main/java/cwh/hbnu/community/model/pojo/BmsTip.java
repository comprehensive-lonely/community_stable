package cwh.hbnu.community.model.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@TableName("bms_tip")
public class BmsTip implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @TableField("`content`")
    private String content;
    private String author;
    private boolean type;

}
