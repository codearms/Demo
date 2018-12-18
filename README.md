# AndroidSkinDemo

AndroidSkin is an Android multi theme library which supporting daily colorful theme.


## 目录

* [Android View继承关系图](#h_1)
* [Android View 所有可以换肤的属性说明(子类拥有父类所有属性)](#h_2)
* [Android Design Support Library](#h_3)

  * [Android Design Support Library v28 新增组件](#h_3_1)
  * [Android Design Support Library v28 Circular Reveal](#h_3_2)

* [Android View 所有可以换肤的属性限制说明和使用注意事项](#h_4)


<h2 id="h_1">Android View继承关系图</h2>

```
├─── View
│    ├─── TextView
│    │    ├─── android.support.v7.widget.AppCompatTextView
│    │    ├─── EditText
│    │    │    ├─── android.support.v7.widget.AppCompatEditText
│    │    │    │    ├─── android.support.design.widget.TextInputEditText
│    │    │    ├─── AutoCompleteTextView
│    │    │    │    ├─── android.support.v7.widget.AppCompatAutoCompleteTextView
│    │    │    │    ├─── MultiAutoCompleteTextView
│    │    │    │    │    ├─── android.support.v7.widget.AppCompatMultiAutoCompleteTextView
│    │    ├─── Button
│    │    │    ├─── android.support.v7.widget.AppCompatButton
│    │    │    │    ├─── android.support.design.button.MaterialButton
│    │    │    ├─── CompoundButton
│    │    │    │    ├─── RadioButton
│    │    │    │    │    ├─── android.support.v7.widget.AppCompatRadioButton
│    │    │    │    ├─── CheckBox
│    │    │    │    │    ├─── android.support.v7.widget.AppCompatCheckBox
│    │    │    │    │    │    ├─── android.support.design.chip.Chip
│    │    │    │    ├─── ToggleButton
│    │    │    │    ├─── Switch
│    │    │    │    ├─── android.support.v7.widget.SwitchCompat
│    │    ├─── CheckedTextView
│    │    │    ├─── android.support.v7.widget.AppCompatCheckedTextView
│    │    ├─── TextClock
│    ├─── ImageView
│    │    ├─── android.support.v7.widget.AppCompatImageView
│    │    ├─── ImageButton
│    │    │    ├─── android.support.v7.widget.AppCompatImageButton
│    │    │    │    ├─── android.support.design.widget.CheckableImageButton
│    │    │    ├─── android.support.design.widget.FloatingActionButton
│    ├─── ProgressBar
│    │    ├─── android.support.v4.widget.ContentLoadingProgressBar
│    │    ├─── RatingBar
│    │    │    ├─── android.support.v7.widget.AppCompatRatingBar
│    │    ├─── SeekBar
│    │    │    ├─── android.support.v7.widget.AppCompatSeekBar
│    ├─── ViewStub
│    ├─── android.support.v7.widget.ViewStubCompat
│    ├─── ViewGroup
│    │    ├─── LinearLayout
│    │    │    ├─── android.support.design.circularreveal.CircularRevealLinearLayout
│    │    │    ├─── TableLayout
│    │    │    ├─── TableRow
│    │    │    ├─── RadioGroup
│    │    │    ├─── NumberPicker
│    │    │    ├─── ZoomControls
│    │    │    ├─── android.support.design.widget.AppBarLayout
│    │    │    ├─── android.support.design.widget.TextInputLayout
│    │    ├─── android.support.v7.widget.LinearLayoutCompat
│    │    │    ├─── android.support.design.internal.ForegroundLinearLayout
│    │    │    ├─── SearchView
│    │    │    ├─── android.support.v7.widget.SearchView
│    │    ├─── RelativeLayout
│    │    │    ├─── android.support.design.circularreveal.CircularRevealRelativeLayout
│    │    ├─── FrameLayout
│    │    │    ├─── android.support.design.circularreveal.CircularRevealFrameLayout
│    │    │    │    ├─── android.support.design.transformation.TransformationChildLayout
│    │    │    ├─── ScrollView
│    │    │    │    ├─── HorizontalScrollView
│    │    │    │    │    ├─── android.support.design.widget.TabLayout
│    │    │    ├─── android.support.v4.widget.NestedScrollView
│    │    │    ├─── CalendarView
│    │    │    ├─── DatePicker
│    │    │    ├─── TimePicker
│    │    │    ├─── ViewAnimator
│    │    │    │    ├─── ViewSwitcher
│    │    │    │    │    ├─── TextSwitcher
│    │    │    │    │    ├─── ImageSwitcher
│    │    │    │    ├─── ViewFlipper
│    │    │    ├─── android.support.v7.widget.CardView
│    │    │    │    ├─── android.support.design.card.MaterialCardView
│    │    │    │    ├─── android.support.design.circularreveal.cardview.CircularRevealCardView
│    │    │    │    │    ├─── android.support.design.transformation.TransformationChildCard
│    │    │    ├─── android.support.design.widget.CollapsingToolbarLayout
│    │    │    ├─── android.support.design.internal.ScrimInsetsFrameLayout
│    │    │    │    ├─── android.support.design.widget.NavigationView
│    │    │    ├─── android.support.design.widget.BottomNavigationView
│    │    ├─── AdapterView
│    │    │    ├─── AdapterViewAnimator
│    │    │    │    ├─── StackView
│    │    │    │    ├─── AdapterViewFlipper
│    │    │    ├─── AbsSpinner
│    │    │    │    ├─── Spinner
│    │    │    │    │    ├─── android.support.v7.widget.AppCompatSpinner
│    │    │    │    ├─── Gallery
│    │    │    ├─── AbsListView
│    │    │    │    ├─── ListView
│    │    │    │    │    ├─── ExpandableListView
│    │    │    │    ├─── GridView
│    │    ├─── GridLayout
│    │    │    │    ├─── android.support.design.circularreveal.CircularRevealGridLayout
│    │    ├─── android.support.v4.widget.DrawerLayout
│    │    ├─── android.support.v4.widget.SlidingPaneLayout
│    │    ├─── android.support.v4.widget.SwipeRefreshLayout
│    │    ├─── android.support.v4.view.ViewPager
│    │    ├─── android.support.v4.view.PagerTitleStrip
│    │    │    ├─── android.support.v4.view.PagerTabStrip
│    │    ├─── Toolbar
│    │    ├─── android.support.v7.widget.Toolbar
│    │    │    ├─── android.support.design.bottomappbar.BottomAppBar
│    │    ├─── android.support.v7.widget.RecyclerView
│    │    ├─── android.support.design.widget.CoordinatorLayout
│    │    │    ├─── android.support.design.circularreveal.coordinatorlayout.CircularRevealCoordinatorLayout
│    │    ├─── android.support.design.internal.FlowLayout
│    │    │    ├─── android.support.design.chip.ChipGroup
│    │    ├─── android.support.design.internal.BaselineLayout
│    │    ├─── android.support.constraint.ConstraintLayout
```


<h2 id="h_2">Android View 所有可以换肤的属性说明(子类拥有父类所有属性)</h2>

<table>
    <tr>
        <td><b>Widget Name</b></td>
        <td><b>[android] [app] Skin Attributes</b></td>
        <td><b>Attributes Format</b></td>
    </tr>
    <tr>
        <td>　　　　　　　　　　　　　　　　　　　</td>
        <td>　　　　　　　　　　　　　　　　　　　　　</td>
        <td>　　　　　　　　　　　</td>
    </tr>
    <tr>
        <td>View</td>
        <td>
        [√][√] background<br>
        [√][√] backgroundTint<br>
        [√][X] foreground<br>
        [√][X] foregroundTint<br>
        [√][X] scrollbarThumbVertical<br>
        [√][X] scrollbarTrackVertical<br>
        [√][X] scrollbarThumbHorizontal<br>
        [√][X] scrollbarTrackHorizontal<br>
        </td>
        <td>
        reference|color<br>
        color<br>
        reference|color<br>
        color<br>
        reference<br>
        reference<br>
        reference<br>
        reference<br>
        </td>
    </tr>
    <tr>
        <td>TextView</td>
        <td>
        [√][X] textAppearance<br>
        [√][X] textColor<br>
        [√][X] textColorHint<br>
        [√][X] textColorLink<br>
        [√][X] textColorHighlight<br>
        [√][X] textCursorDrawable<br>
        [√][X] textSelectHandle<br>
        [√][X] textSelectHandleLeft<br>
        [√][X] textSelectHandleRight<br>
        [√][X] drawableLeft<br>
        [√][X] drawableTop<br>
        [√][X] drawableRight<br>
        [√][X] drawableBottom<br>
        [√][X] drawableStart<br>
        [√][X] drawableEnd<br>
        [√][X] drawableTint<br>
        </td>
        <td>
        reference<br>
        reference|color<br>
        reference|color<br>
        reference|color<br>
        reference|color<br>
        reference<br>
        reference<br>
        reference<br>
        reference<br>
        reference|color<br>
        reference|color<br>
        reference|color<br>
        reference|color<br>
        reference|color<br>
        reference|color<br>
        color<br>
        </td>
    </tr>
    <tr>
        <td>AppCompatTextView</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>EditText</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>AppCompatEditText</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>AutoCompleteTextView</td>
        <td>[√][√] popupTheme<br> [√][X] popupBackground<br> [√][X] dropDownSelector<br></td>
        <td>reference<br> reference|color<br> reference|color<br></td>
    </tr>
    <tr>
        <td>AppCompatAutoCompleteTextView</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>MultiAutoCompleteTextView</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>AppCompatMultiAutoCompleteTextView</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>Button</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>AppCompatButton</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>MaterialButton</td>
        <td>app:backgroundTint<br> app:icon<br> app:iconTint<br> app:strokeColor<br> app:rippleColor<br></td>
        <td>color<br> reference<br> color<br> color<br> color<br></td>
    </tr>
    <tr>
        <td>CompoundButton</td>
        <td>[√][X] button<br> [√][√] buttonTint<br></td>
        <td>reference<br> color<br></td>
    </tr>
    <tr>
        <td>RadioButton</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>AppCompatRadioButton</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>CheckBox</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>AppCompatCheckBox</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>Chip</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>ToggleButton</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>Switch</td>
        <td>[√][√] switchTextAppearance<br> [√][X] thumb<br> [√][√] thumbTint<br> [√][√] track<br> [√][√] trackTint<br></td>
        <td>reference<br> reference<br> color<br> reference<br> color<br></td>
    </tr>
    <tr>
        <td>SwitchCompat</td>
        <td>[√][√] switchTextAppearance<br> [√][X] thumb<br> [√][√] thumbTint<br> [√][√] track<br> [√][√] trackTint<br></td>
        <td>reference<br> reference<br> color<br> reference<br> color<br></td>
    </tr>
    <tr>
        <td>CheckedTextView</td>
        <td>[√][X] checkMark<br> [√][X] checkMarkTint<br></td>
        <td>reference<br> color</td>
    </tr>
    <tr>
        <td>AppCompatCheckedTextView</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>TextClock</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>ImageView</td>
        <td>[√][X] src<br> [X][√] srcCompat<br> [√][√] tint<br></td>
        <td>reference|color"<br> reference<br> color<br></td>
    </tr>
    <tr>
        <td>AppCompatImageView</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>ImageButton</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>AppCompatImageButton</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>CheckableImageButton</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>ProgressBar</td>
        <td>
        [√][X] progressDrawable<br>
        [√][X] indeterminateDrawable<br>
        [√][X] progressTint<br>
        [√][X] secondaryProgressTint<br>
        [√][X] progressBackgroundTint<br>
        [√][X] indeterminateTint<br>
        </td>
        <td>
        reference<br>
        reference<br>
        color<br>
        color<br>
        color<br>
        color<br>
        </td>
    </tr>
    <tr>
        <td>ContentLoadingProgressBar</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>RatingBar</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>AppCompatRatingBar</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>SeekBar</td>
        <td>[√][X] thumb<br> [√][√] thumbTint<br> [√][√] tickMark<br> [√][√] tickMarkTint<br></td>
        <td>reference<br> color<br> reference<br> color<br></td>
    </tr>
    <tr>
        <td>AppCompatSeekBar</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>ViewStub</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>ViewStubCompat</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>ViewGroup</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>LinearLayout</td>
        <td>[√][√] divider<br></td>
        <td>reference|color<br></td>
    </tr>
    <tr>
        <td>CircularRevealLinearLayout</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>TableLayout</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>TableRow</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>RadioGroup</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>NumberPicker</td>
        <td>[√][X] solidColor<br></td>
        <td>reference|color<br></td>
    </tr>
    <tr>
        <td>ZoomControls</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>LinearLayoutCompat</td>
        <td>[√][√] divider<br></td>
        <td>reference<br></td>
    </tr>
    <tr>
        <td>ForegroundLinearLayout</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>SearchView</td>
        <td>
        [√][√] closeIcon<br>
        [√][√] goIcon<br>
        [√][√] searchIcon<br>
        [√][√] searchHintIcon<br>
        [√][√] voiceIcon<br>
        [√][√] commitIcon<br>
        [√][√] queryBackground<br>
        [√][√] submitBackground<br>
        </td>
        <td>
        reference<br>
        reference<br>
        reference<br>
        reference<br>
        reference<br>
        reference<br>
        reference<br>
        reference<br>
        </td>
    </tr>
    <tr>
        <td>android.support.v7.widget.SearchView</td>
        <td>
        [√][√] closeIcon<br>
        [√][√] goIcon<br>
        [√][√] searchIcon<br>
        [√][√] searchHintIcon<br>
        [√][√] voiceIcon<br>
        [√][√] commitIcon<br>
        [√][√] queryBackground<br>
        [√][√] submitBackground<br>
        </td>
        <td>
        reference<br>
        reference<br>
        reference<br>
        reference<br>
        reference<br>
        reference<br>
        reference<br>
        reference<br>
        </td>
    </tr>
    <tr>
        <td>RelativeLayout</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>CircularRevealRelativeLayout</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>FrameLayout</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>CircularRevealFrameLayout</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>ScrollView</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>HorizontalScrollView</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>NestedScrollView</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>ViewAnimator</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>ViewSwitcher</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>TextSwitcher</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>ImageSwitcher</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>ViewFlipper</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>CalendarView</td>
        <td>[√][X] weekDayTextAppearance<br> [√][X] dateTextAppearance<br></td>
        <td>reference<br> reference<br></td>
    </tr>
    <tr>
        <td>DatePicker</td>
        <td>[√][X] headerBackground<br> [√][X] calendarTextColor<br></td>
        <td>reference|color<br> color<br></td>
    </tr>
    <tr>
        <td>TimePicker</td>
        <td>
        [√][X] headerBackground<br>
        [√][X] numbersBackgroundColor<br>
        [√][X] numbersInnerTextColor<br>
        [√][X] numbersTextColor<br>
        [√][X] numbersSelectorColor<br>
        </td>
        <td>
        reference|color<br>
        color<br>
        color<br>
        color<br>
        color<br>
        </td>
    </tr>
    <tr>
        <td>CardView</td>
        <td>[X][√] cardBackgroundColor</td>
        <td>color</td>
    </tr>
    <tr>
        <td>MaterialCardView</td>
        <td>[X][√] strokeColor</td>
        <td>color</td>
    </tr>
    <tr>
        <td>CircularRevealCardView</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>TransformationChildCard</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>AdapterView</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>AbsSpinner</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>Spinner</td>
        <td>[√][√] popupTheme<br> [√][X] popupBackground<br> [√][X] dropDownSelector<br></td>
        <td>reference<br> reference|color<br> reference<br></td>
    </tr>
    <tr>
        <td>AppCompatSpinner</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>Gallery</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>AbsListView</td>
        <td>[√][√] listSelector<br></td>
        <td>reference|color</td>
    </tr>
    <tr>
        <td>ListView</td>
        <td>[√][√] divider<br></td>
        <td>reference|color</td>
    </tr>
    <tr>
        <td>ExpandableListView</td>
        <td>[√][X] groupIndicator<br> [√][X] childIndicator<br> [√][X] childDivider<br></td>
        <td>reference<br> reference<br> reference|color<br></td>
    </tr>
    <tr>
        <td>GridView</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>AdapterViewAnimator</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>StackView</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>AdapterViewFlipper</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>GridLayout</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>CircularRevealGridLayout</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>Toolbar</td>
        <td>
        [√][√] titleTextAppearance<br>
        [√][√] subtitleTextAppearance<br>
        [√][√] titleTextColor<br>
        [√][√] subtitleTextColor<br>
        [√][√] logo<br>
        [√][√] navigationIcon<br>
        [√][√] collapseIcon<br>
        [√][√] popupTheme<br>
        </td>
        <td>
        reference<br>
        reference<br>
        color<br>
        color<br>
        reference<br>
        reference<br>
        reference<br>
        reference<br>
        </td>
    </tr>
    <tr>
        <td>android.support.v7.widget.Toolbar</td>
        <td>
        [√][√] titleTextAppearance<br>
        [√][√] subtitleTextAppearance<br>
        [√][√] titleTextColor<br>
        [√][√] subtitleTextColor<br>
        [√][√] logo<br>
        [√][√] navigationIcon<br>
        [√][√] collapseIcon<br>
        [√][√] popupTheme<br>
        </td>
        <td>
        reference<br>
        reference<br>
        color<br>
        color<br>
        reference<br>
        reference<br>
        reference<br>
        reference<br>
        </td>
    </tr>
    <tr>
        <td>BottomAppBar</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>DrawerLayout</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>SlidingPaneLayout</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>SwipeRefreshLayout</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>ViewPager</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>PagerTitleStrip</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>PagerTabStrip</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>CircularRevealCoordinatorLayout</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>RecyclerView</td>
        <td>
        [X][√] fastScrollHorizontalThumbDrawable<br>
        [X][√] fastScrollHorizontalTrackDrawable<br>
        [X][√] fastScrollVerticalThumbDrawable<br>
        [X][√] fastScrollVerticalTrackDrawable<br>
        </td>
        <td>
        reference<br>
        reference<br>
        reference<br>
        reference<br>
        </td>
    </tr>
    <tr>
        <td>FlowLayout</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>ChipGroup</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>BaselineLayout</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>ConstraintLayout</td>
        <td></td>
        <td></td>
    </tr>
</table>


<h2 id="h_3">Android Design Support Library</h3>

Design Support Library 是 Google 在2015年的IO大会上发布的全新 Material Design 支持库，在这个 support 库里面主要包含了一下几个新的 Material Design 组件。

<table>
    <tr>
        <td><b>Widget Name</b></td>
        <td><b>[android] [app] Skin Attributes</b></td>
        <td><b>Attributes Format</b></td>
    </tr>
    <tr>
        <td>　　　　　　　　　　　　　　　　　　　</td>
        <td>　　　　　　　　　　　　　　　　　　　　　</td>
        <td>　　　　　　　　　　　</td>
    </tr>
    <tr>
        <td>TextInputLayout</td>
        <td>
        [√][X] textColorHint<br>
        [X][√] hintTextAppearance<br>
        [X][√] errorTextAppearance<br>
        [X][√] counterTextAppearance<br>
        [X][√] counterOverflowTextAppearance<br>
        [X][√] passwordToggleDrawable<br>
        [X][√] passwordToggleTint<br>
        </td>
        <td>
        reference|color<br>
        reference<br>
        reference<br>
        reference<br>
        reference<br>
        reference<br>
        color<br>
        </td>
    </tr>
    <tr>
        <td>TextInputEditText</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>ScrimInsetsFrameLayout</td>
        <td>[X][√] insetForeground<br></td>
        <td>reference|color<br></td>
    </tr>
    <tr>
        <td>NavigationView</td>
        <td>[√][√] itemTextAppearance<br> [√][√] itemBackground<br> [X][√] itemIconTint<br> [X][√] itemTextColor<br></td>
        <td>reference<br> reference<br> color<br> color<br></td>
    </tr>
    <tr>
        <td>BottomNavigationView</td>
        <td>[√][√] itemBackground<br> [X][√] itemIconTint<br> [X][√] itemTextColor<br></td>
        <td>reference<br> color<br> color<br></td>
    </tr>
    <tr>
        <td>CoordinatorLayout</td>
        <td>[√][√] statusBarBackground<br></td>
        <td></td>
    </tr>
    <tr>
        <td>AppBarLayout</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>CollapsingToolbarLayout</td>
        <td>
        [X][√] expandedTitleTextAppearance<br>
        [X][√] collapsedTitleTextAppearance<br>
        [X][√] contentScrim<br>
        [X][√] statusBarScrim<br>
        </td>
        <td>
        reference<br>
        reference<br>
        color<br>
        color<br>
        </td>
    </tr>
    <tr>
        <td>TabLayout</td>
        <td>
        [X][√] tabBackground<br>
        [X][√] tabIndicatorColor<br>
        [X][√] tabTextAppearance<br>
        [X][√] tabTextColor<br>
        [X][√] tabSelectedTextColor<br>
        </td>
        <td>
        reference<br>
        color<br>
        reference<br>
        color<br>
        color<br>
        </td>
    </tr>
    <tr>
        <td>FloatingActionButton</td>
        <td>[X][√] rippleColor<br></td>
        <td>color<br></td>
    </tr>
</table>


<h3 id="h_3_1"> Android Design Support Library v28 新增组件</h3>

Google 在近期发布了最新的 Design Support Library 28.0.0 版本，其中新增了一些非常实用的组件。

<table>
    <tr>
        <td><b>Widget Name</b></td>
        <td><b>[android] [app] Skin Attributes</b></td>
        <td><b>Attributes Format</b></td>
    </tr>
    <tr>
        <td>　　　　　　　　　　　　　　　　　　　</td>
        <td>　　　　　　　　　　　　　　　　　　　　　</td>
        <td>　　　　　　　　　　　</td>
    </tr>
    <tr>
        <td>MaterialButton</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>ChipGroup</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>Chip</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>MaterialCardView</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>BottomAppBar</td>
        <td></td>
        <td></td>
    </tr>
</table>

<h3 id="h_3_2"> Android Design Support Library v28 Circular Reveal</h3>

<table>
    <tr>
        <td><b>Widget Name</b></td>
        <td><b>[android] [app] Skin Attributes</b></td>
        <td><b>Attributes Format</b></td>
    </tr>
    <tr>
        <td>　　　　　　　　　　　　　　　　　　　</td>
        <td>　　　　　　　　　　　　　　　　　　　　　</td>
        <td>　　　　　　　　　　　</td>
    </tr>
    <tr>
        <td>CircularRevealLinearLayout</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>CircularRevealRelativeLayout</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>CircularRevealFrameLayout</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>CircularRevealGridLayout</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>CircularRevealCardView</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>CircularRevealCoordinatorLayout</td>
        <td></td>
        <td></td>
    </tr>
</table>


<h2 id="h_4">Android View 所有可以换肤的属性限制说明(最低 SDK Version = 14)和使用注意事项</h2>

<table>
    <tr>
        <td><b>Widget Name</b></td>
        <td><b>[android] [app] Skin Attributes</b></td>
        <td><b>Attributes Description</b></td>
    </tr>
    <tr>
        <td>　　　　　　　　　　　　</td>
        <td>　　　　　　　　　　　　　　　　　</td>
        <td>　　　　　　　　　　　　　　　　　　　　　　</td>
    </tr>
    <tr>
        <td rowspan="2">View</td>
        <td>
        [√][√] background<br>
        [√][√] backgroundTint<br>
        [√][X] foreground<br>
        [√][X] foregroundTint<br>
        </td>
        <td>
        [√][X] <br>
        [√][X][5.0]<br>
        在6.0才支持,之前只支持 FrameLayout<br>
        [5.0] 在5.0只支持 FrameLayout,其它6.0才支持<br>
        </td>
    </tr>
    <tr>
        <td colspan="2">
        AppCompatTextView, AppCompatEditText...所有实现 TintableBackgroundView 接口的类中 app:backgroundTint 有效;
        app:backgroundTint > android:backgroundTint
        </td>
    </tr>
    <tr>
        <td>TextView</td>
        <td>[√][X] drawableTint</td>
        <td>[6.0] drawableStart和drawableEnd在7.0才有效</td>
    </tr>
    <tr>
        <td>AutoCompleteTextView</td>
        <td>[√][√] popupTheme</td>
        <td>[X][X][5.0]</td>
    </tr>
    <tr>
        <td rowspan="2">CompoundButton</td>
        <td>[√][√] buttonTint</td>
        <td>[√][X][5.0]</td>
    </tr>
    <tr>
        <td colspan="2">
        AppCompatRadioButton, AppCompatCheckBox...所有实现 TintableCompoundButton 接口的类中 app:buttonTint 有效, app:buttonTint > android:buttonTint;
        </td>
    </tr>
    <tr>
        <td>Switch</td>
        <td>
        [√][√] switchTextAppearance<br>
        [√][√] thumbTint<br>
        [√][√] track<br>
        [√][√] trackTint<br>
        </td>
        <td>
        [√][X]<br>
        [√][X][5.0] 在6.0才有效,没有指定thumb无效<br>
        [√][X]<br>
        [√][X][6.0] 没有 android:track 无效<br>
        </td>
    </tr>
    <tr>
        <td>SwitchCompat</td>
        <td>
        [√][√] switchTextAppearance<br>
        [√][√] thumbTint<br>
        [√][√] track<br>
        [√][√] trackTint<br>
        </td>
        <td>
        [X][√]<br>
        [X][√]<br>
        [X][√]<br>
        [X][√]<br>
        </td>
    </tr>
    <tr>
        <td>CheckedTextView</td>
        <td>[√][X] checkMarkTint</td>
        <td>[√][X][5.0] 在7.0才有效</td>
    </tr>
    <tr>
        <td rowspan="2">ImageView</td>
        <td>
        [X][√] srcCompat<br>
        [√][√] tint<br>
        </td>
        <td>
        [X][X]<br>
        [√][X]<br>
        </td>
    </tr>
    <tr>
        <td colspan="2">
        AppCompatImageView 及其子类 app:srcCompat 有效, android:src > app:srcCompat;
        AppCompatImageView, AppCompatImageButton...所有实现 TintableImageSourceView 接口的类中 app:tint 有效, app:tint > android:tint;
        </td>
    </tr>
    <tr>
        <td>ProgressBar</td>
        <td>
        [√][X] progressTint<br>
        [√][X] secondaryProgressTint<br>
        [√][X] progressBackgroundTint<br>
        [√][X] indeterminateTint<br>
        </td>
        <td>
        [√][X][5.0]<br>
        [√][X][5.0]<br>
        [√][X][5.0]<br>
        [√][X][5.0]<br>
        </td>
    </tr>
    <tr>
        <td rowspan="2">SeekBar</td>
        <td>
        [√][√] thumbTint<br>
        [√][√] tickMark<br>
        [√][√] tickMarkTint<br>
        </td>
        <td>
        [√][X][5.0]<br>
        [√][X][7.0]<br>
        [√][X][7.0]<br>
        </td>
    </tr>
    <tr>
        <td colspan="2">
        AppCompatSeekBar 中 app:tickMark 和 app:tickMarkTint 有效;
        android:tickMark 和 app:tickMark 效果并存,android:tickMark 在 thumb 下面, app:tickMark 在 thumb 上面;
        android:tickMarkTint 着色 android:tickMark, app:tickMarkTint 着色 app:tickMark
        </td>
    </tr>
    <tr>
        <td>LinearLayout</td>
        <td>[√][√] divider<br></td>
        <td>[√][X] orientation="horizontal" 时 divider 无效<br></td>
    </tr>
    <tr>
        <td>NumberPicker</td>
        <td>[√][X] solidColor<br></td>
        <td></td>
    </tr>
    <tr>
        <td>SearchView</td>
        <td>
        [√][√] closeIcon<br>
        [√][√] goIcon<br>
        [√][√] searchIcon<br>
        [√][√] searchHintIcon<br>
        [√][√] voiceIcon<br>
        [√][√] commitIcon<br>
        [√][√] queryBackground<br>
        [√][√] submitBackground<br>
        </td>
        <td>
        [√][X][5.0] <br>
        [√][X][5.0] <br>
        [√][X][5.0] <br>
        [√][X][5.1] <br>
        [√][X][5.0] <br>
        [√][X][5.0] <br>
        [√][X][5.0] <br>
        [√][X][5.0] <br>
        </td>
    </tr>
    <tr>
        <td>SearchView(v7)</td>
        <td>
        [√][√] closeIcon<br>
        [√][√] goIcon<br>
        [√][√] searchIcon<br>
        [√][√] searchHintIcon<br>
        [√][√] voiceIcon<br>
        [√][√] commitIcon<br>
        [√][√] queryBackground<br>
        [√][√] submitBackground<br>
        </td>
        <td>
        [X][√] <br>
        [X][√] <br>
        [X][√] <br>
        [X][√] <br>
        [X][√] <br>
        [X][√] <br>
        [X][√] <br>
        [X][√] <br>
        </td>
    </tr>
    <tr>
        <td>LinearLayoutCompat</td>
        <td>[√][√] divider<br></td>
        <td>[X][√] orientation="horizontal" 时 divider 无效<br></td>
    </tr>
    <tr>
        <td>DatePicker</td>
        <td>
        [√][X] headerBackground<br>
        [√][X] calendarTextColor<br>
        </td>
        <td>
        [√][X][5.0] <br>
        [√][X][5.0] <br>
        </td>
    </tr>
    <tr>
        <td>TimePicker</td>
        <td>
        [√][X] numbersBackgroundColor<br>
        [√][X] numbersInnerTextColor<br>
        [√][X] numbersTextColor<br>
        [√][X] numbersSelectorColor<br>
        </td>
        <td>
        [√][X][5.0] <br>
        [√][X][6.0] <br>
        [√][X][5.0] <br>
        [√][X][5.0] <br>
        </td>
    </tr>
    <tr>
        <td>CardView</td>
        <td>[X][√] cardBackgroundColor</td>
        <td>[X][√] 注意: background 无效</td>
    </tr>
    <tr>
        <td>Spinner</td>
        <td>
        [√][√] popupTheme<br>
        [√][X] popupBackground<br>
        [√][X] dropDownSelector<br>
        </td>
        <td>
        [X][X][5.0]<br>
        [√][X]<br>
        [X][X]<br>
        </td>
    </tr>
    <tr>
        <td>ListView</td>
        <td>[√][√] divider<br></td>
        <td>[√][X]<br></td>
    </tr>
    <tr>
        <td>Toolbar</td>
        <td>
        [√][√] titleTextAppearance<br>
        [√][√] subtitleTextAppearance<br>
        [√][√] titleTextColor<br>
        [√][√] subtitleTextColor<br>
        [√][√] logo<br>
        [√][√] navigationIcon<br>
        [√][√] collapseIcon<br>
        [√][√] popupTheme<br>
        </td>
        <td>
        [√][X]<br>
        [√][X]<br>
        [√][X][6.0]<br>
        [√][X][6.0]<br>
        [√][X]<br>
        [√][X]<br>
        [√][X][7.0]<br>
        [√][X]<br>
        </td>
    </tr>
    <tr>
        <td>Toolbar(v7)</td>
        <td>
        [√][√] titleTextAppearance<br>
        [√][√] subtitleTextAppearance<br>
        [√][√] titleTextColor<br>
        [√][√] subtitleTextColor<br>
        [√][√] logo<br>
        [√][√] navigationIcon<br>
        [√][√] collapseIcon<br>
        [√][√] popupTheme<br>
        </td>
        <td>
        [X][√]<br>
        [X][√]<br>
        [X][√]<br>
        [X][√]<br>
        [X][√]<br>
        [X][√]<br>
        [X][√]<br>
        [X][√]<br>
        </td>
    </tr>
    <tr>
        <td>RecyclerView</td>
        <td colspan="2">只支持自定义命名空间</td>
    </tr>
    <tr>
        <td>FloatingActionButton</td>
        <td>
        [√][√] background<br>
        [√][√] backgroundTint<br>
        [X][√] rippleColor<br>
        </td>
        <td>
        [X][X] <br>
        [√][√] android:backgroundTint 边框有问题<br>
        [X][√] <br>
        </td>
    </tr>
    <tr>
        <td>ScrimInsetsFrameLayout</td>
        <td colspan="2">RestrictedApi</td>
    </tr>
    <tr>
        <td>NavigationView</td>
        <td>
        [√][√] itemTextAppearance<br>
        [√][√] itemBackground<br>
        [X][√] itemIconTint<br>
        [X][√] itemTextColor<br>
        </td>
        <td>
        [X][√] <br>
        [X][√] <br>
        [X][√] <br>
        [X][√] <br>
        </td>
    </tr>
    <tr>
        <td>BottomNavigationView</td>
        <td>
        [√][√] itemBackground<br>
        [X][√] itemIconTint<br>
        [X][√] itemTextColor<br>
        </td>
        <td>
        [X][√] <br>
        [X][√] <br>
        [X][√] <br>
        </td>
    </tr>
    <tr>
        <td>CoordinatorLayout</td>
        <td>[√][√] statusBarBackground<br></td>
        <td>[X][√]</td>
    </tr>
    <tr>
        <td>CollapsingToolbarLayout</td>
        <td colspan="2">只支持自定义命名空间</td>
    </tr>
    <tr>
        <td>BaselineLayout</td>
        <td colspan="2">RestrictedApi</td>
    </tr>
    <tr>
        <td>ForegroundLinearLayout</td>
        <td colspan="2">RestrictedApi, LinearLayoutCompat 子类, LOLLIPOP 之前版本也支持 foreground</td>
    </tr>
    <tr>
        <td>CheckableImageButton</td>
        <td colspan="2">RestrictedApi, AppCompatImageView 子类</td>
    </tr>
</table>
