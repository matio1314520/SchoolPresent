package com.matio.frameworkmodel.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Angel on 2016/3/18.
 */
public class SearchList implements Serializable {


    private int code;

    private DataEntity data;
    private String message;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static class DataEntity {

        private PagingEntity paging;

        private List<PostsEntity> posts;

        public void setPaging(PagingEntity paging) {
            this.paging = paging;
        }

        public void setPosts(List<PostsEntity> posts) {
            this.posts = posts;
        }

        public PagingEntity getPaging() {
            return paging;
        }

        public List<PostsEntity> getPosts() {
            return posts;
        }

        public static class PagingEntity {
            private String next_url;

            public void setNext_url(String next_url) {
                this.next_url = next_url;
            }

            public String getNext_url() {
                return next_url;
            }
        }

        public static class PostsEntity {
            private String cover_image_url;
            private String cover_webp_url;
            private int id;
            private boolean liked;
            private int likes_count;
            private String title;
            private List<?> ad_monitors;

            public void setCover_image_url(String cover_image_url) {
                this.cover_image_url = cover_image_url;
            }

            public void setCover_webp_url(String cover_webp_url) {
                this.cover_webp_url = cover_webp_url;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setLiked(boolean liked) {
                this.liked = liked;
            }

            public void setLikes_count(int likes_count) {
                this.likes_count = likes_count;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setAd_monitors(List<?> ad_monitors) {
                this.ad_monitors = ad_monitors;
            }

            public String getCover_image_url() {
                return cover_image_url;
            }

            public String getCover_webp_url() {
                return cover_webp_url;
            }

            public int getId() {
                return id;
            }

            public boolean isLiked() {
                return liked;
            }

            public int getLikes_count() {
                return likes_count;
            }

            public String getTitle() {
                return title;
            }

            public List<?> getAd_monitors() {
                return ad_monitors;
            }
        }
    }

}
