package com.lyarc.tp.corp.common.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * common
 * 
 * @version 1.0
 * @since JDK1.8
 * @author lidoudou
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年9月6日 下午3:48:04
 */
public class CommonUtil {

    /**
     * 生成32位的UUID字符串
     *
     * @return
     * 
     * @author lidoudou
     * @date 2017年9月6日 下午3:47:44
     */
    public static String createUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

 /**
  *
  * @description  格式化日期
  * @param
  * @return
  * @author wangting
  * @date 2018/1/25 0025 10:27
  *
  */
    public static String getDateformat(Date date, String mask) {
        String dateString = "";
        if (date != null) {
            if (mask != null) {
                SimpleDateFormat df = new SimpleDateFormat(mask);
                dateString = df.format(date);
            }
        }
        return dateString;
    }

    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将单位为元转换为单位为分
     *
     * @param yuan 将要转换的元的数值字符串
     */
    public static Integer yuanToFee(String yuan) {
        return new BigDecimal(yuan).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue();
    }

    public static String getOrderNo(){
        //订单号规则 日期（毫秒级）+ 4位随机字符串
        String orderNo = CommonUtil.getDateformat(new Date(), "yyyyMMddHHmmssSSS") + RandomStringUtils.random(4, false, true);
        return orderNo;
    }

    /**
     * 使用正则表达式提取中括号中的内容
     * @param msg
     * @return
     */
    public static List<String> extractMessageByRegular(String msg){
        List<String> list=new ArrayList<String>();
        String regex = "(?<=\\{\\{)((.+?))(?=\\}\\})";
        Pattern p = Pattern.compile (regex);
        Matcher m = p.matcher(msg);
        while(m.find()){
            list.add(m.group());
        }
        return list;
    }
}
