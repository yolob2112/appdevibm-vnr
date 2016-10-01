
package com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds;
import ibmmobileappbuilder.ds.restds.GeoPoint;
import java.util.Date;
import java.util.Date;

import ibmmobileappbuilder.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class StatusScreen1DSItem implements Parcelable, IdentifiableBean {

    @SerializedName("status") public String status;
    @SerializedName("location") public GeoPoint location;
    @SerializedName("employee") public String employee;
    @SerializedName("startDate") public Date startDate;
    @SerializedName("endDate") public Date endDate;
    @SerializedName("id") public String id;

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
        dest.writeString(status);
        dest.writeDoubleArray(location != null  && location.coordinates.length != 0 ? location.coordinates : null);
        dest.writeString(employee);
        dest.writeValue(startDate != null ? startDate.getTime() : null);
        dest.writeValue(endDate != null ? endDate.getTime() : null);
        dest.writeString(id);
    }

    public static final Creator<StatusScreen1DSItem> CREATOR = new Creator<StatusScreen1DSItem>() {
        @Override
        public StatusScreen1DSItem createFromParcel(Parcel in) {
            StatusScreen1DSItem item = new StatusScreen1DSItem();

            item.status = in.readString();
            double[] location_coords = in.createDoubleArray();
            if (location_coords != null)
                item.location = new GeoPoint(location_coords);
            item.employee = in.readString();
            Long startDateAux = (Long) in.readValue(null);
            item.startDate = startDateAux != null ? new Date(startDateAux) : null;
            Long endDateAux = (Long) in.readValue(null);
            item.endDate = endDateAux != null ? new Date(endDateAux) : null;
            item.id = in.readString();
            return item;
        }

        @Override
        public StatusScreen1DSItem[] newArray(int size) {
            return new StatusScreen1DSItem[size];
        }
    };

}


