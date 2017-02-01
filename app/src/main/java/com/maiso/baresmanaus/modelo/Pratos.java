package com.maiso.baresmanaus.modelo;


import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigInteger;

/**
 * Created by maiso on 14/01/2017.
 */

public class Pratos implements Parcelable {
    public Long id;
    public String nome_prato;
    public String preço;
    public int sdk_int;
    public String descricao;
    public String year;
    public String image_url;
    public String icon_url;

    public Pratos() {
    }

    private Pratos(Parcel in) {
        nome_prato = in.readString();
        preço = in.readString();
        sdk_int = in.readInt();
        descricao = in.readString();
        year = in.readString();
        image_url = in.readString();
        icon_url = in.readString();
    }

    public void setNome_prato(String nome_prato) {
        this.nome_prato = nome_prato;
    }

    public void setPreço(String preço) {
        this.preço = preço;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public int describeContents() {
    
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome_prato);

        dest.writeString(descricao);
        dest.writeString(preço);
        dest.writeInt(sdk_int);
        dest.writeString(year);
        dest.writeString(image_url);
        dest.writeString(icon_url);
    }

    public static final Creator<Pratos> CREATOR = new Creator<Pratos>() {
        @Override
        public Pratos createFromParcel(Parcel source) {
            return new Pratos(source);
        }

        @Override
        public Pratos[] newArray(int size) {
            return new Pratos[size];
        }
    };

    public void setId(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}