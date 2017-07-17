package katienza.pork.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import katienza.pork.model.util.DateConverter;

/**
 * Created by katienza on 13/07/2017.
 */
@Entity
public class BreedingRecord implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @TypeConverters(DateConverter.class)
    public Date dateBreed;

    public int parity = -1;

    @Embedded
    public Sow sow;

    public BreedingRecord(Date dateBreed){
        this.dateBreed = dateBreed;
    }

    protected BreedingRecord(Parcel in) {
        id = in.readInt();

        parity = in.readInt();
        sow = in.readParcelable(Sow.class.getClassLoader());
    }

    public static final Creator<BreedingRecord> CREATOR = new Creator<BreedingRecord>() {
        @Override
        public BreedingRecord createFromParcel(Parcel in) {
            return new BreedingRecord(in);
        }

        @Override
        public BreedingRecord[] newArray(int size) {
            return new BreedingRecord[size];
        }
    };



    public int getParity() {
        return parity;
    }

    public void setParity(int parity) {
        this.parity = parity;
    }

    public Date getDateBreed() {
        return dateBreed;
    }

    public Sow getSow() {
        return sow;
    }

    public void setSow(Sow sow) {
        this.sow = sow;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(parity);
        dest.writeParcelable(sow, flags);
    }
}
