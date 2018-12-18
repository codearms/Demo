package com.codearms.maoqiqi.skin;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ColorsCache {

    private static final String TAG = "ColorsCache";

    private static Map<String, Integer> colorsCacheMap = new HashMap<>();

    public static void init(Context context) {
        if (context == null) {
            return;
        }
        try {
            String packageName = context.getPackageName();
            // 反射R文件的color类
            String className = packageName + ".R$color";
            Log.e(TAG, "name:" + className);
            Class clazz = Class.forName(className);
            Field[] fields = clazz.getDeclaredFields();
            Log.e(TAG, "fields length:" + fields.length);
            String name;
            int resId;
            Resources resources = context.getResources();
            for (Field field : fields) {
                name = field.getName();
                resId = resources.getIdentifier(name, "color", packageName);
                Log.e(TAG, "name:" + name + "--resId:" + resId + "--color:" + resources.getColor(resId));
                colorsCacheMap.put(name, resources.getColor(resId));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "err:" + e.getMessage());
        }

        Iterator<String> iterator = colorsCacheMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Integer value = colorsCacheMap.get(key);
            if (value == 0x2196F3) {
                Log.e(TAG, "color_primary--");
            }
        }

        int a = 0x7f05002f;
        Log.e(TAG, "stringBuffer:" + changeColorIdToHexString(context, a));
    }

    public static void a(int color) {
        Iterator<String> iterator = colorsCacheMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Integer value = colorsCacheMap.get(key);
            if (value == 0x7f05002f) {
                Log.e(TAG, "color_primary--");
            }
        }
    }

    /**
     * 根据color id获取颜色16进制值
     *
     * @param context the content
     * @param colorId color id
     * @return 颜色16进制值
     */
    public static String changeColorIdToHexString(Context context, int colorId) {
        int color = ContextCompat.getColor(context, colorId);
        return "#" + Integer.toHexString(Color.alpha(color)) +
                Integer.toHexString(Color.red(color)) +
                Integer.toHexString(Color.green(color)) +
                Integer.toHexString(Color.blue(color));
    }
}