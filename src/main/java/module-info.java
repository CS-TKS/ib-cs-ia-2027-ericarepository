module com.cellculture {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports com.cellculture;
    opens com.cellculture to javafx.fxml;
}