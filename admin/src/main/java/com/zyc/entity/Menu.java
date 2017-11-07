package com.zyc.entity;

import java.util.Date;
import java.util.List;

/**
 * @author zhangyuancheng
 * @create 2017-11-05 下午4:12
 * @descripition
 */
public class Menu {
    private Integer id;
    private String name;
    private Integer pid;
    private String url;
    private String comment;
    private String code;
    private Integer isFunction;
    private String alias;
    private String iconFlag;
    private String creator;
    private Date createTime;
    private String updator;
    private Date updateTime;
    private Integer orderNo;

    //父对象
    private Menu menu;

    //子对象集合
    private List<Menu> subMenus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getIsFunction() {
        return isFunction;
    }

    public void setIsFunction(Integer isFunction) {
        this.isFunction = isFunction;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getIconFlag() {
        return iconFlag;
    }

    public void setIconFlag(String iconFlag) {
        this.iconFlag = iconFlag;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Menu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<Menu> subMenus) {
        this.subMenus = subMenus;
    }
}
