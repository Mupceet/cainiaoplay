package com.cniao5.cniao5play.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.cniao5.cniao5play.R;
import com.cniao5.cniao5play.di.component.AppComponent;
import com.cniao5.cniao5play.ui.adapter.ViewPagerAdapter;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).titleBar(R.id.tool_bar).init();
    }

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        initDrawerLayout();
        initTabLayout();
    }

    private void initTabLayout() {
        PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initDrawerLayout() {
        headerView = mNavigationView.getHeaderView(0);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "headerView clicked", Toast.LENGTH_LONG).show();
            }
        });
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_app_update:
                        Toast.makeText(MainActivity.this, "点击了应用更新", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_message:
                        Toast.makeText(MainActivity.this, "点击了消息", Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });

        mToolBar.inflateMenu(R.menu.toolbar_menu);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.open, R.string.close);

        drawerToggle.syncState();

        mDrawerLayout.addDrawerListener(drawerToggle);
    }
}
