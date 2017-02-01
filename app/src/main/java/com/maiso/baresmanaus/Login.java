package com.maiso.baresmanaus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.maiso.baresmanaus.dao.UsuarioDAO;
import com.maiso.baresmanaus.helper.DBHelper;
import com.maiso.baresmanaus.modelo.Usuarios;

import java.util.List;


public class Login extends AppCompatActivity {
    // Referências da Interface do usuário.
    private EditText mUsernameView;
    private EditText mPasswordView;
    private Button mBotaoLogin;
    private String password;
    private String username;
    private DBHelper dbhelper;
    private boolean cancel;
    private View foco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbhelper = new DBHelper(this);

        // formulario de login
        mUsernameView = (EditText) findViewById(R.id.edt_username);
        mPasswordView = (EditText) findViewById(R.id.edt_password);
        mBotaoLogin = (Button) findViewById(R.id.bttn_login);
        mBotaoLogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                verificaLogin();
            }
        });
    }

    private void verificaLogin() {
        //Foco da tela
        cancel = false;
        foco = null;
        if (cancel) {
            foco.requestFocus();
        }

        //Apaga erros do formulário
        mUsernameView.setError(null);
        mPasswordView.setError(null);

        // Armazenar os valores do login
        username = mUsernameView.getText().toString();
        password = mPasswordView.getText().toString();

        // Check usuario vazio
        checkUsuarioVazio(username);


        // Check senha vazia
        checkSenhaVazia(password);


        //Check senha inválida(menor que 4 caracteres)
        isSenhaValida(password);

        mUsernameView.setError(null);
        mPasswordView.setError(null);
        //Check campos vazios
        if(!checkUsuarioVazio(username) && !checkSenhaVazia(password) && isSenhaValida(password) ){
            // verifica se login está cadastrado no banco
            if (estaCadastrado(username, password)) {
                Intent vaipraModulos = new Intent(this, Modulos.class);
                vaipraModulos.putExtra("login", username);
                startActivity(vaipraModulos);
            }else{
                Toast.makeText(this,"usuário não cadastrado",Toast.LENGTH_LONG).show();
            }
        }

    }

    //Método: Check usuario vazio
    private boolean checkUsuarioVazio(String username) {
        if(TextUtils.isEmpty(username)){
            mUsernameView.setError(getString(R.string.Campo_Obrigatorio));
            foco = mUsernameView;
            cancel = true;
            return true;
        }
       return false;
    }

    //Método: Check senha vazia
    private boolean checkSenhaVazia(String password) {
        if(TextUtils.isEmpty(password)){
            mPasswordView.setError(getString(R.string.Campo_Obrigatorio));
            return true;
        }
        return false;
    }

    //Método: verifica se a senha é maior que 4 caracteres
    private boolean isSenhaValida(String password) {
        if (password.length() < 4) {
            mPasswordView.setError(getString(R.string.Senha_invalida));
            foco = mPasswordView;
            cancel = true;
            return false;
        }
        return true;
    }

    //Método: verifica se está cadastrado no banco
    protected Boolean estaCadastrado(String username, String password) {
        UsuarioDAO dao = new UsuarioDAO(dbhelper);
        List<Usuarios> usuariosdoBanco = dao.buscaUsuarios();
        dbhelper.close();
        for (Usuarios usuario : usuariosdoBanco) {
            if (usuario.getUsuario().equals(username) && usuario.getSenha().equals(password)) {
                return true;
            }
        }
        return false;
    }
}

