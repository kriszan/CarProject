package hupetrik.carproject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.zip.DataFormatException;

public class Car {
    private  String licensePlateNumber;
    private  String name;
    private  int year;

    public Car(String licensePlateNumber, String name, Integer year) throws DataFormatException {
        this.licensePlateNumber = licensePlateNumber;
        this.name = name;

        Calendar date = new GregorianCalendar();


        Integer currentYear = date.get(Calendar.YEAR);
        if (year <1950 || year > currentYear){
            throw new DataFormatException(year+"||" + currentYear);
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
