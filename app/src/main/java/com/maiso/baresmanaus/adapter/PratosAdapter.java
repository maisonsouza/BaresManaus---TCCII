package com.maiso.baresmanaus.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ftinc.kit.adapter.BetterRecyclerAdapter;
import com.maiso.baresmanaus.R;
import com.maiso.baresmanaus.modelo.Pratos;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by r0adkll on 1/11/15.
 */
public class PratosAdapter extends BetterRecyclerAdapter<Pratos, PratosAdapter.PratosViewHolder> {

    private List<Pratos> pratos;
    private Activity activity;

    public PratosAdapter(List<Pratos> pratos, Activity activity ) {
        this.pratos = pratos;
        this.activity = activity;
    }

    @Override
    public PratosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item, parent, false);
        return new PratosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PratosViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        Pratos pratos = getItem(i);
        viewHolder.nome_prato.setText(pratos.nome_prato);
        viewHolder.descricao.setText(pratos.descricao);
    }

    public static class PratosViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item)         public TextView nome_prato;
        @BindView(R.id.description)   public TextView descricao;

        public PratosViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}