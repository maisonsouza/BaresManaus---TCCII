package com.maiso.baresmanaus;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.maiso.baresmanaus.adapter.SugestaoAdapter;
import com.maiso.baresmanaus.adapter.UsuarioAdapter;
import com.maiso.baresmanaus.dao.SugestaoDAO;
import com.maiso.baresmanaus.dao.UsuarioDAO;
import com.maiso.baresmanaus.helper.DBHelper;
import com.maiso.baresmanaus.helper.SugestaoHelper;
import com.maiso.baresmanaus.modelo.Sugestoes;

import java.util.List;

public class Tela_Sugestoes extends AppCompatActivity {

    private Sugestoes sugestao;
    private DBHelper dbhelper;
    private ListView listagem_sugestoes;
    private ListView listagem_usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sugestoescadastradas);
        dbhelper = new DBHelper(this);

        carregaLista();

        FloatingActionButton botaoflutuanteCadastrar = (FloatingActionButton) findViewById(R.id.botaoFlutuante_adicionar_sugestao);
        botaoflutuanteCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaiproFormularioCadastroSugestao = new Intent(Tela_Sugestoes.this,Formulario_Cadastro_Sugestao.class);
                startActivity(vaiproFormularioCadastroSugestao);

            }
        });

        registerForContextMenu(listagem_sugestoes);
    }

    private void carregaLista() {
        listagem_sugestoes = (ListView) findViewById(R.id.lista_sugestoes_cadastrados);
        dbhelper = new DBHelper(this);
        SugestaoDAO dao = new SugestaoDAO(dbhelper);
        List<Sugestoes> sugestoes = dao.buscaSugestoes();
        dbhelper.close();
        SugestaoAdapter adapter = new SugestaoAdapter(this,sugestoes);
        listagem_sugestoes.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        carregaLista();
        super.onResume();
    }
}
