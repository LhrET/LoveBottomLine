package net.app.lblpack.common.wiget.recycler;

public interface AdapterCallback <Data>{
        void update(Data data,RecycleAdapter.ViewHolder<Data> viewHolder);
}
