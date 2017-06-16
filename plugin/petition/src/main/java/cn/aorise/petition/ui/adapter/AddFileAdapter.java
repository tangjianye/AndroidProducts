package cn.aorise.petition.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.aorise.common.core.ui.view.recyclerview.BindingRecyclerViewAdapter;
import cn.aorise.common.core.utils.assist.AoriseUtil;
import cn.aorise.petition.BR;
import cn.aorise.petition.R;

/**
 * Created by Administrator on 2017/4/28.
 */

public class AddFileAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> data;

    public AddFileAdapter(List<String> data, Context mContext) {
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
        final AddFileAdapter.Holder holder;
        if (convertView!=null){
            view=convertView;
            holder= (AddFileAdapter.Holder) view.getTag();
        }else{
            view= View.inflate(mContext, R.layout.petition_add_file_list_item,null);
            holder=new AddFileAdapter.Holder();
            holder.st= (TextView) view.findViewById(R.id.textView20);

            view.setTag(holder);
        }

            final String mydata = data.get(position);
            //原先的正常数据的显示，操作等
            holder.st.setText(mydata);
            return view;

    }
    public class Holder{
        private TextView st;

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
