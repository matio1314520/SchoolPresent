package com.matio.frameworkmodel.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Angel on 2016/3/18.
 */
public class SearchGrid implements Serializable {


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

        private List<ItemsEntity> items;

        public void setPaging(PagingEntity paging) {
            this.paging = paging;
        }

        public void setItems(List<ItemsEntity> items) {
            this.items = items;
        }

        public PagingEntity getPaging() {
            return paging;
        }

        public List<ItemsEntity> getItems() {
            return items;
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

        public static class ItemsEntity {
            private String cover_image_url;
            private String cover_webp_url;
            private String description;
            private String favorites_count;
            private int id;
            private boolean liked;
            private int likes_count;
            private String name;
            private String price;
            private int purchase_type;
            private List<?> ad_monitors;

            public void setCover_image_url(String cover_image_url) {
                this.cover_image_url = cover_image_url;
            }

            public void setCover_webp_url(String cover_webp_url) {
                this.cover_webp_url = cover_webp_url;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public void setFavorites_count(String favorites_count) {
                this.favorites_count = favorites_count;
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

            public void setName(String name) {
                this.name = name;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setPurchase_type(int purchase_type) {
                this.purchase_type = purchase_type;
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

            public String getDescription() {
                return description;
            }

            public String getFavorites_count() {
                return favorites_count;
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

            public String getName() {
                return name;
            }

            public String getPrice() {
                return price;
            }

            public int getPurchase_type() {
                return purchase_type;
            }

            public List<?> getAd_monitors() {
                return ad_monitors;
            }
        }
    }
}