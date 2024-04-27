package cn.task.mymvpapplication;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import cn.task.mymvpapplication.ui.base.BaseActivity;
import cn.task.mymvpapplication.ui.cart.CartFragment;
import cn.task.mymvpapplication.ui.home.HomeFragment;
import cn.task.mymvpapplication.ui.mine.MineFragment;

public class MainActivity extends BaseActivity implements NavigationBarView.OnItemSelectedListener {

    private Fragment[] fragments;
    private int lastFragmentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MyMvpApplication);
        super.onCreate(savedInstanceState);

    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    //初始化UI
    @Override
    protected void initView() {
        BottomNavigationView bottomNavigationView = find(R.id.main_bottom_NavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);

        fragments = new Fragment[]{
                new HomeFragment(),
                new CartFragment(),
                new MineFragment()
        };
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_frame,fragments[0])
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        int id  = item.getItemId();

        if (id == R.id.bottom_home){
            switchFragment(0);
        }
        if (id == R.id.bottom_cart){
            switchFragment(1);
        }
        if (id == R.id.bottom_mine){
            switchFragment(2);
        }
        return false;
    }

    public void switchFragment(int to){
        if (lastFragmentIndex == to){
            return;
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (!fragments[to].isAdded()){
            fragmentTransaction.add(R.id.main_frame,fragments[to]);
        }else {
            fragmentTransaction.show(fragments[to]);
        }
        fragmentTransaction.hide(fragments[lastFragmentIndex])
                .commitAllowingStateLoss();
        lastFragmentIndex = to;
    }
}