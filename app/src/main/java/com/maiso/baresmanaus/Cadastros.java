package com.maiso.baresmanaus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.maiso.baresmanaus.helper.UsuarioHelper;
import com.maiso.baresmanaus.modelo.Usuarios;

public class Cadastros extends AppCompatActivity {

    private UsuarioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastros);

         helper = new UsuarioHelper(this);


        Button botaoCadastrar = (Button) findViewById(R.id.botaoCadastrar);
        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuarios usuario = helper.pegaUsuario();
                Toast.makeText(Cadastros.this,"Usuário "+usuario.getNome()+" cadastrado com sucesso",Toast.LENGTH_LONG).show();
                Intent voltaproMenu = new Intent(Cadastros.this,Modulos.class);
                startActivity(voltaproMenu);

            }
        });

    }
}
