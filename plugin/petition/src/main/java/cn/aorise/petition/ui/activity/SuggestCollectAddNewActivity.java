package cn.aorise.petition.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import cn.aorise.petition.R;
import cn.aorise.petition.databinding.PetitionActivitySuggestCollectAddNewBinding;
import cn.aorise.petition.ui.base.PetitionBaseActivity;

/**
 * Created by Administrator on 2017/4/28.
 */

public class SuggestCollectAddNewActivity extends PetitionBaseActivity implements View.OnClickListener{
    private PetitionActivitySuggestCollectAddNewBinding mBinding;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    public static Activity instance;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance=this;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (R.id.rl_next==id) {
            if (mBinding.txtAddress.getText().toString().equals("--")||mBinding.txtType.getText().toString().equals("")){
                showToast(getString(R.string.petition_toast_hint_01));
            } else {
                if (mBinding.ckb5.isChecked() == true) {
                    editor.putString(getString(R.string.petition_suggest_collect_SFGK), "1");
                } else {
                    editor.putString(getString(R.string.petition_suggest_collect_SFGK), "0");
                }
                Intent intent = new Intent(this, SuggestCollectAddAdjunctActivity.class);
                startActivity(intent);
            }
        } else if (R.id.rl_suggest_type==id) {
            Intent intent=new Intent(this,SuggestCollectTypeActivity.class);
            startActivity(intent);
        } else if (R.id.rl_content==id) {
            Intent intent=new Intent(this,SuggestCollectFillContentActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void initData() {
        sp=getSharedPreferences(getString(R.string.petition_sharepre_nomal),MODE_PRIVATE);
        editor=sp.edit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBinding.txtAddress.setText(sp.getString(getString(R.string.petition_suggest_collect_JYLXY),"")
        +"-"+sp.getString(getString(R.string.petition_suggest_collect_JYLXE),"")
        +"-"+sp.getString(getString(R.string.petition_suggest_collect_JYLXS),""));
        mBinding.txtType.setText(sp.getString(getString(R.string.petition_suggest_collect_fill_content),""));
    }

    @Override
    protected void initView() {
        mBinding= DataBindingUtil.setContentView(this, R.layout.petition_activity_suggest_collect_add_new);
        mBinding.rlNext.setOnClickListener(this);
        mBinding.rlSuggestType.setOnClickListener(this);
        mBinding.rlContent.setOnClickListener(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        editor.clear().commit();
    }
}
