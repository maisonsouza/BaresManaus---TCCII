package com.maiso.baresmanaus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Modulos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulos);

        View viewImagem_cadastro = findViewById(R.id.img_modulos_cadastro);
        viewImagem_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaipraCadastro = new Intent(Modulos.this,Cadastros.class);
                startActivity(vaipraCadastro);
            }
        });

    }

}
