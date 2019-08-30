package com.africa.cloud.commandes;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.africa.cloud.commandes.activity.PanierActivity;
import com.africa.cloud.commandes.model.Lunette;
import com.africa.cloud.commandes.model.LunetteAdapter;
import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.List;

public class LunetteActivity extends AppCompatActivity implements LunetteFragment.OnFragmentInteractionListener,
        ProduitFragment.OnFragmentInteractionListener,
        VetementFragment.OnFragmentInteractionListener,
        SportFragment.OnFragmentInteractionListener,
        ChaussuresFragment.OnFragmentInteractionListener,
        ElectroniqueFragment.OnFragmentInteractionListener,
        ModeAccesoiresFragment.OnFragmentInteractionListener,
        ServicesFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener{



    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;
    // urls to load navigation header background image
    // and profile image
    private static final String urlNavHeaderBg = "@Drawable/pusko";
    private static final String urlProfileImg = "https://lh3.googleusercontent.com/eCtE_G34M9ygdkmOpYvCag1vBARCmZwnVS6rS5t4JLzJ6QgQSBquM0nuTsCpLhYbKljoyS-txg";

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_PRODUITS = "Nos Produits";
    private static final String TAG_VETEMENTS = "vetements";
    private static final String TAG_SPORTS = "sport";
    private static final String TAG_CHAUSSURES = "chaussures";
    private static final String TAG_MODE_ACCESSOIRES = "mode";
    private static final String TAG_ELECTRONIQUE = "electronique";
    private static final String TAG_SERVICES = "services";
    private static final String TAG_PARAMETRES = "settings";
    public static String CURRENT_TAG = TAG_PRODUITS;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;



    private static final String TAG ="cool" ;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    LunetteAdapter lunetteAdapter;
    ArrayAdapter adapter;
    private List<Lunette> lunetteList = new ArrayList<>();

    RequestQueue mRequestQueue;
    TextView resultat;
    ImageView lun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunette);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        //fab = (FloatingActionButton) findViewById(R.id.fab);

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.website);




        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);


        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_PRODUITS;
            loadProduitFragment();
        }

    }



    /***
     * Load navigation menu header information
     * like background image, profile image
     * name, website, notifications action view (dot)
     */
    private void loadNavHeader() {
        // name, website
        txtName.setText("Ibrahima Koné");
        txtWebsite.setText("Bakeliste: Dev Web /Mobile");
        // showing dot next to notifications label
        navigationView.getMenu().getItem(2).setActionView(R.layout.menu_dot);
    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadProduitFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            // show or hide the fab button
            //toggleFab();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getProduitFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button
        //toggleFab();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getProduitFragment() {
        switch (navItemIndex) {

            case 0:
                // Acceuil
                ProduitFragment produitFragment = new ProduitFragment();
                return produitFragment;


            case 1:
                // photos
                VetementFragment vetementFragment = new VetementFragment();
                return vetementFragment;

            case 2:
                // movies fragment
                ChaussuresFragment chaussuresFragment = new ChaussuresFragment();
                return chaussuresFragment;

            case 3:
                // movies fragment
                ModeAccesoiresFragment modeFragment = new ModeAccesoiresFragment();
                return modeFragment;


            case 4:
                // movies fragment
                ElectroniqueFragment electroniqueFragment = new ElectroniqueFragment();
                return electroniqueFragment;
            case 5:
                // notifications fragment
                SportFragment sportFragment = new SportFragment();
                return sportFragment;

            case 6:
                // movies fragment
                ServicesFragment servicesFragment = new ServicesFragment();
                return servicesFragment;

            case 7:
                // settings fragment
                SettingsFragment settingsFragment = new SettingsFragment();
                return settingsFragment;
            default:
                return new ProduitFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_produits:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_PRODUITS;
                        break;


                    case R.id.nav_vetements:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_VETEMENTS;
                        break;

                    case R.id.nav_chaussures:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_CHAUSSURES;
                        break;

                    case R.id.nav_modes_accessoire:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_MODE_ACCESSOIRES;
                        break;

                    case R.id.nav_electronique:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_ELECTRONIQUE;
                        break;

                    case R.id.nav_sports:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_SPORTS;
                        break;


                    case R.id.nav_services:
                        navItemIndex = 6;
                        CURRENT_TAG = TAG_SERVICES;
                        break;

                    case R.id.nav_parametres:
                        navItemIndex = 7;
                        CURRENT_TAG = TAG_PARAMETRES;
                        break;
                    case R.id.nav_about_us:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(LunetteActivity.this, AProposActivity.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_privacy_policy:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(LunetteActivity.this, AProposActivity.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_clients:

                        startActivity(new Intent(LunetteActivity.this, PolitiquesUtilisationActivity.class));
                        drawer.closeDrawers();
                        return true;
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadProduitFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // ratherx
            // than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_PRODUITS;
                loadProduitFragment();
                return;
            }
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.


        // show menu only when home fragment is selected
        if (navItemIndex == 0) {

            MenuInflater inflater = getMenuInflater();

            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        // when fragment is notifications, load the menu created for notifications
        if (navItemIndex == 7) {
            getMenuInflater().inflate(R.menu.notifications, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Toast.makeText(getApplicationContext(), "Deconnexion de l'utilisateur!", Toast.LENGTH_LONG).show();
            return true;
        }


        if (id == R.id.panier_settings) {
         Intent panier = new Intent(LunetteActivity.this, PanierActivity.class);
         startActivity(panier);
         return true;
        }

        // user is in notifications fragment
        // and selected 'Mark all as Read'
        if (id == R.id.action_mark_all_read) {
            Toast.makeText(getApplicationContext(), "All notifications marked as read!", Toast.LENGTH_LONG).show();
        }

        // user is in notifications fragment
        // and selected 'Clear All'
        if (id == R.id.action_clear_notifications) {
            Toast.makeText(getApplicationContext(), "Clear all notifications!", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    // show or hide the fab
 /*   private void toggleFab() {
        if (navItemIndex == 0)
            fab.show();
        else
            fab.hide();
    }*/

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
