package ua.com.solodilov.evgen.vjettesttask.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ua.com.solodilov.evgen.vjettesttask.R;
import ua.com.solodilov.evgen.vjettesttask.login_fb.view.LoginActivity;
import ua.com.solodilov.evgen.vjettesttask.main.view.GalleryFragment;
import ua.com.solodilov.evgen.vjettesttask.main.view.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (savedInstanceState == null) {
            startFragment();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting_profile:
                startProfileFragment();
                return true;
            case R.id.action_logout:
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra(LoginActivity.AUTO_START, false);
                startActivity(intent);
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startFragment() {
        Fragment fragment = Fragment.instantiate(this, GalleryFragment.class.getName());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment, GalleryFragment.class.getCanonicalName())
                .commit();
    }

    private void startProfileFragment() {
        Fragment fragment = Fragment.instantiate(this, ProfileFragment.class.getName());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, ProfileFragment.class.getCanonicalName())
                .addToBackStack("")
                .commit();
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            finishAffinity();
        } else {
            super.onBackPressed();
        }
    }
}
