package com.genius.quizapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.genius.quizapp.R;

import com.genius.quizapp.fragment.LoginActivityFragment;

import com.genius.quizapp.fragment.SignUpFragment;
import com.genius.quizapp.helper.Session;

import java.util.ArrayList;
import java.util.List;


public class LoginTabActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private TabLayout tabLayout;
    public static TextView coins;
    public static TextView score, coin, tvName;
    public static ViewPagerAdapter adapter;
    private String[] tabs;
    public static FirebaseAuth mAuth;
    public static boolean isNewuser=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablogin);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tabs = new String[]{getString(R.string.act_login), getString(R.string.new_act)};
        viewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        if (Session.isLogin(LoginTabActivity.this)) {
            Intent intent = new Intent(LoginTabActivity.this, MainActivity.class);
            intent.putExtra("type", "default");
            startActivity(intent);
            finish();

        }
        /*tabLayout.setSelectedTabIndicatorColor(Color.TRANSPARENT);*/
        tabLayout.setupWithViewPager(viewPager);
        mAuth = FirebaseAuth.getInstance();
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new LoginActivityFragment(), tabs[0]);
        adapter.addFrag(new SignUpFragment(), tabs[1]);
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {

            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
