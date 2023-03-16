module com.example.ecomm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ecomm to javafx.fxml;
    exports com.example.ecomm;
}