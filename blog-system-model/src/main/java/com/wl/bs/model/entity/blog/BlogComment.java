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
 * 评论信息表
 * </p>
 *
 * @author wanlin
 * @since 2019-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_blog_comment")
public class BlogComment implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Long commentId;

    /**
     * 关联的blog主键
     */
    @TableField("blog_id")
    private Long blogId;

    /**
     * 评论者名称
     */
    @TableField("commentator")
    private String commentator;

    /**
     * 评论人的邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 网址
     */
    @TableField("website_url")
    private String websiteUrl;

    /**
     * 评论内容
     */
    @TableField("comment_body")
    private String commentBody;

    /**
     * 评论提交时间
     */
    @TableField("comment_create_time")
    private Date commentCreateTime;

    /**
     * 评论时的ip地址
     */
    @TableField("commentator_ip")
    private String commentatorIp;

    /**
     * 回复内容
     */
    @TableField("reply_body")
    private String replyBody;

    /**
     * 回复时间
     */
    @TableField("reply_create_time")
    private Date replyCreateTime;

    /**
     * 是否审核通过 0-未审核 1-审核通过
     */
    @TableField("comment_status")
    private Integer commentStatus;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @TableField("is_deleted")
    private Integer isDeleted;


}
