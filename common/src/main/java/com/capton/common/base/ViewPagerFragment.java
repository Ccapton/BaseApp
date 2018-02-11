package com.capton.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.capton.common.R;

import java.util.List;

/**
 * Created by capton on 2018/1/25.
 */

public class ViewPagerFragment extends Fragment{

    private PagerAdapter pagerAdapter;
    private List<Fragment> fragmentList;
    private List<String> fragmentNameList;
    private ViewPager viewpager;
    private TabLayout tablayout;

    private static ViewPagerFragment fragment;
    public static ViewPagerFragment getInstance(){
        if(fragment == null)
            fragment = new ViewPagerFragment();
        return fragment;
    }

    public List<String> getFragmentNameList() {
        return fragmentNameList;
    }

    public void setFragmentNameList(List<String> fragmentNameList) {
        this.fragmentNameList = fragmentNameList;
    }

    public void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }

    public List<Fragment> getFragmentList() {
        return fragmentList;
    }

    public PagerAdapter getPagerAdapter() {
        return pagerAdapter;
    }

    public void setPagerAdapter(PagerAdapter pagerAdapter) {
        this.pagerAdapter = pagerAdapter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager,container,false);
        viewpager = view.findViewById(R.id.viewpager);
        tablayout = view.findViewById(R.id.tablayout);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(fragmentList != null) {
            pagerAdapter = new FragmentPagerAdapter(getFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return fragmentList.get(position);
                }

                @Override
                public int getCount() {
                    return fragmentList.size();
                }

                @Override
                public CharSequence getPageTitle(int position) {
                    if(fragmentNameList == null)
                        return "";
                    return fragmentNameList.get(position);
                }
            };

            viewpager.setAdapter(pagerAdapter);
            viewpager.setOffscreenPageLimit(3);

            tablayout.setupWithViewPager(viewpager);
        }

         setTabLayoutVisible(isTabLayoutVisible);
        if(pageChangeListenerAdded && onPageChangeListener != null)
            addOnPageChangeListener(onPageChangeListener);
    }

    public void setCurrentFragment(int position,boolean smooth){
        viewpager.setCurrentItem(position,smooth);
    }
    private  boolean pageChangeListenerAdded;
    private ViewPager.OnPageChangeListener onPageChangeListener;
    public void addOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener){
        if(viewpager != null)
            viewpager.addOnPageChangeListener(onPageChangeListener);
        else {
            this.onPageChangeListener = onPageChangeListener;
            pageChangeListenerAdded = true;
        }
    }
    public void setOffscreenPageLimit(int num){
        viewpager.setOffscreenPageLimit(num);
    }

    boolean isTabLayoutVisible;
    public void setTabLayoutVisible(boolean show){
        if(tablayout != null) {
            if (show)
                tablayout.setVisibility(View.VISIBLE);
            else
                tablayout.setVisibility(View.GONE);
        }else
            isTabLayoutVisible = show;
    }
}
