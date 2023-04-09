package com.siwen.imperial.court.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class Emp {
    private Integer empId;
    private String empName;
    private String empPosition;
    private String loginAccount;
    private String loginPassword;
}