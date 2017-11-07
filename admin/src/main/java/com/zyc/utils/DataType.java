package com.zyc.utils;

public enum DataType {

	/**
	 * 数字
	 */
	NUMBER,
	/**
	 * 文本
	 */
	TEXT,
	/**
	 * 文本�?
	 */
	TEXTAREA,
	/**
	 * 日期时间
	 */
	DATE,
	/**
	 * 下拉�?
	 */
	SELECT,
	/**
	 * 单�?�?
	 */
	RADIO,
	/**
	 * 多�?�?
	 */
	CHECKBOX,
	/**
	 * 文件上传
	 */
	FILEUPLOAD,
	/**
	 * 图片上传
	 */
	IMAGEUPLOAD,
	/**
	 * 外键
	 */
	FOREIGNKEY,
	/**
	 * 编辑�?
	 */
	EDITOR;
	public DataType getType(String type) {
		return DataType.valueOf(type);
	}
}
