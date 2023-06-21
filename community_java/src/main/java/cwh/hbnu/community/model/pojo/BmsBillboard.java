package cwh.hbnu.community.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("bms_billboard")
public class BmsBillboard implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("content")
    private String content;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField("`show`")
    private boolean show = false;

}
