package cn.wdb.community.pojo;

import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "comment")
@NameStyle
public class Comment implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long parentId;
    private Integer type;
    private Long gmtCreated;
    private Long gmtModified;
    private Integer commentator;
    private String comment;
    private Integer likeCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Long gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getCommentator() {
        return commentator;
    }

    public void setCommentator(Integer commentator) {
        this.commentator = commentator;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", type=" + type +
                ", gmtCreated=" + gmtCreated +
                ", gmtModified=" + gmtModified +
                ", commentator=" + commentator +
                ", comment='" + comment + '\'' +
                ", likeCount=" + likeCount +
                '}';
    }
}
