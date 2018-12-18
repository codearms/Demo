package com.codearms.maoqiqi.skin.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.codearms.maoqiqi.skin.widget.v7.SkinRecyclerView;

/**
 * @author Doc.March
 * @date 2018/8/10
 */
public class SkinSupportRecyclerViewInflater implements SkinLayoutInflater {

    @Override
    public View createView(View parent, String name, Context context, AttributeSet attrs) {
        if (name.equals("android.support.v7.widget.RecyclerView")) {
            return new SkinRecyclerView(context, attrs);
        }
        return null;
    }
}
