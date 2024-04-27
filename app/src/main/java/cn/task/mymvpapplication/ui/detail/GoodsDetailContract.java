package cn.task.mymvpapplication.ui.detail;

import cn.task.mymvpapplication.bean.BaseBean;
import cn.task.mymvpapplication.bean.GoodsDetail;
import io.reactivex.rxjava3.core.Flowable;

public interface GoodsDetailContract {
    interface IGoodsDetailPresent{
        void getGoodsDetail(int goodsId);
    }

    interface IGoodsDetailModel{
        Flowable<BaseBean<GoodsDetail>> getGoodsDetail(int goodsId);
    }

    interface IGoodsDetailView{
        void getGoodsDetailSuccess(GoodsDetail goodsDetail);

        void getGoodsDetailError(Throwable throwable);
    }
}
