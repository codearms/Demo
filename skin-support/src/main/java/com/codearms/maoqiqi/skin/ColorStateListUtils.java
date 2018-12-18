package com.codearms.maoqiqi.skin;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;

import com.codearms.maoqiqi.skin.manager.SkinResourcesManager;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ColorStateListUtils {

    public Drawable createDrawable(Context context, int resId) {
        if (resId <= 0) return null;

        final TypedValue typedValue = new TypedValue();
        final Resources resources = context.getResources();
        resources.getValue(resId, typedValue, true);

        Drawable drawable = null;
        if (typedValue.type >= TypedValue.TYPE_FIRST_COLOR_INT
                && typedValue.type <= TypedValue.TYPE_LAST_COLOR_INT) {
            String entryName = context.getResources().getResourceEntryName(resId);
            String typeName = context.getResources().getResourceTypeName(resId);
            int colorId = resources.getIdentifier(entryName, typeName, context.getPackageName());
            if (colorId == 0) colorId = resId;
            drawable = new ColorDrawable(ContextCompat.getColor(context, colorId));
        }


        return drawable;
    }

    public static ColorStateList createColorStateList(Resources resources, int resId) {
        TypedValue typedValue = new TypedValue();
        resources.getValue(resId, typedValue, true);
        ColorStateList csl = null;
        if (typedValue.type >= TypedValue.TYPE_FIRST_COLOR_INT
                && typedValue.type <= TypedValue.TYPE_LAST_COLOR_INT) {
            csl = ColorStateList.valueOf(resId);
        } else {
            final String file = typedValue.string.toString();
            try {
                XmlResourceParser parser = resources.getAssets().openXmlResourceParser(typedValue.assetCookie, file);
                csl = ColorStateList.createFromXml(resources, parser);
                parser.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        }
        return csl;
    }

//    public static Drawable getDrawable(Drawable drawable) {
//        if (drawable instanceof StateListDrawable) {
//            Log.e("info1", "StateListDrawable");
//            StateListDrawable stateListDrawable = (StateListDrawable) drawable;
//
//            try {
//                Class<?> aClass = StateListDrawable.class;
//                Method getStateCountMethod = aClass.getDeclaredMethod("getStateCount", null);
//                Method getStateSetMethod = aClass.getDeclaredMethod("getStateSet", int.class);
//                Method getStateDrawableMethod = aClass.getDeclaredMethod("getStateDrawable", int.class);
//                int count = (Integer) getStateCountMethod.invoke(stateListDrawable, null);
//
//                for (int i = 0; i < count; i++) {
//                    int[] stateSet = (int[]) getStateSetMethod.invoke(stateListDrawable, i);
//                    Drawable stateDrawable = (Drawable) getStateDrawableMethod.invoke(stateListDrawable, i);
//                    Log.e("info2", "i:" + i + "," + (stateDrawable == null));
//                    if (stateDrawable instanceof GradientDrawable) {
//                        Log.e("info2", "GradientDrawable");
//                        GradientDrawable gradientDrawable = (GradientDrawable) stateDrawable;
//                        Field field = GradientDrawable.class.getDeclaredField("mFillPaint");
//                        field.setAccessible(true);
//                        Paint paint = (Paint) field.get(gradientDrawable);
//                        Log.e("info3", "paint:" + paint.getColor());
//
//                        gradientDrawable.getConstantState();
//
////                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
////                            int color = gradientDrawable.getColor().getColorForState(new int[]{android.R.attr.state_pressed}, 0);
////                            Log.e("info2", "color:" + color);
////                        }
//////                            gradientDrawable.setColor(Color.YELLOW);
////                        Color.parseColor("#4d2196f3");
//////                            int color = gradientDrawable.get.getPaint().getColor();
//////                            Log.e("info3", "" + color);
//                        stateDrawable = gradientDrawable;
//                    }
//                    stateListDrawable.addState(stateSet, stateDrawable);
//                }
//                return stateListDrawable;
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            } catch (NoSuchFieldException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }

    private Drawable tileifyIndeterminate(Drawable drawable) {
        if (drawable instanceof AnimationDrawable) {
            AnimationDrawable background = (AnimationDrawable) drawable;
            final int N = background.getNumberOfFrames();
            AnimationDrawable newBg = new AnimationDrawable();
            newBg.setOneShot(background.isOneShot());

            for (int i = 0; i < N; i++) {
                Drawable frame = tileify(background.getFrame(i), true);
                frame.setLevel(10000);
                newBg.addFrame(frame, background.getDuration(i));
            }
            newBg.setLevel(10000);
            drawable = newBg;
        }
        return drawable;
    }

    private Drawable tileify(Drawable drawable, boolean clip) {
        if (SkinCompatVersionUtils.isV4WrappedDrawable(drawable)) {
            Drawable inner = SkinCompatVersionUtils.getV4WrappedDrawableWrappedDrawable(drawable);
            if (inner != null) {
                inner = tileify(inner, clip);
                SkinCompatVersionUtils.setV4WrappedDrawableWrappedDrawable(drawable, inner);
            }
        } else if (SkinCompatVersionUtils.isV4DrawableWrapper(drawable)) {
            Drawable inner = SkinCompatVersionUtils.getV4DrawableWrapperWrappedDrawable(drawable);
            if (inner != null) {
                inner = tileify(inner, clip);
                SkinCompatVersionUtils.setV4DrawableWrapperWrappedDrawable(drawable, inner);
            }
        } else if (drawable instanceof LayerDrawable) {
            LayerDrawable background = (LayerDrawable) drawable;
            final int N = background.getNumberOfLayers();
            Drawable[] outDrawables = new Drawable[N];

            for (int i = 0; i < N; i++) {
                int id = background.getId(i);
                outDrawables[i] = tileify(background.getDrawable(i),
                        (id == android.R.id.progress || id == android.R.id.secondaryProgress));
            }
            LayerDrawable newBg = new LayerDrawable(outDrawables);

            for (int i = 0; i < N; i++) {
                newBg.setId(i, background.getId(i));
            }

            return newBg;

        } else if (drawable instanceof BitmapDrawable) {
            final BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            final Bitmap tileBitmap = bitmapDrawable.getBitmap();
            final ShapeDrawable shapeDrawable = new ShapeDrawable(getDrawableShape());
            final BitmapShader bitmapShader = new BitmapShader(tileBitmap,
                    Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
            shapeDrawable.getPaint().setShader(bitmapShader);
            shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
            return (clip) ? new ClipDrawable(shapeDrawable, Gravity.LEFT,
                    ClipDrawable.HORIZONTAL) : shapeDrawable;
        }

        return drawable;
    }

    private Shape getDrawableShape() {
        final float[] roundedCorners = new float[]{5, 5, 5, 5, 5, 5, 5, 5};
        return new RoundRectShape(roundedCorners, null, null);
    }
}