package com.example.zy_1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zy_1.R;
import com.example.zy_1.bean.JavaBean;

import java.util.ArrayList;

public class RcyAdapter extends RecyclerView.Adapter {
    private ArrayList<JavaBean.DataBean.DatasBean> list;
    private Context context;
    private JavaBean.DataBean.DatasBean bean;

    public RcyAdapter(ArrayList<JavaBean.DataBean.DatasBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_rcy, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        bean = list.get(position);
        ViewHolder holder1= (ViewHolder) holder;
        holder1.title.setText(bean.getTitle());
        Glide.with(context).load(bean.getEnvelopePic()).into(holder1.iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickLis.click(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    private OnItemClickLis onItemClickLis;

    public void setOnItemClickLis(OnItemClickLis onItemClickLis) {
        this.onItemClickLis = onItemClickLis;
    }

    public interface  OnItemClickLis{
        void click(int position);
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv;
        public TextView title;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv = (ImageView) rootView.findViewById(R.id.iv);
            this.title = (TextView) rootView.findViewById(R.id.title);
        }

    }
}
