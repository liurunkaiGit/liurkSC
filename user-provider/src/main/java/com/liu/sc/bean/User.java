package com.liu.sc.bean;

import com.liu.sc.utils.contants.ValidContant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private Long id;
    @NotEmpty(message = ValidContant.NAME_NOT_NULL)
    private String userName;
    private String name;
    private String pwd;
    private Integer age;
    private BigDecimal balance;
}
