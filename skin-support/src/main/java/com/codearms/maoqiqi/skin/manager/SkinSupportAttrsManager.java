package com.codearms.maoqiqi.skin.manager;

import com.codearms.maoqiqi.skin.attr.SkinSupportAttr;
import com.codearms.maoqiqi.skin.attr.SkinSupportAttrImpl;

import java.util.HashMap;
import java.util.Map;

public class SkinSupportAttrsManager {

    // 内置支持更改皮肤的属性
    public static final String BACKGROUND = "background";
    public static final String TEXT_COLOR = "textColor";
    public static final String TEXT_COLOR_HINT = "textColorHint";
    public static final String DRAWABLE_LEFT = "drawableLeft";
    public static final String DRAWABLE_TOP = "drawableTop";
    public static final String DRAWABLE_RIGHT = "drawableRight";
    public static final String DRAWABLE_BOTTOM = "drawableBottom";
    public static final String TEXT_APPEARANCE = "textAppearance";
    public static final String TEXT_CURSOR_DRAWABLE = "textCursorDrawable";
    public static final String BUTTON = "button";
    public static final String CHECK_MARK = "checkMark";
    public static final String SRC = "src";
    public static final String SRC_COMPAT = "srcCompat";
    public static final String TINT = "tint";
    public static final String LIST_SELECTOR = "listSelector";
    public static final String DIVIDER = "divider";
    public static final String PROGRESS_DRAWABLE = "progressDrawable";
    public static final String THUMB = "thumb";
    public static final String TRACK = "track";
    public static final String SWITCH_TEXT_APPEARANCE = "switchTextAppearance";
    public static final String WEEK_DAY_TEXT_APPEARANCE = "weekDayTextAppearance";
    // api 17
    public static final String DRAWABLE_START = "drawableStart";
    public static final String DRAWABLE_END = "drawableEnd";
    // api 21
    public static final String BACKGROUND_TINT = "backgroundTint";
    public static final String FOREGROUND_TINT = "foregroundTint";
    public static final String DRAWABLE_TINT = "drawableTint";
    public static final String BUTTON_TINT = "buttonTint";
    public static final String CHECK_MARK_TINT = "checkMarkTint";
    // SeekBar Switch
    public static final String THUMB_TINT = "thumbTint";
    // SwitchBar
    public static final String TRACK_TINT = "trackTint";
    // progress
    public static final String PROGRESS_TINT = "progressTint";
    public static final String SECONDARY_PROGRESS_TINT = "secondaryProgressTint";
    public static final String PROGRESS_BACKGROUND_TINT = "progressBackgroundTint";
    public static final String INDETERMINATE_TINT = "indeterminateTint";
    // SeekBar
    public static final String TICK_MARK_TINT = "tickMarkTint";

    // android.support.design.widget.TextInputLayout
    public static final String HINT_TEXT_APPEARANCE = "hintTextAppearance";
    // android.support.design.widget.NavigationView
    public static final String ITEM_ICON_TINT = "itemIconTint";
    public static final String ITEM_TEXT_COLOR = "itemTextColor";

    /**
     * 存储所有内置支持更改皮肤的属性
     */
    private static Map<String, Class<? extends SkinSupportAttr>> skinSupportAttrMap;

    // 初始化
    static {
        skinSupportAttrMap = new HashMap<>();
        skinSupportAttrMap.put(BACKGROUND, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(TEXT_COLOR, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(TEXT_COLOR_HINT, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(DRAWABLE_LEFT, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(DRAWABLE_TOP, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(DRAWABLE_RIGHT, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(DRAWABLE_BOTTOM, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(TEXT_APPEARANCE, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(TEXT_CURSOR_DRAWABLE, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(BUTTON, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(CHECK_MARK, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(SRC, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(SRC_COMPAT, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(TINT, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(LIST_SELECTOR, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(DIVIDER, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(PROGRESS_DRAWABLE, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(THUMB, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(TRACK, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(SWITCH_TEXT_APPEARANCE, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(WEEK_DAY_TEXT_APPEARANCE, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(DRAWABLE_START, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(DRAWABLE_END, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(BACKGROUND_TINT, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(FOREGROUND_TINT, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(DRAWABLE_TINT, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(BUTTON_TINT, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(CHECK_MARK_TINT, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(THUMB_TINT, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(TRACK_TINT, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(PROGRESS_TINT, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(SECONDARY_PROGRESS_TINT, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(PROGRESS_BACKGROUND_TINT, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(INDETERMINATE_TINT, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(TICK_MARK_TINT, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(HINT_TEXT_APPEARANCE, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(ITEM_ICON_TINT, SkinSupportAttrImpl.class);
        skinSupportAttrMap.put(ITEM_TEXT_COLOR, SkinSupportAttrImpl.class);
    }

    /**
     * 检查属性是否支持更改皮肤
     *
     * @param attrName attribute name
     * @return true or false
     */
    public static boolean isSupportedSkin(String attrName) {
        return !skinSupportAttrMap.containsKey(attrName);
    }

    /**
     * 根据属性名找到对应的类(实现了SkinSupportAttr{@link SkinSupportAttr}的实现类)
     *
     * @param attrName    attribute name
     * @param resId       resource id
     * @param resTypeName resource type name
     * @return {@link SkinSupportAttr}
     */
    public static SkinSupportAttr getSkinSupportAttr(String attrName, int resId, String entryName, String resTypeName) {
        Class<? extends SkinSupportAttr> aClass = skinSupportAttrMap.get(attrName);
        if (aClass == null) return null;

        try {
            SkinSupportAttr skinSupportAttr = aClass.newInstance();
            // 如果不为空,赋值
            skinSupportAttr.setAttrName(attrName);
            skinSupportAttr.setResId(resId);
            skinSupportAttr.setEntryName(entryName);
            skinSupportAttr.setResTypeName(resTypeName);
            return skinSupportAttr;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 添加多个支持更改皮肤的属性
     *
     * @param attrNames 属性名数组
     * @param classes   属性名对应实现类的class数组
     */
    static void addSkinSupportAttrs(String[] attrNames, Class<? extends SkinSupportAttr>[] classes) {
        if (attrNames == null || attrNames.length == 0 ||
                classes == null || attrNames.length != classes.length) {
            return;
        }

        for (int i = 0; i < attrNames.length; i++) {
            if (attrNames[i] != null && classes[i] != null) {
                skinSupportAttrMap.put(attrNames[i], classes[i]);
            }
        }
    }

    /**
     * 删除多个支持更改皮肤的属性
     *
     * @param attrNames 属性名数组
     */
    static void removeSkinSupportAttrs(String[] attrNames) {
        if (attrNames == null || attrNames.length == 0) return;

        for (String attrName : attrNames) {
            if (attrName != null) {
                skinSupportAttrMap.remove(attrName);
            }
        }
    }
}