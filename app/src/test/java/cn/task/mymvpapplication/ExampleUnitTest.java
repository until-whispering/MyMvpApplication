package cn.task.mymvpapplication;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

import cn.task.mymvpapplication.bean.BaseBean;
import cn.task.mymvpapplication.bean.Goods;
import cn.task.mymvpapplication.network.RetrofitClient;
import cn.task.mymvpapplication.network.service.GoodsService;
import io.reactivex.rxjava3.functions.Consumer;
import retrofit2.Retrofit;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);

        GoodsService goodsService = RetrofitClient.getInstance().getService(GoodsService.class);
        goodsService.getGoods().subscribe(new Consumer<BaseBean<List<Goods>>>() {
            @Override
            public void accept(BaseBean<List<Goods>> listBaseBean) throws Throwable {
                System.out.println("得到数据");
                System.out.println(listBaseBean);
            }
        });

        while (true){

        }



    }
}