package com.wb.commonbase.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.wb.commonbase.R;
import com.wb.commonbase.helper.QRCodeHelper;

/**
 *
 */
public    class PopupQRCode extends  PopupWindow  {
    private View mPopView;
    private onItemClickListener mListener;
    Activity activity;
    private ImageView iv1;
    String url ;

    public PopupQRCode(Activity context, String url) {
        super(context);
        activity = context;
        this .url = url;
        init(context);
        setPopupWindow();
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Activity context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        //绑定布局
        mPopView = inflater.inflate(R.layout.popup_qrcode, null);
        setAlpha(0.5f);
        iv1 = mPopView.findViewById(R.id.iv_qrcode);

        QRCodeHelper.showQRcodeImage(iv1,url);

   }

    /**
     * 设置窗口的相关属性
     */
    @SuppressLint("InlinedApi")
    private void setPopupWindow() {
        this.setContentView(mPopView);// 设置View
        this.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);// 设置弹出窗口的宽
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);// 设置弹出窗口的高
        this.setFocusable(true);// 设置弹出窗口可
//        this.setAnimationStyle(R.style.mypopwindow_anim_style );// 设置动画
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));// 设置背景透明
        mPopView.setOnTouchListener(new View.OnTouchListener() {// 如果触摸位置在窗口外面则销毁

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dismiss();
                return true;
            }
        });
    }

    /**
     * 定义一个接口，公布出去 在Activity中操作按钮的单击事件
     */
    public interface onItemClickListener {
        void OnItemClick(View v, int type);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.mListener = listener;
    }


    /***
     * layoutId
     * @param i
     */
    public void show( int i ){
//        if (i>0){
//            showAtLocation(ViewTools.find(activity,i), Gravity.BOTTOM , 0, 0);
//        }
//
        View v = activity.findViewById(i);
      showAtLocation(v, Gravity.CENTER , 0, 0);

//        showAsDropDown(ViewTools.find(activity,i),0,0);
    }

    private void setAlpha(float alfa) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = alfa;
        activity.getWindow().setAttributes(lp);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        setAlpha(1);
    }

}
