package com.siwen.imperial.court.entity;

import lombok.*;

/**
 * @projectName: pro01-all-in-one-demo
 * @package: com.siwen.imperial.court.entity
 * @className: Memorials
 * @author: 749291
 * @description: TODO
 * @date: 4/9/2023 10:29 PM
 * @version: 1.0
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Memorials {
    private Integer memorialsId;
    private String memorialsTitle;
    private String memorialsContent;

    // 奏折摘要数据库没有，这里是为了配和页面显示
    private String memorialsContentDigest;
    private Integer memorialsEmp;

    // 员工姓名数据库没有，这里是为了配合页面显示
    private String memorialsEmpName;
    private String memorialsCreateTime;
    private String feedbackTime;
    private String feedbackContent;
    private Integer memorialsStatus;
}