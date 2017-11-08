package ua.com.solodilov.evgen.vjettesttask.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.Profile;

import ua.com.solodilov.evgen.vjettesttask.R;
import ua.com.solodilov.evgen.vjettesttask.login_fb.view.LoginActivity;
import ua.com.solodilov.evgen.vjettesttask.main.view.GalleryFragment;
import ua.com.solodilov.evgen.vjettesttask.main.view.GalleryView;
import ua.com.solodilov.evgen.vjettesttask.main.view.ProfileFragment;
import ua.com.solodilov.evgen.vjettesttask.models.Person;

public class MainActivity extends AppCompatActivity {
    public static final String PROFILE_OBJECT = "person";

    private Person mPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPerson = getIntent().getExtras().getParcelable(PROFILE_OBJECT);
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
                Intent intent = new Intent(this,LoginActivity.class);
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
        Bundle bundle = new Bundle();
        bundle.putParcelable(MainActivity.PROFILE_OBJECT, mPerson);
        Fragment fragment = Fragment.instantiate(this, GalleryFragment.class.getName(), bundle);
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
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(GalleryFragment.class.getCanonicalName());
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count==0) {finishAffinity();
           // startFragment();
        } else {

             super.onBackPressed();
        }
    }
}
