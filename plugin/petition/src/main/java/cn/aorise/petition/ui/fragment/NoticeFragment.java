package cn.aorise.petition.ui.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import cn.aorise.petition.R;
import cn.aorise.petition.databinding.PetitionFragmentNoticeBinding;
import cn.aorise.petition.ui.activity.AboutActivity;
import cn.aorise.petition.ui.activity.GuideFlowPathActivity;
import cn.aorise.petition.ui.activity.GuideNoticeMatterActivity;
import cn.aorise.petition.ui.activity.GuideRulesActivity;
import cn.aorise.petition.ui.base.PetitionBaseFragment;

/**
 * Created by pc on 2017/3/2.
 */
public class NoticeFragment extends PetitionBaseFragment implements View.OnClickListener{
    private PetitionFragmentNoticeBinding mBinding;

    public NoticeFragment() {
    }

    public static NoticeFragment newInstance() {
        NoticeFragment fragment = new NoticeFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.petition_fragment_notice, container, false);
        mBinding.rl01.setOnClickListener(this);
        mBinding.rl02.setOnClickListener(this);
        mBinding.rl03.setOnClickListener(this);
        mBinding.rl04.setOnClickListener(this);
        return mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Indicate that this fragment would like to influence the set of actions in the action bar.
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.petition_menu_notice, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            getBaseActivity().openActivity(AboutActivity.class);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (R.id.rl_01==id) {
            Intent intent=new Intent(getActivity(), GuideRulesActivity.class);
            startActivity(intent);
        } else if (R.id.rl_02==id) {
            Intent intent=new Intent(getActivity(), GuideNoticeMatterActivity.class);
            startActivity(intent);
        } else if (R.id.rl_03==id) {

        } else if (R.id.rl_04==id) {
            Intent intent=new Intent(getActivity(), GuideFlowPathActivity.class);
            startActivity(intent);

        }
    }
}
