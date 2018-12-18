package com.codearms.maoqiqi.skin.manager;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import com.codearms.maoqiqi.skin.annotation.Skin;
import com.codearms.maoqiqi.skin.app.SkinActivityLifecycle;
import com.codearms.maoqiqi.skin.app.SkinLayoutInflater;
import com.codearms.maoqiqi.skin.attr.SkinSupportAttr;
import com.codearms.maoqiqi.skin.observe.SkinObservable;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * 皮肤管理类
 */
public class SkinManager extends SkinObservable {

    private static final String TAG = "SkinManager";

    /**
     * 缓存当前应用Context
     */
    private final WeakReference<Context> contextWR;

    /**
     * 当前实例对象
     */
    private static SkinManager instance;

    /**
     * 所有Activity都支持换肤(true:所有Activity都支持换肤;false:实现SkinSupportable的Activity支持换肤)
     */
    private boolean skinAllActivityEnable = true;

    /**
     * 是否支持所有Activity的状态栏(statusBarColor)换肤。使用Theme中的{@link android.R.attr#statusBarColor}属性。
     */
    private boolean skinStatusBarColorEnable = true;

    /**
     * 不支持所有Activity的状态栏换肤,部分Activity的状态栏换肤
     */
    private WeakHashMap<Activity, Boolean> skinStatusBarColorEnables = new WeakHashMap<>();

    /**
     * 支持所有Activity的状态栏换肤,部分Activity的状态栏不支持换肤
     */
    private WeakHashMap<Activity, Boolean> skinStatusBarColorDisEnables = new WeakHashMap<>();

    /**
     * 是否支持背景(windowBackground)换肤
     */
    private boolean skinWindowBackgroundEnable = true;

    /**
     * 保存自定义实现{@link SkinLayoutInflater}接口的实例化类
     */
    private Set<SkinLayoutInflater> skinLayoutInflaters = new HashSet<>();

    /**
     * 设置所有Activity都支持换肤
     *
     * @return true:支持;false:不支持
     */
    public boolean isSkinAllActivityEnable() {
        return skinAllActivityEnable;
    }

    /**
     * 设置是否所有所有Activity都支持换肤
     *
     * @param skinAllActivityEnable true:支持;false:不支持。添加{@link Skin}注解和实现SkinSupportable接口的Activity支持换肤。
     */
    public SkinManager setSkinAllActivityEnable(boolean skinAllActivityEnable) {
        this.skinAllActivityEnable = skinAllActivityEnable;
        return this;
    }

    /**
     * 是否支持状态栏换肤
     *
     * @return true:支持;false:不支持
     */
    public boolean isSkinStatusBarColorEnable() {
        return skinStatusBarColorEnable;
    }

    /**
     * 设置状态栏换肤(5.0以上有效)
     *
     * @param skinStatusBarColorEnable true:支持;false:不支持
     */
    public SkinManager setSkinStatusBarColorEnable(boolean skinStatusBarColorEnable) {
        this.skinStatusBarColorEnable = skinStatusBarColorEnable;
        return this;
    }

    /**
     * 设置当前Activity支持状态栏换肤
     *
     * @param activity the activity
     */
    public void setSkinStatusBarColorEnable(Activity activity) {
        this.skinStatusBarColorEnables.put(activity, true);
    }

    public WeakHashMap<Activity, Boolean> getSkinStatusBarColorEnables() {
        return skinStatusBarColorEnables;
    }

    /**
     * 设置当前Activity不支持状态栏换肤
     *
     * @param activity the activity
     */
    public void setSkinStatusBarColorDisEnable(Activity activity) {
        this.skinStatusBarColorDisEnables.put(activity, true);
    }

    public WeakHashMap<Activity, Boolean> getSkinStatusBarColorDisEnables() {
        return skinStatusBarColorDisEnables;
    }

    /**
     * 是否支持背景
     *
     * @return true:支持;false:不支持
     */
    public boolean isSkinWindowBackgroundEnable() {
        return skinWindowBackgroundEnable;
    }

    /**
     * 设置背景换肤
     *
     * @param skinWindowBackgroundEnable true:支持;false:不支持
     */
    public SkinManager setSkinWindowBackgroundEnable(boolean skinWindowBackgroundEnable) {
        this.skinWindowBackgroundEnable = skinWindowBackgroundEnable;
        return this;
    }

    /**
     * 添加自定义layoutInflater
     *
     * @param layoutInflater 自定义的layoutInflater
     */
    public SkinManager addLayoutInflater(SkinLayoutInflater layoutInflater) {
        skinLayoutInflaters.add(layoutInflater);
        return this;
    }

