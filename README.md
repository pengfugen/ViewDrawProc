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
  LinearLayout [horizontal]: [width: 720  exactly,      height: 880  at_most]
   >ProfilePhoto:            [width: 80   exactly,      height: 80   exactly]
   >LinearLayout [vertical]: [width: 720  unspecified,  height: 880  unspecified]
      >Title:                [width: 720  unspecified,  height: 880  unspecified]
      >SubTitle:             [width: 720  unspecified,  height: 826  unspecified]
      >Title:                [width: 230  exactly,      height: 54   exactly]
      >SubTitle:             [width: 230  exactly,      height: 33   exactly]
   >Menu:                    [width: 40   exactly,      height: 40   exactly]
   >LinearLayout [vertical]: [width: 600  exactly,      height: 880  at_most]
      >Title:                [width: 600  exactly,      height: 880  at_most]
      >SubTitle:             [width: 600  exactly,      height: 826  at_most]
   


下面又重复一次measure

LinearLayout [horizontal]: [width: 720  exactly,      height: 1024  at_most]
   >ProfilePhoto:            [width: 80   exactly,      height: 80    exactly]
   >LinearLayout [vertical]: [width: 720  unspecified,  height: 1024  unspecified]
      >Title:                [width: 720  unspecified,  height: 1024  unspecified]
      >SubTitle:             [width: 720  unspecified,  height: 970   unspecified]
      >Title:                [width: 230  exactly,      height: 54    exactly]
      >SubTitle:             [width: 230  exactly,      height: 33    exactly]
   >Menu:                    [width: 40   exactly,      height: 40    exactly]
   >LinearLayout [vertical]: [width: 600  exactly,      height: 1024  at_most]
      >Title:                [width: 600  exactly,      height: 1024  at_most]
      >SubTitle:             [width: 600  exactly,      height: 970   at_most]

LinearLayout [horizontal]: onLayout
ProfilePhoto:              onLayout
LinearLayout [vertical]:   onLayout
Title:                     onLayout
SubTitle:                  onLayout
Menu:                      onLayout

ProfilePhoto: onDraw
Title:        onDraw
SubTitle:     onDraw
Menu:         onDraw
垂直的LinearLayout被测量了两次。第一次的时候，父LinearLayout要求以UNSPECIFIED spec的方式来测量。导致了垂直的LinearLayout也以这种方式测量它的  
子view.此时它在它们返回值的基础上以EXACTLY spec的方式测量它的子view，但是它还没有结束。一旦在测量ProfilePhoto和Menu之后，父布局知道可用于垂直  
的LinearLayout的尺寸大小。以AT_MOST height对Title 和 Subtitle测量之后导致了第二次传值。显然，每一个TextView (Title and Subtitle)被测量3次。  
第二次传值创建或者废弃Layouts，这些操作是昂贵的。如果想ViewGroup发挥更好的性能，首要的工作就是免去对TextViews的测量传值工作。  

为什么没有LinearLayout [horizontal]和LinearLayout [vertical]的onDraw？
1) ViewGroup默认情况下，会被设置成WILL_NOT_DRAW，这是从性能考虑，这样一来，onDraw就不会被调用了。

2) 如果我们要重要一个ViweGroup的onDraw方法，有两种方法：

      1, 在构造函数里面，给其设置一个颜色，如#00000000。

      2, 在构造函数里面，调用setWillNotDraw(false)，去掉其WILL_NOT_DRAW flag。
 ```

第三种布局(自定义View)：  
这个布局在华硕手机的绘制流程：
 ```
 ProfilePhotoLayout:   [width: 720  exactly,  height: 880   at_most]
  >ProfilePhoto:       [width: 80   exactly,  height: 80    exactly]
  >Menu:               [width: 40   exactly,  height: 40    exactly]
  >Title:              [width: 600  at_most,  height: 880   at_most]
  >SubTitle:           [width: 600  at_most,  height: 826   at_most]
又重复一次measure
ProfilePhotoLayout:   [width: 720  exactly,  height: 1024  at_most]
  >ProfilePhoto:       [width: 80   exactly,  height: 80    exactly]
  >Menu:               [width: 40   exactly,  height: 40    exactly]
  >Title:              [width: 600  at_most,  height: 1024  at_most]
  >SubTitle:           [width: 600  at_most,  height: 970   at_most]
  
ProfilePhoto: onLayout left:0,   top=0,  right=80,  bottom=80
Title:        onLayout left:490, top=0,  right=720, bottom=54
SubTitle:     onLayout left:0,   top=54, right=141, bottom=87
Menu:         onLayout left:680, top=47, right=720, bottom=87

ProfilePhoto: onDraw
Title:        onDraw
SubTitle:     onDraw
Menu:         onDraw
 ```
