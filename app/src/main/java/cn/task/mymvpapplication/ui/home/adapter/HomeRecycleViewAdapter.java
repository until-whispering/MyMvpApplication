package cn.task.mymvpapplication.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.List;

import cn.task.mymvpapplication.R;
import cn.task.mymvpapplication.bean.Goods;

public class HomeRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private RecyclerView recycleView;
    private LayoutInflater layoutInflater;
    private Context context;
    private List<Goods> data;
    private OnItemClickLister lister;

    public HomeRecycleViewAdapter(RecyclerView recyclerView,Context context, List<Goods> data) {
        this.recycleView = recyclerView;
        this.context = context;
        this.data = data;
        layoutInflater = LayoutInflater.from(context);
    }

    //更新数据
    public void setGoods(List<Goods> data){
        this.data.clear(); //删除数组所有元素
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(viewType, parent, false);
        view.setOnClickListener(this); //商品详情监听
        //判断ViewHolder类型
        if (viewType == R.layout.home_recycler_image_text){
            return new MultiViewHolder(view);
        }
        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Goods goods = data.get(position);
        int itemViewType = getItemViewType(position);
        //绑定轮播图数据
        if (itemViewType == R.layout.home_recycler_banner){
            ((Banner)holder.itemView).setAdapter(new BannerImageAdapter<String>(goods.getBanners()) {
                        @Override
                        public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                            //图片加载自己实现
                            Glide.with(holder.itemView)
                                    .load(data)
                                    .apply(RequestOptions.centerCropTransform())
                                    .into(holder.imageView);
                        }
                    })
                    .addBannerLifecycleObserver((LifecycleOwner) context)//添加生命周期观察者
                    .setIndicator(new CircleIndicator(context));
        }
        //绑定图片数据
        if (itemViewType == R.layout.home_recycler_image){
            Glide.with(holder.itemView)
                    .load(goods.getImageUrl())
                    .apply(RequestOptions.centerCropTransform())
                    .into((ImageView) holder.itemView);
        }
        //绑定文本数据
        if (itemViewType == R.layout.home_recycler_text){
            ((TextView)holder.itemView).setText(goods.getText());
        }
        //同时绑定文本和图片数据
        if (itemViewType == R.layout.home_recycler_image_text){
            MultiViewHolder multiViewHolder = (MultiViewHolder) holder;
            multiViewHolder.textView.setText(goods.getText());
            Glide.with(holder.itemView)
                    .load(goods.getImageUrl())
                    .apply(RequestOptions.centerCropTransform())
                    .into(multiViewHolder.imageView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Goods goods = data.get(position);
        if (goods.getBanners() != null){//轮播图
            return R.layout.home_recycler_banner;
        }else if (goods.getImageUrl() == null){//显示文字
            return R.layout.home_recycler_image_text;
        }else if (goods.getText() == null){//显示图片
            return R.layout.home_recycler_image;
        }else {//文字+图片
            return R.layout.home_recycler_image_text;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View view) {
        if (lister != null){
            int position = recycleView.getChildAdapterPosition(view);
            lister.onItemClick(data.get(position));
        }
    }

    class SingleViewHolder extends RecyclerView.ViewHolder{

        public SingleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

     class MultiViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;

        public MultiViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            imageView = itemView.findViewById(R.id.image);
        }
    }

    public void setOnItemClickLister(OnItemClickLister lister){
        this.lister = lister;
    }

    public interface OnItemClickLister{
        void onItemClick(Goods goods);
    }
}
