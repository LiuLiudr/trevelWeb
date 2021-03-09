package com.zzxx.travel.util;

import java.io.Serializable;

// 封装响应信息的对象
public class ResponseInfo implements Serializable {
    private boolean flag;
    private Object data;//后端返回结果数据对象
    private String errorMsg; // 发生异常的错误消息

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public ResponseInfo() {

    }

    public ResponseInfo(boolean flag) {
        this.flag = flag;
    }
    public ResponseInfo(boolean flag, String errorMsg) {
        this.flag = flag;
        this.errorMsg = errorMsg;
    }
    public ResponseInfo(boolean flag, Object data, String errorMsg) {
        this.flag = flag;
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
