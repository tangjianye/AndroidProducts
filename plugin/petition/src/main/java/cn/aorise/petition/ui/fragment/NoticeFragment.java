package cn.aorise.petition.ui.fragment;

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
import cn.aorise.petition.ui.base.PetitionBaseFragment;

/**
 * Created by pc on 2017/3/2.
 */
public class NoticeFragment extends PetitionBaseFragment {
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
}
