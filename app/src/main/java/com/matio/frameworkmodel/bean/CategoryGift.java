package com.matio.frameworkmodel.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Angel on 2016/2/15.
 */
public class CategoryGift implements Serializable {

    private int code;

    private CategoryGiftData data;

    private String message;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(CategoryGiftData data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public CategoryGiftData getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static class CategoryGiftData {

        private List<CategoryGiftCategories> categories;

        public List<CategoryGiftCategories> getCategories() {
            return categories;
        }

        public void setCategories(List<CategoryGiftCategories> categories) {
            this.categories = categories;
        }

        public static class CategoryGiftCategories {

            private String icon_url;

            private int id;

            private String name;

            private int order;

            private int status;

            private List<CategoryGiftSubcategories> subcategories;

            public void setIcon_url(String icon_url) {
                this.icon_url = icon_url;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public void setSubcategories(List<CategoryGiftSubcategories> subcategories) {
                this.subcategories = subcategories;
            }

            public String getIcon_url() {
                return icon_url;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public int getOrder() {
                return order;
            }

            public int getStatus() {
                return status;
            }

            public List<CategoryGiftSubcategories> getSubcategories() {
                return subcategories;
            }

            public static class CategoryGiftSubcategories {
                private String icon_url;
                private int id;
                private int items_count;
                private String name;
                private int order;
                private int parent_id;
                private int status;


                public void setIcon_url(String icon_url) {
                    this.icon_url = icon_url;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public void setItems_count(int items_count) {
                    this.items_count = items_count;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setOrder(int order) {
                    this.order = order;
                }

                public void setParent_id(int parent_id) {
                    this.parent_id = parent_id;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getIcon_url() {
                    return icon_url;
                }

                public int getId() {
                    return id;
                }

                public int getItems_count() {
                    return items_count;
                }

                public String getName() {
                    return name;
                }

                public int getOrder() {
                    return order;
                }

                public int getParent_id() {
                    return parent_id;
                }

                public int getStatus() {
                    return status;
                }
            }


        }
    }

}
