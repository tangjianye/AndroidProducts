package cn.aorise.petition.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.aorise.petition.R;
import cn.aorise.petition.module.network.entity.response.RGuideRules;
import cn.aorise.petition.module.network.entity.response.RQuestion;

/**
 * Created by Administrator on 2017/5/15.
 */

public class GuideRulesAdapter extends BaseAdapter {
    private Context mContext;
    private List<RGuideRules> data;

    public GuideRulesAdapter(List<RGuideRules> data, Context mContext) {
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
        final GuideRulesAdapter.Holder holder;
        if (convertView!=null){
            view=convertView;
            holder= (GuideRulesAdapter.Holder) view.getTag();
        }else{
            view= View.inflate(mContext, R.layout.petition_activity_guide_rules_list_item,null);
            holder=new GuideRulesAdapter.Holder();
            holder.BT= (TextView) view.findViewById(R.id.txt_title);
            holder.CJSJ= (TextView) view.findViewById(R.id.txt_date);
            holder.NR= (TextView) view.findViewById(R.id.txt_content);

            view.setTag(holder);
        }

        final RGuideRules mydata = data.get(position);
        //原先的正常数据的显示，操作等
        holder.BT.setText(mydata.getBT());
        holder.CJSJ.setText(mydata.getCJSJ());
        holder.NR.setText(mydata.getNR());
        return view;

    }
    public class Holder{
        private TextView BT,CJSJ,NR;

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
