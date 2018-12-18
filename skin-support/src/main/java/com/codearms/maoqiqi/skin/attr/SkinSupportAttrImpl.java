package com.codearms.maoqiqi.skin.attr;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v4.widget.ImageViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.AbsSeekBar;
import android.widget.CalendarView;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.codearms.maoqiqi.skin.constant.ResourcesConstant;
import com.codearms.maoqiqi.skin.manager.SkinResourcesManager;
import com.codearms.maoqiqi.skin.manager.SkinSupportAttrsManager;

import java.lang.reflect.Field;

public class SkinSupportAttrImpl extends SkinSupportAttr {

    @Override
    public void apply(View view) {
        if (getResId() == ResourcesConstant.INVALID_ID) return;

        switch (getAttrName()) {
            case SkinSupportAttrsManager.BACKGROUND:
                if (isColor()) {
                    int color = SkinResourcesManager.getInstance().getColor(getResId());
                    if (color == 0) return;
                    view.setBackgroundColor(color);
                } else if (isDrawable()) {
                    Drawable drawable = SkinResourcesManager.getInstance().getDrawable(getResId());
                    if (drawable == null) return;
                    ViewCompat.setBackground(view, drawable);
                }
                break;
            case SkinSupportAttrsManager.TEXT_COLOR:
                if (view instanceof TextView) {
                    TextView textView = (TextView) view;
                    if (isColor()) {
                        int color = SkinResourcesManager.getInstance().getColor(getResId());
                        if (color == 0) return;
                        textView.setTextColor(color);
                    } else if (isDrawable()) {
                        ColorStateList colorStateList = SkinResourcesManager.getInstance().getColorStateList(getResId());
                        if (colorStateList == null) return;
                        textView.setTextColor(colorStateList);
                    }
                }
                break;
            case SkinSupportAttrsManager.TEXT_COLOR_HINT:
                if (view instanceof TextView) {
                    TextView textView = (TextView) view;
                    if (isColor()) {
                        int color = SkinResourcesManager.getInstance().getColor(getResId());
                        if (color == 0) return;
                        textView.setHintTextColor(color);
                    } else if (isDrawable()) {
                        ColorStateList colorStateList = SkinResourcesManager.getInstance().getColorStateList(getResId());
                        if (colorStateList == null) return;
                        textView.setHintTextColor(colorStateList);
                    }
                }
                break;
            case SkinSupportAttrsManager.DRAWABLE_LEFT:
            case SkinSupportAttrsManager.DRAWABLE_TOP:
            case SkinSupportAttrsManager.DRAWABLE_RIGHT:
            case SkinSupportAttrsManager.DRAWABLE_BOTTOM:
                if (view instanceof TextView) {
                    TextView textView = (TextView) view;
                    if (isDrawable()) {
                        Drawable drawable = SkinResourcesManager.getInstance().getDrawable(getResId());
                        if (drawable == null) return;
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        Drawable[] drawables = textView.getCompoundDrawables();
                        switch (getAttrName()) {
                            case SkinSupportAttrsManager.DRAWABLE_LEFT:
                                drawables[0] = drawable;
                                break;
                            case SkinSupportAttrsManager.DRAWABLE_TOP:
                                drawables[1] = drawable;
                                break;
                            case SkinSupportAttrsManager.DRAWABLE_RIGHT:
                                drawables[2] = drawable;
                                break;
                            case SkinSupportAttrsManager.DRAWABLE_BOTTOM:
                                drawables[3] = drawable;
                                break;
                        }
                        textView.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
                    }
                }
                break;
            case SkinSupportAttrsManager.TEXT_APPEARANCE:
                if (view instanceof TextView) {
                    TextView textView = (TextView) view;
                    if (isStyle()) {
                        int style = SkinResourcesManager.getInstance().getTargetResId(getResId());
                        if (style == 0) return;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            textView.setTextAppearance(style);
                        } else {
                            textView.setTextAppearance(view.getContext(), style);
                        }
                    }
                }
                break;
            case SkinSupportAttrsManager.TEXT_CURSOR_DRAWABLE:
                if (view instanceof EditText) {
                    EditText editText = (EditText) view;
                    if (isDrawable()) {
                        Drawable drawable = SkinResourcesManager.getInstance().getDrawable(getResId());
                        if (drawable == null) return;
                        // 系统没有提供方法,只能通过反射实现
                        // todo 在TextInputLayout中使用还有点小问题
                        try {
                            Field fEditor = TextView.class.getDeclaredField("mEditor");
                            fEditor.setAccessible(true);
                            Object editor = fEditor.get(editText);

                            Class<?> clazz = editor.getClass();
                            Field fCursorDrawable = clazz.getDeclaredField("mCursorDrawable");
                            fCursorDrawable.setAccessible(true);
                            fCursorDrawable.set(editor, new Drawable[]{drawable});
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case SkinSupportAttrsManager.BUTTON:
                if (view instanceof CompoundButton) {
                    CompoundButton compoundButton = (CompoundButton) view;
                    if (isDrawable()) {
                        Drawable drawable = SkinResourcesManager.getInstance().getDrawable(getResId());
                        if (drawable == null) return;
                        compoundButton.setButtonDrawable(drawable);
                    }
                }
                break;
            case SkinSupportAttrsManager.CHECK_MARK:
                if (view instanceof CheckedTextView) {
                    CheckedTextView checkedTextView = (CheckedTextView) view;
                    if (isDrawable()) {
                        Drawable drawable = SkinResourcesManager.getInstance().getDrawable(getResId());
                        if (drawable == null) return;
                        checkedTextView.setCheckMarkDrawable(drawable);
                    }
                }
                break;
            case SkinSupportAttrsManager.SRC:
            case SkinSupportAttrsManager.SRC_COMPAT:
                if (view instanceof ImageView) {
                    ImageView imageView = (ImageView) view;
                    if (isDrawable()) {
                        Drawable drawable = SkinResourcesManager.getInstance().getDrawable(getResId());
                        if (drawable == null) return;
                        imageView.setImageDrawable(drawable);
                    }
                }
                break;
            case SkinSupportAttrsManager.TINT:
                if (view instanceof ImageView) {
                    ImageView imageView = (ImageView) view;
                    if (isColor()) {
                        ColorStateList colorStateList = SkinResourcesManager.getInstance().getColorStateList(getResId());
                        if (colorStateList == null) return;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            imageView.setImageTintList(colorStateList);
                        } else {
                            ImageViewCompat.setImageTintList(imageView, colorStateList);
                        }
                    }
                }
                break;
            case SkinSupportAttrsManager.LIST_SELECTOR:
                if (view instanceof ListView) {
                    ListView listView = (ListView) view;
                    if (isDrawable()) {
                        Drawable drawable = SkinResourcesManager.getInstance().getDrawable(getResId());
                        if (drawable == null) return;
                        listView.setSelector(drawable);
                    }
                }
                break;
            case SkinSupportAttrsManager.DIVIDER:
                if (view instanceof ListView) {
                    ListView listView = (ListView) view;
                    if (isColor()) {
                        int color = SkinResourcesManager.getInstance().getColor(getResId());
                        if (color == 0) return;
                        listView.setDivider(new ColorDrawable(color));
                        listView.setDividerHeight(1);
                    } else if (isDrawable()) {
                        Drawable drawable = SkinResourcesManager.getInstance().getDrawable(getResId());
                        if (drawable == null) return;
                        listView.setDivider(drawable);
                    }
                }
                break;
            case SkinSupportAttrsManager.PROGRESS_DRAWABLE:
                if (view instanceof ProgressBar) {
                    ProgressBar progressBar = (ProgressBar) view;
                    if (isDrawable()) {
                        Drawable drawable = SkinResourcesManager.getInstance().getDrawable(getResId());
                        if (drawable == null) return;
                        progressBar.setProgressDrawable(drawable);
                    }
                }
                break;
            case SkinSupportAttrsManager.THUMB:
                if (view instanceof Switch) {
                    Switch s = (Switch) view;
                    if (isDrawable()) {
                        Drawable drawable = SkinResourcesManager.getInstance().getDrawable(getResId());
                        if (drawable == null) return;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            s.setThumbDrawable(drawable);
                        }
                    }
                } else if (view instanceof SeekBar) {
                    SeekBar seekBar = (SeekBar) view;
                    if (isDrawable()) {
                        Drawable drawable = SkinResourcesManager.getInstance().getDrawable(getResId());
                        if (drawable == null) return;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            seekBar.setThumb(drawable);
                        }
                    }
                }
                break;
            case SkinSupportAttrsManager.TRACK:
                if (view instanceof Switch) {
                    Switch s = (Switch) view;
                    if (isDrawable()) {
                        Drawable drawable = SkinResourcesManager.getInstance().getDrawable(getResId());
                        if (drawable == null) return;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            s.setTrackDrawable(drawable);
                        }
                    }
                }
                break;
            case SkinSupportAttrsManager.SWITCH_TEXT_APPEARANCE:
                if (view instanceof Switch) {
                    Switch s = (Switch) view;
                    if (isStyle()) {
                        int style = SkinResourcesManager.getInstance().getTargetResId(getResId());
                        if (style == 0) return;
                        s.setSwitchTextAppearance(s.getContext(), style);
                    }
                }
                break;
            case SkinSupportAttrsManager.WEEK_DAY_TEXT_APPEARANCE:
                if (view instanceof CalendarView) {
                    // todo 暂时有问题
                    CalendarView calendarView = (CalendarView) view;
                    if (isStyle()) {
                        int style = SkinResourcesManager.getInstance().getTargetResId(getResId());
                        if (style == 0) return;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            calendarView.setWeekDayTextAppearance(style);
                        }
                    }
                }
                break;
            case SkinSupportAttrsManager.DRAWABLE_START:
                break;
            case SkinSupportAttrsManager.DRAWABLE_END:
                break;
            case SkinSupportAttrsManager.BACKGROUND_TINT:
                if (isColor()) {
                    ColorStateList csl = SkinResourcesManager.getInstance().getColorStateList(getResId());
                    if (csl == null) return;
                    ViewCompat.setBackgroundTintList(view, csl);
                }
                break;
            case SkinSupportAttrsManager.FOREGROUND_TINT:
                break;
            case SkinSupportAttrsManager.DRAWABLE_TINT:
                if (view instanceof TextView) {
                    TextView textView = (TextView) view;
                    if (isColor()) {
                        ColorStateList colorStateList = SkinResourcesManager.getInstance().getColorStateList(getResId());
                        if (colorStateList == null) return;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            textView.setCompoundDrawableTintList(colorStateList);
                        }
                    }
                }
                break;
            case SkinSupportAttrsManager.BUTTON_TINT:
                if (view instanceof CompoundButton) {
                    CompoundButton compoundButton = (CompoundButton) view;
                    ColorStateList colorStateList = null;
                    if (isColor()) {
                        colorStateList = SkinResourcesManager.getInstance().getColorStateList(getResId());
                    }
                    if (colorStateList == null) return;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        compoundButton.setButtonTintList(colorStateList);
                    } else {
                        CompoundButtonCompat.setButtonTintList(compoundButton, colorStateList);
                    }
                }
                break;
            case SkinSupportAttrsManager.CHECK_MARK_TINT:
                if (view instanceof CheckedTextView) {
                    CheckedTextView checkedTextView = (CheckedTextView) view;
                    ColorStateList colorStateList = null;
                    if (isColor()) {
                        colorStateList = SkinResourcesManager.getInstance().getColorStateList(getResId());
                    }
                    if (colorStateList == null) return;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        checkedTextView.setCheckMarkTintList(colorStateList);
                    } else {
                        // @todo
                    }
                }
                break;
            case SkinSupportAttrsManager.THUMB_TINT:
                if (view instanceof AbsSeekBar) {
                    AbsSeekBar absSeekBar = (AbsSeekBar) view;
                    ColorStateList colorStateList = null;
                    if (isColor()) {
                        colorStateList = SkinResourcesManager.getInstance().getColorStateList(getResId());
                    }
                    if (colorStateList == null) return;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        absSeekBar.setThumbTintList(colorStateList);
                    } else {
                        // @todo
                    }
                } else if (view instanceof Switch) {
                    Switch s = (Switch) view;
                    ColorStateList colorStateList = null;
                    if (isColor()) {
                        colorStateList = SkinResourcesManager.getInstance().getColorStateList(getResId());
                    }
                    if (colorStateList == null) return;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        s.setThumbTintList(colorStateList);
                    } else {
                        // todo
                    }
                }
                break;
            case SkinSupportAttrsManager.TRACK_TINT:
                if (view instanceof Switch) {
                    Switch s = (Switch) view;
                    ColorStateList colorStateList = null;
                    if (isColor()) {
                        colorStateList = SkinResourcesManager.getInstance().getColorStateList(getResId());
                    }
                    if (colorStateList == null) return;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        s.setTrackTintList(colorStateList);
                    } else {
                        // todo
                    }
                }
                break;
            case SkinSupportAttrsManager.PROGRESS_TINT:
                if (view instanceof ProgressBar) {
                    ProgressBar progressBar = (ProgressBar) view;
                    if (isColor()) {
                        ColorStateList colorStateList = SkinResourcesManager.getInstance().getColorStateList(getResId());
                        if (colorStateList == null) return;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            progressBar.setProgressTintList(colorStateList);
                        } else {
                            // @todo
                        }
                    }
                }
                break;
            case SkinSupportAttrsManager.SECONDARY_PROGRESS_TINT:
                if (view instanceof ProgressBar) {
                    ProgressBar progressBar = (ProgressBar) view;
                    if (isColor()) {
                        ColorStateList colorStateList = SkinResourcesManager.getInstance().getColorStateList(getResId());
                        if (colorStateList == null) return;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            progressBar.setSecondaryProgressTintList(colorStateList);
                        } else {
                            // @todo
                        }
                    }
                }
                break;
            case SkinSupportAttrsManager.PROGRESS_BACKGROUND_TINT:
                if (view instanceof ProgressBar) {
                    ProgressBar progressBar = (ProgressBar) view;
                    if (isColor()) {
                        ColorStateList colorStateList = SkinResourcesManager.getInstance().getColorStateList(getResId());
                        if (colorStateList == null) return;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            progressBar.setProgressBackgroundTintList(colorStateList);
                        } else {
                            // @todo
                        }
                    }
                }
                break;
            case SkinSupportAttrsManager.INDETERMINATE_TINT:
                if (view instanceof ProgressBar) {
                    ProgressBar progressBar = (ProgressBar) view;
                    if (isColor()) {
                        ColorStateList colorStateList = SkinResourcesManager.getInstance().getColorStateList(getResId());
                        if (colorStateList == null) return;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            progressBar.setIndeterminateTintList(colorStateList);
                        } else {
                            // @todo
                        }
                    }
                }
                break;
            case SkinSupportAttrsManager.TICK_MARK_TINT:
                break;
            case SkinSupportAttrsManager.HINT_TEXT_APPEARANCE:
                if (view instanceof TextInputLayout) {
                    TextInputLayout textInputLayout = (TextInputLayout) view;
//                    Log.e("info", "attr:" + getAttrName() + ",resId:" + getResId() + ",entryName:" + getEntryName() + ",typeName:" + getResTypeName());
                    if (isStyle()) {
                        int style = SkinResourcesManager.getInstance().getTargetResId(getResId());
                        if (style == 0) return;
                        textInputLayout.setHintTextAppearance(style);
                    }
                }
                break;
            case SkinSupportAttrsManager.ITEM_ICON_TINT:
                if (view instanceof NavigationView) {
                    NavigationView navigationView = (NavigationView) view;
                    if (isColor()) {
                        ColorStateList colorStateList = SkinResourcesManager.getInstance().getColorStateList(getResId());
                        if (colorStateList == null) return;
                        navigationView.setItemIconTintList(colorStateList);
                    }
                }
                break;
            case SkinSupportAttrsManager.ITEM_TEXT_COLOR:
                if (view instanceof NavigationView) {
                    NavigationView navigationView = (NavigationView) view;
                    if (isColor()) {
                        ColorStateList colorStateList = SkinResourcesManager.getInstance().getColorStateList(getResId());
                        if (colorStateList == null) return;
                        navigationView.setItemTextColor(colorStateList);
                    }
                }
                break;
        }
    }
}