# ViewDrawProc
View绘制的demo  
第一种布局：  
 ```
<pfg.com.viewdrawproc.MyLinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    tools:context="pfg.com.viewdrawproc.MainActivity">
    <pfg.com.viewdrawproc.ProfilePhoto
        android:layout_width="40dp"
        android:layout_height="40dp"
        android:background="@drawable/photo"/>
    <pfg.com.viewdrawproc.MyLinearLayout
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:orientation="vertical">
        <pfg.com.viewdrawproc.Title
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Shock Spock"
            android:textSize="20dp"/>
        <pfg.com.viewdrawproc.SubTitle
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="2rh, shanghai"
            android:textSize="12dp"/>
    </pfg.com.viewdrawproc.MyLinearLayout>
    <pfg.com.viewdrawproc.Menu
        android:layout_width="20dp"
        android:layout_height="20dp"
        android:background="@drawable/menu"/>
</pfg.com.viewdrawproc.MyLinearLayout>
 ```
 上述布局在华硕手机上的绘制过程：
  ```
   >ProfilePhoto:            [width: 80   exactly,      height: 80   exactly]
      >Title:                [width: 720  unspecified,  height: 880  unspecified]
      >SubTitle:             [width: 720  unspecified,  height: 826  unspecified]
      >Title:                [width: 230  exactly,      height: 54   exactly]
      >SubTitle:             [width: 230  exactly,      height: 33   exactly]
   >LinearLayout [vertical]: [width: 720  unspecified,  height: 880  unspecified]
   >Menu:                    [width: 40   exactly,      height: 40   exactly]
      >Title:                [width: 600  exactly,      height: 880  at_most]
      >SubTitle:             [width: 600  exactly,      height: 826  at_most]
   >LinearLayout [vertical]: [width: 600  exactly,      height: 880  at_most]
LinearLayout [horizontal]:   [width: 720  exactly,      height: 880  at_most]

下面又重复一次

   >ProfilePhoto:            [width: 80   exactly,      height: 80    exactly]
      >Title:                [width: 720  unspecified,  height: 1024  unspecified]
      >SubTitle:             [width: 720  unspecified,  height: 970   unspecified]
      >Title:                [width: 230  exactly,      height: 54    exactly]
      >SubTitle:             [width: 230  exactly,      height: 33    exactly]
   >LinearLayout [vertical]: [width: 720  unspecified,  height: 1024  unspecified]
   >Menu:                    [width: 40   exactly,      height: 40    exactly]
      >Title:                [width: 600  exactly,      height: 1024  at_most]
      >SubTitle:             [width: 600  exactly,      height: 970   at_most]
   >LinearLayout [vertical]: [width: 600  exactly,      height: 1024  at_most]
LinearLayout [horizontal]:   [width: 720  exactly,      height: 1024  at_most]

LinearLayout [horizontal]: onLayout
ProfilePhoto: onLayout
LinearLayout [vertical]: onLayout
Title: onLayout
SubTitle: onLayout
Menu: onLayout
ProfilePhoto: onDraw
Title: onDraw
SubTitle: onDraw
Menu: onDraw
为什么没有LinearLayout [horizontal]和LinearLayout [vertical]的onDraw？
1) ViewGroup默认情况下，会被设置成WILL_NOT_DRAW，这是从性能考虑，这样一来，onDraw就不会被调用了。

2) 如果我们要重要一个ViweGroup的onDraw方法，有两种方法：

      1, 在构造函数里面，给其设置一个颜色，如#00000000。

      2, 在构造函数里面，调用setWillNotDraw(false)，去掉其WILL_NOT_DRAW flag。
 ```

