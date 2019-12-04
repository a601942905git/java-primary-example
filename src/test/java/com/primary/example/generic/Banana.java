package com.primary.example.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * com.primary.example.generic.Banana
 * 香蕉
 *
 * @author lipeng
 * @date 2019-07-18 14:13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Banana extends Fruit {

    private Integer id;

    private String name;
}
