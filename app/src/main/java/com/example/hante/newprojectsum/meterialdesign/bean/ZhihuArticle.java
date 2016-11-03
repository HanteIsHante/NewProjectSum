package com.example.hante.newprojectsum.meterialdesign.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * ZhiHu
 * http://news-at.zhihu.com/api/4/news/latest
 */

public class ZhihuArticle implements Serializable{


    /**
     * date : 20161102
     * stories : [{"images":["http://pic4.zhimg.com/2ab089d4420e36344cd3ad11c99313a3.jpg"],"type":0,"id":8942417,"ga_prefix":"110219","title":"都是「地铁」，Subway 和 Metro 有什么区别？"},{"images":["http://pic2.zhimg.com/3f45c9805fd7430ad47652fece611241.jpg"],"type":0,"id":8942680,"ga_prefix":"110218","title":"「快速解酒」，其实不靠谱"},{"images":["http://pic1.zhimg.com/cb79048026bd7251ae72f1a42bf98d60.jpg"],"type":0,"id":8942765,"ga_prefix":"110217","title":"知乎好问题 · 怎样做正宗的水煮鱼？有哪些技巧？"},{"images":["http://pic4.zhimg.com/2158bebffb91ceaca5b947b4fc26a743.jpg"],"type":0,"id":8942577,"ga_prefix":"110216","title":"二手车砍价指南"},{"images":["http://pic1.zhimg.com/108820db90b760fcaac26b7a9b383c10.jpg"],"type":0,"id":8940934,"ga_prefix":"110215","title":"我们有没有自我思维？"},{"images":["http://pic1.zhimg.com/94b7bacf68d7c1318c9947b1e95729c8.jpg"],"type":0,"id":8942345,"ga_prefix":"110214","title":"网约车新政第一天，滴滴、黑车和出租车司机们在做什么"},{"images":["http://pic2.zhimg.com/89c2852e13f9fa58538c709c8d1b10b5.jpg"],"type":0,"id":8942106,"ga_prefix":"110213","title":"你以为演员没化妆的时候，其实都化了妆"},{"images":["http://pic3.zhimg.com/a4500110473f131eb3c74bcdc73e7d9a.jpg"],"type":0,"id":8940544,"ga_prefix":"110212","title":"大误 · 我是一个捉鬼师"},{"images":["http://pic4.zhimg.com/697833ef43918ca75cea513468633ee7.jpg"],"type":0,"id":8940351,"ga_prefix":"110211","title":"谁再说地幔是液体，说地下全是岩浆，我真跟你急"},{"images":["http://pic3.zhimg.com/bcbc6d8d93ea55241d07ccc3ad1b5616.jpg"],"type":0,"id":8938916,"ga_prefix":"110210","title":"梨花女大的姑娘们是如何引爆韩国政局的？"},{"images":["http://pic2.zhimg.com/3cc13c61ff2038e6c3540d99f3dca651.jpg"],"type":0,"id":8939765,"ga_prefix":"110209","title":"「室友嫌我听歌影响她睡觉，我还嫌她睡觉影响我听歌呢」"},{"images":["http://pic2.zhimg.com/9cb67d95850530a196a6ea3a05398cd1.jpg"],"type":0,"id":8941105,"ga_prefix":"110208","title":"心理学科普真是难，为什么大家这么喜欢伪心理学？"},{"images":["http://pic4.zhimg.com/6e5580aa1714936fd3d7a46663a5e5eb.jpg"],"type":0,"id":8941118,"ga_prefix":"110207","title":"同样都是 50 / 50 的不确定性，风险却可能不一样"},{"images":["http://pic4.zhimg.com/eb7c16fd999f120ac5edb918aaea081b.jpg"],"type":0,"id":8939666,"ga_prefix":"110207","title":"遇到拆房子，先搞清楚是拆迁还是征收、拆违、腾退、征地"},{"images":["http://pic1.zhimg.com/837aaa73e740306d2ea3d51dcd8fcca4.jpg"],"type":0,"id":8939661,"ga_prefix":"110207","title":"新能源汽车要用电，但是可能促进不了新能源发电的发展"},{"images":["http://pic3.zhimg.com/d74afc26e4189b99ae7949363051f216.jpg"],"type":0,"id":8940991,"ga_prefix":"110207","title":"读读日报 24 小时热门 TOP 5 · 网约车新政第一天"},{"images":["http://pic1.zhimg.com/22e9302b820d3b493e94652e327cc1dc.jpg"],"type":0,"id":8939495,"ga_prefix":"110206","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic1.zhimg.com/6307c391adb4ef6e7ba8c46aff706aa0.jpg","type":0,"id":8942765,"ga_prefix":"110217","title":"知乎好问题 · 怎样做正宗的水煮鱼？有哪些技巧？"},{"image":"http://pic1.zhimg.com/f7850200e835876ea3357f43270bdc38.jpg","type":0,"id":8942345,"ga_prefix":"110214","title":"网约车新政第一天，滴滴、黑车和出租车司机们在做什么"},{"image":"http://pic2.zhimg.com/4c6e9ef59f17a618a49bd3430e7dcb55.jpg","type":0,"id":8942106,"ga_prefix":"110213","title":"你以为演员没化妆的时候，其实都化了妆"},{"image":"http://pic3.zhimg.com/f8aac36775889b01b67c010ec45655b6.jpg","type":0,"id":8940991,"ga_prefix":"110207","title":"读读日报 24 小时热门 TOP 5 · 网约车新政第一天"},{"image":"http://pic3.zhimg.com/248dcbe4a103c7d34d207be9e461e6d2.jpg","type":0,"id":8938916,"ga_prefix":"110210","title":"梨花女大的姑娘们是如何引爆韩国政局的？"}]
     */

