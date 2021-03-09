package com.zzxx.travel.domain;

import java.util.List;

public class PageBean<T> {
    //主题数据List
    private List<T> list;
    //总记录数
    private int totalcount;
    //总页数
    private int totalpage;
    //当前页数
    private int currentpage;
    //每页条数
    private int pagesize;

    public PageBean(List<T> list, int totalcount, int totalpage, int currentpage, int pagesize) {
        this.list = list;
        this.totalcount = totalcount;
        this.totalpage = totalpage;
        this.currentpage = currentpage;
        this.pagesize = pagesize;
    }

    public PageBean() {

    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(int totalcount) {
        this.totalcount = totalcount;
    }

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public int getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(int currentpage) {
        this.currentpage = currentpage;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "list=" + list +
                ", totalcount=" + totalcount +
                ", totalpage=" + totalpage +
                ", currentpage=" + currentpage +
                ", pagesize=" + pagesize +
                '}';
    }
}
