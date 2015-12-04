package com.dhiman.databinding.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.dhiman.databinding.BR;

/**
 * Created by dhiman_da on 12/2/2015.
 */
public class ActivityModel extends BaseObservable implements Parcelable {
    public static final String ACTIVITY_MODEL_TAG = "activity_model_tag";
    public static final Parcelable.Creator<ActivityModel> CREATOR = new Parcelable.Creator<ActivityModel>() {
        public ActivityModel createFromParcel(Parcel source) {
            return new ActivityModel(source);
        }

        public ActivityModel[] newArray(int size) {
            return new ActivityModel[size];
        }
    };
    private String firstName;
    private String lastName;
    private boolean showFirstName;
    private boolean showLastName;

    public ActivityModel() {
    }

    protected ActivityModel(Parcel in) {
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.showFirstName = in.readByte() != 0 ? true : false;
        this.showLastName = in.readByte() != 0 ? true : false;
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(com.dhiman.databinding.BR.firstName);
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(com.dhiman.databinding.BR.lastName);
    }

    @Bindable
    public boolean isShowFirstName() {
        return showFirstName;
    }

    public void setShowFirstName(boolean showFirstName) {
        this.showFirstName = showFirstName;
        notifyPropertyChanged(BR.showFirstName);
    }

    @Bindable
    public boolean isShowLastName() {
        return showLastName;
    }

    public void setShowLastName(boolean showLastName) {
        this.showLastName = showLastName;
        notifyPropertyChanged(BR.showLastName);
    }

    @Override
    public String toString() {
        return "ActivityModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", showFirstName=" + showFirstName +
                ", showLastName=" + showLastName +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeByte((byte) (this.showFirstName ? 1 : 0));
        dest.writeByte((byte) (this.showLastName ? 1 : 0));
    }
}
