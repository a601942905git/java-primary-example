package com.primary.example.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * apache.common.utils.sensitive.SensitiveUtils
 *
 * @author lipeng
 * @dateTime 2018/9/12 上午9:49
 */
public class SensitiveUtils {

    /**
     * 电话号码长度最少位数限制
     */
    private static final Integer PHONE_MIN_LENGTH = 4;

    /**
     * 判断手机号正则
     */
    private static Pattern pattern =
            Pattern.compile("^134[0-8]\\d{7}$|^13[^4]\\d{8}$|^14[5-9]\\d{8}$|^15[^4]\\d{8}$|^16[6]\\d{8}$|^17[0-8]\\d{8}$|^18[\\d]{9}$|^19[8,9]\\d{8}$");

    /**
     * 处理手机号码,隐藏中间的4位
     * @param mobile    手机号码
     * @return          返回处理后的手机号码,如:1326****856
     */
    public static String handlerMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return mobile;
        }
        mobile = mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        return mobile;
    }

    /**
     * 处理电话号码,隐藏后4位
     * @param phone 电话号码
     * @return      返回处理后的电话号码,如果0710-331****
     */
    public static String handlerPhone(String phone) {
        if (StringUtils.isEmpty(phone) || phone.length() < PHONE_MIN_LENGTH) {
            return phone;
        }
        phone = phone.replaceAll("(\\w+)(\\d{4})", "$1****");
        return phone;
    }

    /**
     * 处理银行账号
     * @param bankAccount   银行账号
     * @return
     */
    public static String handlerBankAccount(String bankAccount) {
        if (StringUtils.isEmpty(bankAccount) || bankAccount.length() < PHONE_MIN_LENGTH) {
            return bankAccount;
        }
        bankAccount = bankAccount.replaceAll("\\W+", "");
        bankAccount = bankAccount.replaceAll("(\\d+)(\\d{4})", "****$2");
        return bankAccount;
    }

    /**
     * 处理身份证号码
     * @param idCard
     * @return
     */
    public static String handlerIdCard(String idCard) {
        if (StringUtils.isEmpty(idCard)) {
            return idCard;
        }
        String regex = "(\\d{3})(\\d+)(\\w{3})";
        idCard = idCard.replaceAll(regex, "$1****$3");
        return idCard;
    }

    /**
     * 判断是否为手机号
     * @param mobile    手机号码
     * @return
     */
    public static boolean isMobile(String mobile) {
        if (pattern.matcher(mobile).matches()) {
            return true;
        }
        return false;
    }

    /**
     * 去除空格
     * @param str
     * @return
     */
    public static String trim(String str) {
        return Optional.ofNullable(str).map(String::trim).orElse("");
    }

    public static void main(String[] args) {
        System.out.println(SensitiveUtils.handlerBankAccount("62238 014750 610589 06"));
    }
}
