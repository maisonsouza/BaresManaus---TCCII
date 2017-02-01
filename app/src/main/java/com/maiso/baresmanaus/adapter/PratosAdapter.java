package com.maiso.baresmanaus.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ftinc.kit.adapter.BetterRecyclerAdapter;
import com.maiso.baresmanaus.R;
import com.maiso.baresmanaus.modelo.Pratos;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by r0adkll on 1/11/15.
 */
public class PratosAdapter extends BetterRecyclerAdapter<Pratos, PratosAdapter.OSViewHolder> {


    @Override
    public OSViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item, parent, false);
        return new OSViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OSViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        Pratos pratos = getItem(i);
        viewHolder.title.setText(pratos.nome_prato);
        viewHolder.description.setText(pratos.descricao);
    }

    public static class OSViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item)         public TextView title;
        @BindView(R.id.description)   public TextView description;

        public OSViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}