package com.codearms.maoqiqi.skin.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.codearms.maoqiqi.skin.widget.v4.SkinContentLoadingProgressBar;
import com.codearms.maoqiqi.skin.widget.v4.SkinDrawerLayout;
import com.codearms.maoqiqi.skin.widget.v4.SkinNestedScrollView;
import com.codearms.maoqiqi.skin.widget.v4.SkinSlidingPaneLayout;
import com.codearms.maoqiqi.skin.widget.v4.SkinSwipeRefreshLayout;
import com.codearms.maoqiqi.skin.widget.v4.SkinViewPager;

/**
 * @author Doc.March
 * @date 2018/8/10
 */
public class SkinSupportV4ViewInflater implements SkinLayoutInflater {

    @Override
    public View createView(View parent, String name, Context context, AttributeSet attrs) {
        if (!name.startsWith("android.support.v4.widget.")) return null;
        View view = null;
        switch (name) {
            case "android.support.v4.widget.DrawerLayout":
                view = new SkinDrawerLayout(context, attrs);
                break;
            case "android.support.v4.widget.SlidingPaneLayout":
                view = new SkinSlidingPaneLayout(context, attrs);
                break;
            case "android.support.v4.widget.NestedScrollView":
                view = new SkinNestedScrollView(context, attrs);
                break;
            case "android.support.v4.widget.SwipeRefreshLayout":
                view = new SkinSwipeRefreshLayout(context, attrs);
                break;
            case "android.support.v4.view.ViewPager":
                view = new SkinViewPager(context, attrs);
                break;
            case "android.support.v4.widget.ContentLoadingProgressBar":
                view = new SkinContentLoadingProgressBar(context, attrs);
                break;
        }
        return view;
    }
}
