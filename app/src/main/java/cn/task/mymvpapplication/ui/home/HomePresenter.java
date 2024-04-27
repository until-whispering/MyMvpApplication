package cn.task.mymvpapplication.ui.home;

import java.util.List;

import cn.task.mymvpapplication.bean.BaseBean;
import cn.task.mymvpapplication.bean.Goods;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

//Presenter: 逻辑控制层，同时持有View和Model对象；

public class HomePresenter implements HomeContract.IHomePresenter {

    private HomeContract.IHomeView homeView;
    private HomeModel homeModel;

    public HomePresenter(HomeContract.IHomeView homeView) {
        this.homeView = homeView;
        homeModel = new HomeModel();
    }

    @Override
    public void getData() {
        homeModel.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<List<Goods>>>() {
                               @Override
                               public void accept(BaseBean<List<Goods>> listBaseBean) throws Throwable {
                                    homeView.getGoodsSuccess(listBaseBean.getData());//成功获取数据
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Throwable {
                                    homeView.getGoodsError(throwable);//获取数据失败
                               }
                           });
    }
}
