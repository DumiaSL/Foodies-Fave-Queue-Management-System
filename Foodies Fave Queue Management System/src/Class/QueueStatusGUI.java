package Class;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.text.html.ListView;

public class QueueStatusGUI extends Application {
    // Sample data for demonstration
    private ObservableList<String> foodQueue = FXCollections.observableArrayList(
            "Customer 1", "Customer 2", "Customer 3");
    private ObservableList<String> waitingQueue = FXCollections.observableArrayList(
            "Customer 4", "Customer 5");

    private ListView<String> foodQueueListView;
    private ListView<String> waitingQueueListView;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Queue Status");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        // Food Queue label and ListView
        Label foodQueueLabel = new Label("Food Queue");
        foodQueueListView = new ListView<>();
        foodQueueListView.setItems(foodQueue);

        // Waiting Queue label and ListView
        Label waitingQueueLabel = new Label("Waiting Queue");
        waitingQueueListView = new ListView<>();
        waitingQueueListView.setItems(waitingQueue);

        // Search functionality
        TextField searchField = new TextField();
        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> searchCustomer(searchField.getText()));

        // Add all components to the grid
        grid.add(foodQueueLabel, 0, 0);
        grid.add(foodQueueListView, 0, 1);
        grid.add(waitingQueueLabel, 1, 0);
        grid.add(waitingQueueListView, 1, 1);
        grid.add(searchField, 0, 2);
        grid.add(searchButton, 1, 2);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void searchCustomer(String customerName) {
        if (foodQueue.contains(customerName)) {
            foodQueueListView.getSelectionModel().select(customerName);
        } else if (waitingQueue.contains(customerName)) {
            waitingQueueListView.getSelectionModel().select(customerName);
        } else {
            // Customer not found in any queue
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Customer Search");
            alert.setHeaderText(null);
            alert.setContentText("Customer not found.");
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

