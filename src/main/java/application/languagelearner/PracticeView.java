package application.languagelearner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.ButtonSkin;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class PracticeView {

    private List<String> words;
    private Dictionary currentDictionary;
    private int score;
    public PracticeView(Dictionary dictionary) {
        this.currentDictionary = dictionary;
        this.score = 0;
    }

    public Parent getView() {
        // Initialize elements
        AtomicReference<String> word = new AtomicReference<>(currentDictionary.getRandomWord());
        GridPane layout = new GridPane();
        Label wordInstruction = new Label("Translate '" + word + "'");
        TextField translationField = new TextField();
        Label feedback = new Label("");
        Label score = new Label("Your score is " + this.score);

        // Check button
        Button checkButton = new Button("Check");
        checkButton.setSkin(new ButtonSkin(checkButton) {
            {
                this.consumeMouseEvents(false);
            }
        });
        checkButton.setDefaultButton(true);

        // Layout of elements
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

        // Check button event
        checkButton.setOnAction((event) -> {
            if (!this.finishedGame()) {
            String translation = translationField.getText().toLowerCase();
            String currentWord = String.valueOf(word);
            int currentScore = this.score;
                if (currentDictionary.get(currentWord).toLowerCase().equals(translation)) {
                    feedback.setText("Correct! The translation for " + word + " was " + currentDictionary.get(word.get()) + ".");
                    currentDictionary.remove(currentWord);
                    currentScore ++;
                    score.setText("Your score is " + currentScore );
                } else {
                    feedback.setText("Incorrect! The translation for the word '" + word + "' is '" + currentDictionary.get(word.get()) + "'.");
                    int amountToAdd = 2;
                        for (int i = 0; i < amountToAdd; i++){
                            currentDictionary.add(currentWord, currentDictionary.get(currentWord));
                        }
                        if (currentScore > 0) {
                            currentScore --;
                            score.setText("Your score is " + currentScore);
                        }
                    }
                if (!finishedGame()) {
                    word.set(this.currentDictionary.getRandomWord());
                    wordInstruction.setText("Translate '" + word + "'");
                } else {
                    wordInstruction.setText("Congratulations, you've finished all of the words.");
                }

                translationField.clear();
                translationField.requestFocus();
                this.score = currentScore;
        }});

        return layout;
    }

    public Boolean finishedGame() {
        return (!this.currentDictionary.containsWords());
    }

    public int score() {
        return this.score;
    }

}