package com.matio.frameworkmodel.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Angel on 2016/3/17.
 */
public class GuideTab implements Serializable {


    /**
     * code : 200
     * data : {"candidates":[{"editable":true,"id":120,"name":"涨姿势"},{"editable":true,"id":118,"name":"美食"},{"editable":true,"id":121,"name":"数码"},{"editable":true,"id":125,"name":"创意生活"},{"editable":true,"id":111,"name":"礼物"},{"editable":true,"id":112,"name":"家居"},{"editable":true,"id":116,"name":"饰品"},{"editable":true,"id":10,"name":"送女票"},{"editable":true,"id":40,"name":"圣诞节日"},{"editable":true,"id":28,"name":"科技范"},{"editable":true,"id":123,"name":"爱运动"},{"editable":true,"id":32,"name":"情人节"},{"editable":true,"id":126,"name":"奇葩搞怪"},{"editable":true,"id":124,"name":"爱读书"},{"editable":true,"id":122,"name":"爱动漫"},{"editable":true,"id":6,"name":"送爸妈"},{"editable":true,"id":26,"name":"送基友"}],"channels":[{"editable":false,"id":108,"name":"精选"},{"editable":true,"id":120,"name":"涨姿势"},{"editable":true,"id":118,"name":"美食"},{"editable":true,"id":121,"name":"数码"},{"editable":true,"id":125,"name":"创意生活"},{"editable":true,"id":111,"name":"礼物"},{"editable":true,"id":112,"name":"家居"},{"editable":true,"id":116,"name":"饰品"},{"editable":true,"id":10,"name":"送女票"},{"editable":true,"id":40,"name":"圣诞节日"},{"editable":true,"id":28,"name":"科技范"},{"editable":true,"id":123,"name":"爱运动"},{"editable":true,"id":32,"name":"情人节"},{"editable":true,"id":126,"name":"奇葩搞怪"},{"editable":true,"id":124,"name":"爱读书"},{"editable":true,"id":122,"name":"爱动漫"},{"editable":true,"id":6,"name":"送爸妈"},{"editable":true,"id":26,"name":"送基友"}]}
     * message : OK
     */

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
        /**
         * editable : true
         * id : 120
         * name : 涨姿势
         */

        private List<CandidatesEntity> candidates;
        /**
         * editable : false
         * id : 108
         * name : 精选
         */

        private List<ChannelsEntity> channels;

        public void setCandidates(List<CandidatesEntity> candidates) {
            this.candidates = candidates;
        }

        public void setChannels(List<ChannelsEntity> channels) {
            this.channels = channels;
        }

        public List<CandidatesEntity> getCandidates() {
            return candidates;
        }

        public List<ChannelsEntity> getChannels() {
            return channels;
        }

        public static class CandidatesEntity {

            private boolean editable;

            private int id;

            private String name;

            public void setEditable(boolean editable) {
                this.editable = editable;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isEditable() {
                return editable;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }
        }

        public static class ChannelsEntity {

            private boolean editable;

            private int id;

            private String name;

            public void setEditable(boolean editable) {
                this.editable = editable;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isEditable() {
                return editable;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }
        }
    }
}
