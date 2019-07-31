package com.wb.commonbase.base;

import com.wb.commonbase.mvp.IPresenter;
import com.wb.commonbase.mvp.IView;


public abstract class BaseMVPActivity<P extends IPresenter> extends BaseActivity implements IView {
    protected P presenter;

    @Override
    protected void initData() {
        super.initData();
        presenter = createPresenter();
        // presenter 绑定 view
        if (presenter != null) {
            presenter.attachView(this);
        }

    }

    protected abstract int getLayoutResId();

    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Activity 销毁时取消所有的订阅
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
    }
}
