package com.example.BooksHome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class accueil extends AppCompatActivity {

    ListView ls;
    ArrayList<HashMap<String,String>> values=new ArrayList<HashMap<String,String>>();
    HashMap<String,String>map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        ls=findViewById(R.id.lst);

        map=new HashMap<String,String>();
        map.put("type","échange");
        map.put("titre"," Les femmes d'amis");
        map.put("img",String.valueOf(R.mipmap.femam));
        values.add(map);
        map=new HashMap<String,String>();
        map.put("type","10dh");
        map.put("titre"," L'année du diable");
        map.put("img",String.valueOf(R.mipmap.anndi));
        values.add(map);
        map=new HashMap<String,String>();
        map.put("type","échange");
        map.put("titre","Petite Lune");
        map.put("img",String.valueOf(R.mipmap.petlun));
        values.add(map);

        map=new HashMap<String,String>();
        map.put("type","15dh");
        map.put("titre","The colde millon");
        map.put("img",String.valueOf(R.mipmap.coldmill));
        values.add(map);









        SimpleAdapter adapter=new SimpleAdapter( accueil.this,values,R.layout.item,new String[]{"type","titre","img"},new int[]{R.id.type,R.id.titre,R.id.img});
        ls.setAdapter(adapter);

    }
}