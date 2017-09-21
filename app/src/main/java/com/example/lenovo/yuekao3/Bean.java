package com.example.lenovo.yuekao3;

import android.support.v4.app.Fragment;

/**
 * Created by lenovo on 2017/9/21.
 */

public class Bean {
    private String name;
    private Boolean state;
    private Fragment fragment;

    public Bean(String name, Boolean state, Fragment fragment) {
        this.name = name;
        this.state = state;
        this.fragment = fragment;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "name='" + name + '\'' +
                ", state=" + state +
                ", fragment=" + fragment +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
