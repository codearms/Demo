if (VERSION.SDK_INT >= 21) {
    view.setBackgroundTintList(tintList);
    if (VERSION.SDK_INT == 21) {
        Drawable background = view.getBackground();
        boolean hasTint = view.getBackgroundTintList() != null || view.getBackgroundTintMode() != null;
        if (background != null && hasTint) {
            if (background.isStateful()) {
                background.setState(view.getDrawableState());
            }

            view.setBackground(background);
        }
    }
} else if (view instanceof TintableBackgroundView) {
    ((TintableBackgroundView)view).setSupportBackgroundTintList(tintList);
}


 PopupWindow popupBackground
 * @see android.widget.AutoCompleteTextView
 * @see android.widget.Spinner


TabLayout background  可以使用 app 但是无效
tab 开头属性名称 只能用 app 有效


ScrimInsetsFrameLayout insetForeground 只能用 app 有效
NavigationView background  可以使用 app 但是无效 其它 只能用 app 有效
itemBackground  可以使用 app  有效  但是 android 无效  background 和 itemBackground 效果有区别
itemTextAppearance android 无效  可以使用 app  有效
其它 只能用 app 有效

BottomNavigationView background  可以使用 app 但是无效
itemBackground  可以使用 app  有效  但是 android 无效  background 和 itemBackground 效果有区别
其它 只能用 app 有效


CoordinatorLayout  statusBarBackground 只能用 app 有效

CollapsingToolbarLayout  只能用 app 有效


BaselineLayout background  可以使用 app 但是无效


ForegroundLinearLayout background backgroundTint 可以使用 app 但是无效 其它用 android

CheckableImageButton




// todo 注意这四个方法的区别
// view.setCompoundDrawables();
// view.setCompoundDrawablesRelative();
// view.setCompoundDrawablesWithIntrinsicBounds();
// view.setCompoundDrawablesRelativeWithIntrinsicBounds();