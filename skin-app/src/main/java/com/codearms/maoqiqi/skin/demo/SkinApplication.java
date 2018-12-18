package com.codearms.maoqiqi.skin.demo;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.util.TypedValue;

import com.codearms.maoqiqi.skin.app.SkinActivityLifecycle;
import com.codearms.maoqiqi.skin.app.SkinSupportCardViewInflater;
import com.codearms.maoqiqi.skin.app.SkinSupportConstraintLayoutInflater;
import com.codearms.maoqiqi.skin.app.SkinSupportDesignViewInflater;
import com.codearms.maoqiqi.skin.app.SkinSupportRecyclerViewInflater;
import com.codearms.maoqiqi.skin.app.SkinSupportV4ViewInflater;
import com.codearms.maoqiqi.skin.app.SkinSupportV7ViewInflater;
import com.codearms.maoqiqi.skin.app.SkinSupportViewInflater;
import com.codearms.maoqiqi.skin.manager.SkinManager;

public class SkinApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // SkinManager.init(this).setSkinAllActivityEnable(false).setSkinStatusBarColorEnable(false).setSkinWindowBackgroundEnable(false).loadSkin();
        // SkinManager.init(this, true, false, false).loadSkin();
        SkinManager.init(this)
                .addLayoutInflater(new SkinSupportViewInflater())
                .addLayoutInflater(new SkinSupportV4ViewInflater())
                .addLayoutInflater(new SkinSupportV7ViewInflater())
                .addLayoutInflater(new SkinSupportDesignViewInflater())
                .addLayoutInflater(new SkinSupportRecyclerViewInflater())
                .addLayoutInflater(new SkinSupportCardViewInflater())
                .addLayoutInflater(new SkinSupportConstraintLayoutInflater())
                .loadSkin();

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        Log.e("info1", "," + typedValue.data + "," + typedValue.resourceId + "," + typedValue.type + "," + typedValue.string);
    }
}