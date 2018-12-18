package com.codearms.maoqiqi.skin.attr;

import android.view.View;

import java.util.List;

/**
 * 定义支持更改皮肤的视图
 */
public class SkinSupportView {

    /**
     * 当前视图
     */
    private View view;

    /**
     * 当前视图支持更改皮肤的属性集合
     */
    private List<SkinSupportAttr> attrs;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public List<SkinSupportAttr> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<SkinSupportAttr> attrs) {
        this.attrs = attrs;
    }

    /**
     * 应用皮肤
     */
    public void applySkin() {
        if (view == null || attrs == null || attrs.size() == 0) return;
        for (SkinSupportAttr attr : attrs) {
            if (attr != null) {
                attr.apply(view);
            }
        }
    }

    /**
     * 清除数据
     */
    public void clear() {
        if (view != null) view = null;

        if (attrs != null && attrs.size() > 0) attrs.clear();
    }
}
