package com.codearms.maoqiqi.skin.app;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.codearms.maoqiqi.skin.attr.SkinSupportView;
import com.codearms.maoqiqi.skin.manager.SkinManager;
import com.codearms.maoqiqi.skin.manager.SkinResourcesManager;
import com.codearms.maoqiqi.skin.widget.Skinable;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class SkinLayoutInflaterFactory implements LayoutInflater.Factory2 {

    private static final String TAG = "LayoutInflaterFactory";

    /**
     * 当前Activity
     */
    private Activity activity;

    /**
     * 对Context进行包装
     */
    private SkinContextWrapper skinContextWrapper;

    /**
     * 创建View,参考AppCompatDelegateImplV9{@link android.support.v7.app.AppCompatDelegateImplV9}
     */
    private AppCompatViewInflater appCompatViewInflater;

    /**
     * 存储当前页面支持更改皮肤的SkinSupportView(视图和视图的属性)的集合
     */
    private List<SkinSupportView> skinSupportViews = new ArrayList<>();

    private List<WeakReference<Skinable>> skinWeakReferenceSkinableList = new ArrayList<>();

    private SkinLayoutInflaterFactory(Activity activity) {
        super();
        this.activity = activity;
        if (!SkinManager.getInstance().isDefaultSkin()) {
            updateStatusBarColor();
            updateWindowBackground();
            updateSkinSupportable();
        }
    }

    /**
     * 创建对象{@link SkinLayoutInflaterFactory}
     *
     * @param activity the activity
     * @return {@link SkinLayoutInflaterFactory}实例
     */
    public static SkinLayoutInflaterFactory create(Activity activity) {
        return new SkinLayoutInflaterFactory(activity);
    }

    /**
     * From {@link LayoutInflater.Factory2}
     */
    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        if (skinContextWrapper == null) skinContextWrapper = new SkinContextWrapper();
        context = skinContextWrapper.wrapContext(parent, name, context, attrs);

        if (appCompatViewInflater == null) appCompatViewInflater = new AppCompatViewInflater();
        View view = appCompatViewInflater.createView(parent, name, context, attrs);

        if (view != null) {
//            SkinSupportView skinSupportView = SkinAttrsParser.parseSkinAttr(context, view, attrs);
//            if (skinSupportView != null) {
//                // 添加到集合
//                skinSupportViews.add(skinSupportView);
//            }
            if (view instanceof Skinable) {
                Skinable skinable = (Skinable) view;
                // 如果当前选中的皮肤不是默认皮肤
                if (!SkinManager.getInstance().isDefaultSkin()) skinable.applySkin();
                skinWeakReferenceSkinableList.add(new WeakReference<>(skinable));
            }
        }

        return view;
    }

    /**
     * From {@link LayoutInflater.Factory2}
     */
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        Log.e(TAG, "onCreateView(String name, Context context, AttributeSet attrs),name = " + name);
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            Log.e(TAG, attrs.getAttributeName(i) + " , " + attrs.getAttributeValue(i));
        }
        return null;
    }

    /**
     * 更新状态栏
     */
    public void updateStatusBarColor() {
        if (activity == null) return;
        if ((SkinManager.getInstance().isSkinStatusBarColorEnable())) {
            if (SkinManager.getInstance().getSkinStatusBarColorDisEnables().containsKey(activity))
                return;
        } else {
            if (!SkinManager.getInstance().getSkinStatusBarColorEnables().containsKey(activity))
                return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(SkinResourcesManager.getInstance().getStatusBarColor(activity));
        }
    }

    /**
     * 更新windowBackground
     */
    public void updateWindowBackground() {
        if (activity == null) return;
        if (SkinManager.getInstance().isSkinWindowBackgroundEnable()) {
            int resId = SkinResourcesManager.getInstance().getWindowBackgroundResId(activity);
            if (resId != 0) activity.getWindow().setBackgroundDrawableResource(resId);
        }
    }

    /**
     * 应用皮肤
     */
    public void updateSkin() {
        if (!skinSupportViews.isEmpty()) {
            for (SkinSupportView skinSupportView : skinSupportViews) {
                if (skinSupportView != null) skinSupportView.applySkin();
            }
        }

        if (!skinWeakReferenceSkinableList.isEmpty()) {
            for (WeakReference<Skinable> weakReference : skinWeakReferenceSkinableList) {
                if (weakReference.get() != null) weakReference.get().applySkin();
            }
        }
    }

    /**
     * 如果接口实现了SkinSupportable,调用{@link SkinSupportable#applySkin()}方法
     */
    public void updateSkinSupportable() {
        if (activity == null) return;
        if (activity instanceof SkinSupportable) {
            ((SkinSupportable) activity).applySkin();
        }
    }

    /**
     * 清除数据
     */
    public void clear() {
        if (activity != null) activity = null;

        if (skinSupportViews.size() == 0) return;
        for (SkinSupportView skinSupportView : skinSupportViews) {
            if (skinSupportView != null) skinSupportView.clear();
        }
    }
}