package cn.task.mymvpapplication.ui.home;

import java.util.List;

import cn.task.mymvpapplication.bean.BaseBean;
import cn.task.mymvpapplication.bean.Goods;
import io.reactivex.rxjava3.core.Flowable;

public interface HomeContract {
    interface IHomePresenter{
        void getData();
    }

    interface IHomeModel{
        Flowable<BaseBean<List<Goods>>> getData();
    }

    interface IHomeView{
        void getGoodsSuccess(List<Goods> goods);
        void getGoodsError(Throwable throwable);
    }
}
