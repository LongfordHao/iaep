package com.tfjybj.iaep.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Yara
 * @Date 2021/1/21
 * @Time 13:21
 * @Version 1.0
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataModel {
    private String id;
    private String title;
    private String dtcTableName;
}
