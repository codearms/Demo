package com.codearms.maoqiqi.skin.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.codearms.maoqiqi.skin.widget.constraint.SkinConstraintLayout;

public class SkinSupportConstraintLayoutInflater implements SkinLayoutInflater {
    @Override
    public View createView(View parent, String name, Context context, AttributeSet attrs) {
        if (name.equals("android.support.constraint.ConstraintLayout")) {
            return new SkinConstraintLayout(context, attrs);
        }
        return null;
    }
}
