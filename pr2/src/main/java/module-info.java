module com.example.pr2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pr2 to javafx.fxml;
    exports com.example.pr2;
}