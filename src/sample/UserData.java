package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by WickerLabs on 3/11/2017.
 */
//used on loading data to TableViews on the application
public class UserData {


    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty phone = new SimpleStringProperty();
    private StringProperty sex = new SimpleStringProperty();
    private StringProperty appartment = new SimpleStringProperty();
    private StringProperty roomType = new SimpleStringProperty();
    private StringProperty roomNo = new SimpleStringProperty();
    private IntegerProperty fee = new SimpleIntegerProperty();
    private StringProperty contract = new SimpleStringProperty();
    private StringProperty entryDate = new SimpleStringProperty();
    private StringProperty expireDate = new SimpleStringProperty();


    public UserData(String firstName, String lastName, String email, String phone, String sex, String appartment, String roomType, String roomNo, int fee, String contract, String entryDate, String expireDate) {

        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.sex = new SimpleStringProperty(sex);
        this.appartment = new SimpleStringProperty(appartment);
        this.roomType = new SimpleStringProperty(roomType);
        this.roomNo = new SimpleStringProperty(roomNo);
        this.fee = new SimpleIntegerProperty(fee);
        this.contract = new SimpleStringProperty(contract);
        this.entryDate = new SimpleStringProperty(entryDate);
        this.expireDate = new SimpleStringProperty(expireDate);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public StringProperty sexProperty() {
        return sex;
    }

    public StringProperty appartmentProperty() {
        return appartment;
    }

    public StringProperty roomTypeProperty() {
        return roomType;
    }

    public StringProperty roomNoProperty() {
        return roomNo;
    }

    public IntegerProperty feeProperty() {
        return fee;
    }

    public StringProperty contractProperty() {
        return contract;
    }

    public StringProperty entryDateProperty() {
        return entryDate;
    }

    public StringProperty expireDateProperty() {
        return expireDate;
    }
}