package im.assetmanagement.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import im.assetmanagement.R;

public class DashboardActivity extends BaseActivity {

    BottomNavigationView bottomNavigationView;
    String assetId;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initBottomNavigationView();
        Bundle extras = getIntent().getExtras();
        assetId = extras.getString("assetId");
        username = extras.getString("username");
        WifiFragment wifiFragment = new WifiFragment();
        wifiFragment.setArguments(setUserDetails());
        fragmentTransaction(wifiFragment.getClass().getSimpleName(), wifiFragment, true);
    }

    private void initBottomNavigationView() {
        bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_tab:
                        WifiFragment wifiFragment = new WifiFragment();
                        wifiFragment.setArguments(setUserDetails());
                        fragmentTransaction(wifiFragment.getClass().getSimpleName(), wifiFragment, true);
                        return true;
                    case R.id.tab_2:
                        ApplicationsFragment applicationsFragment = new ApplicationsFragment();
                        applicationsFragment.setArguments(setUserDetails());
                        fragmentTransaction(applicationsFragment.getClass().getSimpleName(), applicationsFragment, true);
                        return true;
                    case R.id.tab_3:
                        LocationFragment locationFragment = new LocationFragment();
                        locationFragment.setArguments(setUserDetails());
                        fragmentTransaction(locationFragment.getClass().getSimpleName(), locationFragment, true);
                        return true;
                    case R.id.tab_4:
                        SimDetailsFragment simDetailsFragment = new SimDetailsFragment();
                        simDetailsFragment.setArguments(setUserDetails());
                        fragmentTransaction(simDetailsFragment.getClass().getSimpleName(), simDetailsFragment, true);
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });

    }

    private Bundle setUserDetails() {
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        bundle.putString("assetId", assetId);
        return bundle;
    }

}
