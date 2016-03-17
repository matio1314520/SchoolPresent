package com.matio.frameworkmodel.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Angel on 2016/3/17.
 */
public class CategoryStrategy implements Serializable {

    private int code;

    private CategoryDataEntity data;

    private String message;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(CategoryDataEntity data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public CategoryDataEntity getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }


    public static class CategoryDataEntity   {

        private List<CategoryChannelGroups> channel_groups;

        public void setChannel_groups(List<CategoryChannelGroups> channel_groups) {
            this.channel_groups = channel_groups;
        }

        public List<CategoryChannelGroups> getChannel_groups() {
            return channel_groups;
        }

        public static class CategoryChannelGroups {

            private int id;

            private String name;

            private int order;

            private int status;

            private List<CategoryChannels> channels;

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

            public void setChannels(List<CategoryChannels> channels) {
                this.channels = channels;
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

            public List<CategoryChannels> getChannels() {
                return channels;
            }

            public static class CategoryChannels {

                private int group_id;

                private String icon_url;

                private int id;

                private int items_count;

                private String name;

                private int order;

                private int status;

                public void setGroup_id(int group_id) {
                    this.group_id = group_id;
                }

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

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getGroup_id() {
                    return group_id;
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

                public int getStatus() {
                    return status;
                }
            }
        }
    }

}
