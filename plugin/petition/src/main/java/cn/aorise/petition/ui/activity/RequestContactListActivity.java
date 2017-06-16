package cn.aorise.petition.ui.activity;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import cn.aorise.common.core.utils.assist.GsonUtil;
import cn.aorise.petition.PetitionApplication;
import cn.aorise.petition.R;
import cn.aorise.petition.databinding.PetitionActivityRequestContactListBinding;
import cn.aorise.petition.ui.adapter.BundingContactPeopleAdapter;
import cn.aorise.petition.ui.base.PetitionBaseActivity;
import cn.aorise.petition.ui.bean.Petition_contact_people;

/**
 * Created by Administrator on 2017/4/25.
 */

public class RequestContactListActivity extends PetitionBaseActivity implements View.OnClickListener {
    private PetitionActivityRequestContactListBinding mbinding;
    private BundingContactPeopleAdapter madapter;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String myId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        myId=getIntent().getStringExtra(getString(R.string.petition_activity_to_activity_number));
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initData() {
        sp=getSharedPreferences(getString(R.string.petition_sharepre_nomal),MODE_PRIVATE);
        editor=sp.edit();


    }

    @Override
    protected void initView() {
        myId=getIntent().getStringExtra(getString(R.string.petition_activity_to_activity_number));
        mbinding= DataBindingUtil.setContentView(this,R.layout.petition_activity_request_contact_list);
        if (myId.equals("2")) {
            setPetition_title(this, getString(R.string.petition_contact_list_name), getString(R.string.petition_add), RequestAddContactPeopleActivity.class);
        }else {
            setPetition_title(this,getString(R.string.petition_contact_list_name),"");
        }

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }


    private void getData(){
        if (!sp.getString(getString(R.string.petition_shareprefers_save_list),"").equals("")) {
            final List<Petition_contact_people> data = GsonUtil.fromJson(sp.getString(getString(
                    R.string.petition_shareprefers_save_list), ""), new TypeToken<List<Petition_contact_people>>(){}.getType());
            System.out.println(data);
            madapter=new BundingContactPeopleAdapter(data,this);
            madapter.notifyDataSetChanged();
            mbinding.listView.setAdapter(madapter);
            mbinding.refresh.setEnabled(false);
            mbinding.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    if (myId.equals("2")) {
                        data.remove(position);
                        madapter.notifyDataSetChanged();
                        editor.putString(getString(
                                R.string.petition_shareprefers_save_list),GsonUtil.toJson(data));
                        editor.commit();
                    }
                    return false;
                }
            });

        }
    }
}
