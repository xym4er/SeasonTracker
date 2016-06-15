package com.ychornyi.seasontracker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.ychornyi.seasontracker.model.FilmListAdapter;
import com.ychornyi.seasontracker.model.items.FilmItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UpdateTask.CustomCallback {

    RecyclerView rv;
    FilmListAdapter adapter;
    List<FilmItem> films = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        films.add(new FilmItem("test"));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });

        rv = (RecyclerView) findViewById(R.id.rvMain);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new FilmListAdapter(this, films);
        rv.setAdapter(adapter);
    }


    private void updateData() {
        new UpdateTask(this).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void doSomething(List<FilmItem> result) {
        films.addAll(result);
        adapter.notifyDataSetChanged();
    }
}
