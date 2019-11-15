package com.wl.bs.model.entity.blog;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * 博客跟标签的关系表
 * </p>
 *
 * @author wanlin
 * @since 2019-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_blog_tag_relation")
public class BlogTagRelation implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 关系表id
     */
    @TableId(value = "relation_id", type = IdType.AUTO)
    private Long relationId;

    /**
     * 博客id
     */
    @TableField("blog_id")
    private Long blogId;

    /**
     * 标签id
     */
    @TableField("tag_id")
    private Integer tagId;

    /**
     * 添加时间
     */
    @TableField("create_time")
    private Date createTime;


}
