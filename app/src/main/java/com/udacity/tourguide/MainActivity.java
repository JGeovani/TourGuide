package com.udacity.tourguide;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
                          implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    // index to identify current nav menu item
    public static int navItemIndex = 0;

    private static final String TAG_GUIDES      = "guides";
    private static final String TAG_ATTRACTIONS = "attractions";
    private static final String TAG_HOTELS      = "hotels";
    private static final String TAG_SHARE       = "share";
    public static String CURRENT_TAG            = TAG_GUIDES;

    private String[] activityTitles;

    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;
    // UI
    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;
    private FloatingActionButton mFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        setupFabOnClick(mFab);
        //
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        setupNavigationWithOnClick(toolbar, mDrawer, mNavigationView);
        //
        activityTitles = getResources().getStringArray(R.array.nav);
        mHandler = new Handler();
        //
        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_GUIDES;
            loadHomeFragment();//
        }
    }


    private void loadHomeFragment() {
        selectNavMenu();
        setToolbarTitle();
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            mDrawer.closeDrawers();
            toggleFab();
            return;
        }
        Runnable mPendingRunnable = new Runnable()
        {
            @Override
            public void run()
            {
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.content_main, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };
        if (mPendingRunnable != null) { // If mPendingRunnable is not null, then add to the message queue
            mHandler.post(mPendingRunnable);
        }
        toggleFab();
        mDrawer.closeDrawers();
        invalidateOptionsMenu();
    }


    private Fragment getHomeFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt(PlaceholderFragment.ARG_ARGUMENT, navItemIndex);
        PlaceholderFragment fragment = PlaceholderFragment.newInstance(navItemIndex);
        fragment.setArguments(bundle);
        return fragment;
    }


    private void toggleFab() {
        if (navItemIndex != 0) {
            mFab.hide();
        } else {
            mFab.show();
        }
    }


    private void selectNavMenu() {
        mNavigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }


    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }


    private void setupNavigationWithOnClick(Toolbar toolbar, DrawerLayout drawer, NavigationView navigation) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        {
            @Override
            public void onDrawerClosed(View drawerView)
            {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigation.setNavigationItemSelectedListener(this);
    }


    private void setupFabOnClick(FloatingActionButton fab) {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
            return;
        }
        if (shouldLoadHomeFragOnBackPress) {
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG  = TAG_GUIDES;
                loadHomeFragment();
                return;
            }
        }
        super.onBackPressed();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_guides:
                navItemIndex = 0;
                CURRENT_TAG = TAG_GUIDES;
                break;
            case R.id.nav_attractions:
                navItemIndex = 1;
                CURRENT_TAG = TAG_ATTRACTIONS;
                break;
            case R.id.nav_accommodations:
                navItemIndex = 2;
                CURRENT_TAG = TAG_HOTELS;
                break;
            case R.id.nav_share:
                navItemIndex = 3;
                CURRENT_TAG = TAG_SHARE;
                break;
            default:
                navItemIndex = 0;
        }

        if (item.isChecked()) {
            item.setChecked(false);
        } else {
            item.setChecked(true);
        }
        item.setChecked(true);

        loadHomeFragment();

        return true;
    }



    /**
     * Activities that contain this fragment must implement the
     * {@link PlaceholderFragment.OnFragmentInteractionListener} interface to handle interaction events.
     * Use the {@link PlaceholderFragment#newInstance} factory method to create an instance of this fragment.
     */
    public static class PlaceholderFragment extends Fragment
    {
        private static final String ARG_ARGUMENT = "navItemIndex";
        private int mArgument;

        public PlaceholderFragment()
        {
        }

        public static PlaceholderFragment newInstance(int position)
        {
            Bundle args = new Bundle();
            args.putInt(ARG_ARGUMENT, position);
            //
            PlaceholderFragment fragment = new PlaceholderFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
            if (getArguments() != null)
            {
                mArgument = getArguments().getInt(ARG_ARGUMENT);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            View rootView = inflater.inflate(R.layout.include_content_main, container, false);
            int[] imagesId = new int[]{
                    R.drawable.ic_insert_emoticon_black_24dp,
                    R.drawable.ic_audiotrack_black_24dp,
                    R.drawable.ic_weekend_black_24dp,
                    R.drawable.ic_menu_share,
            };
            ImageView imageView = (ImageView) rootView.findViewById(R.id.image);
            imageView.setImageResource(imagesId[mArgument]);
            //
            TextView textView = (TextView) rootView.findViewById(R.id.labelText);
            textView.setText(getString(R.string.section_format, mArgument));
            //
            String nav = getResources().getStringArray(R.array.nav)[mArgument];
            getActivity().setTitle(nav);
            //
            return rootView;
        }

        public interface OnFragmentInteractionListener
        {
            // TODO: Update argument type and name
            void onFragmentInteraction(Uri uri);
        }
    }



}
