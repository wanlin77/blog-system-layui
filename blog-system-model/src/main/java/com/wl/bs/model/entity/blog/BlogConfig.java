package com.wl.bs.model.entity.blog;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wanlin
 * @since 2019-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_blog_config")
public class BlogConfig implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 字段名
     */
    @TableId("config_field")
    private String configField;

    /**
     * 配置名
     */
    @TableField("config_name")
    private String configName;

    /**
     * 配置项的值
     */
    @TableField("config_value")
    private String configValue;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;


}
