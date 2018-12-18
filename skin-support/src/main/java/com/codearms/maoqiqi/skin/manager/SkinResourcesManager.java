package com.codearms.maoqiqi.skin.manager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * 资源管理类,根据当前选择的皮肤,加载对应的资源
 */
public class SkinResourcesManager {

    /**
     * 日志标签
     */
    private static final String TAG = "SkinResourcesManager";

    /**
     * 无效的资源
     */
    private static final int INVALID_RESOURCES = 0;

    /**
     * 上下文
     */
    private WeakReference<Context> contextWR;

    /**
     * 当前资源对象
     */
    private Resources resources;

    /**
     * 当前实例
     */
    private static SkinResourcesManager instance;

    /**
     * 内置皮肤后缀名
     */
    private String skinSuffixName;

    /**
     * 当前使用皮肤包名
     */
    private String skinPackageName;

    /**
     * 当前使用皮肤资源对象
     */
    private Resources skinResources;

    /**
     * 是否是默认皮肤
     */
    private boolean isDefaultSkin;

    /**
     * color缓存
     */
    private HashMap<String, Integer> intCaches;

    /**
     * Drawable缓存
     */
    private HashMap<String, Drawable.ConstantState> drawableCaches;

    /**
     * ColorStateList缓存
     */
    private HashMap<String, ColorStateList> colorStateListCaches;

    private SkinResourcesManager(Context context) {
        this.contextWR = new WeakReference<>(context);
        this.resources = context.getResources();
    }

    /**
     * 初始化
     *
     * @param context the context
     */
    public static void init(Context context) {
        if (instance == null) {
            synchronized (SkinResourcesManager.class) {
                if (instance == null) {
                    instance = new SkinResourcesManager(context);
                }
            }
        }
    }

    /**
     * 得到当前对象,defined from {@link SkinManager#SkinManager }
     *
     * @return {@link SkinResourcesManager}
     */
    public static SkinResourcesManager getInstance() {
        if (instance == null)
            throw new NullPointerException("SkinResourcesManager must first call the init(Context context)");

        return instance;
    }

    /**
     * 设置内置皮肤相关信息
     *
     * @param skinSuffixName 皮肤后缀名
     */
    public void setBuiltInSkinInfo(String skinSuffixName) {
        this.skinSuffixName = skinSuffixName;
        this.skinPackageName = contextWR.get().getPackageName();
        this.skinResources = contextWR.get().getResources();
        this.isDefaultSkin = SkinPreferencesManager.getInstance().isDefaultSkin();
        clearCaches();
    }

    /**
     * 设置外部皮肤包名和资源
     *
     * @param skinSuffixName 皮肤后缀名
     * @param packageName    包名
     * @param resources      资源
     */
    public void setSkinInfo(String skinSuffixName, String packageName, Resources resources) {
        this.skinSuffixName = skinSuffixName;
        this.skinPackageName = packageName;
        this.skinResources = resources;
        this.isDefaultSkin = SkinPreferencesManager.getInstance().isDefaultSkin();
        clearCaches();
    }

    /**
     * 清除缓存信息
     */
    private void clearCaches() {
        Log.e(TAG, "-------------------------------------------------------------------------");
        if (intCaches != null) {
            for (String key : intCaches.keySet()) {
                Integer value = intCaches.get(key);
                Log.e(TAG, "key:" + key + ",value:" + value);
            }
            intCaches.clear();
        }
        Log.e(TAG, "-------------------------------------------------------------------------");
        if (drawableCaches != null) {
            for (String key : drawableCaches.keySet()) {
                Drawable.ConstantState drawable = drawableCaches.get(key);
                Log.e(TAG, "key:" + key + ",value:" + drawable.getClass().getName());
            }
            drawableCaches.clear();
        }
        Log.e(TAG, "-------------------------------------------------------------------------");
        if (colorStateListCaches != null) {
            for (String key : colorStateListCaches.keySet()) {
                ColorStateList colorStateList = colorStateListCaches.get(key);
                Log.e(TAG, "key:" + key + ",value:" + colorStateList.getClass().getName());
            }
            colorStateListCaches.clear();
        }
        Log.e(TAG, "-------------------------------------------------------------------------");
    }

