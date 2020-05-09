package g.sns_test;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    private ViewPager viewPager;
    private TabLayout tabLayout;

    AllthingsActivity fragment1;
    ListFragment fragment2;

  //  private final String INTENT_FILTER_ACTION = "com.example.sns.SNS_Notification";
 //   public static boolean uploadClicked = false;
//뒤로가기 동작 버튼
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.home:{
//                finish();
//                return true;
//            }
//        }
//        return super.onOptionsItemSelected(item);
//    }



    // public static TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate 호출");

         fragment1 = new AllthingsActivity(); //
         fragment2 = new ListFragment(); //

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setTitle("Dcrew");

        //왼쪽 상단 메뉴
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

//        View viewToolbar = getActivity().getLayoutInflater().inflate(R.layout.activitiy_searchlist,null);
//
//        actionBar.setCustomView(viewToolbar, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));

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


        //탭호스트에 들어갈 버튼에 아이콘 이미지가 담긴 뷰를 넣어주기 위해 선언하는 뷰 인스턴스
        //내 동아리가기
//        View mycrewView  = LayoutInflater.from(MainActivity.this).inflate(R.layout.mycrew, null);
//        //대학교
//        View universityView = LayoutInflater.from(MainActivity.this).inflate(R.layout.university, null);
//        //카테고리
//        View categoryView = LayoutInflater.from(MainActivity.this).inflate(R.layout.category, null);
//        //동아리 연합
//        View uniteView = LayoutInflater.from(MainActivity.this).inflate(R.layout.unite, null);
//        //모두의 게시판
//        View allthingsView = LayoutInflater.from(MainActivity.this).inflate(R.layout.allthings, null);
//        //마이 페이지 뷰
//        View mypageView = LayoutInflater.from(MainActivity.this).inflate(R.layout.mypage, null);

        //    iv_notificationDot = findViewById(R.id.imageview_notification_dot);

      //  Toolbar toolbar =findViewById(R.id.toolbar);
     //   setSupportActionBar(toolbar);


        //탭 호스트 레이아웃과 연결
//        tabHost = findViewById(R.id.tabHost);
//        //탭 호스트에 탭뷰를 추가해주기 전에 셋업 반드시!!
//        tabHost.setup();
//
//        tabHost.addTab(tabHost.newTabSpec("crew")
//                .setIndicator("mycrew")
//                .setContent(new Intent(this, mycrewActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)));
//
//        tabHost.addTab(tabHost.newTabSpec("university")
//                .setIndicator("대학교")
//                .setContent(new Intent(this, universityActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)));
//
//        tabHost.addTab(tabHost.newTabSpec("category")
//                .setIndicator("카테고리")
//                .setContent(new Intent(this, categoryActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)));
//
//        tabHost.addTab(tabHost.newTabSpec("unite")
//                .setIndicator("동아리연합")
//                .setContent(new Intent(this, uniteActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)));
//
//        tabHost.addTab(tabHost.newTabSpec("allthings")
//                .setIndicator("모두의 게시판")
//                .setContent(new Intent(this, allthingsActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)));
//
//        tabHost.addTab(tabHost.newTabSpec("mypage")
//                .setIndicator("me")
//                .setContent(new Intent(this, mypageActivity.class).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);

        SearchView searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("검색해주세요");



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String s) {
                Toast.makeText(getApplicationContext(),"입력중입니다.",Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(getApplicationContext(),"검색을 완료했습니다.",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

//        MenuItem item_like = menu.add(0,0,0,"히든 메뉴");
//        item_like.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
//            public boolean onMenuItemClick(MenuItem item){
//                Intent intent = new Intent(getApplicationContext(),SearchListActivity.class);
//                intent.putExtra("Count",12);
//                startActivity(intent);
//                return true;
//            }
//        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       int id = item.getItemId();
       if(id == R.id.action_search){
           return true;

        }
        return super.onOptionsItemSelected(item);
    }

    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();



    //프래그먼트와 프래그먼트끼리 직접접근을하지않는다. 프래그먼트와 엑티비티가 접근함
    public void onFragmentChange(int index){
        if(index == 1 ){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();  //에러 발생
            //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment2).commit();
            //getSupportFragmentManager().beginTransaction().replace(R.id.viewPager ,fragment2).commit();
        }
//else if(index == 1)
        /*
        else{
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
        }
*/
    }

}
