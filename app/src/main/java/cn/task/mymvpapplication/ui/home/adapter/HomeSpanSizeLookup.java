package cn.task.mymvpapplication.ui.home.adapter;

import androidx.recyclerview.widget.GridLayoutManager;

import java.util.List;

import cn.task.mymvpapplication.bean.Goods;

public class HomeSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private final List<Goods> data;

    public HomeSpanSizeLookup(List<Goods> data) {
        this.data = data;
    }

    @Override
    public int getSpanSize(int position) {
        return data.get(position).getSpanSize();
    }

    public void setGoods(List<Goods> data) {
        this.data.clear(); //删除数组所有元素
        this.data.addAll(data);
    }
}