    /**
     * 追加皮肤后缀名
     *
     * @param entryName resource entry name
     * @return resource entry name
     */
    private String appendSkinSuffixName(String entryName) {
        if (skinSuffixName == null) {
            return entryName;
        }
        return entryName + skinSuffixName;
    }

    private String getKeyByResId(int resId) {
        return "resId:" + resId;
    }

    private String getKeyByAttr(int attr) {
        return "attr:" + attr;
    }

    private Integer getCacheInt(String key) {
        if (intCaches == null) return INVALID_RESOURCES;
        Integer integer = intCaches.get(key);
        return integer == null ? 0 : integer;
    }

    private void addCacheInt(String key, int color) {
        if (intCaches == null) intCaches = new HashMap<>();
        intCaches.put(key, color);
    }

    private Drawable getCacheDrawable(String key) {
        if (drawableCaches == null) return null;
        Drawable.ConstantState constantState = drawableCaches.get(key);
        return constantState == null ? null : constantState.newDrawable();
    }

    private void addCacheDrawable(String key, Drawable drawable) {
        if (drawableCaches == null) drawableCaches = new HashMap<>();
        drawableCaches.put(key, drawable.getConstantState());
    }

    private ColorStateList getCacheColorStateList(String key) {
        if (colorStateListCaches == null) return null;
        return colorStateListCaches.get(key);
    }

    private void addCacheColorStateList(String key, ColorStateList colorStateList) {
        if (colorStateListCaches == null) colorStateListCaches = new HashMap<>();
        colorStateListCaches.put(key, colorStateList);
    }

    /**
     * 得到资源id的type name
     *
     * @param resId resources id
     * @return type name
     */
    public String getTypeName(int resId) {
        return resources.getResourceTypeName(resId);
    }

    /**
     * 根据resId加载对应的resource
     *
     * @param resId resource id
     * @return resource
     */
    public int getTargetResId(int resId) {
        if (resId <= INVALID_RESOURCES) return INVALID_RESOURCES;
        if (isDefaultSkin) return resId;
        try {
            String entryName = resources.getResourceEntryName(resId);
            String typeName = resources.getResourceTypeName(resId);
            return skinResources.getIdentifier(appendSkinSuffixName(entryName), typeName, skinPackageName);
        } catch (Exception e) {
            // 换肤失败不至于应用崩溃
            return INVALID_RESOURCES;
        }
    }

    /**
     * 根据resId加载对应的颜色
     *
     * @param resId resource id
     * @return color
     */
    public int getColor(int resId) {
        if (resId <= INVALID_RESOURCES) return INVALID_RESOURCES;
        int color = getCacheInt(getKeyByResId(resId));
        if (color == 0) {
            if (isDefaultSkin) {
                color = resources.getColor(resId);
            } else {
                int targetResId = getTargetResId(resId);
                if (targetResId == INVALID_RESOURCES) {
                    color = resources.getColor(resId);
                } else {
                    color = skinResources.getColor(targetResId);
                }
            }
            if (color != INVALID_RESOURCES) addCacheInt(getKeyByResId(resId), color);
        }
        return color;
    }

    /**
     * 根据resId加载对应的Drawable
     *
     * @param resId resource id
     * @return Drawable
     */
    public Drawable getDrawable(int resId) {
        if (resId <= INVALID_RESOURCES) return null;
        Drawable drawable = getCacheDrawable(getKeyByResId(resId));
        if (drawable == null) {
            if (isDefaultSkin) {
                drawable = resources.getDrawable(resId);
            } else {
                int targetResId = getTargetResId(resId);
                if (targetResId == INVALID_RESOURCES) {
                    drawable = resources.getDrawable(resId);
                } else {
                    drawable = skinResources.getDrawable(targetResId);
                }
            }
            if (drawable != null) addCacheDrawable(getKeyByResId(resId), drawable);
        }
        return drawable;
    }

