package com.example.newsrecommendation.test;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @Description
 * @Author yawei sun
 * @Date 2019/10/11
 */
public class YidianMessageOther implements Serializable {
    private static final long serialVersionUID = -4963862442656587757L;


    /**
     * 消息类型 新增:      add，
     * 下线删除:   delete
     * 封面图更新：image_update
     */
    @JSONField(name = "message_type")
    private String messageType;

    /**
     * 文章id
     */
    @JSONField(name = "docid")
    private String docId;

    /**
     * 文章类型 （news:新闻，video：视频）
     */
    @JSONField(name = "ctype")
    private String cType;

    private String clazz;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    public YidianMessageOther(String messageType, String docId, String cType) {
        this.messageType = messageType;
        this.docId = docId;
        this.cType = cType;
    }

    @Override
    public String toString() {
        return "YidianMessageOther{" +
                "messageType='" + messageType + '\'' +
                ", docId='" + docId + '\'' +
                ", cType='" + cType + '\'' +
                ", clazz='" + clazz + '\'' +
                '}';
    }

    public YidianMessageOther() {
    }
}
