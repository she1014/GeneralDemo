package com.techfly.demo.util;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ssm on 2015/12/9.
 */
public class RegexUtil {

    /*
     * 获取字符串中数字部分
     */
    public static String matcherReg(CharSequence c){
        String reasult = "";
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(c.toString());
        reasult = m.replaceAll("").trim();
//        Log.i("TTSS", "积分中数字部分:" + reasult + ",Length=" + reasult.length());
        return reasult;
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNumber(String mobiles) {
		/*
		 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
        String telRegex = "[1][3578]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }

    /*
     * 四舍五入
     */
    public static String numberFormat(String pattern, Number number) {
        String result = "";
        DecimalFormat format = new DecimalFormat(pattern);
        result = format.format(number);
        result = result.replace("-","");//解决出现-0.00的情况
        return result;
    }




}
