package com.example.kenzack.applicationtest.View;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kenzack.applicationtest.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar my_toolbar=(Toolbar)findViewById(R.id.my_toolbar);
        my_toolbar.setTitle(R.string.title);
        my_toolbar.setSubtitle(R.string.sub);
        my_toolbar.setLogo(R.drawable.ic_navigation_drawer);
        setSupportActionBar(my_toolbar);

        ListView listView_countries=(ListView)findViewById(R.id.listView_countries);
        ArrayAdapter myAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.countries_array));
        listView_countries.setAdapter(myAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        SearchView searchView=(SearchView) menu.findItem(R.id.item_search).getActionView();
        SearchManager searchManager= (SearchManager) getSystemService(Context.SEARCH_SERVICE);
       searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.item_settings:
              //  Toast.makeText(this,R.string.item_settings,Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,Setting.class));
                        break;
            case R.id.item_profile:
               // Toast.makeText(this,R.string.item_profile,Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,profile.class));
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
