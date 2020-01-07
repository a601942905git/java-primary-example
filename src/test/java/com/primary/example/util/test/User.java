package com.primary.example.util.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * com.primary.example.util.test.User
 *
 * @author lipeng
 * @date 2020/1/7 下午2:27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;

    private Integer age;

    private String name;
}
