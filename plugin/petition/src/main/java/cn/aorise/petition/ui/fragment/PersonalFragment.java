package cn.aorise.petition.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cn.aorise.petition.R;
import cn.aorise.petition.databinding.PetitionActivityMainBinding;
import cn.aorise.petition.databinding.PetitionFragmentPersonalBinding;
import cn.aorise.petition.ui.activity.LoginActivity;
import cn.aorise.petition.ui.activity.PersonalChangeInformationActivity;
import cn.aorise.petition.ui.activity.PersonalChangePasswordActivity;
import cn.aorise.petition.ui.activity.PersonalChangePhoneNumberActivity;
import cn.aorise.petition.ui.activity.RequestActivity;
import cn.aorise.petition.ui.base.PetitionBaseFragment;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by pc on 2017/3/2.
 */
public class PersonalFragment extends PetitionBaseFragment implements View.OnClickListener{
    private PetitionFragmentPersonalBinding mBinding;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    public PersonalFragment() {
    }

    public static PersonalFragment newInstance() {
        PersonalFragment fragment = new PersonalFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.petition_fragment_personal, container, false);
        mBinding.rlEditPersonalInfo.setOnClickListener(this);
        mBinding.rlEditPhonenum.setOnClickListener(this);
        mBinding.rlEditPassword.setOnClickListener(this);
        mBinding.rlExitLogin.setOnClickListener(this);
        intevent();
        return mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    private void intevent(){
        sp=getActivity().getSharedPreferences(getString(R.string.petition_sharepre_name),MODE_PRIVATE);
        editor=sp.edit();
    }
    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (R.id.rl_edit_personal_info==id) {
            if (sp.getBoolean(getString(R.string.petition_sharepre_islogin),false)==false){
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            } else {
                Intent intent=new Intent(getActivity(), PersonalChangeInformationActivity.class);
                startActivity(intent);
            }
        } else if (R.id.rl_edit_phonenum==id) {
            if (sp.getBoolean(getString(R.string.petition_sharepre_islogin),false)==false){
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            } else {
                Intent intent=new Intent(getActivity(), PersonalChangePhoneNumberActivity.class);
                startActivity(intent);
            }

        } else if (R.id.rl_edit_password==id) {
            if (sp.getBoolean(getString(R.string.petition_sharepre_islogin),false)==false){
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            } else {
                Intent intent=new Intent(getActivity(), PersonalChangePasswordActivity.class);
                startActivity(intent);
            }

        } else if (R.id.rl_exit_login==id) {
            if (sp.getBoolean(getString(R.string.petition_sharepre_islogin),false)==false){
                Toast.makeText(getActivity(), R.string.petition_error_toast, Toast.LENGTH_SHORT).show();
            } else {
                editor.clear();
                editor.commit();
                Toast.makeText(getActivity(), getString(R.string.petition_personal_04), Toast.LENGTH_SHORT).show();
            }


        }
    }
}
