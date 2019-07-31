package com.wb.commonbase.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wb.commonbase.R;
import com.wb.commonbase.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

public class BannerPoint extends LinearLayout {
    Context context;
    int size;
    int position;
    int width1,width2,height;
    private List<Point> points;
    private PointAdapter adapter;

    public BannerPoint(Context context) {
        this(context,null);
    }

    public BannerPoint(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BannerPoint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public void setDataSize(int size){
        this.size = size;
        init();
    }

    public void setDataSize(int size,int width1,int width2,int height){
        this.size = size;
        this.width1 = width1;
        this.width2 = width2;
        this.height = height;
        init();
    }

    public void setPosition(int position){
        this.position = position;
        if (position<=size-1){
            for (int i = 0; i < points.size(); i++) {
                points.get(i).setShow(false);
            }
            points.get(position).setShow(true);
            adapter.notifyDataSetChanged();
        }
    }

    private void init() {
        View rootView=   LayoutInflater.from(context).inflate(R.layout.layout_banner_point, this, true);
        RecyclerView rv = rootView.findViewById(R.id.rv_view_point);
        rv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        points = new ArrayList<>();
        if (size!=0){
            for (int i = 0; i < size; i++) {
                points.add(new Point());
            }
        }
        adapter = new PointAdapter(points);
        rv.setAdapter(adapter);
        setPosition(0);
    }
    public class Point{
        boolean isShow;

        public boolean isShow() {
            return isShow;
        }

        public void setShow(boolean show) {
            isShow = show;
        }

    }

    public class PointAdapter extends BaseQuickAdapter<Point, BaseViewHolder> {
        public PointAdapter( @Nullable List<Point> data) {
            super(R.layout.item_banner_point, data);
        }
        @Override
        protected void convert(BaseViewHolder helper, Point item) {
            View viewShow =  helper.getView(R.id.view_show);
            View viewUnshow =  helper.getView(R.id.view_unshow);

            if (width1!=0){
                LayoutParams layoutParams1 = new LayoutParams(DensityUtil.dp2px(context,width1),DensityUtil.dp2px(context,height));
                LayoutParams layoutParams2 = new LayoutParams(DensityUtil.dp2px(context,width2),DensityUtil.dp2px(context,height));
                viewShow.setLayoutParams(layoutParams1);
                viewUnshow.setLayoutParams(layoutParams2);
            }

            if (item.isShow){
                viewShow.setVisibility(VISIBLE);
                viewUnshow.setVisibility(GONE);
            }else {
                viewUnshow.setVisibility(VISIBLE);
                viewShow.setVisibility(GONE);
            }
        }
    }
}
