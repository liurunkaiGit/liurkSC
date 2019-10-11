package com.log.log4j2.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private Long id;
    private String userName;
    private String name;
    private String pwd;
    private Integer age;
    private BigDecimal balance;
}
