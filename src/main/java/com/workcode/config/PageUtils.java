package com.workcode.config;
import java.io.Serializable;
import java.util.List;

public class PageUtils implements Serializable {
    private static final long serialVersionUID = 1L;
    //总数据条数
    private int total;
    //分页查询的数据集合
    private List<?> rows;

    public PageUtils(List<?> list, int total) {
        this.rows = list;
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
