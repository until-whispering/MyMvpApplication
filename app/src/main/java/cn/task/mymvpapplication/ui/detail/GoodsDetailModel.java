package cn.task.mymvpapplication.ui.detail;

import cn.task.mymvpapplication.bean.BaseBean;
import cn.task.mymvpapplication.bean.GoodsDetail;
import cn.task.mymvpapplication.network.RetrofitClient;
import cn.task.mymvpapplication.network.service.GoodsService;
import io.reactivex.rxjava3.core.Flowable;

public class GoodsDetailModel implements GoodsDetailContract.IGoodsDetailModel {
    @Override
    public Flowable<BaseBean<GoodsDetail>> getGoodsDetail(int goodsId) {
        return RetrofitClient.getInstance().getService(GoodsService.class).getGoodDetail(goodsId);
    }
}
