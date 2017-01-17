package com.maiso.baresmanaus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.maiso.baresmanaus.R;

/**
 * Created by maiso on 13/01/2017.
 */

public class TransformerAdapter extends BaseAdapter {
    private Context meuContexto;

    public TransformerAdapter(Context context) {
        meuContexto = context;
    }

    @Override
    public int getCount() {
        return SliderLayout.Transformer.values().length;
    }

    @Override
    public Object getItem(int position) {
        return SliderLayout.Transformer.values()[position].toString();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView t = (TextView) LayoutInflater.from(meuContexto).inflate(R.layout.item,null);
        t.setText(getItem(position).toString());
        return t;
    }
}