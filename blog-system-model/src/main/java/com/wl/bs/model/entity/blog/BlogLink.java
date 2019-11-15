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
 * 友情链接表
 * </p>
 *
 * @author wanlin
 * @since 2019-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_blog_link")
public class BlogLink implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 友链表主键id
     */
    @TableId(value = "link_id", type = IdType.AUTO)
    private Integer linkId;

    /**
     * 友链类别 0-友链 1-推荐 2-个人网站
     */
    @TableField("link_type")
    private Integer linkType;

    /**
     * 网站名称
     */
    @TableField("link_name")
    private String linkName;

    /**
     * 网站链接
     */
    @TableField("link_url")
    private String linkUrl;

    /**
     * 网站描述
     */
    @TableField("link_description")
    private String linkDescription;

    /**
     * 用于列表排序
     */
    @TableField("link_rank")
    private Integer linkRank;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @TableField("is_deleted")
    private Integer isDeleted;

    /**
     * 添加时间
     */
    @TableField("create_time")
    private Date createTime;


}
