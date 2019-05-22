package com.education.classroom.core.moneyaccount;

/**
 * Service传递Bean通用父类.
 * @Class Name BaseInfo
 * @author CHP_3.0 接口组
 * @Create In 2015年12月24日
 */
public class BaseOutInfo extends BaseInfo {
    
    /** 响应码. */
    protected String retCode;
    
    /** 响应消息. */
    protected String retMsg;
    
    /**
     * 响应码 的取得处理.
     *
     * @return 响应码
     */
    public String getRetCode() {
        return this.retCode;
    }

    /**
     * 响应码 的设定处理.
     *
     * @param retCode 响应码
     */
    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    /**
     * 响应消息 的取得处理.
     *
     * @return 响应消息
     */
    public String getRetMsg() {
        return this.retMsg;
    }

    /**
     * 响应消息 的设定处理.
     *
     * @param retMsg 响应消息
     */
    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
}
