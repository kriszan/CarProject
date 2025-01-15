package hupetrik.carproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.zip.DataFormatException;

public class HelloController {

    private ArrayList<Car> dataToBeSaved = new ArrayList<>();

    @FXML
    private TextArea licensePlateNumberInput;

    @FXML
    private TextArea nameInput;

    @FXML
    private TextArea yearInput;

    @FXML
    public void onAddButtonClick() throws DataFormatException {
        String licensePlateNumber = licensePlateNumberInput.getText().toString();
        String name = nameInput.getText().toString();
        String year = yearInput.getText().toString();

        dataToBeSaved.add(new Car(licensePlateNumber, name, Integer.valueOf(year)));

        System.out.println("Adding car: " + licensePlateNumber + ", " + name + ", " + year);
    }

    @FXML
    public void onSaveButtonClick() throws IOException {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Would You Like To Save?");
        alert.setContentText("Please choose an option.");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yesButton) {
            Writer writer = null;
            try {
                File file = new File(System.getProperty("user.dir"), "data.csv");
                writer = new BufferedWriter(new FileWriter(file));
                Car car = new Car(licensePlateNumberInput.getText(), nameInput.getText(), Integer.valueOf(yearInput.getText()));

                String text = car.getLicensePlateNumber() + ";" + car.getName() + " ;" + car.getYear() + System.lineSeparator();
                writer.write(text);

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                writer.flush();
                writer.close();
            }
        } else if (result.get() == noButton) {

        }
        System.err.println("save");
    }

    @FXML
    public void onLoadButtonClick() throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Would You Like To Load?");
        alert.setContentText("Please choose an option.");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yesButton) {
            try {
                File file = new File(System.getProperty("user.dir"), "data.csv");
                BufferedReader reader = new BufferedReader(new FileReader(file));
                ObservableList<Car> cars = FXCollections.observableArrayList();

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(";");
                    cars.add(new Car(data[0], data[1], Integer.valueOf(data[2])));
                }
                reader.close();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("table-view.fxml"));
                Parent root1 =  fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("Data");
                stage.setScene(new Scene(root1));

                TableView<Car> tableView = (TableView<Car>) fxmlLoader.getNamespace().get("tableView");
                TableColumn<Car, String> licensePlateNumberColumn = new TableColumn<>("licensePlateNumber");
                licensePlateNumberColumn.setCellValueFactory(new PropertyValueFactory<>("licensePlateNumber"));

                TableColumn<Car, String> nameColumn = new TableColumn<>("name");
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

                TableColumn<Car, Integer> yearColumn = new TableColumn<>("year");
                yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

                tableView.getColumns().addAll(licensePlateNumberColumn, nameColumn, yearColumn);
                tableView.setItems(cars);
                stage.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (result.get() == noButton) {

        }
        System.err.println("load");
    }


    public void initialize() {

    }
}