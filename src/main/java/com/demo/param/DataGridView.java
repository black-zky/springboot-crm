package com.demo.param;

import lombok.Data;

@Data
public class DataGridView {
    private Integer code=0;
    private String msg="";
    private Long count;
    private Object data;

    public DataGridView(Long count, Object data) {
        this.count = count;
        this.data = data;
    }
}