    /**
     * 得到所有添加的自定义的layoutInflater
     *
     * @return layoutInflater list
     */
    public Set<SkinLayoutInflater> getSkinLayoutInflaters() {
        return skinLayoutInflaters;
    }

    /**
     * 是否是默认皮肤
     *
     * @return true or false
     */
    public boolean isDefaultSkin() {
        return SkinPreferencesManager.getInstance().isDefaultSkin();
    }

    /**
     * 添加多个支持更改皮肤的属性,defined from {@link SkinSupportAttrsManager#addSkinSupportAttrs(String[], Class[])}  }
     *
     * @param attrNames 属性名数组
     * @param classes   属性名对应实现类的class数组
     */
    public void addSkinSupportAttrs(String[] attrNames, Class<? extends SkinSupportAttr>[] classes) {
        SkinSupportAttrsManager.addSkinSupportAttrs(attrNames, classes);
    }

    /**
     * 删除多个支持更改皮肤的属性,defined from {@link SkinSupportAttrsManager#removeSkinSupportAttrs(String[])}  }
     *
     * @param attrNames 属性名数组
     */
    public void removeSkinSupportAttrs(String[] attrNames) {
        SkinSupportAttrsManager.removeSkinSupportAttrs(attrNames);
    }

    /**
     * 私有构造
     *
     * @param application the application
     */
    private SkinManager(Application application) {
        super();
        this.contextWR = new WeakReference<>(application.getApplicationContext());
        SkinActivityLifecycle.init(application);
        SkinPreferencesManager.init(application);
        SkinResourcesManager.init(application);
    }

    /**
     * 初始化,应该在Application的onCreate()方法中调用
     *
     * @param application the application
     * @return {@link SkinManager}
     */
    public static SkinManager init(Application application) {
        if (instance == null) {
            synchronized (SkinManager.class) {
                if (instance == null) {
                    instance = new SkinManager(application);
                }
            }
        }
        return instance;
    }

    /**
     * 得到SkinManager{@link SkinManager}对象
     *
     * @return {@link SkinManager}
     */
    public static SkinManager getInstance() {
        if (instance == null)
            throw new NullPointerException("SkinManager must first call the init(Context context)");

        return instance;
    }

    /**
     * 加载记录的皮肤包,一般在Application中初始化换肤框架后调用.SkinManager.init(this).loadSkin();
     */
    public void loadSkin() {
        // 如果是内置皮肤
        if (SkinPreferencesManager.getInstance().isBuiltInSkin()) {
            // 得到后缀名
            String skinSuffixName = SkinPreferencesManager.getInstance().getSkinSuffixName();
            // 设置皮肤资源管理类皮肤信息
            SkinResourcesManager.getInstance().setBuiltInSkinInfo(skinSuffixName);
            // 通知更新皮肤
            notifyUpdateSkin();
            return;
        }

        // 如果是assets文件夹下皮肤
        if (SkinPreferencesManager.getInstance().isAssetsSkin()) {
            // 得到皮肤路径名
            String skinFilePath = SkinPreferencesManager.getInstance().getSkinFilePath();
            // 获取assets文件夹下皮肤文件路径
            String skinAssetsFilePath = SkinPreferencesManager.getInstance().getSkinAssetsFilePath();
            // 得到后缀名
            String skinSuffixName = SkinPreferencesManager.getInstance().getSkinSuffixName();
            loadSkin(skinFilePath, skinAssetsFilePath, skinSuffixName, true, null);
            return;
        }

        // 如果是外部皮肤
        if (SkinPreferencesManager.getInstance().isExternalSkin()) {
            // 得到皮肤路径名
            String skinFilePath = SkinPreferencesManager.getInstance().getSkinFilePath();
            // 得到后缀名
            String skinSuffixName = SkinPreferencesManager.getInstance().getSkinSuffixName();
            loadSkin(skinFilePath, null, skinSuffixName, false, null);
            return;
        }

        // 都不是,使用默认
        String skinSuffixName = SkinPreferencesManager.SKIN_SUFFIX_NAME_DEFAULT;
        // 设置皮肤资源管理类皮肤信息
        SkinResourcesManager.getInstance().setBuiltInSkinInfo(skinSuffixName);
        // 通知更新皮肤
        notifyUpdateSkin();
    }

