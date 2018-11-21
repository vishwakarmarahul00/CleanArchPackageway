package com.cleanway.apackage.cleanarchpackageway.presenter_layer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.cleanway.apackage.cleanarchpackageway.R;
import com.cleanway.apackage.cleanarchpackageway.model_layer.Apps;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private List<Apps> arrData;
    private HomeAdapterCallback callback;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        Apps apps;
        @BindView(R.id.appname)
        TextView appname;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setApp(Apps apps) {
            this.apps = apps;
        }

        @OnClick(R.id.row)
        void onItemClicked() {
            if (callback != null) callback.onListItemClicked(apps);
        }
    }

    public HomeAdapter(List<Apps> arrData, HomeAdapterCallback callback) {
        this.callback = callback;
        this.arrData = arrData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.homerecycler_listitem, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Apps obj = arrData.get(position);
        holder.setApp(obj);
        holder.appname.setText(obj.getName());
    }

    @Override
    public int getItemCount() {
        return arrData.size();
    }

    //callback interface
    public interface HomeAdapterCallback {
        void onListItemClicked(Apps selectedApp);
    }
}
