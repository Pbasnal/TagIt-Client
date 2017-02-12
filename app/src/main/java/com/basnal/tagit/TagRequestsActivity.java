package com.basnal.tagit;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.basnal.tagit.models.HotspotInformation;
import com.basnal.tagit.tagitapi.IRecyclerViewHandler;
import com.basnal.tagit.tagitapi.RecyclerAdapter;
import com.basnal.tagit.tagitapi.TagItData;
import com.basnal.tagit.tagitapi.TagItRequests;

import java.util.ArrayList;

public class TagRequestsActivity extends AppCompatActivity implements IRecyclerViewHandler, View.OnClickListener
{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_requests);

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
        TextView cardId = holder.getCardId();
        TextView holderText = holder.getText();
        CardView holderCard = holder.getCard();

        ArrayList<TagItRequests> requests = TagItData.getInstance().getRequests();

        if(requests == null || requests.size() == 0)
            return;

        holderText.setText(requests.get(position).getHotspotQueryModel().getName());

        int cardColor = Color.WHITE;

        switch (requests.get(position).getRequestState())
        {
            case Initiated:
                cardColor = Color.BLUE;
                break;
            case Success:
                cardColor = Color.GREEN;
                break;
            case Failed:
                cardColor = Color.RED;
                break;
        }

        holderCard.setBackgroundColor(cardColor);

        cardId.setText(Integer.toString(position));
        holderCard.setOnClickListener(this);
    }

    @Override
    public int getItemCount()
    {
        ArrayList<TagItRequests> requests = TagItData.getInstance().getRequests();

        if(requests == null)
            return 0;

        return requests.size();
    }

    @Override
    public void onClick(View v)
    {
        TextView cardId = (TextView) v.findViewById(R.id.hiddentagid);
        try
        {
            int index = Integer.parseInt(cardId.getText().toString());
            TagItRequests request = TagItData.getInstance().getRequests().get(index);

            HotspotInformation hotspotInformation = new HotspotInformation();

            hotspotInformation.setName(request.getHotspotQueryModel().getName());
            hotspotInformation.setComments(request.getHotspotQueryModel().getComments());

            Intent intent = new Intent(TagRequestsActivity.this, HotspotDetailsActivity.class);
            intent.putExtra("hotspotDetails", hotspotInformation);

            startActivity(intent);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
