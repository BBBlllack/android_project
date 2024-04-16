package edu.hebut.technote.api;

public class ApiConfig {
    public static final int PAGE_SIZE = 10;
    public static final String BASE_URl = "http://49.232.244.90:8082";
    public static final String CIYUN_URl = "http://49.232.244.90:80";
    public static final String AI_URl = "http://10.202.254.25:80";
    public static final String PRE_URL = "http://192.168.43.154:9091";
    public static final String PAPER_CATEGORY_LIST = "/spring/category/get";//各类型论文类型列表
    public static final String RECOM_PAPER_LIST = "/spring/paper/recom";//视频类型列表
    public static final String NEWS_LIST = "/spring/paper/get";//资讯列表
    public static final String CIYUN = "/flask/word/generate";//词云列表
    public static final String GPT = "/flask/openai/summarize";//GPT列表
    public static final String UPDATE_FAVOR = "/spring/paper/favor";//更新收藏
    public static final String PDF_PREVIEW = "/flask/word/preimg";//pdf预览

//    public static final String VIDEO_MYCOLLECT = "/app/videolist/mycollect";//我的收藏
//    public static final String LOGIN = "/user/login"; //登录
//    public static final String REGISTER = "/user/register";//注册
//    public static final String VIDEO_LIST_ALL = "/app/videolist/listAll";//所有类型视频列表
}
