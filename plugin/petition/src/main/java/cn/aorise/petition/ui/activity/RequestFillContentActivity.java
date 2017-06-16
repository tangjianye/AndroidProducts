package cn.aorise.petition.ui.activity;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import cn.aorise.petition.R;
import cn.aorise.petition.databinding.PetitionActivityFillContentBinding;
import cn.aorise.petition.ui.base.PetitionBaseActivity;

/**
 * Created by Administrator on 2017/4/26.
 */

public class RequestFillContentActivity extends PetitionBaseActivity implements View.OnClickListener {
    private PetitionActivityFillContentBinding mBinding;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String myId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.rl_submit) {
            editor.putString(getString(R.string.petition_shareprefers_request_content),mBinding.editText.getText().toString());
            editor.commit();
            this.finish();
        }
    }

    @Override
    protected void initData() {
        sp=getSharedPreferences(getString(R.string.petition_sharepre_nomal),MODE_PRIVATE);
        editor=sp.edit();
    }

    @Override
    protected void initView() {
        myId=getIntent().getStringExtra(getString(R.string.petition_activity_to_activity_number));
        mBinding= DataBindingUtil.setContentView(this, R.layout.petition_activity_fill_content);
        mBinding.rlSubmit.setOnClickListener(this);
        mBinding.editText.setText(sp.getString(getString(R.string.petition_shareprefers_request_content),""));
        if (myId.equals("1")) {
            mBinding.rlSubmit.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void initEvent() {

    }
}
