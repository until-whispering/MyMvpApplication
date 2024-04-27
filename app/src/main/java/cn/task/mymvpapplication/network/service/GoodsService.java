package cn.task.mymvpapplication.network.service;

import java.util.List;

import cn.task.mymvpapplication.bean.BaseBean;
import cn.task.mymvpapplication.bean.Goods;
import cn.task.mymvpapplication.bean.GoodsDetail;
import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoodsService {

    @GET("until-whispering/text/main/goods_list")
    Flowable<BaseBean<List<Goods>>> getGoods();

    @GET("until-whispering/text/main/goods_detail")
    Flowable<BaseBean<GoodsDetail>> getGoodDetail(@Query("goodsId") int goodsId);
}
