package edu.hebut.technote.response;

import java.util.List;

import edu.hebut.technote.entity.NewsEntity;

public class RecommendNewsResponse {
    private int code;
    private List<NewsEntity> data;
    private String msg;
    private Object others;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<NewsEntity> getData() {
        return data;
    }

    public void setData(List<NewsEntity> data) {
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

}
