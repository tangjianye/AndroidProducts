package cn.aorise.petition.ui.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import cn.aorise.petition.R;
import cn.aorise.petition.databinding.PetitionActivityRequestBinding;
import cn.aorise.petition.ui.base.PetitionBaseActivity;

/**
 * Created by Administrator on 2017/4/21.
 */

public class RequestActivity extends PetitionBaseActivity implements View.OnClickListener{
    private PetitionActivityRequestBinding mbinding;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    public static Activity instance=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance=this;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (R.id.rl_address==id) {
            openActivity(RequestAddressChooseActivity.class);
        } else if (R.id.rl_type==id) {
            openActivity(RequestAddQuestActivity.class);
        } else if (R.id.rl_next==id) {
            if (sp.getString(getString(R.string.petition_sharepre_SFD_SH),"").equals("")||
                    sp.getString(getString(R.string.petition_sharepre_LXY),"").equals("")){
                showToast(getString(R.string.petition_toast_01));
            }else {
                Save();
                openActivity(RequestSubmitActivity.class);
            }
        }
    }

    @Override
    protected void initData() {
        sp=getSharedPreferences(getString(R.string.petition_sharepre_nomal),MODE_PRIVATE);
        editor=sp.edit();
    }

    @Override
    protected void initView() {
        mbinding= DataBindingUtil.setContentView(this, R.layout.petition_activity_request);
        mbinding.rlNext.setOnClickListener(this);
        mbinding.rlAddress.setOnClickListener(this);
        mbinding.rlType.setOnClickListener(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        /*设置返回的地点消息和问题类型*/
        mbinding.txtType.setText(sp.getString(getString(R.string.petition_sharepre_LXY),"")
                +"-"+sp.getString(getString(R.string.petition_sharepre_LXE),"")
                +"-"+sp.getString(getString(R.string.petition_sharepre_LXS),""));
        mbinding.txtAddress.setText(sp.getString(getString(R.string.petition_sharepre_SFD_SH),"")
        +"-"+sp.getString(getString(R.string.petition_sharepre_SFD_S),"")
        +"-"+sp.getString(getString(R.string.petition_sharepre_SFD_X),""));
        setcheckbox();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        editor.clear().commit();
    }
    private void Save(){        /*保存五个checkbox的信息*/
        if (mbinding.ckb1.isChecked()==true) {
            editor.putString(getString(R.string.petition_sharepre_SFFH),"1");
        }else {
            editor.putString(getString(R.string.petition_sharepre_SFFH),"0");
        }
        if (mbinding.ckb2.isChecked()==true) {
            editor.putString(getString(R.string.petition_sharepre_FYSFSL),"1");
        }else {
            editor.putString(getString(R.string.petition_sharepre_FYSFSL),"0");
        }
        if (mbinding.ckb3.isChecked()==true) {
            editor.putString(getString(R.string.petition_sharepre_SFXZFY),"1");
        }else {
            editor.putString(getString(R.string.petition_sharepre_SFXZFY),"0");
        }
        if (mbinding.ckb4.isChecked()==true) {
            editor.putString(getString(R.string.petition_sharepre_XZJGSFSL),"1");
        }else {
            editor.putString(getString(R.string.petition_sharepre_XZJGSFSL),"0");
        }
        if (mbinding.ckb5.isChecked()==true) {
            editor.putString(getString(R.string.petition_sharepre_SFGK),"1");
        }else {
            editor.putString(getString(R.string.petition_sharepre_SFGK),"0");
        }
        editor.commit();
    }
    private void setcheckbox() {
        if ((sp.getString(getString(R.string.petition_sharepre_SFFH),"")).equals("1")) {
            mbinding.ckb1.setChecked(true);
        } else  {
            mbinding.ckb1.setChecked(false);
        }
        if ((sp.getString(getString(R.string.petition_sharepre_FYSFSL),"")).equals("1")) {
            mbinding.ckb2.setChecked(true);
        } else  {
            mbinding.ckb2.setChecked(false);
        }
        if (sp.getString(getString(R.string.petition_sharepre_SFXZFY),"").equals("1")) {
            mbinding.ckb3.setChecked(true);
        } else  {
            mbinding.ckb3.setChecked(false);
        }
        if (sp.getString(getString(R.string.petition_sharepre_XZJGSFSL),"").equals("1")) {
            mbinding.ckb4.setChecked(true);
        } else  {
            mbinding.ckb4.setChecked(false);
        }
        if (sp.getString(getString(R.string.petition_sharepre_SFGK),"").equals("1")) {
            mbinding.ckb5.setChecked(true);
        } else  {
            mbinding.ckb5.setChecked(false);
        }
    }
}
