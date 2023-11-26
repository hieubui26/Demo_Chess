package com.mad.g1.bui_minh_hieu.demo_chess.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mad.g1.bui_minh_hieu.demo_chess.Adapter.ViewPagerAdapter;
import com.mad.g1.bui_minh_hieu.demo_chess.R;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottom;
    private ViewPager viewPager;
    private Button btnfab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottom = findViewById(R.id.bottom);
        viewPager = findViewById(R.id.view);
        btnfab = findViewById(R.id.btnfab);
        btnfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottom.getMenu().findItem(R.id.menuList).setChecked(true);
                        break;
                    case 1:
                        bottom.getMenu().findItem(R.id.menuInfo).setChecked(true);
                        break;
                    case 2:
                        bottom.getMenu().findItem(R.id.menuSearch).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menuList) {
                    viewPager.setCurrentItem(0);
//                } else if (item.getItemId() == R.id.menuInfo) {
//                    viewPager.setCurrentItem(1);
//                } else if (item.getItemId() == R.id.menuSearch) {
//                    viewPager.setCurrentItem(2);
                } else {
                    viewPager.setCurrentItem(0);  // Giá trị mặc định nếu không có trường hợp nào khớp
                }
                return true;
            }
        });

        btnfab.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
        });
    }
}