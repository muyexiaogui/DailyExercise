package com.cdv.viewpager2view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager中包含View
 */
public class MainActivity extends AppCompatActivity {

    /**
     * ViewPager控件
     */
    private ViewPager  mViewPager;
    /**
     * 底部的指示器控件
     */
    private LinearLayout mDotLL;
    /**
     * ViewPager的数据源
     * 其中存放的为ViewPager的布局View
     */
    private List<View> views;
    /**
     * ViewPager中的布局
     */
    private View viewOne,viewTwo;
    /**
     * ViewPager 的适配器
     */
    private ViewPagerAdapter mAdpter;
    /**
     * 当前显示的是第几页
     */
    private int curIndex = 0;

    /**
     * 布局加载起
     */
    private  LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInflater = LayoutInflater.from(this);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mDotLL = (LinearLayout) findViewById(R.id.ll_dot);


        views = new ArrayList<>();
        viewOne = mInflater.inflate(R.layout.layout_one, null);
        viewTwo = mInflater.inflate(R.layout.layout_two, null);
        views.add(viewOne);
        views.add(viewTwo);
        mAdpter = new ViewPagerAdapter(views);
        mViewPager.setAdapter(mAdpter);
        mViewPager.setCurrentItem(curIndex);
        mViewPager.setOffscreenPageLimit(2);

        setOvalLayout();


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // 取消圆点选中
                mDotLL.getChildAt(curIndex)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_normal);
                // 圆点选中
                mDotLL.getChildAt(position)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_selected);
                curIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /**
     * 设置圆点
     */
    public void setOvalLayout() {
        for (int i = 0; i < views.size(); i++) {
            mDotLL.addView(mInflater.inflate(R.layout.dot, null));
        }
        // 默认显示第一页
        mDotLL.getChildAt(0).findViewById(R.id.v_dot)
                .setBackgroundResource(R.drawable.dot_selected);
    }
}