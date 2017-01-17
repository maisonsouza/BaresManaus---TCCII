package com.maiso.baresmanaus.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.maiso.baresmanaus.R;
import com.maiso.baresmanaus.dao.UsuarioDAO;
import com.maiso.baresmanaus.helper.DBHelper;
import com.maiso.baresmanaus.helper.UsuarioHelper;
import com.maiso.baresmanaus.modelo.Sugestoes;
import com.maiso.baresmanaus.modelo.Usuarios;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.R.attr.bitmap;

/**
 * Created by maiso on 11/01/2017.
 */

public class SugestaoAdapter extends BaseAdapter {

    private final DBHelper dbhelper;
    private Activity activity;
    List<Sugestoes> sugestoes;
    private Usuarios usuario;
    List<Usuarios> usuarios;
    private Usuarios usuario_do_banco;
    private UsuarioHelper helper;
    private String nome_do_usuario;

    public SugestaoAdapter(Activity activity, List<Sugestoes> sugestoes) {
        this.activity = activity;
        this.sugestoes = sugestoes;
        dbhelper = new DBHelper(activity);
        usuario = new Usuarios();
        helper = new UsuarioHelper(activity,activity);
    }

    @Override
    public int getCount() {
        return sugestoes.size();
    }

    @Override
    public Object getItem(int position) {
        return sugestoes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return sugestoes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Sugestoes sugestao = sugestoes.get(position);

        LayoutInflater inflater = activity.getLayoutInflater();
        View linha = inflater.inflate(R.layout.lista_sugestoes_cadastradas,parent, false);


        TextView autor_sugestao = (TextView) linha.findViewById(R.id.text_sugestao_autor);
        if(sugestao.getAutor()!=null){
            autor_sugestao.setText(sugestao.getAutor());
        }else{
            autor_sugestao.setText("Desconhecido");
        }

        TextView sugestao_cadastrada = (TextView) linha.findViewById(R.id.txt_sugestao);
        sugestao_cadastrada.setText(sugestao.getSugestao());

        RatingBar nota_atendimento = (RatingBar) linha.findViewById(R.id.nota_formulario_sugestao);
        nota_atendimento.setRating(sugestao.getNota_atendimento().floatValue());

        return linha;
    }
}
