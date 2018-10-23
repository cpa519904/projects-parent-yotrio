package com.yotrio.common.domain;

import java.util.Map;

/**
 * Created by WYQ on 2015/12/22.
 */
public class DataTablePage {
    private int page;
    private int limit;
    private Map<String,Object> map;


    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
