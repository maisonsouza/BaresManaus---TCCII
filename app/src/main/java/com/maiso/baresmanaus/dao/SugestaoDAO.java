package com.maiso.baresmanaus.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.maiso.baresmanaus.helper.DBHelper;
import com.maiso.baresmanaus.modelo.Sugestoes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maiso on 11/01/2017.
 */

public class SugestaoDAO{

    private static final String TABELA = "Sugestoes";
    private DBHelper helper;

    public SugestaoDAO(DBHelper helper) {
        this.helper = helper;
    }

    public void insere(Sugestoes sugestao) {
        ContentValues values = pegadadosdaSugestao(sugestao);
        helper.getWritableDatabase().insert(TABELA,null,values);

    }

    @NonNull
    private ContentValues pegadadosdaSugestao(Sugestoes sugestao) {
        ContentValues values = new ContentValues();
        values.put("sugestao",sugestao.getSugestao());
        values.put("autor",sugestao.getAutor());
        values.put("nota_atendimento",sugestao.getNota_atendimento());
        return values;
    }

    public List<Sugestoes> buscaSugestoes() {
        String sql = "SELECT * FROM Sugestoes;";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        List<Sugestoes> sugestoescadastradas = new ArrayList<Sugestoes>();
        while(c.moveToNext()){
            Sugestoes sugestao = new Sugestoes();
            sugestao.setId(c.getLong(c.getColumnIndex("id")));
            sugestao.setAutor(c.getString(c.getColumnIndex("autor")));
            sugestao.setSugestao(c.getString(c.getColumnIndex("sugestao")));
            sugestao.setNota_atendimento(c.getDouble(c.getColumnIndex("nota_atendimento")));
            sugestoescadastradas.add(sugestao);
        }
        c.close();
        return sugestoescadastradas;
    }

    public void deleta(Sugestoes sugestao) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String [] params = {sugestao.getId().toString()};
        db.delete(TABELA,"id= ?",params);
    }

    public void altera(Sugestoes sugestao) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String [] params = {sugestao.getId().toString()};
        ContentValues dados =  pegadadosdaSugestao(sugestao);
        db.update(TABELA,dados,"id = ?",params);
    }
}
