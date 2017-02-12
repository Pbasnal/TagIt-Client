package com.basnal.tagit.tagitapi;

/**
 * Created by Basnal on 11-02-2017.
 */

public interface IRecyclerViewHandler
{
    void inflateView(RecyclerAdapter.RecyclerViewHolder holder, int position);
    int getItemCount();
}
