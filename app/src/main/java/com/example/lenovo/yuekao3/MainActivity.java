package com.example.lenovo.yuekao3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private TabLayout tab;
    private ViewPager vp;
    private SharedPreferences.Editor edit;
    private List<Bean> list;
    private SharedPreferences sp;
    private String jsonstr;
    private SharedPreferences bu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("msg", MODE_PRIVATE);
        edit = sp.edit();
        initView();
        initDate();
        setOnclick();
    }
    private void initDate() {
        list = new ArrayList<Bean>();
        bu = getSharedPreferences("bu", MODE_PRIVATE);
        boolean flag = bu.getBoolean("flag", true);
        if(flag)
        {
            list.add(new Bean("头条1",true,new Fragment1()));
            list.add(new Bean("头条2",true,new Fragment1()));
            list.add(new Bean("头条3",true,new Fragment1()));
            list.add(new Bean("头条4",true,new Fragment1()));
            VpAdadpter adadpter=new VpAdadpter(getSupportFragmentManager());
            vp.setAdapter(adadpter);
            tab.setupWithViewPager(vp);
        }
           jsonstr =sp.getString("user_setting",null);
            if(jsonstr!=null)
            {
                list.clear();
                try {
                    JSONArray array=new JSONArray(jsonstr);
                    for (int i = 0; i <array.length() ; i++) {
                        JSONObject ob = (JSONObject) array.get(i);
                        String name = ob.getString("name");
                        boolean isSelect = ob.getBoolean("isSelect");
                        if(isSelect==true)
                        {
                            if(name.equals("头条1"))
                            {
                                list.add(new Bean("头条1",true,new Fragment1()));
                            }
                            else if(name.equals("头条2"))
                            {
                                list.add(new Bean("头条2",true,new Fragment1()));
                            }
                            else if(name.equals("头条3"))
                            {
                                list.add(new Bean("头条3",true,new Fragment1()));
                            }
                            else if(name.equals("头条4"))
                            {
                                list.add(new Bean("头条4",true,new Fragment1()));
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
             VpAdadpter adadpter=new VpAdadpter(getSupportFragmentManager());
            vp.setAdapter(adadpter);
            tab.setupWithViewPager(vp);
        }

    private void setOnclick() {
        iv.setOnClickListener(new View.OnClickListener() {

            private List<ChannelBean> cblist;

            @Override
            public void onClick(View view) {
                cblist = new ArrayList<ChannelBean>();
                jsonstr=sp.getString("user_setting",null);
                if(jsonstr==null)
                {
                    cblist.add(new ChannelBean("头条1",true));
                    cblist.add(new ChannelBean("头条2",true));
                    cblist.add(new ChannelBean("头条3",true));
                    cblist.add(new ChannelBean("头条4",true));
                    ChannelActivity.startChannelActivity(MainActivity.this, cblist);
                }
                else
                {
                    ChannelActivity.startChannelActivity(MainActivity.this,jsonstr);
                }
                bu.edit().putBoolean("flag",false).commit();
            }
        });
    }
    private void initView() {
        iv = (ImageView) findViewById(R.id.iv);
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==101)
        {
            jsonstr = data.getStringExtra(ChannelActivity.RESULT_JSON_KEY);
            edit.putString("user_setting",jsonstr).commit();
            recreate();
        }
    }
    class VpAdadpter extends FragmentPagerAdapter
    {

        public VpAdadpter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position).getFragment();
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position).getName();
        }
    }

}
