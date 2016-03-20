package com.matio.frameworkmodel.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Angel on 2016/3/18.
 */
public class SearchHot implements Serializable {


    /**
     * code : 200
     * data : {"hot_words":["零食礼盒","手机壳","杯子","双肩包","情侣","钱包","手表","耳钉","手链","食物","戒指","耳机"],"placeholder":"快选一份礼物，送给亲爱的Ta吧"}
     * message : OK
     */

    private int code;
    /**
     * hot_words : ["零食礼盒","手机壳","杯子","双肩包","情侣","钱包","手表","耳钉","手链","食物","戒指","耳机"]
     * placeholder : 快选一份礼物，送给亲爱的Ta吧
     */

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

        private String placeholder;

        private List<String> hot_words;

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public void setHot_words(List<String> hot_words) {
            this.hot_words = hot_words;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public List<String> getHot_words() {
            return hot_words;
        }
    }
}
