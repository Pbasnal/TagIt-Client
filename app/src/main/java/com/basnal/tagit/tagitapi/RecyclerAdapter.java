package com.basnal.tagit.tagitapi;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.basnal.tagit.R;

/**
 * Created by Basnal on 11-02-2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>
{
    IRecyclerViewHandler handler;

    public RecyclerAdapter(IRecyclerViewHandler handler)
    {
        this.handler = handler;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position)
    {
        handler.inflateView(holder, position);
    }

    @Override
    public int getItemCount()
    {
        return handler.getItemCount();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView cardId;
        TextView text;
        CardView card;


        public RecyclerViewHolder(View itemView)
        {
            super(itemView);
            text = (TextView)itemView.findViewById(R.id.tag_name);
            cardId = (TextView) itemView.findViewById(R.id.hiddentagid);
            card = (CardView)itemView.findViewById(R.id.card_view);
        }

        public TextView getText()
        {
            return text;
        }
        public void setText(String str)
        {
            text.setText(str);
        }

        public CardView getCard()
        {
            return card;
        }
        public void setCard(CardView card)
        {
            this.card = card;
        }

        public TextView getCardId()
        {
            return cardId;
        }
        public void setCardId(TextView itemId)
        {
            this.cardId = itemId;
        }

    }
}
