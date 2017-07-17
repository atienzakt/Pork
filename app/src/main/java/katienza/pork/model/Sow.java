package katienza.pork.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import katienza.pork.model.util.DateConverter;

/**
 * Created by katienza on 14/07/2017.
 */
@Entity
public class Sow implements Parcelable {

    @PrimaryKey
    public String sowNo;

    @ColumnInfo( name = "breed")
    public String breed;

    public String origin;

    @TypeConverters(DateConverter.class)
    public Date birthDate;

    public Sow(String sowNo){
        this.sowNo=sowNo;
    }

    protected Sow(Parcel in) {
        sowNo = in.readString();
        breed = in.readString();
        origin = in.readString();
    }

    public static final Creator<Sow> CREATOR = new Creator<Sow>() {
        @Override
        public Sow createFromParcel(Parcel in) {
            return new Sow(in);
        }

        @Override
        public Sow[] newArray(int size) {
            return new Sow[size];
        }
    };

    public String getSowNo() {
        return sowNo;
    }

    public void setSowNo(String sowNo){
        this.sowNo=sowNo;
    }
    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sowNo);
        dest.writeString(breed);
        dest.writeString(origin);
    }

    @Override
    public String toString() {
        return sowNo;
    }
}
