package com.codearms.maoqiqi.skin.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.TintContextWrapper;
import android.support.v7.widget.VectorEnabledTintResources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;

import org.xmlpull.v1.XmlPullParser;

/**
 * @author Doc.March
 * @date 2018/7/28
 */
public class SkinContextWrapper {

    private static final String TAG = "SkinContextWrapper";

    private static final boolean IS_PRE_LOLLIPOP = Build.VERSION.SDK_INT < 21;

    @SuppressLint("RestrictedApi")// 使用受限制的API
    public Context wrapContext(View parent, String name, Context context, AttributeSet attrs) {
        // 如果在LOLLIPOP之前,我们希望继承context
        boolean inheritContext = false;
        final Context originalContext = context;
        // 只有在LOLLIPOP之前,我们读取android:theme属性
        final boolean readAndroidTheme = IS_PRE_LOLLIPOP;
        // 每次都读取app:theme,因为历史遗留的原因
        final boolean readAppTheme = true;
        // 如果在LOLLIPOP之前,用户启动了AppCompatDelegate.isCompatVectorFromResourcesEnabled(),我们需要使用TintContextWrapper包裹context
        final boolean wrapContext = VectorEnabledTintResources.shouldBeUsed();

        if (IS_PRE_LOLLIPOP) {
            inheritContext = (attrs instanceof XmlPullParser)
                    // If we have a XmlPullParser, we can detect where we are in the layout
                    ? ((XmlPullParser) attrs).getDepth() > 1
                    // Otherwise we have to use the old heuristic
                    : shouldInheritContext(context, (ViewParent) parent);
        }

        // 我们可以模拟Lollipop's android:theme属性来向下传播视图层次结构,使用parent上下文
        if (inheritContext && parent != null) {
            context = parent.getContext();
        }

        // 我们将主题应用于上下文,如果指定的话
        context = themifyContext(context, attrs, readAndroidTheme, readAppTheme);

        if (wrapContext) {
            context = TintContextWrapper.wrap(context);
        }
//        Log.e(TAG, "name:" + name + ",inheritContext:" + inheritContext + ",wrapContext:" + wrapContext
//                + ",parent == null:" + (parent == null) + ",originalContext == context:" + (originalContext == context));
        return context;
    }

    private boolean shouldInheritContext(Context context, ViewParent parent) {
        if (!(context instanceof Activity)) {
            // context不存在Activity,所以返回false
            return false;
        }
        if (parent == null) {
            // parent为null,所以返回false
            return false;
        }
        // 得到顶层布局
        final View windowDecor = ((Activity) context).getWindow().getDecorView();
        while (true) {
            if (parent == null) {
                // Bingo. We've hit a view which has a null parent before being terminated from
                // the loop. This is (most probably) because it's the root view in an inflation
                // call, therefore we should inherit. This works as the inflated layout is only
                // added to the hierarchy at the end of the inflate() call.
                return true;
            } else if (parent == windowDecor || !(parent instanceof View)
                    || ViewCompat.isAttachedToWindow((View) parent)) {
                // We have either hit the window's decor view, a parent which isn't a View
                // (i.e. ViewRootImpl), or an attached view, so we know that the original parent
                // is currently added to the view hierarchy. This means that it has not be
                // inflated in the current inflate() call and we should not inherit the context.
                return false;
            }
            parent = parent.getParent();
        }
    }

    /**
     * 允许我们在LOLLIPOP之前模拟android:theme属性
     */
    private static Context themifyContext(Context context, AttributeSet attrs, boolean useAndroidTheme, boolean useAppTheme) {
        final TypedArray a = context.obtainStyledAttributes(attrs, android.support.v7.appcompat.R.styleable.View, 0, 0);
        int themeId = 0;
        if (useAndroidTheme) {
            // 首先尝试读取android:theme
            themeId = a.getResourceId(android.support.v7.appcompat.R.styleable.View_android_theme, 0);
            Log.e(TAG, "themeId:" + themeId);
        }
        if (useAppTheme && themeId == 0) {
            // 如果读取失败,读取app:theme
            themeId = a.getResourceId(android.support.v7.appcompat.R.styleable.View_theme, 0);

            if (themeId != 0) {
                // 提示用户使用android:theme代替app:theme
                Log.i(TAG, "app:theme is now deprecated. " + "Please move to using android:theme instead.");
            }
        }
        a.recycle();

        // 如果主题不为空,并且(context不包含ContextThemeWrapper或者包含的主题不相等),创建一个新的ContextThemeWrapper
        if (themeId != 0 && (!(context instanceof ContextThemeWrapper)
                || ((ContextThemeWrapper) context).getThemeResId() != themeId)) {
            // If the context isn't a ContextThemeWrapper, or it is but does not have
            // the same theme as we need, wrap it in a new wrapper
            context = new ContextThemeWrapper(context, themeId);
        }
        return context;
    }
}