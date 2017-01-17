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

    // UI references.
    private EditText mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button mBotaoLogin;
    private String password;
    private String username;
    private DBHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbhelper = new DBHelper(this);

        // formulario de login
        mUsernameView = (EditText) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);
        mBotaoLogin = (Button) findViewById(R.id.botao_login);
        mBotaoLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {


                verificaLogin();

            }
        });
    }

    private void verificaLogin() {

        //reset errors
        mUsernameView.setError(null);
        mPasswordView.setError(null);


        // Store values at the time of the login attempt.
        username = mUsernameView.getText().toString();
        password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check senha válida
        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check username vazio
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else if (estaCadastrado(username, password)) {
            Intent vaipraModulos = new Intent(Login.this, Modulos.class);
            vaipraModulos.putExtra("login", username);
            startActivity(vaipraModulos);


        } else {
            Toast.makeText(Login.this, "Usuário ou Senha INCORRETOS", Toast.LENGTH_LONG).show();
        }
    }


    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

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

