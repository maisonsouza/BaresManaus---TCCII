package com.maiso.baresmanaus.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.maiso.baresmanaus.Formulario_Cadastro_Usuario;
import com.maiso.baresmanaus.R;
import com.maiso.baresmanaus.modelo.Usuarios;

/**
 * Created by maiso on 10/11/2016.
 */

public class UsuarioHelper {

    private Context context;
    private final EditText camponome;
    private final EditText campousuario;
    private final EditText camposenha;
    private final TextView campo_informa_tipo;
    private Spinner campo_tipo_usuario;
    private Usuarios usuario;
    private ImageView campoFoto;

    public UsuarioHelper(Context context, Activity activity) {
        this.context = context;
        camponome = (EditText) activity.findViewById(R.id.edt_cadastro_nome);
        campousuario = (EditText) activity.findViewById(R.id.edt_cadastro_usuario);
        camposenha = (EditText) activity.findViewById(R.id.edt_cadastro_senha);
        campo_tipo_usuario = (Spinner) activity.findViewById(R.id.spnnr_seletor_tipo_usuario);
        usuario = new Usuarios();
        campoFoto = (ImageView) activity.findViewById(R.id.imgm_cadastros_usuario_foto);
        campo_informa_tipo = (TextView) activity.findViewById(R.id.txt_informa_tipo);
        usuario = new Usuarios();


    }

    public Usuarios pegaUsuario() {
        usuario.setNome(camponome.getText().toString());
        usuario.setUsuario(campousuario.getText().toString());
        usuario.setSenha(camposenha.getText().toString());
        usuario.setTipo_de_usuario(campo_tipo_usuario.getSelectedItem().toString());
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
        campo_tipo_usuario.setSelection(Formulario_Cadastro_Usuario.posicao);
        carregaImagem(usuario.getFoto());
        return usuario;
    }

    public void carregaImagem(String caminhoFoto) {
        if(caminhoFoto!= null){
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 600, 400, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            campoFoto.setTag(caminhoFoto);
        }

    }


}
