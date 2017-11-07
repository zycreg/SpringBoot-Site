package com.zyc.bean;

import java.io.Serializable;

/**
 * @author Vincent
 * @version V1.0
 * @company 武汉福禄网络科技 有限公司 http://www.fulu.com
 * @Title: Response.java
 * @Package com.fulu.pojo
 * @email zhangyuancheng@fulu.com
 * @date 2017年2月9日 下午2:53:30
 */
public class ResponseData implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String msg;
    private Object data;

    public ResponseData(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
