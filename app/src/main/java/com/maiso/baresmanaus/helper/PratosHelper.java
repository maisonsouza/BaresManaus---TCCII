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
import com.maiso.baresmanaus.modelo.Pratos;
import com.maiso.baresmanaus.modelo.Usuarios;

import static com.maiso.baresmanaus.R.raw.pratos;

/**
 * Created by maiso on 10/11/2016.
 */

public class PratosHelper {

    private Context context;
    private final EditText camponome;
    private final EditText campodescricao;
    private final EditText campopreço;
    private ImageView campoFoto;
    private Pratos pratos;


    public PratosHelper(Context context, Activity activity) {
        this.context = context;
        camponome = (EditText) activity.findViewById(R.id.edt_nome_prato);
        campodescricao = (EditText) activity.findViewById(R.id.edt_descricao);
        campopreço = (EditText) activity.findViewById(R.id.edt_preco);
        campoFoto = (ImageView) activity.findViewById(R.id.imgm_cadastro_pratos);
        pratos = new Pratos();


    }

    public Pratos pegaPratos() {
        pratos.setNome_prato(camponome.getText().toString());
        pratos.setDescricao(campodescricao.toString());
        pratos.setPreço(campopreço.getText().toString());
        pratos.setImage_url((String) campoFoto.getTag());
        return pratos;
    }

    public String quemEstaLogado(){
        return camponome.getText().toString();
    }

    public Pratos preencheFormulario(Pratos pratos) {
        this.pratos = pratos;
        camponome.setText(pratos.nome_prato);
        campodescricao.setText(pratos.descricao);
        campopreço.setText(pratos.preço);
        carregaImagem(pratos.image_url);
        return pratos;
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
