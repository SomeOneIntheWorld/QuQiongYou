package dream.quqiongyou.main.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import dream.quqiongyou.R;
import dream.quqiongyou.home.widget.HomeFragment;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public class MainActivity extends AppCompatActivity {
    private final static String ACCOUNT = "account";
    @BindView(R.id.main_viewpager)
    ViewPager mViewPager;
    private MainPagerAdapter mMainPagerAdapter;

    public static void startMainActivity(Context context, String account){
        Intent intent = new Intent(context,MainActivity.class);
        intent.putExtra(ACCOUNT,account);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mMainPagerAdapter);
    }

    private class MainPagerAdapter extends FragmentStatePagerAdapter {
        private static final int NUM_PAGES = 3;

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    return new HomeFragment();
                case 1:
                    return new HomeFragment();
                case 2:
                    return new HomeFragment();
                default:
                    return new HomeFragment();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
