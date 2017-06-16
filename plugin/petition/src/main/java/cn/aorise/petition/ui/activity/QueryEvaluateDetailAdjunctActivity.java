package cn.aorise.petition.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import cn.aorise.common.core.module.network.APICallback;
import cn.aorise.common.core.module.network.APIResult;
import cn.aorise.common.core.utils.assist.AoriseUtil;
import cn.aorise.common.core.utils.assist.GsonUtil;
import cn.aorise.petition.R;
import cn.aorise.petition.common.Utils;
import cn.aorise.petition.databinding.PetitionActivityQueryEvaluateAdjunctListBinding;
import cn.aorise.petition.module.network.Mock;
import cn.aorise.petition.module.network.PetitionApiService;
import cn.aorise.petition.module.network.entity.response.RRegister;
import cn.aorise.petition.ui.adapter.AddAdjunctAdapter;
import cn.aorise.petition.ui.adapter.AddFileAdapter;
import cn.aorise.petition.ui.adapter.QueryEvaluateAdjunctAdapter;
import cn.aorise.petition.ui.base.PetitionBaseActivity;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/22.
 */

public class QueryEvaluateDetailAdjunctActivity extends PetitionBaseActivity implements View.OnClickListener {
    private PetitionActivityQueryEvaluateAdjunctListBinding mBinding;
    private String adjunctList;
    private String[] data;
    private List<String> PictureList=new ArrayList<>();
    private List<String> FileList=new ArrayList<>();
    private QueryEvaluateAdjunctAdapter mAdapter;
    private AddFileAdapter addFileAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initData() {
        mAdapter=new QueryEvaluateAdjunctAdapter(PictureList,this);
        addFileAdapter=new AddFileAdapter(FileList,this);
    }

    @Override
    protected void initView() {
        mBinding= DataBindingUtil.setContentView(this, R.layout.petition_activity_query_evaluate_adjunct_list);
        getPathData();
        mBinding.mlistview.setAdapter(mAdapter);
        mBinding.mylistview.setAdapter(addFileAdapter);
        mBinding.mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(QueryEvaluateDetailAdjunctActivity.this,QueryEvaluateDetailAdjunctPictureActivity.class);
                intent.putExtra("path",PictureList.get(position));
                startActivity(intent);

            }
        });
        mBinding.mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Intent.ACTION_VIEW , Uri.parse("http://192.168.1.40:1111"+FileList.get(position).toString()));
                startActivity(i);
            }
        });
        /*AoriseUtil.loadImage(QueryEvaluateDetailAdjunctActivity.this,mBinding.imgDouble,"http://192.168.1.40:1111/XFWD/2017/05/12/4c6ba902e34b4d0ab7b8ff510eb8c4e3.jpg");*/
    }

    @Override
    protected void initEvent() {

    }

    private void getPathData(){
        adjunctList=getIntent().getStringExtra("fj");
        if (!adjunctList.equals("")) {
            data = adjunctList.split(",");
            for (int i = 0; i < data.length; i++) {
                if (data[i].substring(data[i].lastIndexOf("."), data[i].length()).equals(".doc") ||
                        data[i].substring(data[i].lastIndexOf("."), data[i].length()).equals(".docx") ||
                        data[i].substring(data[i].lastIndexOf("."), data[i].length()).equals(".gif") ||
                        data[i].substring(data[i].lastIndexOf("."), data[i].length()).equals(".jip") ||
                        data[i].substring(data[i].lastIndexOf("."), data[i].length()).equals(".jpeg") ||
                        data[i].substring(data[i].lastIndexOf("."), data[i].length()).equals(".png") ||
                        data[i].substring(data[i].lastIndexOf("."), data[i].length()).equals(".bmp") ||
                        data[i].substring(data[i].lastIndexOf("."), data[i].length()).equals(".txt") ||
                        data[i].substring(data[i].lastIndexOf("."), data[i].length()).equals(".pdf")) {
                    FileList.add(data[i]);
                } else {
                    PictureList.add(data[i]);
                }
            }
            System.out.println(FileList + "-" + PictureList);

        }
    }

    private void downloadFile(){

    }

}
