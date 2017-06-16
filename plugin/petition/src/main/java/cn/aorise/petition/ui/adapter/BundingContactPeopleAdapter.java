package cn.aorise.petition.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.aorise.common.core.ui.view.recyclerview.BindingRecyclerViewAdapter;
import cn.aorise.petition.BR;
import cn.aorise.petition.R;
import cn.aorise.petition.ui.bean.Petition_contact_people;

/**
 * Created by Administrator on 2017/4/26.
 */

public class BundingContactPeopleAdapter extends BaseAdapter {
    private Context mContext;
    private List<Petition_contact_people> data;

    public BundingContactPeopleAdapter(List<Petition_contact_people> data, Context mContext) {
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
        final BundingContactPeopleAdapter.Holder holder;
        if (convertView!=null){
            view=convertView;
            holder= (BundingContactPeopleAdapter.Holder) view.getTag();
        }else{
            view= View.inflate(mContext, R.layout.petition_activity_request_add_contact_people_item,null);
            holder=new BundingContactPeopleAdapter.Holder();
            holder.name= (TextView) view.findViewById(R.id.textView16);
            holder.number= (TextView) view.findViewById(R.id.txt_petition_num);
            holder.address= (TextView) view.findViewById(R.id.txt_contact_address);

            view.setTag(holder);
        }

        final Petition_contact_people mydata = data.get(position);
        //原先的正常数据的显示，操作等
        holder.name.setText(mydata.getName());
        holder.number.setText(mydata.getNum());
        holder.address.setText(mydata.getAddress());
        return view;

    }
    public class Holder{
        private TextView name,number,address;

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
