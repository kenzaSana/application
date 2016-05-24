package com.example.kenzack.applicationtest.View;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kenzack.applicationtest.R;

import java.util.ArrayList;

public class Search_Result extends AppCompatActivity {
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__result);

        Intent myIntent=getIntent();
        if(Intent.ACTION_SEARCH.equals(myIntent.getAction())){
            query=myIntent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(this,query,Toast.LENGTH_SHORT).show();
        }
        Toolbar toolbar_searchResults=(Toolbar)findViewById(R.id.toolbar_searchResults);
        toolbar_searchResults.setTitle(query);
        setSupportActionBar(toolbar_searchResults);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String[] countries=getResources().getStringArray(R.array.countries_array);
        ArrayList<String> arrayList_searchResults=new ArrayList<String>();
        for(int i=0;i<countries.length;i++)
            if(countries[i].toLowerCase().contains(query.toLowerCase()))
                arrayList_searchResults.add(countries[i]);

        ListView listView_searchResults=(ListView)findViewById(R.id.listView_searchResults);
        ArrayAdapter myAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList_searchResults);
        listView_searchResults.setAdapter(myAdapter);

    }
}
