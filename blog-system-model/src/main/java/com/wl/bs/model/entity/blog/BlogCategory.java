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
 * 博客分类
 * </p>
 *
 * @author wanlin
 * @since 2019-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_blog_category")
public class BlogCategory implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 分类表主键
     */
    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

    /**
     * 分类的名称
     */
    @TableField("category_name")
    private String categoryName;

    /**
     * 分类的图标
     */
    @TableField("category_icon")
    private String categoryIcon;

    /**
     * 分类的排序值 被使用的越多数值越大
     */
    @TableField("category_rank")
    private Integer categoryRank;

    /**
     * 是否删除 0=否 1=是
     */
    @TableField("is_deleted")
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


}
