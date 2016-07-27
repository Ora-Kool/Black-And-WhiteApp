package xyz.bhent.production.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import xyz.bhent.production.PreManager;
import xyz.bhent.production.R;

public class SplashScreen extends AppCompatActivity{
    private ViewPager viewPager;
    private BlackViewPagerAdapter blachViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext;
    private PreManager preManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preManager = new PreManager(this);
        if(!preManager.isFirstLaunch()){
            launchHomeScreen();
            finish();
        }

        //Making  notification bar transparent
        if(Build.VERSION.SDK_INT >= 21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.splashlayout);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout)findViewById(R.id.layoutDots);
        btnNext = (Button)findViewById(R.id.btn_next);
        btnSkip = (Button)findViewById(R.id.btn_skip);




        //layouts of all  sliders
        layouts = new int[]{
                R.layout.slide1,
                R.layout.slide2,
                R.layout.slide3
        };
        //add the dots at the bottom
        addBottomDots(0);

        //making notification bar transparent
        changeStatusBarColor();

        blachViewPagerAdapter = new BlackViewPagerAdapter();
        viewPager.setAdapter(blachViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageListener);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                launchHomeScreen();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check  for last page
                //if last page home screen will be launched
                int current = getItem(+1);
                if(current < layouts.length){
                    //moving to the next screen
                    viewPager.setCurrentItem(current);
                }else{
                    launchHomeScreen();
                }
            }
        });
    }



    private void addBottomDots(int currentPage){
        dots = new TextView[layouts.length];

        //defining the colors of the dots when a page is active or inactive
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInActive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews(); //clear the dotslayout if there is any view in it
        for(int i = 0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(40);
            //dots[i].setBackgroundResource(R.drawable.ring);
            dots[i].setTextColor(colorsInActive[currentPage]); // setting the dot color in relation
            //to the current view
            dotsLayout.addView(dots[i]);
        }
        if(dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }
    private void launchHomeScreen(){
        preManager.setFirstLaunch(false);
        startActivity(new Intent(SplashScreen.this, BlacknWhiteHome.class));
        finish();
    }

    //view pager change listener
    ViewPager.OnPageChangeListener viewPagerPageListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            //changing the next button text ie, next - Got it
            if(position == layouts.length - 1){

                //last page, makes button text to got it
                btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);
            } else{

                //still having more pages to the left
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /**
     * Making the notification  bar transparent
     */
    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * View Pager adapter
     */
    public class BlackViewPagerAdapter extends PagerAdapter{
        private LayoutInflater layoutInflater;

        public BlackViewPagerAdapter(){}

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);

            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View)object;
            container.removeView(view);
        }
    }


}
