package cn.aorise.petition.ui.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

import cn.aorise.petition.R;
import cn.aorise.petition.databinding.PetitionActivityQueryEvaluateDetailAdjunctPictureBinding;
import cn.aorise.petition.ui.base.PetitionBaseActivity;

/**
 * Created by Administrator on 2017/5/23.
 */

public class QueryEvaluateDetailAdjunctPictureActivity extends PetitionBaseActivity implements View.OnClickListener{
    private String picturePath;
    private PetitionActivityQueryEvaluateDetailAdjunctPictureBinding mBinding;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBinding= DataBindingUtil.setContentView(this, R.layout.petition_activity_query_evaluate_detail_adjunct_picture);
        picturePath=getIntent().getStringExtra("path");
        System.out.println(picturePath);
        Glide.with(this).load("http://192.168.1.40:1111/XFWD/2017/05/12/4c6ba902e34b4d0ab7b8ff510eb8c4e3.jpg").asBitmap().into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                int imageWidth = resource.getWidth();
                int imageHeight = resource.getHeight();
                WindowManager manager = (WindowManager) QueryEvaluateDetailAdjunctPictureActivity.this
                        .getSystemService(Context.WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();
                int height = display.getWidth() * imageHeight / imageWidth;
                ViewGroup.LayoutParams para = mBinding.imgDouble.getLayoutParams();
                para.height = height;
                para.width = display.getWidth();
                mBinding.imgDouble.setImageBitmap(resource);
            }
        });
    }

    @Override
    protected void initEvent() {

    }
}
