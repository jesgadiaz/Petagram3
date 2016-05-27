package org.coursera.petagram2;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvPets;
    private Toolbar toolbar;
    private TabLayout tabPets;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ActionBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabPets = (TabLayout) findViewById(R.id.tabPets);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        if (toolbar != null){
            setSupportActionBar(toolbar);
        }

        setUpViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }



    private ArrayList<Fragment> addFragment(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new PetsFragment());
        fragments.add(new MyPetFragment());
        return fragments;
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), addFragment()));
        tabPets.setupWithViewPager(viewPager);
        tabPets.getTabAt(0).setIcon(R.drawable.ic_dog_house);
        tabPets.getTabAt(1).setIcon(R.drawable.ic_pet);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.mContact:
                Intent intent1 = new Intent(this, MailActivity.class);
                startActivity(intent1);
                break;
            case R.id.mAbout:
                Intent intent2 = new Intent(this, AboutActivity.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

