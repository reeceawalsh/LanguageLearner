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

    private Dictionary dictionary;
    private Dictionary currentDictionary;

    public PracticeView(Dictionary dictionary) {
        this.dictionary = dictionary;
        this.currentDictionary = this.dictionary;
    }

    public Parent getView() {
        AtomicReference<String> word = new AtomicReference<>(currentDictionary.getRandomWord());
        GridPane layout = new GridPane();
        Label wordInstruction = new Label("Translate the word '" + word + "'");
        TextField translationField = new TextField();

        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setAlignment(Pos.CENTER);

        Button checkButton = new Button("Check");
        checkButton.setSkin(new ButtonSkin(checkButton) {
            {
                this.consumeMouseEvents(false);
            }
        });

        checkButton.setDefaultButton(true);

        Label feedback = new Label("");

        layout.add(wordInstruction, 0, 0);
        layout.add(translationField, 0, 1);
        layout.add(checkButton, 0, 2);
        layout.add(feedback, 0, 3);

        checkButton.setOnAction((event) -> {
            String translation = translationField.getText().toLowerCase();
            String currentWord = String.valueOf(word);
            if (dictionary.get(word.get()).equals(translation)) {
                feedback.setText("Correct! The translation for " + word + " was " + dictionary.get(word.get()) + ".");
                currentDictionary.remove(currentWord);
            } else {
                feedback.setText("Incorrect! The translation for the word '" + word + "' is '" + dictionary.get(word.get()) + "'.");
                int amountToAdd = 2;
                for (int i = 0; i < amountToAdd; i++){
                    currentDictionary.add(currentWord, translation);
                }
            }

            word.set(this.dictionary.getRandomWord());
            wordInstruction.setText("Translate the word '" + word + "'");
            translationField.clear();
        });


        return layout;
    }
}