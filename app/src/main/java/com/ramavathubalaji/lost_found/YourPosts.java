package com.ramavathubalaji.lost_found;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class YourPosts extends AppCompatActivity {


    public  String lf;
    Boolean update;
    public static TabLayout tabs;
    public static YourPosts INSTANCE;
    private ViewPager mViewPager;
    private ViewPagerAdapter viewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_posts);
        INSTANCE=this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater li = LayoutInflater.from(this);
        View customView = li.inflate(R.layout.custom_menu, null);
        mActionBar.setCustomView(customView);
        mActionBar.setDisplayShowCustomEnabled(true);
        ImageButton addContent = (ImageButton)    customView.findViewById(R.id.action_back);
        addContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Main22Activity.class);
                finish();
                startActivity(i);
            }
        });


        tabs = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.container);

        //int pos=tabs.getSelectedTabPosition();
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new YourLostFragment(),"LOST");
        viewPagerAdapter.addFragment(new YourFoundFragment(),"FOUND");
        mViewPager.setAdapter(viewPagerAdapter);
        tabs.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getApplicationContext(), PostActivity.class);
                finish();
                startActivity(i);
            }
        });*/

    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_your_posts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_back) {
            Intent i = new Intent(getApplicationContext(), Main22Activity.class);
            finish();
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }*/

    
    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(getApplicationContext(), Main22Activity.class);
        finish();
        startActivity(i);
    }

    public YourPosts getINSTANCE()
    {
        return INSTANCE;
    }
}
