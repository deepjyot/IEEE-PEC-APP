package com.example.hp.ieeepec;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    //a list to store all the products
    List<Notification> notificationList;

    //the recyclerview
    RecyclerView recyclerView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        notificationList = new ArrayList<>();


        //adding some items to our list
        notificationList.add(
                new Notification(
                        "First GBM",
                        "Please gather for the meeting",
                        "Date: 3rd October 2018",
                        "Time: 5 pm",
                        "Venue: L21",
                        R.drawable.ic_announcement_black_24dp));

        //creating recyclerview adapter
        NotificationAdapter adapter = new NotificationAdapter(this, notificationList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
//                        mDrawerLayout.closeDrawers();
                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        switch(menuItem.getItemId()) {
                            case R.id.menu_about_us:
                                startActivity(new Intent(NotificationActivity.this, AboutUs.class));
                                break;
                            case R.id.menu_profile:
                                startActivity(new Intent(NotificationActivity.this, MyProfile.class));
                                break;
                            case R.id.menu_home:
                                startActivity(new Intent(NotificationActivity.this, RSSFeedActivity.class));
                                break;
                            case R.id.menu_notification:
                                startActivity(new Intent(NotificationActivity.this, NotificationActivity.class));
                                break;
                        }
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

}

