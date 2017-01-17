package com.maiso.baresmanaus.modelo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by maiso on 10/11/2016.
 */

public class Usuarios implements Serializable{




    private Long id;
    private String nome;
    private String usuario;
    private String senha;
    private String tipo_de_usuario;
    private List usuarios;
    private String foto;



    public List getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List usuarios) {
        this.usuarios = usuarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo_de_usuario() {
        return tipo_de_usuario;
    }

    public void setTipo_de_usuario(String tipo_de_usuario) {
        this.tipo_de_usuario = tipo_de_usuario;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "  "+id+"          -|-      "+nome+"       -|-       "+usuario;
    }

}
