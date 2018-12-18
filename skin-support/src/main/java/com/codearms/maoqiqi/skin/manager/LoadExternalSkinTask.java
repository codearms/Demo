package com.codearms.maoqiqi.skin.manager;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;

import com.codearms.maoqiqi.skin.FileUtils;

import java.io.File;
import java.lang.ref.WeakReference;

/**
 * 加载外部皮肤包任务
 */
public class LoadExternalSkinTask extends AsyncTask<LoadExternalSkinTask.ILoadExternalSkin, Void, Resources> {

    private WeakReference<Context> contextWeakReference;
    private String skinFilePath;
    private String skinAssetsFilePath;
    private String skinSuffixName;
    private boolean isAssetsFile;
    private ILoadExternalSkin loadExternalSkin;

    private String skinPackageName;

    // 错误码
    private int code;
    // 错误信息
    private String message;

    public LoadExternalSkinTask(Context context, String skinFilePath, String skinAssetsFilePath, String skinSuffixName, boolean isAssetsFile) {
        this.contextWeakReference = new WeakReference<>(context);
        this.skinFilePath = skinFilePath;
        this.skinAssetsFilePath = skinAssetsFilePath;
        this.skinSuffixName = skinSuffixName;
        this.isAssetsFile = isAssetsFile;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (loadExternalSkin != null) loadExternalSkin.onLoadSkinStart();
    }

    @Override
    protected Resources doInBackground(LoadExternalSkinTask.ILoadExternalSkin... params) {
        if (params.length > 1) loadExternalSkin = params[0];

        if (skinFilePath == null || skinFilePath.equals("") || !new File(skinFilePath).exists()) {
            if (!isAssetsFile || skinAssetsFilePath == null || skinAssetsFilePath.equals("")) {
                code = 10001;
                message = "File does not exist.";
                return null;
            } else {
                // 复制assets下的文件到指定文件夹下
                skinFilePath = FileUtils.copySkinFromAssets(contextWeakReference.get(), skinAssetsFilePath);
                if (skinFilePath == null) {
                    code = 10002;
                    message = "Failed to copy files.";
                    return null;
                }
            }
        }

        try {
            skinPackageName = SkinManager.getInstance().getSkinPackageName(skinFilePath);
            return SkinManager.getInstance().getSkinResources(skinFilePath);
        } catch (Exception e) {
            e.printStackTrace();
            code = 10003;
            message = e.getMessage();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Resources result) {
        super.onPostExecute(result);
        if (result != null) { // 加载成功
            if (isAssetsFile) {
                // 设置皮肤模式
                SkinPreferencesManager.getInstance().saveSkinModeAssets();
                // 设置assets文件夹下皮肤文件路径
                SkinPreferencesManager.getInstance().saveSkinAssetsFilePath(skinAssetsFilePath);
            } else {
                // 设置皮肤模式
                SkinPreferencesManager.getInstance().saveSkinModeExternal();
            }
            // 设置外部皮肤包文件的路径
            SkinPreferencesManager.getInstance().saveSkinFilePath(skinFilePath);
            if (skinSuffixName == null)
                skinSuffixName = SkinPreferencesManager.SKIN_SUFFIX_NAME_DEFAULT;
            // 设置内置皮肤后缀名
            SkinPreferencesManager.getInstance().saveSkinSuffixName(skinSuffixName);
            // 设置皮肤资源管理类信息
            SkinResourcesManager.getInstance().setSkinInfo(skinSuffixName, skinPackageName, result);
            // 通知更新皮肤
            SkinManager.getInstance().notifyUpdateSkin();

            if (loadExternalSkin != null) loadExternalSkin.onLoadSkinSuccess();
        } else {
            // 加载失败,清空数据,使用原来的皮肤
            SkinPreferencesManager.getInstance().clearData();
            if (loadExternalSkin != null) loadExternalSkin.onLoadSkinFailed(code, message);
        }
    }

    /**
     * 加载外部皮肤包监听
     */
    public interface ILoadExternalSkin {
        /**
         * 皮肤包开始加载时调用
         */
        void onLoadSkinStart();

        /**
         * 皮肤包加载成功调用
         */
        void onLoadSkinSuccess();

        /**
         * 皮肤包加载失败调用
         *
         * @param code    错误码
         * @param message 错误信息
         */
        void onLoadSkinFailed(int code, String message);
    }
}