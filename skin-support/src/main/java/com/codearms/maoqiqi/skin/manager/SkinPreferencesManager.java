package com.codearms.maoqiqi.skin.manager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 保存当前所使用皮肤的相关信息,使得重启程序皮肤不变
 */
public class SkinPreferencesManager {

    /**
     * SharedPreferences文件名称
     */
    private static final String PREFERENCE_NAME = "skin_preferences";

    /**
     * 皮肤模式(1:内置皮肤;2:使用assets文件夹下皮肤;3:使用外部皮肤)
     */
    private static final String SKIN_MODE = "skin_mode";

    /**
     * 默认皮肤模式
     */
    private static final int SKIN_MODE_DEFAULT = 0;

    /**
     * 内置皮肤模式
     */
    private static final int SKIN_MODE_BUILT_IN = 1;

    /**
     * 使用assets文件夹下皮肤模式
     */
    private static final int SKIN_MODE_ASSETS = 2;

    /**
     * 使用外部皮肤模式
     */
    private static final int SKIN_MODE_EXTERNAL = 3;

    /**
     * 存储 皮肤后缀名 名称
     */
    private static final String SKIN_SUFFIX_NAME = "skin_suffix_name";

    /**
     * 默认皮肤后缀名
     */
    public static final String SKIN_SUFFIX_NAME_DEFAULT = "";

    /**
     * assets文件夹下皮肤文件路径
     */
    private static final String SKIN_ASSETS_FILE_PATH = "skin_assets_file_path";

    /**
     * 默认assets文件夹下文件名称
     */
    private static final String SKIN_ASSETS_FILE_PATH_DEFAULT = "";

    /**
     * 存储 皮肤文件路径 名称
     */
    private static final String SKIN_FILE_PATH = "skin_file_path";

    /**
     * 默认皮肤文件路径
     */
    private static final String SKIN_FILE_PATH_DEFAULT = "";

    /**
     * SharedPreferences文件存储对象
     */
    private final SharedPreferences sharedPreferences;

    /**
     * 当前实例
     */
    private static SkinPreferencesManager instance;

    /**
     * 私有构造
     *
     * @param context the context
     */
    private SkinPreferencesManager(Context context) {
        // 实例化SharedPreferences文件存储对象
        this.sharedPreferences = context.getApplicationContext()
                .getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 初始化
     *
     * @param context the context
     */
    public static void init(Context context) {
        if (instance == null) {
            synchronized (SkinPreferencesManager.class) {
                if (instance == null) {
                    instance = new SkinPreferencesManager(context);
                }
            }
        }
    }

    /**
     * 得到当前对象,defined from {@link SkinManager#SkinManager }
     *
     * @return {@link SkinPreferencesManager}
     */
    public static SkinPreferencesManager getInstance() {
        if (instance == null)
            throw new NullPointerException("SkinPreferencesManager must first call the init(Context context)");

        return instance;
    }

    /**
     * 保存皮肤模式
     *
     * @param mode 皮肤模式
     * @return true or false
     */
    private boolean saveSkinMode(int mode) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SKIN_MODE, mode);
        return editor.commit();
    }

    /**
     * 保存为内置皮肤模式
     *
     * @return true or false
     */
    public boolean saveSkinModeBuiltIn() {
        return saveSkinMode(SKIN_MODE_BUILT_IN);
    }

    /**
     * 保存为使用assets文件夹下皮肤模式
     *
     * @return true or false
     */
    public boolean saveSkinModeAssets() {
        return saveSkinMode(SKIN_MODE_ASSETS);
    }

    /**
     * 保存为使用外部皮肤模式
     *
     * @return true or false
     */
    public boolean saveSkinModeExternal() {
        return saveSkinMode(SKIN_MODE_EXTERNAL);
    }

    /**
     * 获取皮肤模式
     *
     * @return skin mode
     */
    private int getSkinMode() {
        return sharedPreferences.getInt(SKIN_MODE, SKIN_MODE_DEFAULT);
    }

    /**
     * 保存皮肤后缀名
     *
     * @param skinSuffixName 后缀名
     * @return true or false
     */
    public boolean saveSkinSuffixName(String skinSuffixName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SKIN_SUFFIX_NAME, skinSuffixName);
        return editor.commit();
    }

    /**
     * 获取皮肤后缀名
     *
     * @return skin suffix name
     */
    public String getSkinSuffixName() {
        return sharedPreferences.getString(SKIN_SUFFIX_NAME, SKIN_SUFFIX_NAME_DEFAULT);
    }

    /**
     * 保存assets文件夹下皮肤文件路径
     *
     * @param skinAssetsFilePath assets文件夹下皮肤文件路径
     * @return true or false
     */
    public boolean saveSkinAssetsFilePath(String skinAssetsFilePath) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SKIN_ASSETS_FILE_PATH, skinAssetsFilePath);
        return editor.commit();
    }

    /**
     * 获取assets文件夹下皮肤文件路径
     *
     * @return skin suffix name
     */
    public String getSkinAssetsFilePath() {
        return sharedPreferences.getString(SKIN_ASSETS_FILE_PATH, SKIN_ASSETS_FILE_PATH_DEFAULT);
    }

    /**
     * 保存皮肤文件的路径
     *
     * @param skinFilePath the skin file's path
     * @return true or false
     */
    public boolean saveSkinFilePath(String skinFilePath) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SKIN_FILE_PATH, skinFilePath);
        return editor.commit();
    }

    /**
     * 获取外皮肤文件的路径
     *
     * @return the skin file's path
     */
    public String getSkinFilePath() {
        return sharedPreferences.getString(SKIN_FILE_PATH, SKIN_FILE_PATH_DEFAULT);
    }

    /**
     * 是否是默认皮肤
     *
     * @return true or false
     */
    public boolean isDefaultSkin() {
        return SKIN_MODE_DEFAULT == getSkinMode();
    }

    /**
     * 是否是内置皮肤
     *
     * @return true or false
     */
    public boolean isBuiltInSkin() {
        return SKIN_MODE_BUILT_IN == getSkinMode();
    }

    /**
     * 是否是assets文件夹下皮肤
     *
     * @return true or false
     */
    public boolean isAssetsSkin() {
        return SKIN_MODE_ASSETS == getSkinMode();
    }

    /**
     * 是否是外部皮肤
     *
     * @return true or false
     */
    public boolean isExternalSkin() {
        return SKIN_MODE_EXTERNAL == getSkinMode();
    }

    /**
     * 清除数据,恢复默认皮肤
     */
    public void clearData() {
        saveSkinMode(SKIN_MODE_DEFAULT);
        saveSkinFilePath(SKIN_FILE_PATH_DEFAULT);
        saveSkinAssetsFilePath(SKIN_ASSETS_FILE_PATH_DEFAULT);
        saveSkinSuffixName(SKIN_SUFFIX_NAME_DEFAULT);
    }
}