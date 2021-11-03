module com.example.projectthree {
    requires javafx.controls;
    requires javafx.fxml;


    opens tuition to javafx.fxml;
    exports tuition;
}