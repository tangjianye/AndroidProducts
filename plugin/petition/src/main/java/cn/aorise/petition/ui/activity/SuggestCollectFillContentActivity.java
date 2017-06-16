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

public class SuggestCollectFillContentActivity extends PetitionBaseActivity implements View.OnClickListener {
    private PetitionActivityFillContentBinding mBinding;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==R.id.rl_submit) {
            editor.putString(getString(R.string.petition_suggest_collect_fill_content),mBinding.editText.getText().toString());
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
        mBinding= DataBindingUtil.setContentView(this, R.layout.petition_activity_fill_content);
        mBinding.rlSubmit.setOnClickListener(this);

    }

    @Override
    protected void initEvent() {

    }
}
