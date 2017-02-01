package com.maiso.baresmanaus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ftinc.kit.adapter.BetterRecyclerAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.maiso.baresmanaus.adapter.PratosAdapter;
import com.maiso.baresmanaus.dao.PratosDAO;
import com.maiso.baresmanaus.helper.DBHelper;
import com.maiso.baresmanaus.helper.PratosHelper;
import com.maiso.baresmanaus.modelo.Pratos;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Cadastro_Pratos extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    private PratosAdapter mAdapter;
    private FloatingActionButton botao_flutuante_adicionar_prato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pratos);

        ButterKnife.bind(this);
        initRecycler();
        botao_flutuante_adicionar_prato = (FloatingActionButton) findViewById(R.id.fltng_action_bttn_adicionar_prato);
        botao_flutuante_adicionar_prato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vai_para_cadastro_pratos = new Intent(Cadastro_Pratos.this, Formulario_Cadastro_Prato.class);
                startActivity(vai_para_cadastro_pratos);
            }
        });
    }

    /**
     * Inicializa o Recycler
     */
    private void initRecycler(){

        DBHelper dbhelper = new DBHelper(this);
        PratosHelper helper = new PratosHelper(this,this);
        PratosDAO dao = new PratosDAO(dbhelper);
        List<Pratos> pratos_cadastrados = dao.buscaPratos();
        dbhelper.close();
        mAdapter = new PratosAdapter(pratos_cadastrados,this);
        mRecycler.setAdapter(mAdapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setOnItemClickListener(new BetterRecyclerAdapter.OnItemClickListener<Pratos>() {
            @Override
            public void onItemClick(View v, Pratos pratos, int position) {
                // Launch the slidable activity
                Intent viewer = new Intent(Cadastro_Pratos.this, ViewerActivity.class);
                viewer.putExtra(ViewerActivity.EXTRA_OS, pratos);
                startActivity(viewer);
            }
        });
    }

    private List<Pratos> getData(){
        InputStream is = getResources().openRawResource(R.raw.pratos);
        InputStreamReader isr = new InputStreamReader(is);
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Pratos>>(){}.getType();
        List<Pratos> oss = gson.fromJson(isr, listType);
        return oss;
    }




}