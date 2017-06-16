package cn.aorise.education.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import cn.aorise.education.R;
import cn.aorise.education.databinding.EducationFragmentChatBinding;
import cn.aorise.education.ui.activity.ContactsActivity;
import cn.aorise.education.ui.base.EducationBaseFragment;

/**
 * Created by pc on 2017/3/2.
 */
public class ChatFragment extends EducationBaseFragment {
    private EducationFragmentChatBinding mBinding;

    public ChatFragment() {
    }

    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.education_fragment_chat, container, false);
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
        inflater.inflate(R.menu.education_menu_chat, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.action_contract == item.getItemId()) {
            getBaseActivity().openActivity(ContactsActivity.class);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
