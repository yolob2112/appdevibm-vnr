
package com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds;
import java.util.Date;

import ibmmobileappbuilder.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class NewsScreen1DSItem implements Parcelable, IdentifiableBean {

    @SerializedName("title") public String title;
    @SerializedName("subtitle") public String subtitle;
    @SerializedName("content") public String content;
    @SerializedName("date") public Date date;
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
        dest.writeString(title);
        dest.writeString(subtitle);
        dest.writeString(content);
        dest.writeValue(date != null ? date.getTime() : null);
        dest.writeString(id);
    }

    public static final Creator<NewsScreen1DSItem> CREATOR = new Creator<NewsScreen1DSItem>() {
        @Override
        public NewsScreen1DSItem createFromParcel(Parcel in) {
            NewsScreen1DSItem item = new NewsScreen1DSItem();

            item.title = in.readString();
            item.subtitle = in.readString();
            item.content = in.readString();
            Long dateAux = (Long) in.readValue(null);
            item.date = dateAux != null ? new Date(dateAux) : null;
            item.id = in.readString();
            return item;
        }

        @Override
        public NewsScreen1DSItem[] newArray(int size) {
            return new NewsScreen1DSItem[size];
        }
    };

}


