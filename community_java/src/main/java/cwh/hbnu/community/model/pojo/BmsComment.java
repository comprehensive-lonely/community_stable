package cwh.hbnu.community.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;


@Data
@Builder
@TableName("bms_comment")
@AllArgsConstructor
@NoArgsConstructor
public class BmsComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @NotBlank(message = "内容不可以为空")
    @TableField(value = "content")
    private String content;

    @TableField("user_id")
    private String userId;

    @TableField("topic_id")
    private String topicId;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "modify_time", fill = FieldFill.UPDATE)
    private Date modifyTime;
}
