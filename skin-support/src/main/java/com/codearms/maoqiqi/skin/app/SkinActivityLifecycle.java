package com.codearms.maoqiqi.skin.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.util.Log;
import android.view.LayoutInflater;

import com.codearms.maoqiqi.skin.annotation.Skin;
import com.codearms.maoqiqi.skin.manager.SkinManager;
import com.codearms.maoqiqi.skin.observe.SkinObserver;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.WeakHashMap;

public class SkinActivityLifecycle implements Application.ActivityLifecycleCallbacks {

    private static final String TAG = "SkinActivityLifecycle";

    private static SkinActivityLifecycle instance;

    private WeakHashMap<Activity, SkinLayoutInflaterFactory> skinLayoutInflaterFactoryMap;
    private WeakHashMap<Activity, LazySkinObserver> skinObserverMap;

    /**
     * 用于记录当前Activity,在换肤之后,立即刷新当前Activity
     */
    private WeakReference<Activity> currentActivityRef;

    private SkinActivityLifecycle(Application application) {
        super();
        application.registerActivityLifecycleCallbacks(this);
    }

    /**
     * 初始化在{@link SkinManager#SkinManager }中
     *
     * @param application the application
     */
    public static void init(Application application) {
        if (instance == null) {
            synchronized (SkinActivityLifecycle.class) {
                if (instance == null) {
                    instance = new SkinActivityLifecycle(application);
                }
            }
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (isSkinEnable(activity)) {
            setFactory(activity);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (isSkinEnable(activity)) {
            // 保存当前Activity
            currentActivityRef = new WeakReference<>(activity);
            LazySkinObserver observer = getSkinObserver(activity);
            /**
             * 添加到{@link SkinManager#skinObservers}中
             */
            SkinManager.getInstance().addObserver(observer);
            // 如果被标记为需要更新皮肤,则更新皮肤
            observer.updateSkinIfNeeded();
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (isSkinEnable(activity)) {
            // 清空数据
            getSkinLayoutInflaterFactory(activity).clear();
            /**
             * 从{@link skinLayoutInflaterFactoryMap}删除
             */
            skinLayoutInflaterFactoryMap.remove(activity);
            /**
             * 从{@link SkinManager#skinObservers}删除
             */
            SkinManager.getInstance().removeObserver(getSkinObserver(activity));
            /**
             * 从{@link skinObserverMap}删除
             */
            skinObserverMap.remove(activity);
        }
    }

    /**
     * 是否需要换肤功能
     * SkinManager.getInstance().isSkinAllActivityEnable():所有Activity都支持换肤
     * activity.getClass().getAnnotation(Skin.class):activity添加了Skinable注解
     * activity instanceof SkinSupportable:activity实现了SkinSupportable接口
     * 以三个条件满足任何一个表示都需要换肤功能
     *
     * @param activity the activity
     * @return true:需要;false:不需要
     */
    private boolean isSkinEnable(final Activity activity) {
        return SkinManager.getInstance().isSkinAllActivityEnable()
                || activity.getClass().getAnnotation(Skin.class) != null
                || activity instanceof SkinSupportable;
    }

    /**
     * 设置自定义的LayoutInflaterFactory
     *
     * @param activity the activity
     */
    private void setFactory(final Activity activity) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        // 判断是否已经LayoutInflater已经存在Factory
        if (layoutInflater.getFactory() != null) {
            try {
                // 通过反射得到mFactorySet这个私有属性
                Field field = LayoutInflater.class.getDeclaredField("mFactorySet");
                // 值为true则指示反射的对象在使用时应该取消Java语言访问检查
                field.setAccessible(true);
                // 更改私有属性的值
                field.setBoolean(layoutInflater, false);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        LayoutInflaterCompat.setFactory2(layoutInflater, getSkinLayoutInflaterFactory(activity));
    }

    /**
     * 根据Activity得到SkinLayoutInflaterFactory
     *
     * @param activity the activity
     * @return 返回{@link SkinLayoutInflaterFactory}
     */
    private SkinLayoutInflaterFactory getSkinLayoutInflaterFactory(final Activity activity) {
        Log.e(TAG, "activityName:" + activity.getClass().getName());
        if (skinLayoutInflaterFactoryMap == null) {
            skinLayoutInflaterFactoryMap = new WeakHashMap<>();
        }

        SkinLayoutInflaterFactory skinLayoutInflaterFactory = skinLayoutInflaterFactoryMap.get(activity);
        if (skinLayoutInflaterFactory == null) {
            Log.e(TAG, "SkinLayoutInflaterFactory.create(activity)");
            skinLayoutInflaterFactory = SkinLayoutInflaterFactory.create(activity);
            skinLayoutInflaterFactoryMap.put(activity, skinLayoutInflaterFactory);
        }
        return skinLayoutInflaterFactory;
    }

    /**
     * 根据Activity得到SkinObserver
     *
     * @param activity the activity
     * @return 返回{@link SkinObserver}
     */
    private LazySkinObserver getSkinObserver(final Activity activity) {
        Log.e(TAG, "activityName:" + activity.getClass().getName());
        if (skinObserverMap == null) {
            skinObserverMap = new WeakHashMap<>();
        }

        LazySkinObserver skinObserver = skinObserverMap.get(activity);
        if (skinObserver == null) {
            Log.e(TAG, "new LazySkinObserver(activity)");
            skinObserver = new LazySkinObserver(activity);
            skinObserverMap.put(activity, skinObserver);
        }
        return skinObserver;
    }

    private class LazySkinObserver implements SkinObserver {

        private final Activity activity;
        // true:不是当前activity;false:activity是当前Activity或者未知
        private boolean markNeedUpdate;

        LazySkinObserver(Activity activity) {
            this.activity = activity;
        }

        @Override
        public void updateSkin() {
            // 当前Activity,在换肤之后,立即刷新当前Activity。其它页面延迟更新皮肤
            if (currentActivityRef.get() == null || activity == currentActivityRef.get()) {
                updateSkinForce();
            } else {
                markNeedUpdate = true;
            }
        }

        /**
         * 当markNeedUpdate=true时,表示不是当前activity,需要延迟更新皮肤。在{@link SkinActivityLifecycle#onActivityResumed(Activity)}中调用
         */
        public void updateSkinIfNeeded() {
            if (markNeedUpdate) {
                updateSkinForce();
            }
        }

        /**
         * 更新具体内容
         */
        private void updateSkinForce() {
            if (activity == null) {
                return;
            }
            Log.e(TAG, "activityName:" + activity.getClass().getName() + "updateSkinForce()");

            getSkinLayoutInflaterFactory(activity).updateStatusBarColor();
            getSkinLayoutInflaterFactory(activity).updateWindowBackground();
            getSkinLayoutInflaterFactory(activity).updateSkin();
            getSkinLayoutInflaterFactory(activity).updateSkinSupportable();
        }
    }
}