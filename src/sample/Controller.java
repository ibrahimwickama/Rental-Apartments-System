package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {

    public static Statement stat;
    private static Connection con;
    public PreparedStatement prep;
    public Button logButton, signButton, regUser, regCustomButton, customerViewButton, roomsAvailableButton, addAppart;
    public MenuItem logOu;
    public BorderPane borderPan;
    public ComboBox<String> combBoxApp, combBoxRmTyp, combBoxApp2;
    public TextField userFName, userLName, userEmail, userPhonNo, userAge, userRoomNo, userFee;
    public TextField appartName, appartCountry, street, appartCity, appartRoomsTotal, appartmastroomTotal, appartSingleroomTotal, appartmastroomFee, appartSingleroomFee;
    public DatePicker userBirth, userStartDate, userEndDate;
    public RadioButton userMale, userFemale, userCon_6M, userCon_1y;
    public TableView customerTable, appartmentTable;
    public TableColumn usIndexNo, usFName, usLName, usEmail, usPhoneNo, usGender, usAppartment, usRoomType, usRoomNo, usFee, usContract, usStartDate, usEndDate, apptName, apptRoomTypes, apptRoomNo;
    ObservableList<String> appartmentList = FXCollections
            .observableArrayList("");
    ObservableList<String> roomTypes = FXCollections
            .observableArrayList("Master Deluxe", "Master Room", "Single Room");
    private ObservableList<UserData> data;

    public void loginClick() throws Exception {
        //this will close login window & start next scene
        closeLogin();
        loadScene2();
    }

    public void signupClick() throws Exception {
        //closeLog();
        loadRegster();
    }

    public void loadScene2() throws Exception {
        // This loads the main Windows
        Stage mainStage = new Stage();
        Parent parent = FXMLLoader.load(getClass().getResource("RRMSscene2.fxml"));
        Scene scene = new Scene(parent);
        mainStage.setTitle("Wickerman's Control Center");
        mainStage.setResizable(true);
        mainStage.setMaximized(true);
        // mainStage.setMaxHeight(950);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void closeLogin() throws Exception {
        Stage stage = (Stage) logButton.getScene().getWindow();
        stage.close();
    }

    public void closeRegUser() throws Exception {
        Stage stage = (Stage) regUser.getScene().getWindow();
        stage.close();
    }

    public void loadRegster() throws Exception {
        // This loads the main Windows
        Stage mainStage = new Stage();
        Parent parent = FXMLLoader.load(getClass().getResource("RRMSregisterScene.fxml"));
        Scene scene = new Scene(parent);
        mainStage.setTitle("Register pane");
        mainStage.setResizable(false);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void reLoadLogin() throws Exception {
        closeRegUser();
        //loadScene2();
        // exits the Register pane for new User to LogIn again
        //Stage stage = (Stage) regUser.getScene().getWindow();
        //stage.close();
    }

    public void regCustomer() throws IOException {
        // Registers a new Customer
        Parent parent = FXMLLoader.load(getClass().getResource("RRMSregisterCustomerScene.fxml"));
        borderPan.setLeft(null);
        borderPan.setCenter(parent);
    }

    public void customerView() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("RRMSCustomerViewScene.fxml"));
        borderPan.setCenter(null);
        borderPan.setLeft(parent);
    }

    public void availableRooms() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("RRMSavailableRoomsScene.fxml"));
        borderPan.setCenter(null);
        borderPan.setLeft(parent);
    }

    public void addAptmnt() throws IOException {
        // Adds a new Apartment details
        Parent parent = FXMLLoader.load(getClass().getResource("RRMSaddApartmentScene.fxml"));
        borderPan.setLeft(null);
        borderPan.setCenter(parent);
    }

    public void signOut() throws Exception {
        closeMain();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Wickerman's Login System");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void closeMain() throws Exception {
        // regCustomerButton assigned is just a decoy so as to successful close Main window
        Stage main = (Stage) regCustomButton.getScene().getWindow();
        main.close();
    }

    public void combCustomer() {
        //shows appartment name loccations on Customer Registration ComboBox form list is fetched from database.
        try {
            List simple = new ArrayList<>();
            con = DriverManager.getConnection("jdbc:sqlite:RRMSDataBase.db");
            stat = con.createStatement();
            ResultSet rs = stat.executeQuery("select * from appartments");
            while (rs.next()) {
                String current = rs.getString(1);
                System.out.println(" " + current);
                simple.add(current);
                ObservableList<String> appartmentList = FXCollections
                        .observableArrayList(simple);
                combBoxApp2.setItems(appartmentList);
            }
            con.close();
            stat.close();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    public void combRoomTyp() {
        combBoxRmTyp.setItems(roomTypes);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // section for loading appartment lists from database to ComboBox
        try {
            List sampulii = new ArrayList<>();
            con = DBConnect.getConnected();
            stat = con.createStatement();
            ResultSet rs = stat.executeQuery("select * from appartments");
            while (rs.next()) {
                String current = rs.getString(1);
                System.out.println(" " + current);
                sampulii.add(current);
                ObservableList<String> appartmentList = FXCollections
                        .observableArrayList(sampulii);
                combBoxApp.setItems(appartmentList);
            }
            //con.close();
            stat.close();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        // section for loading customer information for database to application table view
        ResultSet rst;
        try {

            con = DriverManager.getConnection("jdbc:sqlite:RRMSDataBase.db");
            stat = con.createStatement();
            data = FXCollections.observableArrayList();
            rst = stat.executeQuery("select * from RegUser");
            while (rst.next()) {
                data.add(new UserData(rst.getString("firstName"), rst.getString("lastName"),
                        rst.getString("email"), rst.getString("phone"),
                        rst.getString("sex"), rst.getString("appartment"),
                        rst.getString("roomType"), rst.getString("roomNo"),
                        rst.getInt("fee"), rst.getString("contract"),
                        rst.getString("entryDate"), rst.getString("expireDate")));
            }

            usFName.setCellValueFactory(new PropertyValueFactory("firstName"));
            usLName.setCellValueFactory(new PropertyValueFactory("lastName"));
            usEmail.setCellValueFactory(new PropertyValueFactory("email"));
            usPhoneNo.setCellValueFactory(new PropertyValueFactory("phone"));
            usGender.setCellValueFactory(new PropertyValueFactory("sex"));
            usAppartment.setCellValueFactory(new PropertyValueFactory("appartment"));
            usRoomType.setCellValueFactory(new PropertyValueFactory("roomType"));
            usRoomNo.setCellValueFactory(new PropertyValueFactory("roomNo"));
            usFee.setCellValueFactory(new PropertyValueFactory("fee"));
            usContract.setCellValueFactory(new PropertyValueFactory("contract"));
            usStartDate.setCellValueFactory(new PropertyValueFactory("entryDate"));
            usEndDate.setCellValueFactory(new PropertyValueFactory("expireDate"));

            customerTable.setItems(data);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data On Customers");
        }
    }

    // Method for adding new Appartments to the DB
    public void addAppartment() throws SQLException {

        String query = "insert into appartments values(?,?,?,?,?,?,?,?)";
        con = null;
        prep = null;

        try {
            con = DBConnect.getConnected();
            //con = DriverManager.getConnection("jdbc:sqlite:RRMSDataBase.db");
            prep = con.prepareStatement(query);
            // inserts new appartment details to Database from the application
            prep.setString(1, appartName.getText());
            prep.setString(2, appartCountry.getText());
            prep.setString(3, appartCity.getText());
            prep.setInt(4, Integer.parseInt(appartRoomsTotal.getText()));
            prep.setInt(5, Integer.parseInt(appartmastroomTotal.getText()));
            prep.setInt(6, Integer.parseInt(appartSingleroomTotal.getText()));
            prep.setInt(7, Integer.parseInt(appartmastroomFee.getText()));
            prep.setInt(8, Integer.parseInt(appartSingleroomFee.getText()));
            prep.execute();

            con.close();
            //clears the textFields after Adding new Appartment
            appartName.setText(null);
            appartCountry.setText(null);
            appartCity.setText(null);
            appartRoomsTotal.setText(null);
            street.setText(null);
            appartmastroomTotal.setText(null);
            appartSingleroomTotal.setText(null);
            appartmastroomFee.setText(null);
            appartSingleroomFee.setText(null);

        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addNewUser() throws SQLException {

        String query = "insert into RegUser values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        con = null;
        prep = null;

        String gender = null;
        if (userMale.isSelected()) {
            gender = "Male";
        } else if (userFemale.isSelected()) {
            gender = "Female";
        }

        String contract = null;
        if (userCon_6M.isSelected()) {
            contract = "6 Months";
        } else if (userCon_1y.isSelected()) {
            contract = "1 Year";
        }
        String typesroom = combBoxRmTyp.getSelectionModel().getSelectedItem().toString();
        String appartmentChoosed = combBoxApp2.getSelectionModel().getSelectedItem().toString();

        try {

            con = DriverManager.getConnection("jdbc:sqlite:RRMSDataBase.db");
            prep = con.prepareStatement(query);
            //inserts values to the DataBase from the Customer Registration form
            prep.setString(1, userFName.getText());
            prep.setString(2, userLName.getText());
            prep.setString(3, userEmail.getText());
            prep.setInt(4, Integer.parseInt(userPhonNo.getText()));
            prep.setString(5, userBirth.getValue().toString());
            prep.setInt(6, Integer.parseInt(userAge.getText()));
            prep.setString(7, gender);
            prep.setString(8, appartmentChoosed);
            prep.setString(9, typesroom);
            prep.setString(10, userRoomNo.getText());
            prep.setInt(11, Integer.parseInt(userFee.getText()));
            prep.setString(12, contract);
            prep.setString(13, userStartDate.getValue().toString());
            prep.setString(14, userEndDate.getValue().toString());
            prep.execute();

            //clears the textFields after registering new customer
            userFName.setText(null);
            userLName.setText(null);
            userEmail.setText(null);
            userPhonNo.setText(null);
            userBirth.getEditor().clear();
            userAge.setText(null);
            userRoomNo.setText(null);
            userFee.setText(null);
            userStartDate.getEditor().clear();
            userEndDate.getEditor().clear();

        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}

