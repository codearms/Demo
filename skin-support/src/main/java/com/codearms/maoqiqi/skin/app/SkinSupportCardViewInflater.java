package com.codearms.maoqiqi.skin.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.codearms.maoqiqi.skin.widget.v7.SkinCardView;

public class SkinSupportCardViewInflater implements SkinLayoutInflater {

    @Override
    public View createView(View parent, String name, Context context, AttributeSet attrs) {
        if (name.equals("android.support.v7.widget.CardView")) {
            return new SkinCardView(context, attrs);
        }
        return null;
    }
}
