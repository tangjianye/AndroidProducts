package cn.aorise.petition.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.aorise.common.core.utils.assist.AoriseUtil;
import cn.aorise.petition.R;

/**
 * Created by Administrator on 2016/11/24.
 */
public class QueryEvaluateAdjunctAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> data;

    public QueryEvaluateAdjunctAdapter(List<String> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return data.size();//返回listiview数目加1
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
        if (convertView!=null){
            view=convertView;
            holder= (Holder) view.getTag();
        }else{

            view= View.inflate(mContext, R.layout.petition_add_adjunct_picture_item,null);
            holder=new Holder();
            holder.tupian= (ImageView) view.findViewById(R.id.imageView4);
            holder.shanchu= (ImageView) view.findViewById(R.id.imageView13);
            holder.delete= (RelativeLayout) view.findViewById(R.id.rl_delete);
            view.setTag(holder);
        }


            final String mydata = data.get(position);
            //原先的正常数据的显示，操作等
            AoriseUtil.loadImage(mContext,holder.tupian,holder.http+mydata);
        System.out.println(holder.http+mydata);
            holder.shanchu.setVisibility(View.GONE);
            return view;


    }
    public class Holder{
        private String http="http://192.168.1.40:1111";
        private ImageView tupian,shanchu;
        private RelativeLayout delete;

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
