package com.esop.airport.common;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;

/**
 * Created by liliqiang on 2017/9/5.
 */
public class SqlInjectionCheck {

    /**
     * 检查是否存在sql 注入 传入 model 即可
     * @param objects 要检查的对象
     * @return 是否存在sql注入
     */
    public static boolean Check(Object... objects) {

        for (Object c : objects) {
            try {
                if (c instanceof String) {
                    if (sqlValidate(c + "")) {
                        return true;
                    }
                } else if (c.getClass().isPrimitive()) {
                    return false;
                } else if (c instanceof Integer || c instanceof Float || c instanceof Double ||
                        c instanceof Long || c instanceof Byte || c instanceof Short ||
                        c instanceof Character || c instanceof Boolean || c instanceof Void) {
                    return false;
                } else {
                    Field[] fs = c.getClass().getDeclaredFields();
                    for (Field f : fs) {
                        f.setAccessible(true); //设置些属性是可以访问的
                        Object val = f.get(c);//得到此属性的值
                        System.out.println("name:" + f.getName() + "\t value = " + val);
                        // 验证是否存在sql 注入
                        if (sqlValidate(val + "")) {
                            return true;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return true;
            }
        }
        return false;
    }

    //效验
    private static boolean sqlValidate(String str) {
        str = str.toLowerCase();//统一转为小写
        //todo 这里 验证 去掉了  ' |-  时间分页 会出现sql 检查不通过现象'
        String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" +
                "char|declare|sitename|net user|xp_cmdshell|;|or|+|like'|and|exec|execute|insert|create|drop|" +
                "table|from|grant|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" +
                "chr|mid|master|truncate|char|declare|or|;|--|+|like|//|/|%|#";     //过滤掉的sql关键字，可以手动添加
        String[] badStrs = badStr.split("\\|");
        for (String badStr1 : badStrs) {
            if (str.contains(badStr1)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("coursePackId", 127);
        jsonObject.put("userIds", "502367,502480,575646,575688");
        SqlInjectionCheck.Check(jsonObject.toJSONString());
    }


}
