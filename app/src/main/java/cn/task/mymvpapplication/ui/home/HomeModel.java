package cn.task.mymvpapplication.ui.home;

import java.util.List;

import cn.task.mymvpapplication.bean.BaseBean;
import cn.task.mymvpapplication.bean.Goods;
import cn.task.mymvpapplication.network.RetrofitClient;
import cn.task.mymvpapplication.network.service.GoodsService;
import io.reactivex.rxjava3.core.Flowable;


//Model: 实体层，负责获取实体数据。

public class HomeModel implements HomeContract.IHomeModel {

    //从网络获取
    @Override
    public Flowable<BaseBean<List<Goods>>> getData() {
        return RetrofitClient.getInstance().getService(GoodsService.class)
                .getGoods();
    }
}