    private String date;
    /**
     * images : ["http://pic4.zhimg.com/2ab089d4420e36344cd3ad11c99313a3.jpg"]
     * type : 0
     * id : 8942417
     * ga_prefix : 110219
     * title : 都是「地铁」，Subway 和 Metro 有什么区别？
     */

    @SerializedName("stories")
    private List<StoriesBean> mStories;
    /**
     * image : http://pic1.zhimg.com/6307c391adb4ef6e7ba8c46aff706aa0.jpg
     * type : 0
     * id : 8942765
     * ga_prefix : 110217
     * title : 知乎好问题 · 怎样做正宗的水煮鱼？有哪些技巧？
     */

    @SerializedName("top_stories")
    private List<TopStoriesBean> mTop_stories;

    public String getDate () {
        return date;
    }

    public void setDate (String date) {
        this.date = date;
    }

    public List<StoriesBean> getMStories () {
        return mStories;
    }

    public void setMStories (List<StoriesBean> mStories) {
        this.mStories = mStories;
    }

    public List<TopStoriesBean> getMTop_stories () {
        return mTop_stories;
    }

    public void setMTop_stories (List<TopStoriesBean> mTop_stories) {
        this.mTop_stories = mTop_stories;
    }

    public static class StoriesBean {
        @SerializedName("type")
        private int mType;
        @SerializedName("id")
        private int mId;
        @SerializedName("ga_prefix")
        private String mGa_prefix;
        @SerializedName("title")
        private String mTitle;
        @SerializedName("images")
        private List<String> mImages;

        public int getMType () {
            return mType;
        }

        public void setMType (int mType) {
            this.mType = mType;
        }

        public int getMId () {
            return mId;
        }

        public void setMId (int mId) {
            this.mId = mId;
        }

        public String getMGa_prefix () {
            return mGa_prefix;
        }

        public void setMGa_prefix (String mGa_prefix) {
            this.mGa_prefix = mGa_prefix;
        }

        public String getMTitle () {
            return mTitle;
        }

        public void setMTitle (String mTitle) {
            this.mTitle = mTitle;
        }

        public List<String> getMImages () {
            return mImages;
        }

        public void setMImages (List<String> mImages) {
            this.mImages = mImages;
        }
    }

    public static class TopStoriesBean {
        @SerializedName("image")
        private String mImage;
        @SerializedName("type")
        private int mType;
        @SerializedName("id")
        private int mId;
        @SerializedName("ga_prefix")
        private String mGa_prefix;
        @SerializedName("title")
        private String mTitle;

        public String getMImage () {
            return mImage;
        }

        public void setMImage (String mImage) {
            this.mImage = mImage;
        }

        public int getMType () {
            return mType;
        }

        public void setMType (int mType) {
            this.mType = mType;
        }

        public int getMId () {
            return mId;
        }

        public void setMId (int mId) {
            this.mId = mId;
        }

        public String getMGa_prefix () {
            return mGa_prefix;
        }

        public void setMGa_prefix (String mGa_prefix) {
            this.mGa_prefix = mGa_prefix;
        }

        public String getMTitle () {
            return mTitle;
        }

        public void setMTitle (String mTitle) {
            this.mTitle = mTitle;
        }
    }
}