    /**
     * 根据resId加载对应的ColorStateList
     *
     * @param resId resource id
     * @return ColorStateList
     */
    public ColorStateList getColorStateList(int resId) {
        if (resId <= INVALID_RESOURCES) return null;
        ColorStateList colorStateList = getCacheColorStateList(getKeyByResId(resId));
        if (colorStateList == null) {
            if (isDefaultSkin) {
                colorStateList = resources.getColorStateList(resId);
            } else {
                int targetResId = getTargetResId(resId);
                if (targetResId == INVALID_RESOURCES) {
                    colorStateList = resources.getColorStateList(resId);
                } else {
                    colorStateList = skinResources.getColorStateList(targetResId);
                }
            }
            if (colorStateList != null)
                addCacheColorStateList(getKeyByResId(resId), colorStateList);
        }
        return colorStateList;
    }

    /**
     * 获取应用的主色
     *
     * @return colorPrimary
     */
    public int getColorPrimary() {
        return getColorByAttr(android.support.v7.appcompat.R.attr.colorPrimary);
    }

    /**
     * 获取应用的主暗色
     *
     * @return colorPrimaryDark
     */
    public int getDarkColorPrimary() {
        return getColorByAttr(android.support.v7.appcompat.R.attr.colorPrimaryDark);
    }

    /**
     * 获取应用的选中色
     *
     * @return colorAccent
     */
    public int getColorAccent() {
        return getColorByAttr(android.support.v7.appcompat.R.attr.colorAccent);
    }

    /**
     * 获取应用的状态栏颜色
     *
     * @return statusBarColor
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public int getStatusBarColor(Activity activity) {
        TypedValue typedValue = new TypedValue();
        activity.getTheme().resolveAttribute(android.R.attr.statusBarColor, typedValue, true);
        return getColor(typedValue.resourceId);
    }

    /**
     * 获取应用的textColorPrimary颜色
     *
     * @return
     */
    public int getTextColorPrimary() {
        return getColorByAttr(android.R.attr.textColorPrimary);
    }

    /**
     * 获取应用的BackBackground资源
     *
     * @return resource id
     */
    public int getWindowBackgroundResId(Activity activity) {
        TypedValue typedValue = new TypedValue();
        activity.getTheme().resolveAttribute(android.R.attr.windowBackground, typedValue, true);
        return getTargetResId(typedValue.resourceId);
    }

    public void getValue(int resId, TypedValue outValue, boolean resolveRefs) {
        if (!isDefaultSkin) {
            int targetResId = getTargetResId(resId);
            if (targetResId != INVALID_RESOURCES) {
                skinResources.getValue(targetResId, outValue, resolveRefs);
                return;
            }
        }
        resources.getValue(resId, outValue, resolveRefs);
    }

    /**
     * 根据属性获取资源ID
     *
     * @param attr the attr
     * @return resource id
     */
    public int getResIdByAttr(int attr) {
        try {
            TypedValue typedValue = new TypedValue();
            contextWR.get().getTheme().resolveAttribute(attr, typedValue, true);
            return typedValue.resourceId;
        } catch (Exception e) {
            return INVALID_RESOURCES;
        }
    }

    /**
     * 根据属性名称获取颜色
     *
     * @param attr the attr
     * @return 颜色
     */
    public int getColorByAttr(int attr) {
        int color = getCacheInt(getKeyByAttr(attr));
        if (color == INVALID_RESOURCES) {
            color = getColor(getResIdByAttr(attr));
            if (color != INVALID_RESOURCES) addCacheInt(getKeyByAttr(attr), color);
        }
        return color;
    }

    /**
     * 根据属性名称获取Drawable
     *
     * @param attr the attr
     * @return Drawable
     */
    public Drawable getDrawableByAttr(int attr) {
        Drawable drawable = getCacheDrawable(getKeyByAttr(attr));
        if (drawable == null) {
            drawable = getDrawable(getResIdByAttr(attr));
            if (drawable != null) addCacheDrawable(getKeyByAttr(attr), drawable);
        }
        return drawable;
    }
}