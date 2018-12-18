package com.codearms.maoqiqi.skin.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.codearms.maoqiqi.skin.widget.design.SkinAppBarLayout;
import com.codearms.maoqiqi.skin.widget.design.SkinBottomNavigationView;
import com.codearms.maoqiqi.skin.widget.design.SkinCollapsingToolbarLayout;
import com.codearms.maoqiqi.skin.widget.design.SkinCoordinatorLayout;
import com.codearms.maoqiqi.skin.widget.design.SkinFloatingActionButton;
import com.codearms.maoqiqi.skin.widget.design.SkinNavigationView;
import com.codearms.maoqiqi.skin.widget.design.SkinTabLayout;
import com.codearms.maoqiqi.skin.widget.design.SkinTextInputEditText;
import com.codearms.maoqiqi.skin.widget.design.SkinTextInputLayout;

public class SkinSupportDesignViewInflater implements SkinLayoutInflater {

    @Override
    public View createView(View parent, String name, Context context, AttributeSet attrs) {
        if (!name.startsWith("android.support.design.widget.")) return null;
        View view = null;
        switch (name) {
            case "android.support.design.widget.CollapsingToolbarLayout":
                view = new SkinCollapsingToolbarLayout(context, attrs);
                break;
            case "android.support.design.widget.CoordinatorLayout":
                view = new SkinCoordinatorLayout(context, attrs);
                break;
            case "android.support.design.widget.AppBarLayout":
                view = new SkinAppBarLayout(context, attrs);
                break;
            case "android.support.design.widget.TabLayout":
                view = new SkinTabLayout(context, attrs);
                break;
            case "android.support.design.widget.TextInputLayout":
                view = new SkinTextInputLayout(context, attrs);
                break;
            case "android.support.design.widget.TextInputEditText":
                view = new SkinTextInputEditText(context, attrs);
                break;
            case "android.support.design.widget.FloatingActionButton":
                view = new SkinFloatingActionButton(context, attrs);
                break;
            case "android.support.design.widget.NavigationView":
                view = new SkinNavigationView(context, attrs);
                break;
            case "android.support.design.widget.BottomNavigationView":
                view = new SkinBottomNavigationView(context, attrs);
                break;
        }
        return view;
    }
}
