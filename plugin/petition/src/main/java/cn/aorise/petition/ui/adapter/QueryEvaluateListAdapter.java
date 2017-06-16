package cn.aorise.petition.ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.aorise.petition.R;
import cn.aorise.petition.module.network.entity.response.RQuestion;
import cn.aorise.petition.ui.bean.QueryEvaluateListInfo;

/**
 * Created by Administrator on 2017/5/5.
 */

public class QueryEvaluateListAdapter extends BaseAdapter {
    private Context mContext;
    private List<QueryEvaluateListInfo> data;

    public QueryEvaluateListAdapter(List<QueryEvaluateListInfo> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return  data.size();//返回listiview数目加1
    }

    @Override
    public Object getItem(int position) {

        return data.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        final QueryEvaluateListAdapter.Holder holder;
        if (convertView!=null){
            view=convertView;
            holder= (QueryEvaluateListAdapter.Holder) view.getTag();
        }else{
            view= View.inflate(mContext, R.layout.petition_activity_query_evaluate_detail_list_item,null);
            holder=new QueryEvaluateListAdapter.Holder();
            holder.JG= (TextView) view.findViewById(R.id.txt1);
            holder.RY= (TextView) view.findViewById(R.id.txt2);
            holder.SJ= (TextView) view.findViewById(R.id.txt3);
            holder.SX= (TextView) view.findViewById(R.id.txt4);
            holder.LX= (TextView) view.findViewById(R.id.txt5);

            view.setTag(holder);
        }

        final QueryEvaluateListInfo mydata = data.get(position);
        //原先的正常数据的显示，操作等
        if (position==0){
            holder.JG.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            holder.RY.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            holder.SJ.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            holder.SX.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            holder.LX.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }else {holder.JG.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            holder.RY.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            holder.SJ.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            holder.SX.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            holder.LX.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
        holder.JG.setText(mydata.getJBJG());
        holder.RY.setText(mydata.getJBRY());
        holder.SJ.setText(mydata.getCZSJ());
        holder.SX.setText(mydata.getBLSC());
        holder.LX.setText(mydata.getCZLX());

        return view;

    }
    public class Holder{
        private TextView JG,RY,SJ,SX,LX;

    }
    public static String GetStringFromLong(long millis) {//yyyy年MM月dd日HH时mm分
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(millis);
        int i = Integer.parseInt(String.valueOf(millis));
        String times = sdr.format(new Date(i * 1000L));
        return times;
    }
}
