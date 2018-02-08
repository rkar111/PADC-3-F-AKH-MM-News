package xyz.arkarhein.news.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.arkarhein.news.MMNewsApp;
import xyz.arkarhein.news.R;
import xyz.arkarhein.news.adapters.NewsAdapter;
import xyz.arkarhein.news.data.model.LoginUserModel;
import xyz.arkarhein.news.data.model.NewsModel;
import xyz.arkarhein.news.data.vo.NewsVO;
import xyz.arkarhein.news.delegates.BeforeLoginDelegate;
import xyz.arkarhein.news.delegates.LogInUserDelegate;
import xyz.arkarhein.news.delegates.NewsActionDelegate;
import xyz.arkarhein.news.events.LoadedNewsEvent;
import xyz.arkarhein.news.viewpods.AccountControlViewPod;
import xyz.arkarhein.news.viewpods.BeforeLoginViewPod;

public class MainActivity extends AppCompatActivity
        implements NewsActionDelegate, BeforeLoginDelegate, LogInUserDelegate {

    @BindView(R.id.rv_news)
    RecyclerView rvNews;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private NewsAdapter mNewsAdapter = new NewsAdapter(this);

    private AccountControlViewPod vpAccountControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_all_news);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_24dp);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);

        rvNews.setLayoutManager(linearLayoutManager);

        /*GridLayoutManager gridLayoutManager=new GridLayoutManager(getApplicationContext(),2);
        rvNews.setLayoutManager(gridLayoutManager);*/

        rvNews.setAdapter(mNewsAdapter);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_news_by_category) {
                    Intent intent = new Intent(getApplicationContext(), NewsByCategoryActivity.class);
                    startActivity(intent);
                }


                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        vpAccountControl = (AccountControlViewPod) navigationView.getHeaderView(0);
        vpAccountControl.setDelegate((BeforeLoginDelegate) this);
        vpAccountControl.setDelegate((LogInUserDelegate) this);

        NewsModel.getsObjInstance().loadNews();

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            //request call phone permission
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                String numberToCall = "+959963381811";
                callToNumber(numberToCall);

            }
        }
    }

    @OnClick(R.id.fab)
    public void onTapFab(View view) {
       /* Snackbar.make(view, "Replace with your own action - ButterKnife", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        String numberToCall = "+959963381811";
        callToNumber(numberToCall);*/
        showConfirmDialog();

    }

    private void callToNumber(String numberToCall) {
        Uri numberToCallUri = Uri.parse("tel:" + numberToCall);
        Intent intentToCall = new Intent(Intent.ACTION_CALL, numberToCallUri);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    100);
            return;
        }
        startActivity(intentToCall);
    }

    private void showConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Order Success")
                .setCancelable(true)
                .setMessage(getResources().getString(R.string.msg_to_exit, LoginUserModel.getsObjInstance(getApplicationContext()).getmLogInUSer().getName()))
                .setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Snackbar.make(rvNews, "Ok. You will exit in an hour.", Snackbar.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "This is the right choice.", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onTapNewsItem(NewsVO news) {
        Intent intent = new Intent(getApplicationContext(), NewsDetailsActivity.class);
        intent.putExtra("news_id", news.getNewsId());
        startActivity(intent);
    }

    @Override
    public void onTapCommentButton() {

    }

    @Override
    public void onTapSendButton(NewsVO news) {
        //Intent intent=new Intent(Intent.ACTION_SEND);

        Intent shareIntent = ShareCompat.IntentBuilder
                .from(this)
                .setType("text/plain")
                .setText(news.getBrief())
                .getIntent();

        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(shareIntent);
        } else {
            Snackbar.make(rvNews, "No app to handle share action.", Snackbar.LENGTH_INDEFINITE);
        }


    }

    @Override
    public void onTapFavoriteButton() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewsLoaded(LoadedNewsEvent event) {
        Log.d(MMNewsApp.LOG_TAG, "onNewsLoaded" + event.getNewsList().size());
        mNewsAdapter.setNews(event.getNewsList());
    }

    @Override
    public void onTapToLogin() {
        Intent intent = AccountControlActivity.newIntentLogin(getApplicationContext());
        startActivity(intent);
    }

    @Override
    public void onTapToRegister() {
        Intent intent = AccountControlActivity.newIntentRegister(getApplicationContext());
        startActivity(intent);
    }

    @Override
    public void onTapLogout() {
        LoginUserModel.getsObjInstance(getApplicationContext()).logout();
    }
}
