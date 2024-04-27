package cn.task.mymvpapplication.ui.detail;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import cn.task.mymvpapplication.R;
import cn.task.mymvpapplication.bean.GoodsDetail;
import cn.task.mymvpapplication.ui.base.BaseActivity;

public class GoodsDetailActivity extends BaseActivity implements View.OnClickListener, GoodsDetailContract.IGoodsDetailView {

    public static final String GOODS_ID  = "goods_id";
    private GoodsDetailPresenter goodsDetailPresenter;
    private Toolbar toolbar;
    private TextView detailContent;


    @Override
    protected void initView() {
        toolbar = find(R.id.detail_toolbar);
        toolbar.setNavigationOnClickListener(this);
        detailContent = find(R.id.detail_content);
        int goodsId = getIntent().getIntExtra(GOODS_ID,0);
        goodsDetailPresenter = new GoodsDetailPresenter(this);
        //获取数据
        goodsDetailPresenter.getGoodsDetail(goodsId);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    public void onClick(View view) {
        finish();
    }

    @Override
    public void getGoodsDetailSuccess(GoodsDetail goodsDetail) {
        toolbar.setTitle(goodsDetail.getName());
        detailContent.setText(goodsDetail.getDescription());
    }

    @Override
    public void getGoodsDetailError(Throwable throwable) {
        Toast.makeText(this,"获取商品数据失败",Toast.LENGTH_LONG).show();
    }
}
