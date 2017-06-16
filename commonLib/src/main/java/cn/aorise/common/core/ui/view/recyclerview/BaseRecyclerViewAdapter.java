package cn.aorise.common.core.ui.view.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

/**
 * 基础RecyclerView.Adapter
 * 1）增加了emptyview，可以给列表为空的时候显示
 * 2）可以给databing的item使用，也可以给普通的item使用
 * Created by tangjy on 2016-8-23.
 */
abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements View.OnClickListener, View.OnLongClickListener {
    protected static final int TYPE_HEADER = 0;
    protected static final int TYPE_FOOTER = 1;
    protected static final int TYPE_ITEM = 2;
    protected static final int TYPE_EMPTY = 3;
    protected View mEmptyView;
    protected View mFooterView;
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List<T> mList;
    protected IItemClickListener<T> mItemClickListener;
    protected IItemLongClickListener<T> mItemLongClickListener;

    public BaseRecyclerViewAdapter(Context context) {
        this.mContext = context;
        this.mInflater = ((Activity) context).getLayoutInflater();
    }

    public BaseRecyclerViewAdapter(Context context, List<T> list) {
        this(context);
        this.mList = list;
    }

    public void setList(List<T> list) {
        mList = list;
        removeEmptyView();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (null != mEmptyView) {
            return TYPE_EMPTY;
        } else {
            if (getFooterCount() == 0) {
                return TYPE_ITEM;
            } else {
                // 有footerview
                if (position == getItemCount() - 1) {
                    return TYPE_FOOTER;
                }
            }
            return TYPE_ITEM;
        }
    }

    public T getItem(int position) {
        int type = getItemViewType(position);
        if (TYPE_ITEM == type) {
            if (null == mList)
                return null;
            return mList.get(position);
        } else {
            return null;
        }
    }

    @Override
    public int getItemCount() {
        if (null != mEmptyView) {
            return 1;
        } else {
            if (null == mList) {
                return getFooterCount();
            } else {
                return mList.size() + getFooterCount();
            }
        }
    }

    /**
     * 给databing的item使用
     */
    public static class BindingItemViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BindingItemViewHolder(View itemView) {
            super(itemView);
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return this.binding;
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public CustomViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void removeEmptyView() {
        mEmptyView = null;
        notifyDataSetChanged();
    }

    public void addEmptyView(View emptyView) {
        mEmptyView = emptyView;
        if (null != mList) {
            mList.clear();
        }
        notifyDataSetChanged();
    }

    public void removeFooterView() {
        mFooterView = null;
        notifyDataSetChanged();
    }

    public void addFooterView(View footerView) {
        mFooterView = footerView;
        notifyDataSetChanged();
    }

    public int getFooterCount() {
        return null != mFooterView ? 1 : 0;
    }

    public void setItemClickListener(IItemClickListener<T> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setItemLongClickListener(IItemLongClickListener<T> itemLongClickListener) {
        mItemLongClickListener = itemLongClickListener;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onClick(View view) {
        if (null != mItemClickListener) {
            mItemClickListener.onItemClick(view, (T) view.getTag());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean onLongClick(View view) {
        if (null != mItemLongClickListener) {
            mItemLongClickListener.onItemLongClick(view, (T) view.getTag());
        }
        return true;
    }

    public interface IItemClickListener<T> {
        void onItemClick(View view, T t);
    }

    public interface IItemLongClickListener<T> {
        void onItemLongClick(View view, T t);
    }
}
