
package com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds;
import android.graphics.Bitmap;
import android.net.Uri;

import ibmmobileappbuilder.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class EmployeesDBDSItem implements Parcelable, IdentifiableBean {

    @SerializedName("name") public String name;
    @SerializedName("lastname") public String lastname;
    @SerializedName("picture") public String picture;
    @SerializedName("role") public String role;
    @SerializedName("email") public String email;
    @SerializedName("phone") public String phone;
    @SerializedName("id") public String id;
    @SerializedName("pictureUri") public transient Uri pictureUri;

    @Override
    public String getIdentifiableId() {
      return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(lastname);
        dest.writeString(picture);
        dest.writeString(role);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(id);
    }

    public static final Creator<EmployeesDBDSItem> CREATOR = new Creator<EmployeesDBDSItem>() {
        @Override
        public EmployeesDBDSItem createFromParcel(Parcel in) {
            EmployeesDBDSItem item = new EmployeesDBDSItem();

            item.name = in.readString();
            item.lastname = in.readString();
            item.picture = in.readString();
            item.role = in.readString();
            item.email = in.readString();
            item.phone = in.readString();
            item.id = in.readString();
            return item;
        }

        @Override
        public EmployeesDBDSItem[] newArray(int size) {
            return new EmployeesDBDSItem[size];
        }
    };

}


