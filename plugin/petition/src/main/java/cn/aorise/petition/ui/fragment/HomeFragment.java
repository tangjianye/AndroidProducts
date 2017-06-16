package cn.aorise.petition.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.aorise.petition.R;
import cn.aorise.petition.databinding.PetitionFragmentHomeBinding;
import cn.aorise.petition.ui.activity.LoginActivity;
import cn.aorise.petition.ui.activity.QueryEvaluateActivity;
import cn.aorise.petition.ui.activity.RequestActivity;
import cn.aorise.petition.ui.activity.SuggestCollectActivity;
import cn.aorise.petition.ui.base.PetitionBaseFragment;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by pc on 2017/3/2.
 */
public class HomeFragment extends PetitionBaseFragment implements View.OnClickListener{
    private PetitionFragmentHomeBinding mBinding;
    private SharedPreferences sp;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.petition_fragment_home, container, false);
        mBinding.petitionRlRequest.setOnClickListener(this);
        mBinding.petitionRlSuggest.setOnClickListener(this);
        mBinding.petitionRlQuery.setOnClickListener(this);
        sp=getActivity().getSharedPreferences(getString(R.string.petition_sharepre_name),MODE_PRIVATE);
        return mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (R.id.petition_rl_request==id) {
            if (sp.getBoolean(getString(R.string.petition_sharepre_islogin),false)==false){
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(getActivity(), RequestActivity.class);
                startActivity(intent);
            }
        } else if (R.id.petition_rl_suggest==id) {
            if (sp.getBoolean(getString(R.string.petition_sharepre_islogin),false)==false){
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            } else {
                Intent intent=new Intent(getActivity(), SuggestCollectActivity.class);
                startActivity(intent);
            }

        } else if (R.id.petition_rl_query==id) {
            if (sp.getBoolean(getString(R.string.petition_sharepre_islogin),false)==false){
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            } else {
                Intent intent=new Intent(getActivity(), QueryEvaluateActivity.class);
                startActivity(intent);
            }
        }
    }
}
