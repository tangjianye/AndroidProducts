package cn.aorise.petition.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.aorise.petition.R;
import cn.aorise.petition.module.network.entity.response.RQueryEvaluate;
import cn.aorise.petition.ui.bean.QueryEvaluationInfo;
import cn.aorise.petition.ui.bean.SuggestCollectInfo;

import static android.view.View.GONE;

/**
 * Created by Administrator on 2017/4/28.
 */

public class QueryEvaluationAdapter extends BaseAdapter {
    private Context mContext;
    private List<RQueryEvaluate> data;

    public QueryEvaluationAdapter(List<RQueryEvaluate> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return data.size();
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
        final Holder holder;
        if (convertView != null) {
            view = convertView;
            holder = (Holder) view.getTag();
        } else {
            view = View.inflate(mContext, R.layout.petition_query_evaluation_list_item, null);
            holder = new Holder();
            holder.num = (TextView) view.findViewById(R.id.textView19);
            holder.content = (TextView) view.findViewById(R.id.textView26);
            holder.date = (TextView) view.findViewById(R.id.textView23);

            holder.icon = (ImageView) view.findViewById(R.id.imageView15);


            view.setTag(holder);
        }
        final RQueryEvaluate mydata = data.get(position);
        holder.num.setText(mydata.getBH());
        holder.content.setText(mydata.getNR());
        holder.date.setText(mydata.getCJSJ());

        if (mydata.getPJZT() == 1) {
            holder.icon.setImageResource(R.drawable.petition_has_evaluate);

        } else if (mydata.getPJZT() == 0) {
            holder.icon.setImageResource(R.drawable.petition_not_evaluate);

        }

        return view;
    }

    public class Holder {
        private TextView num, content, date;
        private ImageView icon;

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
