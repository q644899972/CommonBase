package com.wb.commonbase.base;

/**
 * 网络请求返回的数据，按格式统一包装成 BaseResponse 类
 * Created by Administrator on 2018/9/15.
 */

public class BaseResponse<T> {

    private int errorCode = -1;
    private String errorMsg;
    private T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }



    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }





    @Override
    public String toString() {
        return "BaseResponse{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + result +
                '}';
    }
}
