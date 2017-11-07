package com.zyc.exception;
/**
 * 
 * @company 武汉福禄网络科技 有限公司 http://www.fulu.com 
 * @Title: UserNotFoundException.java 
 * @Package com.fulu.exception 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author Vincent
 * @email zhangyuancheng@fulu.com
 * @date 2015年10月8日 下午3:56:19 
 * @version V1.0
 */
public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	public UserNotFoundException(){
		super();
	}
	public UserNotFoundException(String message) {
		super(message);
	}
}
