package com.maiso.baresmanaus.helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.maiso.baresmanaus.R;
import com.maiso.baresmanaus.adapter.SugestaoAdapter;
import com.maiso.baresmanaus.modelo.Sugestoes;
import com.maiso.baresmanaus.modelo.Usuarios;

/**
 * Created by maiso on 11/01/2017.
 */

public class SugestaoHelper {


    private EditText campo_autor;
    private Sugestoes sugestao;
    private final EditText campo_sugestao;
    private final RatingBar campo_nota_atendimento;

    public SugestaoHelper(Activity activity) {

        campo_autor = (EditText) activity.findViewById(R.id.editText_autor_da_sugestao);
        campo_sugestao = (EditText) activity.findViewById(R.id.texto_da_sugestao);
        campo_nota_atendimento = (RatingBar) activity.findViewById(R.id.nota_feedback);
        sugestao = new Sugestoes();


    }

    public Sugestoes pegaSugestao() {
        sugestao.setAutor(campo_autor.getText().toString());
        sugestao.setSugestao(campo_sugestao.getText().toString());
        sugestao.setNota_atendimento((double) campo_nota_atendimento.getRating());
        return sugestao;
    }

    public Sugestoes preencheSugestoes(Sugestoes sugestao) {
        this.sugestao = sugestao;
        campo_autor.setText(sugestao.getAutor());
        campo_sugestao.setText(sugestao.getSugestao());
        campo_nota_atendimento.setRating(sugestao.getNota_atendimento().floatValue());
        return sugestao;
    }

}
