package com.dhiman.databinding.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.dhiman.databinding.BR;

/**
 * Created by dhiman_da on 12/2/2015.
 */
public class LauncherModel extends BaseObservable implements Parcelable {
    public static final Parcelable.Creator<LauncherModel> CREATOR = new Parcelable.Creator<LauncherModel>() {
        public LauncherModel createFromParcel(Parcel source) {
            return new LauncherModel(source);
        }

        public LauncherModel[] newArray(int size) {
            return new LauncherModel[size];
        }
    };
    private String text;
    private String className;

    public LauncherModel() {
    }

    public LauncherModel(String text, String className) {
        this.text = text;
        this.className = className;
    }

    private LauncherModel(Parcel in) {
        this.text = in.readString();
        this.className = in.readString();
    }

    @Bindable
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);
    }

    @Bindable
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
        notifyPropertyChanged(BR.className);
    }

    @Override
    public String toString() {
        return "LauncherModel{" +
                "text='" + text + '\'' +
                ", className='" + className + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeString(this.className);
    }
}
