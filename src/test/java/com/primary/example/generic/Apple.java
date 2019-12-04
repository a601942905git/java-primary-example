package com.primary.example.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * com.primary.example.generic.Apple
 * 苹果
 *
 * @author lipeng
 * @date 2019-07-18 14:12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Apple extends Fruit {

    private Integer id;

    private String name;

    public static Apple get(Fruit apple) {
        return new Apple();
    }
}
