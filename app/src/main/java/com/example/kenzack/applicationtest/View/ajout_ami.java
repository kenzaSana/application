package com.example.kenzack.applicationtest.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kenzack.applicationtest.R;

public class ajout_ami extends AppCompatActivity {
    String[] data={"A","B","C","D"};
    ListView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_ami);
       /* RelativeLayout layout = (RelativeLayout) RelativeLayout.inflate(this,R.layout.activity_ajout_ami, null);
        int nbre = 20;
       for(int i=0;i<nbre ; i++) {
            Button b = new Button(this);
            b.setHeight(15);
            b.setWidth(15);
            b.setText(i+"");
            layout.addView(b);
        }
        setContentView(layout); */
        l=(ListView)findViewById(R.id.listView);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.ajout,R.id.textView,data);
        l.setAdapter(adapter);
    }

}
