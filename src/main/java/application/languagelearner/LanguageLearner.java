package application.languagelearner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class LanguageLearner extends Application {

    private Dictionary dictionary;
    @Override
    public void init() throws Exception {
        // 1. Create the dictionary that the application uses
        this.dictionary = new Dictionary();
        String language = "French";

        try {
            Scanner reader = new Scanner(Paths.get(language + ".txt"));

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                String parts[] = line.split(",");
                String word = parts[0].trim();
                String translation = parts[1].trim();
                this.dictionary.add(word, translation);
            }
        } catch (Exception e) {
            System.out.println("Error message " + e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        // 2. Create the views ("subviews")
        InputView inputView = new InputView(dictionary);
        PracticeView practiceView = new PracticeView(dictionary);
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

        // Add error message
        Label errorMessage = new Label();

        // Add the buttons to the menu
        menu.getChildren().addAll(enterButton, practiceButton, errorMessage);
        layout.setTop(menu);

        // Connect the subviews with the buttons. Clicking menu buttons changes the subview.
        enterButton.setOnAction((event) -> layout.setCenter(inputView.getView()));
        practiceButton.setOnAction((event) -> {
            if (dictionary.containsWords()) {
                layout.setCenter(practiceView.getView());
                errorMessage.setText("");
            } else {
                errorMessage.setText("You need to input words to practice.");
            }
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