package katienza.pork.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

import katienza.pork.model.util.DateConverter;

/**
 * Created by katienza on 13/07/2017.
 */
@Entity
public class BreedingRecord {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @TypeConverters(DateConverter.class)
    public Date dateBreed;

    public String testName;

    public int parity = -1;

    public BreedingRecord(Date dateBreed){
        this.dateBreed = dateBreed;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getParity() {
        return parity;
    }

    public void setParity(int parity) {
        this.parity = parity;
    }

    public Date getDateBreed() {
        return dateBreed;
    }
}
