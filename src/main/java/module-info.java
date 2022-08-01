module application.languagelearner {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens application.languagelearner to javafx.fxml;
    exports application.languagelearner;
}