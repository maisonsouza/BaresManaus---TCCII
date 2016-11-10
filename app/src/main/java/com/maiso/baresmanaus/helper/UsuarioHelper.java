package com.maiso.baresmanaus.helper;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.maiso.baresmanaus.Cadastros;
import com.maiso.baresmanaus.R;
import com.maiso.baresmanaus.modelo.Usuarios;

/**
 * Created by maiso on 10/11/2016.
 */

public class UsuarioHelper {

    private final EditText campoNome;
    private final EditText campoSenha;
    private final EditText campoUsuario;

    public UsuarioHelper(Cadastros activity) {

         campoNome = (EditText) activity.findViewById(R.id.editText_nome);
        String nome = campoNome.getText().toString();
         campoUsuario = (EditText) activity.findViewById(R.id.editText_usuario);
        String usuario = campoNome.getText().toString();
         campoSenha = (EditText) activity.findViewById(R.id.editTextsenha);
        String senha = campoNome.getText().toString();
         //campoTipo = (Spinner) activity.findViewById(R.id.spinner);
       // String tipo = campoTipo.toString();
    }

    public Usuarios pegaUsuario() {
       Usuarios usuario = new Usuarios();
        usuario.setNome(campoNome.getText().toString());
        usuario.setUsuario(campoUsuario.getText().toString());
        usuario.setSenha(campoSenha.getText().toString());
        return usuario;



    }
}
