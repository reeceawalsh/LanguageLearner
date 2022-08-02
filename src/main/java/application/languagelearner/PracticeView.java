package application.languagelearner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.ButtonSkin;
import javafx.scene.layout.GridPane;
import java.util.concurrent.atomic.AtomicReference;

public class PracticeView {
    private Dictionary currentDictionary;
    private ScoringSystem scoringSystem;
    public PracticeView(Dictionary dictionary, ScoringSystem scoringSystem) {
        this.currentDictionary = dictionary;
        this.scoringSystem = scoringSystem;
    }

    public Parent getView() {
        // Word is an atomic reference which allows it to keep changing without synchronization.
        AtomicReference<String> word = new AtomicReference<>(currentDictionary.getRandomWord());
        // Initialize elements.
        GridPane layout = new GridPane();
        Label wordInstruction = new Label("Translate '" + word + "'");
        TextField translationField = new TextField();
        Label feedback = new Label("");
        Label score = new Label("Your score is " + scoringSystem.getScore());

        // Check button.
        Button checkButton = new Button("Check");
        // This consumesMouseEvents(false) allows it so that I can set the checkButton to default and use enter to trigger it's event.
        checkButton.setSkin(new ButtonSkin(checkButton) {
            {this.consumeMouseEvents(false);}
        });
        checkButton.setDefaultButton(true);

        // Layout of elements.
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setAlignment(Pos.CENTER);
        layout.add(wordInstruction, 0, 0);
        layout.add(translationField, 0, 1);
        layout.add(checkButton, 0, 2);
        layout.add(feedback, 0, 3);
        layout.add(score, 0, 6);

        // Check button event.
        checkButton.setOnAction((event) -> {
            this.currentDictionary.print();
            // Check to see if there's still words left, if not the game is over.
            if (!this.finishedGame()) {
                // Set the translation to lowerCase, ready to be checked against the dictionary word which will be lowercase also.
                String translation = translationField.getText().toLowerCase();
                // Word is an atomic reference so it updates automatically. Here it's converted to a string to be used in the following code.
                String currentWord = String.valueOf(word);
                // Check if the word matches the dictionary translation.
                if (currentDictionary.get(currentWord).toLowerCase().equals(translation)) {
                    feedback.setText("Correct! The translation for '" + word + "' was '" + currentDictionary.get(word.get()) + "'.");
                    // Remove the current word from the list because it doesn't need further practice.
                    currentDictionary.remove(currentWord);
                    // Increment the score and change the text to indicate the change.
                    scoringSystem.increaseScore();
                    score.setText("Your score is " + scoringSystem.getScore());
                } else {
                    // If it's not a match then we need to show the correct word and inform the user.
                    feedback.setText("Incorrect! The translation for the word '" + word + "' is '" + currentDictionary.get(word.get()) + "'.");
                    // This is an integer in case I add settings down the line, some may want to add the word more than twice.
                    int amountToAdd = 2;
                        // This for loop just adds the word back to the list then it can be practiced more.
                        for (int i = 0; i < amountToAdd; i++){
                            currentDictionary.add(currentWord, currentDictionary.get(currentWord));
                        }
                        // I don't want the score to be less than 0, so it only decrements to 0.
                        if (scoringSystem.getScore() > 0) {
                            scoringSystem.decreaseScore();
                            score.setText("Your score is " + scoringSystem.getScore());
                        }
                    }
                // Doesn't make sense that this code is here but the game doesn't finish correctly otherwise, it can probably be moved further up.
                if (!finishedGame()) {
                    word.set(this.currentDictionary.getRandomWord());
                    wordInstruction.setText("Translate '" + word + "'");
                } else {
                    wordInstruction.setText("Congratulations, you've finished all of the words.");
                }
                translationField.clear();
                // Request focus makes the textField active then the user can type in it.
                translationField.requestFocus();
        }});

        return layout;
    }

    public Boolean finishedGame() {
        return (!this.currentDictionary.containsWords());
    }

}