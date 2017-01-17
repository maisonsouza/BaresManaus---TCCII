package com.maiso.baresmanaus;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maiso.baresmanaus.helper.UsuarioHelper;

public class Modulos extends AppCompatActivity {


    private ImageView imagem_pratos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulos);

        final TextView campousuariologado = (TextView) findViewById(R.id.textUsuarioLogado);
        Intent intent = getIntent();
        final String username = (String) intent.getSerializableExtra("login");
        campousuariologado.setText(username);


        View viewImagem_cadastro = findViewById(R.id.img_modulos_cadastro);
        viewImagem_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaipraCadastro = new Intent(Modulos.this,Cadastros.class);
                startActivity(vaipraCadastro);
            }
        });

        View viewImagem_sugestao = findViewById(R.id.image_modulo_sugestao);
        viewImagem_sugestao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaipraSugestão = new Intent(Modulos.this,Tela_Sugestoes.class);
                vaipraSugestão.putExtra("usuario_logado",campousuariologado.getText().toString());
                startActivity(vaipraSugestão);
            }
        });

        View Imagem_anuncios = findViewById(R.id.ImageView_Anuncios);
        Imagem_anuncios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaipraAnuncios = new Intent(Modulos.this,Anuncios.class);
                startActivity(vaipraAnuncios);
            }
        });

        imagem_pratos = (ImageView) findViewById(R.id.imagem_modulos_cadatrar_pratos);
        imagem_pratos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaipraPratos = new Intent(Modulos.this,Pratos.class);
                startActivity(vaipraPratos);
            }
        });



    }

}
