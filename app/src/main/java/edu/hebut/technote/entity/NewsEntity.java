package edu.hebut.technote.entity;

import java.io.Serializable;

public class NewsEntity implements Serializable {
    private int id;
    private int postId;
    private int pid;
    private String pname;
    private int fid;
    private String fname;
    private String titleZh;
    private String titleEn;
    private String author;
    private String link;
    private Object comment;
    private String detailEn;
    private String detailZh;
    private String createdAt;
    private int favor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getTitleZh() {
        return titleZh;
    }

    public void setTitleZh(String titleZh) {
        this.titleZh = titleZh;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Object getComment() {
        return comment;
    }

    public void setComment(Object comment) {
        this.comment = comment;
    }

    public String getDetailEn() {
        return detailEn;
    }

    public void setDetailEn(String detailEn) {
        this.detailEn = detailEn;
    }

    public String getDetailZh() {
        return detailZh;
    }

    public void setDetailZh(String detailZh) {
        this.detailZh = detailZh;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getFavor() {
        return favor;
    }

    public void setFavor(int favor) {
        this.favor = favor;
    }


//    private int id;
//    private int postId;
//    private int pid;
//    private String titleZh;
//    private String titleEn;
//    private String author;
//    private String link;
//    private Object comment;
//    private String detailEn;
//    private String detailZh;
//    private String createdAt;
//    private String pname;
//    private int fid;
//    private String fname;
//    @SerializedName("author")
//    private Object authorX;
//    @SerializedName("comment")
//    private Object commentX;
//    private int favor;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getPostId() {
//        return postId;
//    }
//
//    public void setPostId(int postId) {
//        this.postId = postId;
//    }
//
//    public int getPid() {
//        return pid;
//    }
//
//    public void setPid(int pid) {
//        this.pid = pid;
//    }
//
//    public String getTitleZh() {
//        return titleZh;
//    }
//
//    public void setTitleZh(String titleZh) {
//        this.titleZh = titleZh;
//    }
//
//    public String getTitleEn() {
//        return titleEn;
//    }
//
//    public void setTitleEn(String titleEn) {
//        this.titleEn = titleEn;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public String getLink() {
//        return link;
//    }
//
//    public void setLink(String link) {
//        this.link = link;
//    }
//
//    public Object getComment() {
//        return comment;
//    }
//
//    public void setComment(Object comment) {
//        this.comment = comment;
//    }
//
//    public String getDetailEn() {
//        return detailEn;
//    }
//
//    public void setDetailEn(String detailEn) {
//        this.detailEn = detailEn;
//    }
//
//    public String getDetailZh() {
//        return detailZh;
//    }
//
//    public void setDetailZh(String detailZh) {
//        this.detailZh = detailZh;
//    }
//
//    public String getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(String createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public String getPname() {
//        return pname;
//    }
//
//    public void setPname(String pname) {
//        this.pname = pname;
//    }
//
//    public int getFid() {
//        return fid;
//    }
//
//    public void setFid(int fid) {
//        this.fid = fid;
//    }
//
//    public String getFname() {
//        return fname;
//    }
//
//    public void setFname(String fname) {
//        this.fname = fname;
//    }
//
//    public Object getAuthorX() {
//        return authorX;
//    }
//
//    public void setAuthorX(Object authorX) {
//        this.authorX = authorX;
//    }
//
//    public Object getCommentX() {
//        return commentX;
//    }
//
//    public void setCommentX(Object commentX) {
//        this.commentX = commentX;
//    }
//
//    public int getFavor() {
//        return favor;
//    }
//
//    public void setFavor(int favor) {
//        this.favor = favor;
//    }
}
