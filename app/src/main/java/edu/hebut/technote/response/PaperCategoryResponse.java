package edu.hebut.technote.response;


import java.util.List;

import edu.hebut.technote.entity.PaperCategoryEntity;

public class PaperCategoryResponse {

    private int code;
    private List<PaperCategoryEntity> data;
    private String msg;
    private String others;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<PaperCategoryEntity> getData() {
        return data;
    }

    public void setData(List<PaperCategoryEntity> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

//    public static class DataDTO {
//        private int categoryId;
//        private int canCreateThread;
//        private String description;
//        private String icon;
//        private String name;
//        private int parentid;
//        private int pid;
//        private int property;
//        private int sort;
//        private int threadCount;
//        @SerializedName("...")
//        private String _$276;// FIXME check this code
//
//        public int getCategoryId() {
//            return categoryId;
//        }
//
//        public void setCategoryId(int categoryId) {
//            this.categoryId = categoryId;
//        }
//
//        public int getCanCreateThread() {
//            return canCreateThread;
//        }
//
//        public void setCanCreateThread(int canCreateThread) {
//            this.canCreateThread = canCreateThread;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//        public void setDescription(String description) {
//            this.description = description;
//        }
//
//        public String getIcon() {
//            return icon;
//        }
//
//        public void setIcon(String icon) {
//            this.icon = icon;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public int getParentid() {
//            return parentid;
//        }
//
//        public void setParentid(int parentid) {
//            this.parentid = parentid;
//        }
//
//        public int getPid() {
//            return pid;
//        }
//
//        public void setPid(int pid) {
//            this.pid = pid;
//        }
//
//        public int getProperty() {
//            return property;
//        }
//
//        public void setProperty(int property) {
//            this.property = property;
//        }
//
//        public int getSort() {
//            return sort;
//        }
//
//        public void setSort(int sort) {
//            this.sort = sort;
//        }
//
//        public int getThreadCount() {
//            return threadCount;
//        }
//
//        public void setThreadCount(int threadCount) {
//            this.threadCount = threadCount;
//        }
//
//        public String get_$276() {
//            return _$276;
//        }
//
//        public void set_$276(String _$276) {
//            this._$276 = _$276;
//        }
//    }
}
