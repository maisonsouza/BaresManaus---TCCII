package com.maiso.baresmanaus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.maiso.baresmanaus.dao.PratosDAO;
import com.maiso.baresmanaus.helper.DBHelper;
import com.maiso.baresmanaus.helper.PratosHelper;
import com.maiso.baresmanaus.modelo.Pratos;

/**
 * Created by maiso on 24/01/2017.
 */
public class Formulario_Cadastro_Prato extends AppCompatActivity{

    private ImageView foto_do_prato;
    private EditText nome_do_prato;
    private EditText preco_do_prato;
    private EditText descricao_do_prato;
    private Button botao_cadastrar_prato;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pratos);

        foto_do_prato = (ImageView) findViewById(R.id.imgm_cadastro_pratos);
        nome_do_prato = (EditText) findViewById(R.id.edt_nome_prato);
        preco_do_prato = (EditText) findViewById(R.id.edt_preco);
        descricao_do_prato = (EditText) findViewById(R.id.edt_descricao);

        botao_cadastrar_prato = (Button) findViewById(R.id.buttn_cadastrar_prato);
        botao_cadastrar_prato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper DBhelper = new DBHelper(Formulario_Cadastro_Prato.this);
                PratosDAO dao = new PratosDAO(DBhelper);
                PratosHelper helper = new PratosHelper(Formulario_Cadastro_Prato.this,Formulario_Cadastro_Prato.this);
                Pratos pratos = helper.pegaPratos();
                dao.insere(pratos);
                Toast.makeText(Formulario_Cadastro_Prato.this, pratos.nome_prato+ " cadastrado com sucesso", Toast.LENGTH_LONG).show();
                Intent vaipraUsuariosCadastrados = new Intent(Formulario_Cadastro_Prato.this, Cadastro_Pratos.class);
                startActivity(vaipraUsuariosCadastrados);


            }
        });

    }
}
