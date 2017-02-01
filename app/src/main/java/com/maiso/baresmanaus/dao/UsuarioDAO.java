package com.maiso.baresmanaus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.maiso.baresmanaus.helper.DBHelper;
import com.maiso.baresmanaus.modelo.Usuarios;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maiso on 10/11/2016.
 */

public class UsuarioDAO  {

    private static final String TABELA = "Usuarios";
    private DBHelper helper;
    private Usuarios usuario;

    public UsuarioDAO(DBHelper helper) {
        this.helper = helper;
    }

    public void insere(Usuarios usuario) {
        ContentValues values = pegadadosdoUsuario(usuario);
        helper.getWritableDatabase().insert(TABELA,null,values);

    }

    @NonNull
    private ContentValues pegadadosdoUsuario(Usuarios usuario) {
        ContentValues values = new ContentValues();
        values.put("nome",usuario.getNome());
        values.put("login",usuario.getUsuario());
        values.put("senha",usuario.getSenha());
        values.put("tipo_usuario",usuario.getTipo_de_usuario());
        values.put("foto",usuario.getFoto());
        return values;
    }

    public List<Usuarios> buscaUsuarios() {
        String sql = "SELECT * FROM Usuarios;";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        List<Usuarios> usuarioscadastrados = new ArrayList<Usuarios>();
        while(c.moveToNext()){
            Usuarios usuario = new Usuarios();
            usuario.setId(c.getLong(c.getColumnIndex("id")));
            usuario.setNome(c.getString(c.getColumnIndex("nome")));
            usuario.setUsuario(c.getString(c.getColumnIndex("login")));
            usuario.setSenha(c.getString(c.getColumnIndex("senha")));
            usuario.setTipo_de_usuario(c.getString(c.getColumnIndex("tipo_usuario")));
            usuario.setFoto(c.getString(c.getColumnIndex("foto")));

            usuarioscadastrados.add(usuario);
        }
        c.close();
        return usuarioscadastrados;
    }

    public List<Usuarios> buscaUsuariosPeloNome(String nome) {
        String sql = "SELECT * FROM "+TABELA+" WHERE nome LIKE '%"+nome+"%';";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        List<Usuarios> usuariosencontrados = new ArrayList<Usuarios>();
        while(c.moveToNext()){
            Usuarios usuario = new Usuarios();
            usuario.setId(c.getLong(c.getColumnIndex("id")));
            usuario.setNome(c.getString(c.getColumnIndex("nome")));
            usuario.setUsuario(c.getString(c.getColumnIndex("login")));
            usuario.setSenha(c.getString(c.getColumnIndex("senha")));
            usuario.setTipo_de_usuario(c.getString(c.getColumnIndex("tipo_usuario")));
            usuario.setFoto(c.getString(c.getColumnIndex("foto")));

            usuariosencontrados.add(usuario);
        }
        c.close();
        return usuariosencontrados;
    }

    public Usuarios buscaUsuariosPeloId(Long usuario_id) {
        String sql = "Select * FROM " + TABELA + " WHERE " + "id" + " =  \"" + usuario_id + "\"";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        usuario = new Usuarios();
        if(c.moveToFirst()){
            c.moveToFirst();
            usuario.setId(c.getLong(c.getColumnIndex("id")));
            usuario.setNome(c.getString(c.getColumnIndex("nome")));
            usuario.setUsuario(c.getString(c.getColumnIndex("login")));
            usuario.setSenha(c.getString(c.getColumnIndex("senha")));
            usuario.setTipo_de_usuario(c.getString(c.getColumnIndex("tipo_usuario")));
            usuario.setFoto(c.getString(c.getColumnIndex("foto")));

        }else{
            usuario=null;
        }
        c.close();
        return usuario;
    }

    public void deleta(Usuarios usuario) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String [] params = {usuario.getId().toString()};
        db.delete(TABELA,"id= ?",params);
    }

    public void altera(Usuarios usuario) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String [] params = {usuario.getId().toString()};
        ContentValues dados =  pegadadosdoUsuario(usuario);
        db.update(TABELA,dados,"id = ?",params);
    }
}
