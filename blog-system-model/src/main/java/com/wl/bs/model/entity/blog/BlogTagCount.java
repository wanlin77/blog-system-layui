package com.wl.bs.model.entity.blog;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @description: 标签数量
 * @author: wanlin
 * @version: 1.0
 * @createtime: 2019/10/20 21:23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogTagCount {
    // 标签id
    private Integer tagId;

    // 标签名字
    private String tagName;

    // 标签使用数量
    private long tagCount;

}
