package application.languagelearner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

public class LanguageLearner extends Application {

    private Dictionary dictionary;
    private ScoringSystem scoringSystem;
    @Override
    public void init() throws Exception {
        // Create the dictionary and scoring system that the application uses. Init gets initialized before start.
        this.dictionary = new Dictionary();
        this.scoringSystem = new ScoringSystem();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Create the views ("subviews").
        InputView inputView = new InputView(dictionary);
        PracticeView practiceView = new PracticeView(dictionary, scoringSystem);
        ScoreView scoreView = new ScoreView(dictionary);

        // Create the higher level layout.
        BorderPane layout = new BorderPane();

        // Create the menu for the general layout.
        HBox menu = new HBox();
        menu.setPadding(new Insets(20, 20, 20, 20));
        menu.setSpacing(10);

        // Create the menu buttons.
        Button enterButton = new Button("Enter new words");
        Button practiceButton = new Button("Practice");

        // Menu for buttons.
        HBox languageMenu = new HBox();
        languageMenu.setPadding(new Insets(20, 20, 20, 20));
        languageMenu.setSpacing(20);

        // Language buttons.
        ToggleGroup languages = new ToggleGroup();
        ToggleButton frenchButton = new ToggleButton("French");
        ToggleButton moroccanButton = new ToggleButton("Moroccan");
//        ToggleButton chineseButton = new ToggleButton("Chinese");
        languages.getToggles().addAll(frenchButton, moroccanButton);

        // Toggle language buttons.
        // French.
        frenchButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            try {
                dictionary.toFrench();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (newValue) {
                frenchButton.setStyle(
                        "-fx-background-color: green;" +
                                "-fx-text-fill: white");
            } else {
                frenchButton.setStyle(null);
            }
            scoringSystem.clearScore();
            layout.setCenter(inputView.getView());
            practiceButton.setText("Practice");
        });
        // Moroccan
        moroccanButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            try {
                dictionary.toMoroccan();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (newValue) {
                moroccanButton.setStyle(
                        "-fx-background-color: green;" +
                                "-fx-text-fill: white");
            } else {
                moroccanButton.setStyle(null);
            }
            scoringSystem.clearScore();
            layout.setCenter(inputView.getView());
            practiceButton.setText("Practice");
        });
        // Chinese.
//        chineseButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
//            try {
//                dictionary.toChinese();
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//            if (newValue) {
//                chineseButton.setStyle(
//                        "-fx-background-color: green;" +
//                                "-fx-text-fill: white");
//            } else {
//                chineseButton.setStyle(null);
//            }
//            scoringSystem.clearScore();
//            layout.setCenter(inputView.getView());
//            practiceButton.setText("Practice");
//        });

        // Add error message.
        Label errorMessage = new Label();

        // Add the buttons to the menu.
        menu.getChildren().addAll(enterButton, practiceButton, errorMessage);
        layout.setTop(menu);
        languageMenu.getChildren().addAll(frenchButton, moroccanButton);
        layout.setBottom(languageMenu);

        // Connect the subviews with the buttons. Clicking menu buttons changes the subview.
        enterButton.setOnAction((event) -> {
                    layout.setCenter(inputView.getView());
                    practiceButton.setText("Practice");
                    scoringSystem.clearScore();
                });

        // Practice button event.
        practiceButton.setOnAction((event) -> {
            try {
                // New dictionary, words and score.
                dictionary.read();
                scoringSystem.clearScore();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            // If there are words to practice then continue to practice view, if not then show error message.
            if (dictionary.containsWords()) {
                layout.setCenter(practiceView.getView());
                errorMessage.setText("");
                practiceButton.setText("Retry");
            } else {
                errorMessage.setText("You need to input words to practice.");
            }
        });

        // First show the input view.
        layout.setCenter(inputView.getView());

        // Create the main view and add the high level layout.
        Scene view = new Scene(layout, 500, 400);

        // Show the application.
        stage.setScene(view);
        stage.show();
    }

    public static void main(String[] args) {
        launch(LanguageLearner.class);
    }
}