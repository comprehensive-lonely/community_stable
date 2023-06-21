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
@TableName("bms_post")
@AllArgsConstructor
@NoArgsConstructor
public class BmsPost implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @NotBlank(message = "标题不可以为空")
    @TableField(value = "title")
    private String title;

    @NotBlank(message = "内容不可以为空")
    @TableField("`content`")
    private String content;

    @TableField("user_id")
    private String userId;

    @TableField("comments")
    @Builder.Default
    private Integer comments = 0;

    @TableField("collects")
    @Builder.Default
    private Integer collects = 0;

    @TableField("view")
    @Builder.Default
    private Integer view = 0;

    @TableField("section_id")
    @Builder.Default
    private Integer sectionId = 0;
    @TableField("top")
    @Builder.Default
    private Boolean top = false;

    @TableField("essence")
    @Builder.Default
    private Boolean essence = false;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "modify_time", fill = FieldFill.UPDATE)
    private Date modifyTime;
}
