module com.evalit {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.evalit to javafx.fxml;
    exports com.evalit;
}
