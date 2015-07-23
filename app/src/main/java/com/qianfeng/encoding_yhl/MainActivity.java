package com.qianfeng.encoding_yhl;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.MenuItem;
public class MainActivity extends ActionBarActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigation;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //用于侧滑的整体布局
        drawer = ((DrawerLayout) findViewById(R.id.drawer));
        //侧滑菜单
        navigation = ((NavigationView) findViewById(R.id.navigation));
        //三横线变箭头的控件
        toggle = new ActionBarDrawerToggle(this, drawer, 0, 0);
        //toggle和drawer关联
        drawer.setDrawerListener(toggle);
        //同步当前状态
        toggle.syncState();
        //显示actionBar的Home菜单
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //菜单监听
        navigation.setNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item)||toggle.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        switch (menuItem.getItemId()){
            case R.id.Md5:
                Md5ragment md5=new Md5ragment();
                transaction.replace(R.id.containe,md5);
                break;
            case R.id.Base64:
                BaseFragment base=new BaseFragment();
                transaction.replace(R.id.containe,base);

                break;
            case R.id.Des:
                DesFragment des=new DesFragment();
                transaction.replace(R.id.containe,des);
                break;
        }
        transaction.commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
