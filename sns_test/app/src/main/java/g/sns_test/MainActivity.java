package g.sns_test;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements OnQueryTextListener {
    private String TAG = "MainActivity";
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private DrawerLayout mDrawerLayout;
    private Context context = this;

    AllthingsFragment allthingsfragment;
    ListFragment listfragment;
    MyPageFragment mypagefragment;

    DrawerLayout drawerLayout;
    ListView listView;
    ArrayAdapter<String> adapterDrawerList;
    String[] menuItems = new String[] {"MyPageFragment"};

    // public static TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate 호출");

        allthingsfragment = new AllthingsFragment(); //
        listfragment = new ListFragment(); //
       // insertpost = new InsertPostActivity();

        //툴바 ->액션바
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //액션바
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Dcrew");

        //왼쪽 상단 메뉴
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        // 검색문 쿼리 수신하기
        Intent intent = getIntent();
        if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }

        //왼쪽 상단 네비게이션 드로어
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        //버튼이 클릭된 경우 openDrawer()메소드를 이용하고 매개변수로 GravityCompat.START를 넘긴다
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mypagefragment = mypagefragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.drawer_layout,mypagefragment).commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View nav_header_view = navigationView.getHeaderView(0);  //네비게이션 헤더 값 변경을 위해
        TextView textNic =(TextView) nav_header_view.findViewById(R.id.textNickname);
        //textNic.setText(LoginUser.getAccount());
        textNic.setText(LoginUser.getNickname());
        //textNic.setText("nick");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuitem) {
                menuitem.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id = menuitem.getItemId();

                if (id == R.id.Login){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else if(id==R.id.mypage){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.drawer_layout, mypagefragment).commit();
                } else if (id == R.id.dcrewmark) {

                } else if(id == R.id.notion){
                    Toast.makeText(context,  "공지", Toast.LENGTH_SHORT).show();
                } else if(id == R.id.tip){
                    Toast.makeText(context, ": 팁", Toast.LENGTH_SHORT).show();
                }

                //drawerLayout.closeDrawer(listView);
                return true;
            }

        });

        //탭
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("mycrew"));
        tabLayout.addTab(tabLayout.newTab().setText("home"));
        tabLayout.addTab(tabLayout.newTab().setText("대학교"));
        tabLayout.addTab(tabLayout.newTab().setText("카테고리"));
        tabLayout.addTab(tabLayout.newTab().setText("동아리연합"));
        tabLayout.addTab(tabLayout.newTab().setText("모두의 게시판"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = findViewById(R.id.viewPager);
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
/*
        // 로그인창으로 액티비티 전환
        Button LoginButton = (Button) findViewById(R.id.loginButton);
        LoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
*/
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
        {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        super.onBackPressed();
    }


    //네비게이션 메뉴 클릭
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2){
            SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
            searchView.setMaxWidth(Integer.MAX_VALUE);
            searchView.setQueryHint("검색해주세요");

            // 쿼리를 구현할 리스너
            searchView.setOnQueryTextListener(this);
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            if (null != searchManager) {
                searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            }
            //추천 검색어의 쿼리 조정
            searchView.setQueryRefinementEnabled(true);
            //검색버튼 누르면 펼치기
            searchView.setIconifiedByDefault(true);
////처리 로직 구현 코드 나중에 붙이기 (데베 연결)
    }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("test2","onOptionsItemSelected 호출");

        switch (item.getItemId()) {

            case R.id.action_search:
                return true;

            case R.id.home:
            {
                Toast.makeText(context,  "네비게이션 열림을 확인합니다.",Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextChange(String s) {
        // Toast.makeText(getApplicationContext(),"입력중입니다.",Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        //  Toast.makeText(getApplicationContext(),"검색을 완료했습니다.",Toast.LENGTH_SHORT).show();
        return false;
    }

    private void doMySearch(String query) {
    }


   // FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

    // 게시판 프래그먼트 전환
    //프래그먼트와 프래그먼트끼리 직접접근을하지않는다. 프래그먼트와 엑티비티가 접근함
    public void onFragmentChange(int index){
        if(index == 1 ){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, listfragment).commit();
            //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment2).commit();
            //getSupportFragmentManager().beginTransaction().replace(R.id.viewPager ,fragment2).commit();
        }


        else if(index == 2){
            //getSupportFragmentManager().beginTransaction().replace(R.id.container, insertpost).commit();
        }
    }

}
