package com.maiso.baresmanaus.modelo;


import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by maiso on 14/01/2017.
 */

public class AndroidOS implements Parcelable {
    public String nome_produto;
    public String preço;
    public int sdk_int;
    public String description;
    public String year;
    public String image_url;
    public String icon_url;

    public AndroidOS() {
    }

    private AndroidOS(Parcel in) {
        nome_produto = in.readString();
        preço = in.readString();
        sdk_int = in.readInt();
        description = in.readString();
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
        dest.writeString(nome_produto);
        dest.writeString(preço);
        dest.writeInt(sdk_int);
        dest.writeString(description);
        dest.writeString(year);
        dest.writeString(image_url);
        dest.writeString(icon_url);
    }

    public static final Creator<AndroidOS> CREATOR = new Creator<AndroidOS>() {
        @Override
        public AndroidOS createFromParcel(Parcel source) {
            return new AndroidOS(source);
        }

        @Override
        public AndroidOS[] newArray(int size) {
            return new AndroidOS[size];
        }
    };
}