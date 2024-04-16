package edu.hebut.technote.response;

import java.io.Serializable;
import java.util.List;

import edu.hebut.technote.entity.NewsEntity;

public class NewsListResponse implements Serializable {

    private int code;
    private DataDTO data;
    private String msg;
    private Object others;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getOthers() {
        return others;
    }

    public void setOthers(Object others) {
        this.others = others;
    }

    public static class DataDTO {
        private List<NewsEntity> records;
        private int total;
        private int size;
        private int current;
        private List<?> orders;
        private boolean optimizeCountSql;
        private boolean hitCount;
        private Object countId;
        private Object maxLimit;
        private boolean searchCount;
        private int pages;

        public List<NewsEntity> getRecords() {
            return records;
        }

        public void setRecords(List<NewsEntity> records) {
            this.records = records;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public List<?> getOrders() {
            return orders;
        }

        public void setOrders(List<?> orders) {
            this.orders = orders;
        }

        public boolean isOptimizeCountSql() {
            return optimizeCountSql;
        }

        public void setOptimizeCountSql(boolean optimizeCountSql) {
            this.optimizeCountSql = optimizeCountSql;
        }

        public boolean isHitCount() {
            return hitCount;
        }

        public void setHitCount(boolean hitCount) {
            this.hitCount = hitCount;
        }

        public Object getCountId() {
            return countId;
        }

        public void setCountId(Object countId) {
            this.countId = countId;
        }

        public Object getMaxLimit() {
            return maxLimit;
        }

        public void setMaxLimit(Object maxLimit) {
            this.maxLimit = maxLimit;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

//        public static class RecordsDTO {
//            private int id;
//            private int postId;
//            private int pid;
//            private String titleZh;
//            private String titleEn;
//            private Object author;
//            private String link;
//            private Object comment;
//            private String detailEn;
//            private String detailZh;
//            private String createdAt;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public int getPostId() {
//                return postId;
//            }
//
//            public void setPostId(int postId) {
//                this.postId = postId;
//            }
//
//            public int getPid() {
//                return pid;
//            }
//
//            public void setPid(int pid) {
//                this.pid = pid;
//            }
//
//            public String getTitleZh() {
//                return titleZh;
//            }
//
//            public void setTitleZh(String titleZh) {
//                this.titleZh = titleZh;
//            }
//
//            public String getTitleEn() {
//                return titleEn;
//            }
//
//            public void setTitleEn(String titleEn) {
//                this.titleEn = titleEn;
//            }
//
//            public Object getAuthor() {
//                return author;
//            }
//
//            public void setAuthor(Object author) {
//                this.author = author;
//            }
//
//            public String getLink() {
//                return link;
//            }
//
//            public void setLink(String link) {
//                this.link = link;
//            }
//
//            public Object getComment() {
//                return comment;
//            }
//
//            public void setComment(Object comment) {
//                this.comment = comment;
//            }
//
//            public String getDetailEn() {
//                return detailEn;
//            }
//
//            public void setDetailEn(String detailEn) {
//                this.detailEn = detailEn;
//            }
//
//            public String getDetailZh() {
//                return detailZh;
//            }
//
//            public void setDetailZh(String detailZh) {
//                this.detailZh = detailZh;
//            }
//
//            public String getCreatedAt() {
//                return createdAt;
//            }
//
//            public void setCreatedAt(String createdAt) {
//                this.createdAt = createdAt;
//            }
//        }
    }
}
