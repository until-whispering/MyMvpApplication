package cn.task.mymvpapplication.ui.home;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import cn.task.mymvpapplication.R;
import cn.task.mymvpapplication.bean.Goods;
import cn.task.mymvpapplication.bean.GoodsDetail;
import cn.task.mymvpapplication.ui.base.BaseFragment;
import cn.task.mymvpapplication.ui.detail.GoodsDetailActivity;
import cn.task.mymvpapplication.ui.home.adapter.HomeRecycleViewAdapter;
import cn.task.mymvpapplication.ui.home.adapter.HomeSpanSizeLookup;

//View: 视图层，对应xml文件与Activity/Fragment；

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener
        , HomeContract.IHomeView, HomeRecycleViewAdapter.OnItemClickLister {

    private HomePresenter homePresenter;
    private HomeSpanSizeLookup homeSpanSizeLookup;
    private HomeRecycleViewAdapter homeRecycleViewAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    //初始化UI
    @Override
    protected void initView() {
        swipeRefreshLayout = find(R.id.home_swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        RecyclerView recyclerView = find(R.id.home_recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),4);

        List<Goods> goods = new ArrayList<>();
        homeSpanSizeLookup = new HomeSpanSizeLookup(goods);
        gridLayoutManager.setSpanSizeLookup(homeSpanSizeLookup);
        recyclerView.setLayoutManager(gridLayoutManager);

         homeRecycleViewAdapter = new HomeRecycleViewAdapter(recyclerView,getActivity(), goods);
         homeRecycleViewAdapter.setOnItemClickLister(this);
        recyclerView.setAdapter(homeRecycleViewAdapter);

        homePresenter = new HomePresenter(this);
        homePresenter.getData();
    }

    //刷新UI数据
    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false); //刷新状态
        homePresenter.getData();
    }

    //成功获取
    @Override
    public void getGoodsSuccess(List<Goods> goods) {
        homeSpanSizeLookup.setGoods(goods);
        homeRecycleViewAdapter.setGoods(goods);
    }

    //获取失败
    @Override
    public void getGoodsError(Throwable throwable) {

    }

    @Override
    public void onItemClick(Goods goods) {
        Intent intent = new Intent(getActivity(),GoodsDetailActivity.class);
        intent.putExtra(GoodsDetailActivity.GOODS_ID,goods.getGoodsId());
        startActivity(intent);
    }
}
