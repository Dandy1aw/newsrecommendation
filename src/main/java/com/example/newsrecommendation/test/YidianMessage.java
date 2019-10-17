package com.example.newsrecommendation.test;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @Description
 * @Author yawei sun
 * @Date 2019/10/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YidianMessage implements Serializable {
    private static final long serialVersionUID = -4963862442656587757L;


    /**
     * 消息类型 新增:      add，
     * 下线删除:   delete
     * 封面图更新：image_update
     */
    @JSONField(name = "message_type")
    private String messageType;

    public enum Code{
        ERROR("错误"),
        SUCCESS("成功");
        private String desc;
        Code(String desc){
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }
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
}
