package com.maiso.baresmanaus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Modulos extends AppCompatActivity {


    private ImageView imagem_cadastro_usuario;
    private ImageView imagem_pagamento;
    private ImageView imagem_cadastro_pratos;
    private ImageView imagem_anuncios;
    private ImageView imagem_sugestao;

    private boolean doubleBackToExitPressedOnce = false;
    private TextView campousuariologado;
    public static String usuario_logado;
    private String logado_no_sistema;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulos);

        campousuariologado = (TextView) findViewById(R.id.txt_usuario_logado);
        Intent intent = getIntent();
        usuario_logado = (String) intent.getSerializableExtra("login");
        campousuariologado.setText(usuario_logado);


        imagem_cadastro_usuario = (ImageView) findViewById(R.id.imgm_modulos_cadastro_usuario);
        imagem_sugestao = (ImageView) findViewById(R.id.imgm_modulo_sugestao);
        imagem_anuncios = (ImageView) findViewById(R.id.imgm_modulos_Anuncios);
        imagem_cadastro_pratos = (ImageView) findViewById(R.id.imgm_modulos_cadatrar_pratos);
        imagem_pagamento = (ImageView) findViewById(R.id.imgm_modulos_pagamento);

        imagem_cadastro_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaipraCadastro = new Intent(Modulos.this,Cadastros.class);
                startActivity(vaipraCadastro);
            }
        });

        imagem_pagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaipraCadastro = new Intent(Modulos.this,PaypalPagamento.class);
                startActivity(vaipraCadastro);
            }
        });


        imagem_sugestao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaipraSugestão = new Intent(Modulos.this,Tela_Sugestoes.class);
                vaipraSugestão.putExtra("usuario_logado",campousuariologado.getText().toString());
                startActivity(vaipraSugestão);
            }
        });


        imagem_anuncios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaipraAnuncios = new Intent(Modulos.this,Anuncios.class);
                startActivity(vaipraAnuncios);
            }
        });


        imagem_cadastro_pratos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaipraPratos = new Intent(Modulos.this,Cadastro_Pratos.class);
                startActivity(vaipraPratos);
            }
        });

    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent  = new Intent(this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Pressione VOLTAR novamente para Sair", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);

    }

    @Override
    protected void onResume() {
        campousuariologado.setText(usuario_logado);
        super.onResume();
    }
}
