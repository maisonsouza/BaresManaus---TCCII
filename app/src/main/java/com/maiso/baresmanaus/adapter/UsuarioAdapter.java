package com.maiso.baresmanaus.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.maiso.baresmanaus.R;
import com.maiso.baresmanaus.modelo.Usuarios;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by maiso on 09/01/2017.
 */

public class UsuarioAdapter extends BaseAdapter {

    private CircleImageView foto;
    private TextView nome;
    private  TextView tipo;
    private Activity activity;
    private List<Usuarios> usuarios;
    private Usuarios usuario;

    public UsuarioAdapter(Activity activity, List<Usuarios> usuarios) {
        this.activity = activity;
        this.usuarios = usuarios;

    }

    @Override
    public int getCount() {
        return usuarios.size();
    }

    @Override
    public Object getItem(int position) {
        return usuarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return usuarios.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        usuario = usuarios.get(position);

        LayoutInflater inflater = activity.getLayoutInflater();
        View linha = inflater.inflate(R.layout.layout_lista_cadastrados,parent, false);
        foto = (CircleImageView) linha.findViewById(R.id.circl_img_viw_imagem_perfil);
        nome = (TextView) linha.findViewById(R.id.txt_nome_cadastrado);
        tipo = (TextView) linha.findViewById(R.id.txt_tipo_cadastrado);

        if(usuario.getFoto()!= null){
           Bitmap bitmap = BitmapFactory.decodeFile(usuario.getFoto());
           Bitmap imagemReduzida = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
            foto.setImageBitmap(imagemReduzida);
       }

        nome.setText(usuario.getNome());
        tipo.setText(usuario.getTipo_de_usuario());


        return linha;
    }
}
