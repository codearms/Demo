package com.codearms.maoqiqi.skin.app;

import android.content.Context;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.view.View;

import com.codearms.maoqiqi.skin.widget.SkinFrameLayout;
import com.codearms.maoqiqi.skin.widget.SkinLinearLayout;
import com.codearms.maoqiqi.skin.widget.SkinProgressBar;
import com.codearms.maoqiqi.skin.widget.SkinRadioGroup;
import com.codearms.maoqiqi.skin.widget.SkinRelativeLayout;
import com.codearms.maoqiqi.skin.widget.SkinScrollView;
import com.codearms.maoqiqi.skin.widget.SkinSwitch;
import com.codearms.maoqiqi.skin.widget.SkinView;
import com.codearms.maoqiqi.skin.widget.v7.SkinAutoCompleteTextView;
import com.codearms.maoqiqi.skin.widget.v7.SkinButton;
import com.codearms.maoqiqi.skin.widget.v7.SkinCheckBox;
import com.codearms.maoqiqi.skin.widget.v7.SkinCheckedTextView;
import com.codearms.maoqiqi.skin.widget.v7.SkinEditText;
import com.codearms.maoqiqi.skin.widget.v7.SkinImageButton;
import com.codearms.maoqiqi.skin.widget.v7.SkinImageView;
import com.codearms.maoqiqi.skin.widget.v7.SkinMultiAutoCompleteTextView;
import com.codearms.maoqiqi.skin.widget.v7.SkinRadioButton;
import com.codearms.maoqiqi.skin.widget.v7.SkinRatingBar;
import com.codearms.maoqiqi.skin.widget.v7.SkinSearchView;
import com.codearms.maoqiqi.skin.widget.v7.SkinSeekBar;
import com.codearms.maoqiqi.skin.widget.v7.SkinSpinner;
import com.codearms.maoqiqi.skin.widget.v7.SkinSwitchCompat;
import com.codearms.maoqiqi.skin.widget.v7.SkinTextView;
import com.codearms.maoqiqi.skin.widget.v7.SkinToolbar;

/**
 * @author Doc.March
 * @date 2018/8/10
 */
public class SkinSupportV7ViewInflater implements SkinLayoutInflater {

    @Override
    public View createView(View parent, String name, Context context, AttributeSet attrs) {
        if (!name.startsWith("android.support.v7.widget.")) return null;
        View view = null;
        switch (name) {
            case "android.support.v7.widget.AppCompatTextView":
                view = new SkinTextView(context, attrs);
                break;
            case "android.support.v7.widget.AppCompatEditText":
                view = new SkinEditText(context, attrs);
                break;
            case "android.support.v7.widget.AppCompatAutoCompleteTextView":
                view = new SkinAutoCompleteTextView(context, attrs);
                break;
            case "android.support.v7.widget.AppCompatMultiAutoCompleteTextView":
                view = new SkinMultiAutoCompleteTextView(context, attrs);
                break;
            case "android.support.v7.widget.AppCompatButton":
                view = new SkinButton(context, attrs);
                break;
            case "android.support.v7.widget.AppCompatRadioButton":
                view = new SkinRadioButton(context, attrs);
                break;
            case "android.support.v7.widget.AppCompatCheckBox":
                view = new SkinCheckBox(context, attrs);
                break;
            case "android.support.v7.widget.SwitchCompat":
                view = new SkinSwitchCompat(context, attrs);
                break;
            case "android.support.v7.widget.AppCompatCheckedTextView":
                view = new SkinCheckedTextView(context, attrs);
                break;
            case "android.support.v7.widget.AppCompatImageView":
                view = new SkinImageView(context, attrs);
                break;
            case "android.support.v7.widget.AppCompatImageButton":
                view = new SkinImageButton(context, attrs);
                break;
            case "RatingBar":
                // todo 还没有写
                view = new AppCompatRatingBar(context, attrs);
                break;
            case "SeekBar":
                // todo 还没有写
                view = new AppCompatSeekBar(context, attrs);
                break;

            case "android.support.v7.widget.AppCompatSpinner":
                view = new SkinSpinner(context, attrs);
                break;
            case "android.support.v7.widget.Toolbar":
                view = new SkinToolbar(context, attrs);
                break;
            case "android.support.v7.widget.SearchView":
                view = new SkinSearchView(context, attrs);
                break;
        }
        return view;
    }
}
