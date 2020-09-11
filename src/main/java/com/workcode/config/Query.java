package com.workcode.config;
import java.util.LinkedHashMap;
import java.util.Map;
//该Query类继承了LinkedHashMap    等同于Query对象与Map<String, Object> params属于同类
public class Query extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    // 偏移页数
    private int offset;
    // 每页条数
    private int limit;

    public Query(Map<String, Object> params) {
//把另一个Map集合对象中的所有内容添加到当前Map集合对象，此处是多此一举，在本篇别无他用
        this.putAll(params);
        // 分页参数
        this.offset = Integer.parseInt(params.get("offset").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
//开始索引
        this.put("offset", offset);
//页码
        this.put("page", offset / limit + 1);
//每页记录数
        this.put("limit", limit);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.put("offset", offset);
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
