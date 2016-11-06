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
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.tourguide.adapter.DwellerAdapter;
import com.udacity.tourguide.dummy.DummyContent;
import com.udacity.tourguide.model.Dweller;
import com.udacity.tourguide.util.GridSpacingItemDecoration;
import com.udacity.tourguide.util.Transformation;

import java.util.List;


public class MainActivity extends AppCompatActivity
                          implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static int navItemIndex = 0;

    private static final String TAG_GUIDES      = "guides";
    private static final String TAG_BREAKFAST   = "breakfast";
    private static final String TAG_LUNCH       = "lunch";
    private static final String TAG_SNACK       = "snack";
    private static final String TAG_DINNERS     = "dinners";
    private static final String TAG_DRINKS      = "drinks";
    private static final String TAG_ATTRACTIONS = "attractions";
    private static final String TAG_HOTELS      = "hotels";
    private static final String TAG_SHARE       = "share";
    private static final String TAG_ABOUT       = "about";
    public static String CURRENT_TAG            = TAG_GUIDES;

    private static String[] activityTitles;

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
        activityTitles = getResources().getStringArray(R.array.navDo);
        mHandler = new Handler();
        //
        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_GUIDES;
            loadHomeFragment();
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
//        MenuItem menuItem = mNavigationView.getMenu().getItem(navItemIndex).setChecked(true);
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
        if (navItemIndex != 0) {
            navItemIndex = 0;
            CURRENT_TAG  = TAG_GUIDES;
            loadHomeFragment();
            return;
        }
        super.onBackPressed();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_guides:
                navItemIndex = 0;
                CURRENT_TAG  = TAG_GUIDES;
                break;
            case R.id.nav_breakfast:
                navItemIndex = 1;
                CURRENT_TAG  = TAG_BREAKFAST;
                break;
            case R.id.nav_lunch:
                navItemIndex = 2;
                CURRENT_TAG  = TAG_LUNCH;
                break;
            case R.id.nav_snack:
                navItemIndex = 3;
                CURRENT_TAG  = TAG_SNACK;
                break;
            case R.id.nav_dinners:
                navItemIndex = 4;
                CURRENT_TAG  = TAG_DINNERS;
                break;
            case R.id.nav_drinks:
                navItemIndex = 5;
                CURRENT_TAG  = TAG_DRINKS;
                break;
            case R.id.nav_attractions:
                navItemIndex = 6;
                CURRENT_TAG  = TAG_ATTRACTIONS;
                break;
            case R.id.nav_accommodations:
                navItemIndex = 7;
                CURRENT_TAG  = TAG_HOTELS;
                break;
            case R.id.nav_share:
                navItemIndex = 8;
                CURRENT_TAG  = TAG_SHARE;
                break;
            case R.id.nav_about:
                navItemIndex = 9;
                CURRENT_TAG  = TAG_ABOUT;
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



    public static class PlaceholderFragment extends Fragment
    {
        private static final String ARG_ARGUMENT = "navItemIndex";
        private int mArgument;
        //
        private List<Dweller> mDwellers;
        private DwellerAdapter mAdapter;
        // UI
        private RecyclerView mRecyclerView;

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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);//include_content_main.xml
            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
            //
            switchDummyAdapter(mArgument);
            //
            setupRecyclerView(recyclerView, mAdapter);
            mAdapter.notifyDataSetChanged();
            //
            getActivity().setTitle(activityTitles[mArgument]);
            //
            return rootView;
        }

        private void switchDummyAdapter(int argument)
        {
            switch (argument) {
                case 0:
                    mDwellers = DummyContent.DWELLERS;
                    mAdapter = new DwellerAdapter(getContext(), mDwellers);
                    break;
                case 1:
                    //
                    //mAdapter = new ;
                    break;
                case 2:
                    //
                    //mAdapter = new ;
                    break;
                case 3:
                    //
                    //mAdapter = new ;
                    break;
                case 4:
                    //
                    //mAdapter = new ;
                break;
                case 5:
                    //
                    //mAdapter = new ;
                break;
                case 6:
                    //
                    //mAdapter = new ;
                    break;
                case 7:
                    //
                    //mAdapter = new ;
                    break;
                case 8:
                    //
                    //mAdapter = new ;
                    break;
                case 9:
                    //
                    //mAdapter = new ;
                    break;
                default:
                    break;
            }
        }

        private void setupRecyclerView(RecyclerView recyclerView, DwellerAdapter dwellerAdapter)
        {
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, Transformation.dpToPx(10, getResources()), true));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(dwellerAdapter);
        }

        public interface OnFragmentInteractionListener
        {
            // TODO: Update argument type and name
            void onFragmentInteraction(Uri uri);
        }
    }



}
