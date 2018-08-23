# ViewDrawProc
View绘制的demo  
第一种布局：  
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
