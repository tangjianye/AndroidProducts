package com.product.masktime.ui.view;

import android.content.Context;
import android.util.AttributeSet;

import com.facebook.drawee.view.SimpleDraweeView;
import com.product.common.utils.ScreenUtils;
import com.product.masktime.R;

/**
 * 正方形布局
 */
public class AdImageView extends SimpleDraweeView {

    public AdImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public AdImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public AdImageView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = ScreenUtils.getScreenWidth(getContext());
        int height = getContext().getResources().getDimensionPixelOffset(R.dimen.ad_image_view_height);
        setMeasuredDimension(width, height);
    }
}
