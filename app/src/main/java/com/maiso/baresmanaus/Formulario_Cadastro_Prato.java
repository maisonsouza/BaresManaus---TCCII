package com.maiso.baresmanaus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by maiso on 24/01/2017.
 */
public class Formulario_Cadastro_Prato extends AppCompatActivity{

    private ImageView foto_do_prato;
    private EditText nome_do_prato;
    private EditText preco_do_prato;
    private EditText descricao_do_prato;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pratos);

        foto_do_prato = (ImageView) findViewById(R.id.imgm_cadastro_pratos);
        nome_do_prato = (EditText) findViewById(R.id.edt_nome_prato);
        preco_do_prato = (EditText) findViewById(R.id.edt_preco);
        descricao_do_prato = (EditText) findViewById(R.id.edt_descricao);

    }
}
