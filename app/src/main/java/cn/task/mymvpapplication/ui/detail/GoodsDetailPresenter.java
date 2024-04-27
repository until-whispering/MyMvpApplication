package cn.task.mymvpapplication.ui.detail;

import cn.task.mymvpapplication.bean.BaseBean;
import cn.task.mymvpapplication.bean.GoodsDetail;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GoodsDetailPresenter implements GoodsDetailContract.IGoodsDetailPresent{
    private final GoodsDetailContract.IGoodsDetailView goodsDetailView;
    GoodsDetailModel goodsDetailModel;

    public GoodsDetailPresenter(GoodsDetailContract.IGoodsDetailView goodsDetailView) {
        this.goodsDetailView = goodsDetailView;
        goodsDetailModel = new GoodsDetailModel();
    }

    @Override
    public void getGoodsDetail(int goodsId) {
        goodsDetailModel.getGoodsDetail(goodsId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<GoodsDetail>>() {
                    @Override
                    public void accept(BaseBean<GoodsDetail> goodsDetailBaseBean) throws Throwable {
                        goodsDetailView.getGoodsDetailSuccess(goodsDetailBaseBean.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        goodsDetailView.getGoodsDetailError(throwable);
                    }
                });
    }
}
