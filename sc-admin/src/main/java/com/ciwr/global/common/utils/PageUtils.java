package com.ciwr.global.common.utils;

import com.baomidou.mybatisplus.plugins.Page;

import java.io.Serializable;
import java.util.List;

public class PageUtils implements Serializable {

    private static final long serialVersionUID = 1L;
    //当前页数
    private int page;

    //每页显示数量
    private int pagesize;

    //总条数
    private long records;

    //数据列表
    private List<?> rows;

    //总页数
    private long total;
    /**
     * 分页
     * @param rows        列表数据
     * @param records  总记录数
     * @param pageSize    每页记录数
     * @param currPage    当前页数
     */
    public PageUtils(List<?> rows, long records, int pageSize, int currPage) {
        this.rows = rows;
        this.records = records;
        this.pagesize = pageSize;
        this.page = currPage;
        this.total = (long) Math.ceil((double)records/pageSize);
    }

    /**
     * 分页
     */
    public PageUtils(Page<?> page) {
        this.rows = page.getRecords();
        this.records = page.getTotal();
        this.pagesize = page.getSize();
        this.page = page.getCurrent();
        this.total = page.getPages();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
