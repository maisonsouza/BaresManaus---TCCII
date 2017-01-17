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

    private Activity activity;
    List<Usuarios> usuarios;

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
        Usuarios usuario = usuarios.get(position);

        LayoutInflater inflater = activity.getLayoutInflater();
        View linha = inflater.inflate(R.layout.foto_circular,parent, false);

        CircleImageView foto = (CircleImageView) linha.findViewById(R.id.imagem_perfil);
        if(usuario.getFoto()!= null){
           Bitmap bitmap = BitmapFactory.decodeFile(usuario.getFoto());
           Bitmap imagemReduzida = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            foto.setImageBitmap(imagemReduzida);
       }

        TextView nome = (TextView) linha.findViewById(R.id.nomecadastrado);
       nome.setText(usuario.getNome());

        TextView tipo = (TextView) linha.findViewById(R.id.tipocadastrado);
        tipo.setText(usuario.getTipo_de_usuario());

        return linha;
    }
}
