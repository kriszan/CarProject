package hupetrik.carproject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.DataFormatException;

public class Car {
    private  String licensePlateNumber;
    private  String name;
    private  int year;

    public Car(String licensePlateNumber, String name, Integer year) throws DataFormatException {
        this.licensePlateNumber = licensePlateNumber;
        this.name = name;
        Date date = new Date();
        Integer currentYear = date.getYear();
        if (date.getYear() <1950 || date.getYear() > currentYear){
            throw new DataFormatException("IDK");
        }

        this.year = year;

    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
