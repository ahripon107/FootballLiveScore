package com.sfuronlabs.livescore.football.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * @author Ripon
 */

public abstract class BasicListAdapter<X, T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    ArrayList<X> elements, secondList;

    public BasicListAdapter(ArrayList<X> elements) {
        this.elements = elements;
    }

    public BasicListAdapter(ArrayList<X> list1, ArrayList<X> list2) {
        this.elements = list1;
        this.secondList = list2;
    }

    @Override
    public abstract T onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(T holder, int position);

    @Override
    public int getItemCount() {
        return elements.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}

