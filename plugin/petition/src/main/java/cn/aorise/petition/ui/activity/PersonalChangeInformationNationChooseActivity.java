package cn.aorise.petition.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import cn.aorise.petition.R;
import cn.aorise.petition.databinding.PetitionActivityRequestAddAddressBinding;
import cn.aorise.petition.module.network.entity.response.RQuestion;
import cn.aorise.petition.ui.adapter.AddAddressAdapter;
import cn.aorise.petition.ui.base.PetitionBaseActivity;

/**
 * Created by Administrator on 2017/5/18.
 */

public class PersonalChangeInformationNationChooseActivity extends PetitionBaseActivity implements View.OnClickListener {
    private PetitionActivityRequestAddAddressBinding mBinding;
    private AddAddressAdapter mAdapter;
    private List<RQuestion> data=new ArrayList<>();
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String[] list={"汉族","蒙古族","回族","藏族","维吾尔族","苗族","彝族","壮族","布依族","朝鲜族","满族","侗族","瑶族","白族","土家族","哈尼族","哈萨克族","傣族",
            "黎族","僳僳族","佤族","畲族","高山族","拉祜族","水族","东乡族","纳西族","景颇族","柯尔克孜族","土族","达斡尔族","仫佬族","羌族","布朗族",
            "撒拉族","毛南族","仡佬族","锡伯族","阿昌族","普米族","塔吉克族","怒族","乌孜别克族","俄罗斯族","鄂温克族","德昂族","保安族","裕固族",
            "京族","塔塔尔族","独龙族","鄂伦春族","赫哲族","门巴族","珞巴族","基诺族"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mBinding= DataBindingUtil.setContentView(this, R.layout.petition_activity_request_add_address);
        for (int i=0;i<list.length;i++){
            RQuestion rQuestion=new RQuestion();
            rQuestion.setMC(list[i]);
            data.add(rQuestion);
        }
        mAdapter=new AddAddressAdapter(data,this);
        mBinding.listView.setAdapter(mAdapter);
        mBinding.listView.setAdapter(mAdapter);
        mBinding.refresh.setEnabled(false);
        mBinding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editor.putString(getString(R.string.petition_nation_01),data.get(position).getMC());
                editor.commit();
                PersonalChangeInformationNationChooseActivity.this.finish();
            }
        });
    }

    @Override
    protected void initEvent() {

    }
}
