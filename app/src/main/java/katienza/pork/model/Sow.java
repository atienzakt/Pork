package katienza.pork.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

import katienza.pork.model.util.DateConverter;

/**
 * Created by katienza on 14/07/2017.
 */
@Entity
public class Sow {

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

    public String getSowNo() {
        return sowNo;
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
}
