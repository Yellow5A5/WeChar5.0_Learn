package com.V5.weixin_viewpager;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jauker.widget.BadgeView;


public class MainActivity extends FragmentActivity {

	private ViewPager mViewPager;
	private FragmentPagerAdapter mFragmentPagerAdapter;
	private List<Fragment> mData;

	private TextView title1;
	private TextView title2;
	private TextView title3;
	
	
	private BadgeView mBadgeView;
	private LinearLayout TitleLinearLayout1;
	private LinearLayout TitleLinearLayout2;
	private LinearLayout TitleLinearLayout3;
	
	private ImageView mImageView;
	
	private int lineWidth;
	
	private OnClickListener titleListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.titlelayout01:
				while (mViewPager.getCurrentItem()!=0) {
					mViewPager.arrowScroll(1);
				}
				break;
			case R.id.titlelayout02:
				if (mViewPager.getCurrentItem() == 0) {
					mViewPager.arrowScroll(2);
				}else if (mViewPager.getCurrentItem() == 2) {
					mViewPager.arrowScroll(1);
				}
				break;
			case R.id.titlelayout03:
				while (mViewPager.getCurrentItem()!=2) {
					mViewPager.arrowScroll(2);
				}
				break;

			default:
				break;
			}
			
		}
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        initTabLine();
        
        initView();
        
        initListener();
    }

	private void initListener() {
		TitleLinearLayout1.setOnClickListener(titleListener);
		TitleLinearLayout2.setOnClickListener(titleListener);
		TitleLinearLayout3.setOnClickListener(titleListener);
	}

	private void initTabLine() {
		mImageView = (ImageView) findViewById(R.id.tabline);
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        lineWidth = outMetrics.widthPixels / 3;
        LayoutParams lp = mImageView.getLayoutParams();
        lp.width = lineWidth;
        mImageView.setLayoutParams(lp);
	}

	private void initView() {
		
		title1 = (TextView) findViewById(R.id.title01);
		title2 = (TextView) findViewById(R.id.title02);
		title3 = (TextView) findViewById(R.id.title03);
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		TitleLinearLayout1 = (LinearLayout) findViewById(R.id.titlelayout01);
		TitleLinearLayout2 = (LinearLayout) findViewById(R.id.titlelayout02);
		TitleLinearLayout3 = (LinearLayout) findViewById(R.id.titlelayout03);
		mData = new ArrayList<Fragment>();
		ChatMainTabFragment fragment1 = new ChatMainTabFragment();
		FriendsTabFragment fragment2 = new FriendsTabFragment();
		ContactMainTabFragment fragment3 = new ContactMainTabFragment();
		
		mData.add(fragment1);
		mData.add(fragment2);
		mData.add(fragment3);
		
		//加入消息提醒BadgeView
		mBadgeView = new BadgeView(MainActivity.this);
		mBadgeView.setBadgeCount(2);
		TitleLinearLayout1.addView(mBadgeView);
		
		mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mData.size();
			}
			
			@Override
			public Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				return mData.get(arg0);
			}
		};
		mViewPager.setAdapter(mFragmentPagerAdapter);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				switch (position) {
				case 0:
					title1.setTextColor(Color.parseColor("#008000"));//颜色要改善一下。
					title2.setTextColor(Color.BLACK);
					title3.setTextColor(Color.BLACK);
					break;
				case 1:
					title1.setTextColor(Color.BLACK);
					title2.setTextColor(Color.parseColor("#008000"));
					title3.setTextColor(Color.BLACK);
					break;
				case 2:
					title1.setTextColor(Color.BLACK);
					title2.setTextColor(Color.BLACK);
					title3.setTextColor(Color.parseColor("#008000"));
					break;
				default:
					break;
				}
				
			}
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPx) {
				// TODO Auto-generated method stub
//				Log.e("test", position+ "---"+positionOffset+"----"+positionOffsetPx);
				LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mImageView.getLayoutParams();
				lp.leftMargin = (int) ((position + positionOffset)*lineWidth);
				mImageView.setLayoutParams(lp);
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
