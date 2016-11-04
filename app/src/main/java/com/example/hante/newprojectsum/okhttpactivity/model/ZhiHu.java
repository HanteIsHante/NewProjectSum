package com.example.hante.newprojectsum.okhttpactivity.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * zhihu
 */

public class ZhiHu implements Serializable{

    @Override
    public String toString () {
        return "ZhiHu{" +
                "mDate='" + mDate + '\'' +
                ", mStories=" + mStories +
                ", mTop_stories=" + mTop_stories +
                '}';
    }

    /**
     * date : 20161104
     * stories : [{"title":"名字听起来都很霸气，但鹰、隼、雕、枭有什么区别？","ga_prefix":"110414","images":["http://pic1.zhimg.com/0ad8abab59338de013ffc7563f5280ec.jpg"],"multipic":true,"type":0,"id":8943387},{"title":"学会做川式火锅底料，随便放东西涮一涮，都好吃得没天理","ga_prefix":"110413","images":["http://pic2.zhimg.com/a28eaa33bc3af979b8dd965a8faf0af1.jpg"],"multipic":true,"type":0,"id":8943521},{"images":["http://pic3.zhimg.com/2f26505423168e94ea66a64cb4ee7532.jpg"],"type":0,"id":8946522,"ga_prefix":"110412","title":"大误 · 我选择主动失忆"},{"images":["http://pic4.zhimg.com/dcf1c6af1e306752a5b14600f6ba22a3.jpg"],"type":0,"id":8947948,"ga_prefix":"110411","title":"如果达 · 芬奇会看到毕加索的画，会说什么呢？"},{"images":["http://pic3.zhimg.com/669b8349c8d06119e31e46b78355b656.jpg"],"type":0,"id":8939310,"ga_prefix":"110410","title":"微信整顿公众号刷赞，也戳不破网红经济的泡沫"},{"images":["http://pic1.zhimg.com/8d5e1470a1c5147333c520c997f82bc4.jpg"],"type":0,"id":8945100,"ga_prefix":"110409","title":"美国总统能炒掉司法部长、提名法官，如何保证司法独立？"},{"images":["http://pic4.zhimg.com/96c42665a09102c2de0853cebd3a2b5b.jpg"],"type":0,"id":8946966,"ga_prefix":"110408","title":"雾霾天里算算这笔账，想要清洁的空气得花多少钱"},{"images":["http://pic2.zhimg.com/338227af1c031ec532e6acc2ff8c7975.jpg"],"type":0,"id":8947185,"ga_prefix":"110407","title":"心理学家觉得，一个人独居也是件不错的事儿"},{"images":["http://pic4.zhimg.com/f98f9be4eb2904ee6179a7282f0aee9f.jpg"],"type":0,"id":8945314,"ga_prefix":"110407","title":"念 MBA 到底有没有用？"},{"images":["http://pic3.zhimg.com/c7fcea0d72cf3292e8b5231429c67eb2.jpg"],"type":0,"id":8946692,"ga_prefix":"110407","title":"和父亲一起抗癌三年，这些经验，希望你永远也用不到"},{"images":["http://pic1.zhimg.com/07fbef7ddb2494216477ae527bec1760.jpg"],"type":0,"id":8947119,"ga_prefix":"110407","title":"读读日报 24 小时热门 TOP 5 · 人们只知她绯闻，她却有完美人生"},{"images":["http://pic4.zhimg.com/f6dc89ec897cb3f1d601034bacf17597.jpg"],"type":0,"id":8945554,"ga_prefix":"110406","title":"瞎扯 · 如何正确地吐槽"},{"images":["http://pic3.zhimg.com/2f673d9a2cd3e27894ee9b55e9aaa9e6.jpg"],"type":0,"id":8945682,"ga_prefix":"110406","title":"这里是广告 · 救救我的手抖症"}]
     * top_stories : [{"image":"http://pic1.zhimg.com/fb8f561d7d04c0c1b6571fd7cdf01084.jpg","type":0,"id":8943521,"ga_prefix":"110413","title":"学会做川式火锅底料，随便放东西涮一涮，都好吃得没天理"},{"image":"http://pic1.zhimg.com/5510cba6343d9779a29e04ff5fad1d0c.jpg","type":0,"id":8939310,"ga_prefix":"110410","title":"微信整顿公众号刷赞，也戳不破网红经济的泡沫"},{"image":"http://pic1.zhimg.com/6ddc7e85fa31b42bab5c5e42bd8a6d58.jpg","type":0,"id":8947119,"ga_prefix":"110407","title":"读读日报 24 小时热门 TOP 5 · 人们只知她绯闻，她却有完美人生"},{"image":"http://pic4.zhimg.com/061e951375160b19e93bd85963d43783.jpg","type":0,"id":8946692,"ga_prefix":"110407","title":"和父亲一起抗癌三年，这些经验，希望你永远也用不到"},{"image":"http://pic1.zhimg.com/9b0584a156f7046b2a4299c1bf275ce0.jpg","type":0,"id":8946221,"ga_prefix":"110320","title":"「长征五号」发射，中国运载火箭技术水平重回世界前列"}]
     */

    @SerializedName("date")
    private String mDate;
    /**
     * title : 名字听起来都很霸气，但鹰、隼、雕、枭有什么区别？
     * ga_prefix : 110414
     * images : ["http://pic1.zhimg.com/0ad8abab59338de013ffc7563f5280ec.jpg"]
     * multipic : true
     * type : 0
     * id : 8943387
     */

    @SerializedName("stories")
    private List<StoriesBean> mStories;
    /**
     * image : http://pic1.zhimg.com/fb8f561d7d04c0c1b6571fd7cdf01084.jpg
     * type : 0
     * id : 8943521
     * ga_prefix : 110413
     * title : 学会做川式火锅底料，随便放东西涮一涮，都好吃得没天理
     */

    @SerializedName("top_stories")
    private List<TopStoriesBean> mTop_stories;

    public String getMDate () {
        return mDate;
    }

    public void setMDate (String mDate) {
        this.mDate = mDate;
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
        @SerializedName("title")
        private String mTitle;
        @SerializedName("ga_prefix")
        private String mGa_prefix;
        @SerializedName("multipic")
        private boolean mMultipic;
        @SerializedName("type")
        private int mType;
        @SerializedName("id")
        private int mId;
        @SerializedName("images")
        private List<String> mImages;

        public String getMTitle () {
            return mTitle;
        }

        public void setMTitle (String mTitle) {
            this.mTitle = mTitle;
        }

        public String getMGa_prefix () {
            return mGa_prefix;
        }

        public void setMGa_prefix (String mGa_prefix) {
            this.mGa_prefix = mGa_prefix;
        }

        public boolean isMMultipic () {
            return mMultipic;
        }

        public void setMMultipic (boolean mMultipic) {
            this.mMultipic = mMultipic;
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
