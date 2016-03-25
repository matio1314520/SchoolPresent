package com.matio.frameworkmodel.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Angel on 2016/3/25.
 */
public class CommentGrid implements Serializable {

    private int code;

    private HotDataEntity data;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public HotDataEntity getData() {
        return data;
    }

    public void setData(HotDataEntity data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class HotDataEntity {

        private List<HotGrid.HotDataEntity.HotItems.HotData> recommend_items;

        public List<HotGrid.HotDataEntity.HotItems.HotData> getRecommend_items() {
            return recommend_items;
        }

        public void setRecommend_items(List<HotGrid.HotDataEntity.HotItems.HotData> recommend_items) {
            this.recommend_items = recommend_items;
        }
    }
}
