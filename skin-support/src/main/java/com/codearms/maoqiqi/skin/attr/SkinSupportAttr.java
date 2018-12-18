package com.codearms.maoqiqi.skin.attr;

import android.view.View;

import com.codearms.maoqiqi.skin.constant.ResourcesConstant;

public abstract class SkinSupportAttr {

    /**
     * 属性名称
     */
    private String attrName;

    /**
     * 资源id
     */
    private int resId;

    /**
     * 资源名称
     */
    private String entryName;

    /**
     * 资源类型名称
     */
    private String resTypeName;

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public String getResTypeName() {
        return resTypeName;
    }

    public void setResTypeName(String resTypeName) {
        this.resTypeName = resTypeName;
    }

    /**
     * 资源类型名称是否为style
     *
     * @return
     */
    protected boolean isStyle() {
        return ResourcesConstant.RES_TYPE_NAME_STYLE.equals(resTypeName);
    }

    /**
     * 资源类型名称是否为color
     *
     * @return
     */
    protected boolean isColor() {
        return ResourcesConstant.RES_TYPE_NAME_COLOR.equals(resTypeName);
    }

    /**
     * 资源类型名称是否为drawable
     *
     * @return
     */
    protected boolean isDrawable() {
        return ResourcesConstant.RES_TYPE_NAME_DRAWABLE.equals(resTypeName)
                || ResourcesConstant.RES_TYPE_NAME_MIPMAP.equals(resTypeName);
    }

    /**
     * 需要实现的方法,根据属性名称、资源id、资源类型名称 设置属性
     *
     * @param view 支持更改皮肤的视图
     */
    public abstract void apply(View view);
}