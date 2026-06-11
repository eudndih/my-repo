package com.atguigu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class portalVo {
    private String keyWords;
    private Integer type;
    private Integer pageNum=1;
    private Integer pageSize=10;
}
