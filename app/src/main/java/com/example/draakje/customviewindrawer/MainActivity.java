package com.example.draakje.customviewindrawer;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,MainViewFragment.OnFragmentInteractionListener, CustomViewFragment.OnFragmentInteractionListener, MoreViewsFragment.OnFragmentInteractionListener {

    private NavigationView navigationView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //3 bolletjes
        getMenuInflater().inflate(R.menu.main, menu);
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
    public boolean onNavigationItemSelected(MenuItem item) {

        DrawerLayout drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_main_view) {

            mToolbar.setTitle(R.string.title_section1);
            fragment = new MainViewFragment();

        } else if (id == R.id.nav_custom_view) {

            mToolbar.setTitle(R.string.title_section2);
            fragment = new CustomViewFragment();

        } else if (id == R.id.nav_more_view) {

            mToolbar.setTitle(R.string.title_section3);
            fragment = new MoreViewsFragment();

        }
        if (fragment !=null) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
            fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    Fragment f = getSupportFragmentManager().findFragmentById(R.id.container);
                    updateTitleAndDrawer(f);
                }

                private void updateTitleAndDrawer(Fragment f) {
                    if (f != null) {
                        String fragClassName = f.getClass().getName();
                        if (fragClassName.equals(MainViewFragment.class.getName())) {

                            mToolbar.setTitle(R.string.title_section1);
                        } else if (fragClassName.equals(CustomViewFragment.class.getName())) {
                            mToolbar.setTitle(R.string.title_section2);
                        } else if (fragClassName.equals(MoreViewsFragment.class.getName())) {
                            mToolbar.setTitle(R.string.title_section3);
                        }
                        else {
                            mToolbar.setTitle(R.string.main_fragment_text);
                        }
                    }
                }});
            drawerLayout.closeDrawer(GravityCompat.START);

        }
        else {
            Log.e("Navigation Drawer", "Error in creating fragment");
        }
        return true;

}
    public void onFragmentInteraction (Uri uri) {

    }
}
