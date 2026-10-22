module com.cellculture {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    exports com.cellculture;

    opens com.cellculture to javafx.fxml;
    opens com.cellculture.controller to javafx.fxml;
} //chatgpt CODE