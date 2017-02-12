package com.basnal.tagit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.basnal.tagit.models.HotspotInformation;
import com.basnal.tagit.tagitapi.IRecyclerViewHandler;
import com.basnal.tagit.tagitapi.RecyclerAdapter;

public class HotspotDetailsActivity extends AppCompatActivity implements IRecyclerViewHandler
{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private HotspotInformation hotspotInformation;
    private TextView hotspotTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotspot_details);

        Intent intent = getIntent();
        hotspotInformation = intent.getParcelableExtra("hotspotDetails");

        initialize();
    }

    private void initialize()
    {
        if(hotspotInformation == null)
            return;

        hotspotTitle = (TextView) findViewById(R.id.hotspottiletext);
        hotspotTitle.setText(hotspotInformation.getName());

        //hotspotComments = (TextView) findViewById(R.id.hotspot_comments);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new RecyclerAdapter(this);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void inflateView(RecyclerAdapter.RecyclerViewHolder holder, int position)
    {
        if(hotspotInformation == null)
            return;
        TextView holderText = holder.getText();
        holderText.setText(hotspotInformation.getComments().get(position));
    }

    @Override
    public int getItemCount()
    {
        if(hotspotInformation == null)
            return 0;
        return hotspotInformation.getComments().size();
    }
}


























