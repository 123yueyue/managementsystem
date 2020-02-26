package com.aynu.data.common.bean;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Auther: zhangyue
 * @Date: 2020/2/24
 * @Description:
 */
public class ResponseEntity extends GenericBean {
    private static final long serialVersionUID = -8299562540124103135L;
    protected Integer status = 0;
    protected String msg = "操作成功.";

    public ResponseEntity() {
        this.put("status", this.status);
        this.put("msg", this.msg);
    }

    public ResponseEntity(Integer status, String msg) {
        this.put("status", status);
        this.put("msg", msg);
    }

    public Integer getStatus() {
        return (Integer)this.get("status");
    }

    public void setStatus(Integer status) {
        this.put("status", status);
    }

    public String getMsg() {
        return (String)this.get("msg");
    }

    public void setMsg(String msg) {
        this.put("msg", msg);
    }

    public Object getResponseObject() {
        return this.get("responseObject");
    }

    public void setResponseObject(Object responseObject) {
        this.put("responseObject", responseObject);
    }

    public List<?> getResponseList() {
        return (List)this.get("responseList");
    }

    public void setResponseList(List<?> responseList) {
        this.put("responseList", responseList);
    }

    public GenericBean getResponsePageData() {
        return (GenericBean)this.get("responsePageData");
    }

    public void setResponsePageData(GenericBean responsePageData) {
        this.put("responsePageData", responsePageData);
    }

    public PageInfo<?> getResponsePageInfo() {
        return (PageInfo)this.get("responsePageInfo");
    }

    public void setResponsePageInfo(PageInfo<?> responsePageInfo) {
        this.put("responsePageInfo", responsePageInfo);
    }


}
