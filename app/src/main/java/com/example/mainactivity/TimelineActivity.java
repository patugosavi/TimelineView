package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TimelineActivity extends AppCompatActivity {

    public static final String ORIENTATION = "orientation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        int orientation = getIntent().getIntExtra(ORIENTATION, LinearLayoutManager.VERTICAL);

        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this, orientation, false));

        TimelineAdapter adapter = new TimelineAdapter(orientation, getItems());
        recycler.setAdapter(adapter);
    }

    private List<ListItem> getItems() {
        List<ListItem> items = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            ListItem item = new ListItem();
            item.setName("Client number " + i);
            item.setAddress("Some address " + random.nextInt(100));
            items.add(item);
        }

        return items;
    }

}
