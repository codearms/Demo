package com.codearms.maoqiqi.skin.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.codearms.maoqiqi.skin.widget.SkinFrameLayout;
import com.codearms.maoqiqi.skin.widget.SkinGridLayout;
import com.codearms.maoqiqi.skin.widget.SkinGridView;
import com.codearms.maoqiqi.skin.widget.SkinLinearLayout;
import com.codearms.maoqiqi.skin.widget.SkinListView;
import com.codearms.maoqiqi.skin.widget.SkinProgressBar;
import com.codearms.maoqiqi.skin.widget.SkinRadioGroup;
import com.codearms.maoqiqi.skin.widget.SkinRelativeLayout;
import com.codearms.maoqiqi.skin.widget.SkinScrollView;
import com.codearms.maoqiqi.skin.widget.SkinSearchView;
import com.codearms.maoqiqi.skin.widget.SkinSwitch;
import com.codearms.maoqiqi.skin.widget.SkinToggleButton;
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
import com.codearms.maoqiqi.skin.widget.v7.SkinSeekBar;
import com.codearms.maoqiqi.skin.widget.v7.SkinSpinner;
import com.codearms.maoqiqi.skin.widget.v7.SkinTextView;

/**
 * @author Doc.March
 * @date 2018/8/10
 */
public class SkinSupportViewInflater implements SkinLayoutInflater {

    @Override
    public View createView(View parent, String name, Context context, AttributeSet attrs) {
        if (name.contains(".")) return null;
        View view = null;
        switch (name) {
            case "View":
                view = new SkinView(context, attrs);
                break;
            case "TextView":
                view = new SkinTextView(context, attrs);
                break;
            case "EditText":
                view = new SkinEditText(context, attrs);
                break;
            case "AutoCompleteTextView":
                view = new SkinAutoCompleteTextView(context, attrs);
                break;
            case "MultiAutoCompleteTextView":
                view = new SkinMultiAutoCompleteTextView(context, attrs);
                break;
            case "Button":
                view = new SkinButton(context, attrs);
                break;
            case "RadioButton":
                view = new SkinRadioButton(context, attrs);
                break;
            case "CheckBox":
                view = new SkinCheckBox(context, attrs);
                break;
            case "ToggleButton":
                view = new SkinToggleButton(context, attrs);
                break;
            case "Switch":
                view = new SkinSwitch(context, attrs);
                break;
            case "CheckedTextView":
                view = new SkinCheckedTextView(context, attrs);
                break;
            case "ImageView":
                view = new SkinImageView(context, attrs);
                break;
            case "ImageButton":
                view = new SkinImageButton(context, attrs);
                break;
            case "ProgressBar":
                view = new SkinProgressBar(context, attrs);
                break;
            case "RatingBar":
                view = new SkinRatingBar(context, attrs);
                break;
            case "SeekBar":
                view = new SkinSeekBar(context, attrs);
                break;

            case "ScrollView":
                view = new SkinScrollView(context, attrs);
                break;
            case "LinearLayout":
                view = new SkinLinearLayout(context, attrs);
                break;
            case "RelativeLayout":
                view = new SkinRelativeLayout(context, attrs);
                break;
            case "FrameLayout":
                view = new SkinFrameLayout(context, attrs);
                break;
            case "RadioGroup":
                view = new SkinRadioGroup(context, attrs);
                break;
            case "Spinner":
                view = new SkinSpinner(context, attrs);
                break;
            case "SearchView":
                view = new SkinSearchView(context, attrs);
                break;
            case "ListView":
                view = new SkinListView(context, attrs);
                break;
            case "GridView":
                view = new SkinGridView(context, attrs);
                break;
            case "GridLayout":
                view = new SkinGridLayout(context, attrs);
                break;
        }
        return view;
    }
}
