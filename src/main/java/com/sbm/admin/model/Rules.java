package com.sbm.admin.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

@Data
@TableName(value = "rules")
public class Rules implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "key")
    private String key;

    @TableField(value = "condition")
    private String condition;

    @TableField(value = "value")
    private String value;

    @TableField(value = "group_id")
    private Integer groupId;

    private static final long serialVersionUID = 1L;
}