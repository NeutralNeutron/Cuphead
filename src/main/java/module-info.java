module com.example.cuphead {
        requires javafx.controls;
        requires javafx.fxml;
        requires javafx.media;

        opens com.example.cuphead to javafx.fxml;
        exports com.example.cuphead;
        }