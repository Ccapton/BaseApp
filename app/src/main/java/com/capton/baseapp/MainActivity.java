package com.capton.baseapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.capton.baseapp.databinding.ActivityMainBinding;
import com.capton.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends BaseActivity<ActivityMainBinding> {


    @Override
    public String[] getPermissions() {
        return requestPermissions;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }



    @Override
    public void clickMore() {

    }

    @Override
    public void clickRightText() {

    }

     MainFragment mainFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


       /* IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("去注册");
        intentFilter.addAction("去登录");
        intentFilter.addAction("去更新");
        intentFilter.addAction("登录成功");
        intentFilter.addAction("更新成功");

        registerReceiver(broadcastReceiver,intentFilter);

        mainFragment = new MainFragment();
        FragmentUtils.add(getSupportFragmentManager(),mainFragment,R.id.fragmentContainer);
*/

    }

    DemoAdapter adapter;
    Map<Integer,Integer> resIdMap;
    List<String> data;
    @Override
    protected void yourOperation() {
        setTitle(R.string.app_name);
        setShowRightText(true);
        setRightText("更多");

        resIdMap = new HashMap<>();
        resIdMap.put(0,R.layout.item_left);
        resIdMap.put(1,R.layout.item_right);
        data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add("Demo Hello World! "+i);
        }
        adapter = new DemoAdapter(this,data,resIdMap);
       // adapter = new DemoAdapter(this,data,R.layout.item_left);
        binding.rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.rv.setAdapter(adapter);

    }

   /* BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if("去注册".equals(intent.getAction())){
                setTitle("注册");
            }
            if("去登录".equals(intent.getAction())){
                setTitle("去登录");
            }
            if("去更新".equals(intent.getAction())){
                setTitle("去更新");
            }
            if("登录成功".equals(intent.getAction())||"更新成功".equals(intent.getAction())){
                 setTitle(R.string.app_name);
                 FragmentUtils.hide(getSupportFragmentManager());
                 FragmentUtils.show(mainFragment);
            }
        }
    };

    @Override
    protected void onDestroy() {
        unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }*/

    @Override
    public void onBackPressed() {
      //  FragmentUtils.hide(getSupportFragmentManager());
      //  FragmentUtils.show(mainFragment);
        super.onBackPressed();
    }
}
