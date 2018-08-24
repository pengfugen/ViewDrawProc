package pfg.com.viewdrawproc;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by fpeng3 on 2018/8/23.
 */

public class ProfilePhotoLayout extends RelativeLayout {

    private static final String TAG = "ProfilePhotoLayout";

    private ProfilePhoto mProfilePhoto;
    private Menu mMenu;
    private Title mTitle;
    private SubTitle mSubTitle;

    // 如何实例化mProfilePhoto等？: 在onFinishInflate时通过findViewById实例化。
    // 如何添加到ProfilePhotoLayout：通过XML配置

    public ProfilePhotoLayout(Context context) {
        super(context);
        //initViews();
    }

    public ProfilePhotoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //initViews();
    }

    public ProfilePhotoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //initViews();
    }

    public ProfilePhotoLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        //initViews();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //setWillNotDraw(false);
        initViews();
    }

    private void initViews() {
        mProfilePhoto = findViewById(R.id.profile_photo);
        mMenu = findViewById(R.id.menu);
        mTitle = findViewById(R.id.title);
        mSubTitle = findViewById(R.id.subtitle);
        // 为什么在构造方法中调用initViews得到的mProfilePhoto会为null？
        MyLog.logd(TAG, "initViews mProfilePhoto = "+mProfilePhoto);
        MyLog.logd(TAG, "initViews childviewcount:"+getChildCount());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // RelativeLayout选择子View进行的measure的原则是优先选择没有依赖的view进行。
        // 这里的ProfilePhoto和Menu都没有layout_toLeftOf等依赖，因此优选进行measure。
        // 但是onLayout过程还是会按顺序来的。
        String widthmode = SpecUtil.getModeString(MeasureSpec.getMode(widthMeasureSpec));
        String heightmode = SpecUtil.getModeString(MeasureSpec.getMode(heightMeasureSpec));
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        MyLog.logd(TAG, "[width: "+sizeWidth+"  "+widthmode+",  height: "+sizeHeight+"  "+heightmode+"]");
        // 1. Setup initial constraints.
        int widthConstraints = getPaddingLeft()+getPaddingRight();
        int heightContraints = getPaddingTop()+getPaddingBottom();
        int width = 0;
        int height = 0;
        //MyLog.logd(TAG, "onMeasure widthConstraints="+widthConstraints+", heightContraints="+heightContraints);

        // 2. Measure the ProfilePhoto
        measureChildWithMargins(mProfilePhoto, widthMeasureSpec, widthConstraints, heightMeasureSpec, heightContraints);

        // 3. Update the contraints
        widthConstraints += mProfilePhoto.getMeasuredWidth();
        width += mProfilePhoto.getMeasuredWidth();
        height = Math.max(mProfilePhoto.getMeasuredHeight(), height);

        //MyLog.logd(TAG, "onMeasure widthConstraints="+widthConstraints+", mProfilePhoto width="+width+" height="+height);
        // 4. Measure the Menu
        measureChildWithMargins(mMenu, widthMeasureSpec, widthConstraints, heightMeasureSpec, heightContraints);

        // 5. Update the contraints
        widthConstraints += mMenu.getMeasuredWidth();
        width += mMenu.getMeasuredWidth();
        height = Math.max(mMenu.getMeasuredHeight(), height);

        // 6. Prepare the vertical MeasureSpec.
        int verticalWidthMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec) - widthConstraints, MeasureSpec.getMode(widthMeasureSpec));
        int verticalHeightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec) - heightContraints, MeasureSpec.getMode(heightMeasureSpec));

        // 7. Measure the Title
        measureChildWithMargins(mTitle, verticalWidthMeasureSpec, 0, verticalHeightMeasureSpec, 0);

        // 8. Measure the Subtitle
        measureChildWithMargins(mSubTitle, verticalWidthMeasureSpec, 0, verticalHeightMeasureSpec, mTitle.getMeasuredHeight());

        // 9. Update the sizes.
        width += Math.max(mTitle.getMeasuredWidth(), mSubTitle.getMeasuredWidth());
        // 为什么height只需取最大值就可以而不是累加？
        // 你要看这个布局的是平行的，意思哪个View的height最大就用哪个的height。
        height = Math.max(mTitle.getMeasuredHeight() + mSubTitle.getMeasuredHeight(), height);

        // 10. Set the dimesion for this ViewGroup.
        setMeasuredDimension(resolveSize(width, widthMeasureSpec), resolveSize(height, heightMeasureSpec));

        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec, widthUsed + lp.leftMargin + lp.rightMargin, lp.width);

        int childHeightMeasureSpec = getChildMeasureSpec(parentHeightMeasureSpec, heightUsed + lp.bottomMargin + lp.topMargin, lp.height);

        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        MyLog.logd(TAG, "onLayout left:"+l+", top="+t+", right="+r+", bottom="+b);
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                MarginLayoutParams st =
                        (MarginLayoutParams) child.getLayoutParams();
                int measuredWidth = child.getMeasuredWidth();
                int measuredHeight = child.getMeasuredHeight();
                // 这几个值是相对父亲来说的吧？
                int leftMargin = st.leftMargin;
                int topMargin = st.topMargin;
                int rightMargin = st.rightMargin;
                int bottomMargin = st.bottomMargin;

                int cl = 0, ct = 0, cr = 0, cb = 0;

                switch (i) {
                    // ProfilePhoto
                    case 0:
                        cl = leftMargin;
                        ct = topMargin;
                        break;
                    // Title
                    case 1:
                        cl = mProfilePhoto.getMeasuredWidth() + leftMargin;
                        ct = topMargin;
                        break;
                    // SubTitle
                    case 2:
                        cl = mProfilePhoto.getMeasuredWidth() + leftMargin;
                        ct = mTitle.getMeasuredHeight() + topMargin;
                        break;
                    // Menu
                    case 3:
                        cl = getWidth() - (rightMargin + measuredWidth);
                        ct = topMargin;
                        break;
                }

                cr = cl + measuredWidth;
                cb = measuredHeight + ct;
                // 这里只用考虑相对于父ViewGroup的情况，而不用去考虑整个布局
                child.layout(cl, ct, cr, cb);
            }
        }
    }

    // for debug child view's onDraw
    @Override
    protected void dispatchDraw(Canvas canvas) {
        //MyLog.logd(TAG, "dispatchDraw");
        super.dispatchDraw(canvas);
    }

    // drawChild流程走了，为什么child view的onDraw没有走进去？
    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        //MyLog.logd(TAG, "drawChild child:"+child.getId());
        return super.drawChild(canvas, child, drawingTime);
    }
}
