package com.ramavathubalaji.lost_found;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Main22Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView profile_name;
    private FirebaseAuth firebaseAuth;
    String email,name;
    public static TabLayout tabs22;
    public static FloatingActionButton fab;


    private ViewPager mViewPager;
    private ViewPagerAdapter viewPagerAdapter;
    public String lf="lost";
    String plf;
    int i;

    static Main22Activity INSTANCE;

    HashMap<String, Object> user;
    int pos;
    int f;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        INSTANCE=this;
        setContentView(R.layout.activity_main22);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar22);
        setSupportActionBar(toolbar);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater li = LayoutInflater.from(this);
        View customView = li.inflate(R.layout.custom_appbar_title, null);
        mActionBar.setCustomView(customView);
        mActionBar.setDisplayShowCustomEnabled(true);
        TextView addContent = (TextView)    customView.findViewById(R.id.appbar_title);
        addContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ...
            }
        });
        tabs22 = (TabLayout) findViewById(R.id.tabs_main22);

        mViewPager = (ViewPager) findViewById(R.id.container22);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new LostFragment(),"LOST");
        viewPagerAdapter.addFragment(new FoundFragment(),"FOUND");
        mViewPager.setAdapter(viewPagerAdapter);

        tabs22.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs22));




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab22);
        Drawable drawable=getResources().getDrawable(R.drawable.gradient1);
        fab.setBackgroundDrawable(drawable);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getApplicationContext(), PostActivity.class);
                finish();
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_signout).setTitle("Sign  Out");
        menu.findItem(R.id.nav_change_password).setTitle("Change password");

        /*menu.findItem(R.id.nav_share).setVisible(false);*/
        menu.findItem(R.id.nav_your_posts).setTitle("Your Posts");
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.tvprofilename);
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        email=firebaseAuth.getCurrentUser().getEmail().toString();
        navUsername.setText(email);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            if(f==0)
            {
                Toast.makeText(Main22Activity.this,"Press back once more to exit",Toast.LENGTH_LONG);
                f++;
            }
            else {
                f=0;
                super.onBackPressed();
            }



        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main22, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_signout)
        {
            //sign out
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

            // Handle the camera action
        }
        else if (id == R.id.nav_change_password)
        {
            //change pasword
            Intent i=new Intent(Main22Activity.this,ChangePassword.class);
            finish();
            startActivity(i);

        }
        else if (id == R.id.nav_your_posts)
        {
            //Your posts
            Intent i=new Intent(Main22Activity.this,YourPosts.class);
            if(lf!=null)
            {
                i.putExtra("lf",lf);
            }
            else
            {
                i.putExtra("lf",plf);

            }
            finish();
            startActivity(i);


        }
        else if (id == R.id.nav_chats)
        {
            Intent i=new Intent(Main22Activity.this,ChatsActivity.class);
            finish();
            startActivity(i);

        }
        /*else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public Main22Activity getInstance()
    {
        return INSTANCE;
    }

}
