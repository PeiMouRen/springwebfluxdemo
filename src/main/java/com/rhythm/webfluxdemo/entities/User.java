package com.rhythm.webfluxdemo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author peixi
 * @Date 2022/1/20 17:31
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String sex;
    private Integer age;
}
