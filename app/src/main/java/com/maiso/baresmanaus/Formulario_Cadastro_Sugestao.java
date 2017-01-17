package com.maiso.baresmanaus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.maiso.baresmanaus.dao.SugestaoDAO;
import com.maiso.baresmanaus.dao.UsuarioDAO;
import com.maiso.baresmanaus.helper.DBHelper;
import com.maiso.baresmanaus.helper.SugestaoHelper;
import com.maiso.baresmanaus.helper.UsuarioHelper;
import com.maiso.baresmanaus.modelo.Sugestoes;

public class Formulario_Cadastro_Sugestao extends AppCompatActivity {

    private DBHelper dbhelper;
    private SugestaoHelper helper;
    private Sugestoes sugestao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugestoes);

        dbhelper = new DBHelper(this);
        helper = new SugestaoHelper(this);

        Button botao_adicionar_sugestao = (Button) findViewById(R.id.botao_enviar_sugestao);
        botao_adicionar_sugestao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SugestaoDAO dao = new SugestaoDAO(dbhelper);
                sugestao = helper.pegaSugestao();
                dao.insere(sugestao);
                dbhelper.close();
                Toast.makeText(Formulario_Cadastro_Sugestao.this, sugestao.getAutor() + " CADASTRADA com sucesso", Toast.LENGTH_LONG).show();
                Intent volta_para_Cadastradas = new Intent(Formulario_Cadastro_Sugestao.this, Tela_Sugestoes.class);
                startActivity(volta_para_Cadastradas);
            }
        });
    }
}
