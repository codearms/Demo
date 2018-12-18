package com.codearms.maoqiqi.skin;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.DrawableUtils;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

/**
 * @author Doc.March
 * @date 2018/7/28
 */
public class A {

    // 动态修改SeekBar的颜色
    /*public void setSeekBarColor(SeekBar seekBar, int color) {
        // 获取SeekBar层次的drawable对象
        LayerDrawable layerDrawable = (LayerDrawable) seekBar.getProgressDrawable();
        // 有多少层次(最多三个)
        int layers = layerDrawable.getNumberOfLayers();
        Drawable[] drawables = new Drawable[layers];
        for (int i = 0; i < layers; i++) {
            switch (layerDrawable.getId(i)) {
                case android.R.id.background:// 背景色
                    drawables[i] = layerDrawable.getDrawable(i);
                    break;
                case android.R.id.progress:// 第一进度条
                    // 这里为动态的颜色值
                    drawables[i] = new PaintDrawable(progressColor);
                    drawables[i].setBounds(layerDrawable.getDrawable(i).getBounds());
                    break;
                case android.R.id.secondaryProgress:
                    drawables[i] = layerDrawable.getDrawable(i);
                    break;
            }
        }
        seekBar.setProgressDrawable(new LayerDrawable(drawables));
        seekBar.setThumb(thumb);
        seekBar.invalidate();

        //
        Drawable dra = layerDrawable.getDrawable(2);
        dra.setColorFilter(color, PorterDuff.Mode.SRC);
        //Drawable dra2 = seekBar.getThumb();
        seekBar.getThumb().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        seekBar.invalidate();
    }*/


    private void a(){
//        DrawableUtils;
//        ContextThemeWrapper;
//        AppCompatDrawableManager;
//        Userth
    }
}
