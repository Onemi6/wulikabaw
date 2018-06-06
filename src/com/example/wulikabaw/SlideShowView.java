package com.example.wulikabaw;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;


/**
 * ViewPagerʵ�ֵ��ֲ�ͼ����Զ�����ͼ���義����ҳ�Ĺ���ֲ�ͼЧ����
 * ��֧���Զ��ֲ�ҳ��Ҳ֧�����ƻ����л�ҳ��
 * @author caizhiming
 *
 */

public class SlideShowView extends FrameLayout {

  //�ֲ�ͼͼƬ����
  private final static int IMAGE_COUNT = 5;
  //�Զ��ֲ���ʱ����
  private final static int TIME_INTERVAL = 5;
  //�Զ��ֲ����ÿ���
  private final static boolean isAutoPlay = true; 
  
  //�Զ����ֲ�ͼ����ԴID
  private int[] imagesResIds;
  //���ֲ�ͼƬ��ImageView ��list
  private List<ImageView> imageViewsList;
  //��Բ���View��list
  private List<View> dotViewsList;
  
  private ViewPager viewPager;
  //��ǰ�ֲ�ҳ
  private int currentItem  = 0;
  //��ʱ����
  private ScheduledExecutorService scheduledExecutorService;
  //Handler
  private Handler handler = new Handler(){

    @Override
    public void handleMessage(Message msg) {
      // TODO Auto-generated method stub
      super.handleMessage(msg);
      viewPager.setCurrentItem(currentItem);
    }
    
  };
  
  public SlideShowView(Context context) {
    this(context,null);
    // TODO Auto-generated constructor stub
  }
  public SlideShowView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
    // TODO Auto-generated constructor stub
  }
  public SlideShowView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    // TODO Auto-generated constructor stub
    initData();
    initUI(context);
    if(isAutoPlay){
      startPlay();
    }
    
  }
  /**
   * ��ʼ�ֲ�ͼ�л�
   */
  private void startPlay(){
    scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4, TimeUnit.SECONDS);
  }
  /**
   * ֹͣ�ֲ�ͼ�л�
   */
  private void stopPlay(){
    scheduledExecutorService.shutdown();
  }
  /**
   * ��ʼ�����Data
   */
  private void initData(){
    imagesResIds = new int[]{
        R.drawable.p1,
        R.drawable.p2,
        R.drawable.p3,
        R.drawable.p6,
        R.drawable.p7,
        
    };
    imageViewsList = new ArrayList<ImageView>();
    dotViewsList = new ArrayList<View>();
    
  }
  /**
   * ��ʼ��Views��UI
   */
  private void initUI(Context context){
    LayoutInflater.from(context).inflate(R.layout.activity_showview, this, true);
    for(int imageID : imagesResIds){
      ImageView view =  new ImageView(context);
      view.setImageResource(imageID);
      view.setScaleType(ScaleType.FIT_XY);
      imageViewsList.add(view);
    }
    dotViewsList.add(findViewById(R.id.v_dot1));
    dotViewsList.add(findViewById(R.id.v_dot2));
    dotViewsList.add(findViewById(R.id.v_dot3));
    dotViewsList.add(findViewById(R.id.v_dot4));
    dotViewsList.add(findViewById(R.id.v_dot5));
    
    viewPager = (ViewPager) findViewById(R.id.viewPager);
    viewPager.setFocusable(true);
    
    viewPager.setAdapter(new MyPagerAdapter());
    viewPager.setOnPageChangeListener(new MyPageChangeListener());
  }
  
  /**
   * ���ViewPager��ҳ��������
   * @author caizhiming
   */
  private class MyPagerAdapter  extends PagerAdapter{

    @Override
    public void destroyItem(View container, int position, Object object) {
      // TODO Auto-generated method stub
      //((ViewPag.er)container).removeView((View)object);
      ((ViewPager)container).removeView(imageViewsList.get(position));
    }

    @Override
    public Object instantiateItem(View container, int position) {
      // TODO Auto-generated method stub
      ((ViewPager)container).addView(imageViewsList.get(position));
      return imageViewsList.get(position);
    }

    @Override
    public int getCount() {
      // TODO Auto-generated method stub
      return imageViewsList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
      // TODO Auto-generated method stub
      return arg0 == arg1;
    }
    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
      // TODO Auto-generated method stub

    }

    @Override
    public Parcelable saveState() {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public void startUpdate(View arg0) {
      // TODO Auto-generated method stub

    }

    @Override
    public void finishUpdate(View arg0) {
      // TODO Auto-generated method stub
      
    }
    
  }
  /**
   * ViewPager�ļ�����
   * ��ViewPager��ҳ���״̬�����ı�ʱ����
   * @author caizhiming
   */
  private class MyPageChangeListener implements OnPageChangeListener{

    boolean isAutoPlay = false;

    @Override
    public void onPageScrollStateChanged(int arg0) {
      // TODO Auto-generated method stub
      switch (arg0) {
            case 1:// ���ƻ�����������
                isAutoPlay = false;
                break;
            case 2:// �����л���
            	isAutoPlay = true;
                break;
            case 0:// �������������л���ϻ��߼������
                // ��ǰΪ���һ�ţ���ʱ�������󻬣����л�����һ��
                if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                    viewPager.setCurrentItem(0);
                }
                // ��ǰΪ��һ�ţ���ʱ�������һ������л������һ��
                else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
                    viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
                }
                break;
        }
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void onPageSelected(int pos) {
      // TODO Auto-generated method stub
      
      currentItem = pos;
      for(int i=0;i < dotViewsList.size();i++){
        if(i == pos){
          ((View)dotViewsList.get(pos)).setBackgroundResource(R.drawable.dot_black);
        }else {
          ((View)dotViewsList.get(i)).setBackgroundResource(R.drawable.dot_white);
        }
      }
    }
    
  }
  
  /**
   *ִ���ֲ�ͼ�л�����
   *@author caizhiming
   */
  private class SlideShowTask implements Runnable{

    @Override
    public void run() {
      // TODO Auto-generated method stub
      synchronized (viewPager) {
        currentItem = (currentItem+1)%imageViewsList.size();
        handler.obtainMessage().sendToTarget();
      }
    }
    
  }
  /**
     * ����ImageView��Դ�������ڴ�
     * @author caizhiming
     */
    private void destoryBitmaps() {

        for (int i = 0; i < IMAGE_COUNT; i++) {
            ImageView imageView = imageViewsList.get(i);
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                //���drawable��view������
                drawable.setCallback(null);
            }
        }
    }
  
}