package net.app.lblpack.common.wiget.recycler;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.app.lblpack.common.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressWarnings("unused")
public abstract class RecycleAdapter<Data> extends RecyclerView.Adapter<RecycleAdapter.ViewHolder<Data>>
implements View.OnClickListener,View.OnLongClickListener,AdapterCallback<Data>{

    private final List<Data> mDataList;
    private  AdapterListener<Data> adapterListener;


    public RecycleAdapter(){
        this(null);
    }
    public RecycleAdapter(AdapterListener<Data> listener){
        this(new ArrayList<Data>(),listener);
    }
    public RecycleAdapter(List<Data> dataList,AdapterListener<Data> listener){
        this.mDataList = dataList;
        this.adapterListener = listener;
    }


    @Override
    public int getItemViewType(int position) {
        return getItemViewType(position, mDataList.get(position));
    }

    /**
     * 创建一个ViewHolder
     * @param viewGroup
     * @param i 界面的类型，约定为布局的Id
     * @return
     */
    @NonNull
    @Override
    public ViewHolder<Data> onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(i,viewGroup,false);
        ViewHolder<Data> holder =  onCreateViewHolder(root,i);
        //设置View的Tag为ViewHolder
        root.setTag(R.id.tag_recycler_holder,holder);

        root.setOnClickListener(this);
        root.setOnLongClickListener(this);

        //界面绑定
        holder.unbinder =  ButterKnife.bind(holder,root);
        holder.adapterCallback = this;
        return holder;
    }

    /**
     * 得到布局的类型
     * @param position
     * @param data
     * @return xml当前的Id, 用于创建ViewHolder
     */
    @LayoutRes
    protected abstract int getItemViewType(int position,Data data);
    /**
     * 得到一个新的ViewHolder
     * @param root
     * @param viewType
     * @return
     */
    protected  abstract ViewHolder<Data> onCreateViewHolder(View root,int viewType);
    @Override
    public void onBindViewHolder(@NonNull ViewHolder<Data> dataViewHolder, int i) {
        Data data = mDataList.get(i);
        dataViewHolder.bind(data);
    }

    /**
     * 得到当前集合数据量
     * @return
     */
    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    /**
     * 插入数据
     * @param data
     */
    public void add(Data data){
        mDataList.add(data);
        notifyItemInserted(mDataList.size()-1);
    }

    public final void add(Data... dataList){
        if(dataList!=null&&dataList.length>0){
            int startPos = mDataList.size();
            Collections.addAll(mDataList,dataList);
            notifyItemRangeInserted(startPos,dataList.length);
        }
    }
    public void add(Collection<Data> dataList){
        if(dataList!=null&&dataList.size()>0){
            int startPos = mDataList.size();
            mDataList.addAll(dataList);
            notifyItemRangeInserted(startPos,dataList.size());
        }
    }
    public void clear(){
        mDataList.clear();
        notifyDataSetChanged();
    }
    public void replace(Collection<Data> dataList){
        mDataList.clear();
        if(dataList==null||dataList.size()==0)
            return;
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        ViewHolder viewHolder = (ViewHolder)v.getTag(R.id.tag_recycler_holder);
        if(this.adapterListener!=null){
            int pos = viewHolder.getAdapterPosition();
            this.adapterListener.onItemClick(viewHolder,mDataList.get(pos));
        }
    }

    @Override
      public boolean onLongClick(View v) {
        ViewHolder viewHolder = (ViewHolder)v.getTag(R.id.tag_recycler_holder);
        if(this.adapterListener!=null){
            int pos = viewHolder.getAdapterPosition();
            this.adapterListener.onItemClick(viewHolder,mDataList.get(pos));
            return true;
        }
        return false;
    }

    /**
     * 设置适配器监听
     * @param adapterListener
     */
    public void setAdapterListener(AdapterListener<Data>adapterListener){
        this.adapterListener = adapterListener;
    }

    /**
     * 我们的自定义监听器
     * @param <Data>
     */
    public interface AdapterListener<Data>{
        //当Cell点击
        void onItemClick(RecycleAdapter.ViewHolder holder,Data data);
        //长按
        void onItemLongClick(RecycleAdapter.ViewHolder holder,Data data);
    }
    /**
     * ViewHolde
     * @param <Data>
     */
    public static abstract class ViewHolder<Data> extends RecyclerView.ViewHolder{
        protected Data mData;
        private AdapterCallback<Data> adapterCallback;
        private Unbinder unbinder;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        /**
         * 绑定数据的触发
         * @param data
         */
        void bind(Data data){
            this.mData = data;
            onBind(data);
        }

        protected  abstract void onBind(Data data);

        /**
         * Holder自己对自己对应的Data进行更新
         * @param data
         */
        public void updateData(Data data){
            if(this.adapterCallback != null){
                this.adapterCallback.update(data,this);
            }
        }
    }
}
