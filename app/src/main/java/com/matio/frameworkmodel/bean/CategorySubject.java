package com.matio.frameworkmodel.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Angel on 2016/3/18.
 */
public class CategorySubject implements Serializable {
    private int code;

    private CategoryData data;

    private String message;

    public CategorySubject(int code, CategoryData data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public CategorySubject() {
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(CategoryData data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public CategoryData getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static class CategoryData  {

        private PagingEntity paging;

        private List<CategoryCollections> collections;

        public void setPaging(PagingEntity paging) {
            this.paging = paging;
        }

        public void setCollections(List<CategoryCollections> collections) {
            this.collections = collections;
        }

        public PagingEntity getPaging() {
            return paging;
        }

        public List<CategoryCollections> getCollections() {
            return collections;
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

        public static class CategoryCollections {
            private String banner_image_url;
            private String cover_image_url;
            private int created_at;
            private int id;
            private int posts_count;
            private int status;
            private String subtitle;
            private String title;
            private int updated_at;

            public String getBanner_image_url() {
                return banner_image_url;
            }

            public void setBanner_image_url(String banner_image_url) {
                this.banner_image_url = banner_image_url;
            }

            public String getCover_image_url() {
                return cover_image_url;
            }

            public void setCover_image_url(String cover_image_url) {
                this.cover_image_url = cover_image_url;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPosts_count() {
                return posts_count;
            }

            public void setPosts_count(int posts_count) {
                this.posts_count = posts_count;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }
        }

    }


}