    /**
     * 加载内置皮肤(传null或者""恢复默认)
     *
     * @param skinSuffixName 皮肤后缀名
     */
    public void loadBuiltInSkin(String skinSuffixName) {
        if (skinSuffixName == null) skinSuffixName = "";
        // 重复加载
        if (SkinPreferencesManager.getInstance().getSkinSuffixName().equals(skinSuffixName)) {
            Log.e(TAG, "SkinPreferencesManager.getInstance().getSkinSuffixName().equals(skinSuffixName):true");
            return;
        }
        // 设置皮肤模式
        SkinPreferencesManager.getInstance().saveSkinModeBuiltIn();
        // 设置内置皮肤后缀名
        SkinPreferencesManager.getInstance().saveSkinSuffixName(skinSuffixName);
        // 设置皮肤资源管理类信息
        SkinResourcesManager.getInstance().setBuiltInSkinInfo(skinSuffixName);
        // 通知更新皮肤
        notifyUpdateSkin();
    }

    public void loadAssetsSkin(String skinAssetsFilePath) {
        loadAssetsSkin(skinAssetsFilePath, null);
    }

    /**
     * 加载assets文件夹下皮肤
     *
     * @param skinAssetsFilePath assets文件夹下皮肤文件路径
     * @param skinSuffixName     皮肤后缀名
     */
    public void loadAssetsSkin(String skinAssetsFilePath, String skinSuffixName) {
        loadAssetsSkin(skinAssetsFilePath, skinSuffixName, null);
    }

    /**
     * 加载assets文件夹下皮肤
     *
     * @param skinAssetsFilePath assets文件夹下皮肤文件路径
     * @param skinSuffixName     皮肤后缀名
     * @param loadExternalSkin   加载外部皮肤包监听{@link LoadExternalSkinTask.ILoadExternalSkin}
     */
    public void loadAssetsSkin(String skinAssetsFilePath, String skinSuffixName, LoadExternalSkinTask.ILoadExternalSkin loadExternalSkin) {
        loadSkin(null, skinAssetsFilePath, skinSuffixName, true, loadExternalSkin);
    }

    /**
     * 加载外部皮肤
     *
     * @param skinFilePath 外部皮肤路径
     */
    public void loadExternalSkin(String skinFilePath) {
        loadExternalSkin(skinFilePath, null);
    }

    /**
     * 加载外部皮肤
     *
     * @param skinFilePath   外部皮肤路径
     * @param skinSuffixName 皮肤后缀名
     */
    public void loadExternalSkin(String skinFilePath, String skinSuffixName) {
        loadExternalSkin(skinFilePath, skinSuffixName, null);
    }

    /**
     * 加载外部皮肤
     *
     * @param skinFilePath     外部皮肤路径
     * @param skinSuffixName   皮肤后缀名
     * @param loadExternalSkin 加载外部皮肤包监听{@link LoadExternalSkinTask.ILoadExternalSkin}
     */
    public void loadExternalSkin(String skinFilePath, String skinSuffixName, LoadExternalSkinTask.ILoadExternalSkin loadExternalSkin) {
        loadSkin(skinFilePath, null, skinSuffixName, false, loadExternalSkin);
    }

    /**
     * 加载皮肤
     *
     * @param skinFilePath       外部皮肤路径
     * @param skinAssetsFilePath assets文件夹下皮肤文件路径
     * @param skinSuffixName     皮肤后缀名
     * @param isAssetsFile       皮肤是否是来自assets文件夹
     * @param loadExternalSkin   加载外部皮肤包监听{@link LoadExternalSkinTask.ILoadExternalSkin}
     */
    private void loadSkin(String skinFilePath, String skinAssetsFilePath, String skinSuffixName, boolean isAssetsFile, LoadExternalSkinTask.ILoadExternalSkin loadExternalSkin) {
        if (contextWR.get() == null) {
            Log.e(TAG, "contextWR.get() == null");
            return;
        }
        new LoadExternalSkinTask(contextWR.get(), skinFilePath, skinAssetsFilePath, skinSuffixName, isAssetsFile).execute(loadExternalSkin);
    }

    public String getSkinPackageName(String skinFilePath) {
        if (contextWR.get() == null) {
            Log.e(TAG, "contextWR.get() == null");
            return null;
        }
        // 初始化包管理器
        PackageManager packageManager = contextWR.get().getPackageManager();
        // 得到包信息
        PackageInfo info = packageManager.getPackageArchiveInfo(skinFilePath, PackageManager.GET_ACTIVITIES);
        // 得到包名
        return info.packageName;
    }

    public Resources getSkinResources(String skinFilePath) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        if (contextWR.get() == null) {
            Log.e(TAG, "contextWR.get() == null");
            return null;
        }
        // AssetManager实例
        AssetManager assetManager = AssetManager.class.newInstance();
        // 通过反射调用addAssetPath方法
        Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
        // assetManager对象中带有参数path的addAssetPath方法。返回值是Object,也既是该方法的返回值
        addAssetPath.invoke(assetManager, skinFilePath);

        Resources resources = contextWR.get().getResources();
        // 实例化皮肤资源,返回
        return new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
    }
}