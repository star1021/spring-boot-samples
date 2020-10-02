package com.gupaoedu.springbootbeanvalidation.validation;

import com.gupaoedu.springbootbeanvalidation.validation.constraints.ValidCardNumber;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * {@link ValidCardNumber} {@link ConstraintValidator} 实现
 *
 * 第35分钟
 *
 * @Author: LIH
 * @Date: 2020/9/12 23:02
 */
public class ValidCardNumberConstraintValidator
        implements ConstraintValidator<ValidCardNumber, String> {
    @Override
    public void initialize(ValidCardNumber constraintAnnotation) {

    }

    /**
     * 合法 卡号校验
     * 需求：通过员工的卡号来校验，需要通过工号的前缀和后缀来判断
     * <p>
     * 前缀必须以“GUPAO”
     * <p>
     * 后缀必须是数字
     * <p>
     * 需要通过 Bean Validator 校验
     *
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        //前半部分和后半部分
        String[] parts = StringUtils.split(value, "-");

        //为什么不用String#split方法，原因在于该方法使用了正则表达式
        //其次是NPE保护不够
        //如果在依赖中，没有 StringUtils.delimitedListToStringArray API的话呢，
        //可以使用 JDK里面StringTokenizer（不足类似于枚举Enumeration API）
        //Apache commons-lang StringUtils

        /*if (parts.length != 2) {
            return false;
        }*/
        if (ArrayUtils.getLength(parts) != 2) {
            return false;
        }

        String prefix = parts[0];
        String suffix = parts[1];

        //Objects.equals(prefix, "GUPAO");
        boolean isValidPrefix = prefix.equals("GUPAO");
        boolean isValidInteger = StringUtils.isNumeric(suffix);
        return isValidPrefix && isValidInteger;
    }
}
