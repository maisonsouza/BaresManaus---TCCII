package com.maiso.baresmanaus.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.maiso.baresmanaus.Cadastros;
import com.maiso.baresmanaus.Formulario_Cadastro_Usuario;
import com.maiso.baresmanaus.R;
import com.maiso.baresmanaus.modelo.Usuarios;

import java.io.Serializable;

/**
 * Created by maiso on 10/11/2016.
 */

public class UsuarioHelper {

    private Context context;
    private final EditText camponome;
    private final EditText campousuario;
    private final EditText camposenha;
    private final TextView campo_informa_tipo;
    private Spinner campotipo;
    private Usuarios usuario;
    private ImageView campoFoto;

    public UsuarioHelper(Context context, Activity activity) {
        this.context = context;

        camponome = (EditText) activity.findViewById(R.id.cadastro_nome);
        campousuario = (EditText) activity.findViewById(R.id.cadastro_usuario);
        camposenha = (EditText) activity.findViewById(R.id.cadastro_senha);
        campotipo = (Spinner) activity.findViewById(R.id.seletor_tipo_usuario);
        usuario = new Usuarios();
        campoFoto = (ImageView) activity.findViewById(R.id.cadastro_formulario_foto);
        campo_informa_tipo = (TextView) activity.findViewById(R.id.informa_tipo);
        usuario = new Usuarios();


    }

    public Usuarios pegaUsuario() {
        usuario.setNome(camponome.getText().toString());
        usuario.setUsuario(campousuario.getText().toString());
        usuario.setSenha(camposenha.getText().toString());
        usuario.setTipo_de_usuario(campotipo.getSelectedItem().toString());
        usuario.setFoto((String) campoFoto.getTag());
        return usuario;
    }

    public String quemEstaLogado(){
        return camponome.getText().toString();
    }

    public Usuarios preencheFormulario(Usuarios usuario) {
        this.usuario = usuario;
        camponome.setText(usuario.getNome());
        campousuario.setText(usuario.getUsuario());
        camposenha.setText(usuario.getSenha());
        campo_informa_tipo.setText(usuario.getTipo_de_usuario());
        campotipo.setPrompt(usuario.getTipo_de_usuario());

        carregaImagem(usuario.getFoto());
        return usuario;
    }

    public void carregaImagem(String caminhoFoto) {
        Toast.makeText(context, caminhoFoto, Toast.LENGTH_LONG).show();
        if(caminhoFoto!= null){
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            campoFoto.setTag(caminhoFoto);
        }

    }

    public void carregaImagemdaGaleria(String caminhoFoto) {
        Toast.makeText(context, caminhoFoto, Toast.LENGTH_LONG).show();
        if(caminhoFoto!= null){
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            campoFoto.setImageBitmap(bitmap);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            campoFoto.setTag(caminhoFoto);
        }

    }



}
