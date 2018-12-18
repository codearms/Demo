package com.codearms.maoqiqi.skin.app;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.codearms.maoqiqi.skin.attr.SkinSupportAttr;
import com.codearms.maoqiqi.skin.attr.SkinSupportView;
import com.codearms.maoqiqi.skin.manager.SkinManager;
import com.codearms.maoqiqi.skin.manager.SkinSupportAttrsManager;

import java.util.ArrayList;
import java.util.List;

public class SkinAttrsParser {

    private static final String TAG = "SkinAttrsParser";

    /**
     * 解析当前视图的属性集合,判断属性是否支持换肤,如果属性不支持换肤,循环下一个属性.
     * 根据属性值得到资源Id和类型名,人后得到SkinSupportAttr,如果不为空添加到集合.
     * 循环结束以后,如果skinSupportAttrs长度大于0,创建SkinSupportView对象,添加到skinSupportViews集合中.
     * 如果当前不是默认皮肤,调用skinSupportView的applySkin方法.
     *
     * @param context the context
     * @param view    view
     * @param attrs   attribute set
     */
    public static SkinSupportView parseSkinAttr(Context context, View view, AttributeSet attrs) {
        SkinSupportView skinSupportView = null;
        List<SkinSupportAttr> skinSupportAttrs = new ArrayList<>();
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            String attrName = attrs.getAttributeName(i);
            String attrValue = attrs.getAttributeValue(i);

            Log.e(TAG, "attrName:" + attrName + ",attrValue:" + attrValue + ",isSupportedSkin:" + SkinSupportAttrsManager.isSupportedSkin(attrName));
            // 如果属性不支持换肤,循环下一个属性
            if (SkinSupportAttrsManager.isSupportedSkin(attrName)) continue;

            // 判断属性值是否是以为@开头,@开头的是引用资源
            if (attrValue.startsWith("@")) {
                // 得到资源Id,需要去掉前面的@符号
                int resId = Integer.parseInt(attrValue.substring(1));
                // 如果资源Id为0,循环下一个属性
                if (resId == 0) continue;
                // 得到资源名
                String resEntryName = context.getResources().getResourceEntryName(resId);
                // 得到类型名
                String resTypeName = context.getResources().getResourceTypeName(resId);
                Log.e(TAG, "attrName:" + attrName + ",resId:" + resId + ",resEntryName:" + resEntryName + ",resTypeName:" + resTypeName);
                // 得到SkinSupportAttr
                SkinSupportAttr skinSupportAttr = SkinSupportAttrsManager.getSkinSupportAttr(attrName, resId, resEntryName, resTypeName);
                // 如果skinSupportAttr不为空,添加到集合中
                if (skinSupportAttr != null) skinSupportAttrs.add(skinSupportAttr);
            }
        }

        // 如果skinSupportAttrs长度大于0,说明有需要换肤的属性以及显示换肤的实现类
        if (skinSupportAttrs.size() > 0) {
            skinSupportView = new SkinSupportView();
            skinSupportView.setView(view);
            skinSupportView.setAttrs(skinSupportAttrs);

            // 如果当前选中的皮肤不是默认皮肤,调用skinSupportView的applySkin方法
            if (!SkinManager.getInstance().isDefaultSkin()) {
                skinSupportView.applySkin();
            }
        }

        return skinSupportView;
    }
}
