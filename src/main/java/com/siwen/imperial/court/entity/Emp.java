package com.siwen.imperial.court.entity;

import lombok.*;

/**
 * @projectName: pro01-all-in-one-demo
 * @package: com.siwen.imperial.court.entity
 * @className: Emp
 * @author: 749291
 * @description: TODO
 * @date: 4/9/2023 10:18 PM
 * @version: 1.0
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Emp {
    private Integer empId;
    private String empName;
    private String empPosition;
    private String loginAccount;
    private String loginPassword;
}