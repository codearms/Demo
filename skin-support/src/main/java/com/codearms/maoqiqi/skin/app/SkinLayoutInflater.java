package com.codearms.maoqiqi.skin.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public interface SkinLayoutInflater {

    View createView(View parent, String name, Context context, AttributeSet attrs);
}