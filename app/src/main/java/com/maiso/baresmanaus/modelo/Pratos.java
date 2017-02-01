package com.maiso.baresmanaus.modelo;


import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by maiso on 14/01/2017.
 */

public class Pratos implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome_prato);
        dest.writeString(preço);
        dest.writeInt(sdk_int);
        dest.writeString(descricao);
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
}