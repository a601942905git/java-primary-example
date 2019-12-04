package com.primary.example.string;

/**
 * com.primary.example.string.StringConstantTest
 *
 * @author lipeng
 * @date 2019-01-31 13:31
 */
public class StringConstantDemo {

    public static void main(String[] args) {
        User user = new User();
        user.setId(10001);
        user.setName("test");

        test(user);

        /**
         * 可以发现调用结果仍然没有改变
         * 首先user指向堆中的一块地址
         * test方法参数user1一开始和user指向的是同一块地址
         * 当执行user1 = new User()的时候，user1重新指向新创建的一块地址
         * 此时user1的任何改变都会不影响user对象了
         */
        System.out.println(user.getId() + "===" + user.getName());
    }

    private static void test(User user1) {
        user1 = new User();
        user1.setName("smile");
    }

    static class User {
        private Integer id;

        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
