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
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class LanguageLearner extends Application {

    private Dictionary dictionary;
    private String language;
    private ScoringSystem scoringSystem;
    @Override
    public void init() throws Exception {
        // 1. Create the dictionary that the application uses
        this.dictionary = new Dictionary();
        this.scoringSystem = new ScoringSystem();
        this.dictionary.read();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Create the views ("subviews")
        InputView inputView = new InputView(dictionary);
        PracticeView practiceView = new PracticeView(dictionary, scoringSystem);
        ScoreView scoreView = new ScoreView(dictionary);

        // Create the higher level layout
        BorderPane layout = new BorderPane();

        // Create the menu for the general layout
        HBox menu = new HBox();
        menu.setPadding(new Insets(20, 20, 20, 20));
        menu.setSpacing(10);


        // Create the menu buttons
        Button enterButton = new Button("Enter new words");
        Button practiceButton = new Button("Practice");

        // Menu for buttons
        HBox languageMenu = new HBox();
        languageMenu.setPadding(new Insets(20, 20, 20, 20));
        languageMenu.setSpacing(20);

        // Language buttons
        ToggleGroup languages = new ToggleGroup();
        ToggleButton frenchButton = new ToggleButton("French");
        ToggleButton germanButton = new ToggleButton("German");
        ToggleButton chineseButton = new ToggleButton("Chinese");
        languages.getToggles().addAll(frenchButton, germanButton, chineseButton);

        // Toggle language buttons
        // French
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
        // German
        germanButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            try {
                dictionary.toGerman();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (newValue) {
                germanButton.setStyle(
                        "-fx-background-color: green;" +
                                "-fx-text-fill: white");
            } else {
                germanButton.setStyle(null);
            }
            scoringSystem.clearScore();
            layout.setCenter(inputView.getView());
            practiceButton.setText("Practice");
        });
        // Chinese
        chineseButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            try {
                dictionary.toChinese();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (newValue) {
                chineseButton.setStyle(
                        "-fx-background-color: green;" +
                                "-fx-text-fill: white");
            } else {
                chineseButton.setStyle(null);
            }
            scoringSystem.clearScore();
            layout.setCenter(inputView.getView());
            practiceButton.setText("Practice");
        });

        // Add error message
        Label errorMessage = new Label();

        // Add the buttons to the menu
        menu.getChildren().addAll(enterButton, practiceButton, errorMessage);
        layout.setTop(menu);
        languageMenu.getChildren().addAll(frenchButton, germanButton, chineseButton);
        layout.setBottom(languageMenu);

        // Connect the subviews with the buttons. Clicking menu buttons changes the subview.
        enterButton.setOnAction((event) -> {
                    layout.setCenter(inputView.getView());
                    practiceButton.setText("Practice");
                    scoringSystem.clearScore();
                });

        practiceButton.setOnAction((event) -> {
            if (dictionary.containsWords()) {
                layout.setCenter(practiceView.getView());
                errorMessage.setText("");
            } else {
                errorMessage.setText("You need to input words to practice.");
            }
            scoringSystem.clearScore();
            practiceButton.setText("Retry");
        });

        // First show the input view
        layout.setCenter(inputView.getView());

        // Create the main view and add the high level layout
        Scene view = new Scene(layout, 500, 400);

        // Show the application
        stage.setScene(view);
        stage.show();
    }

    public static void main(String[] args) {
        launch(LanguageLearner.class);
    }
}