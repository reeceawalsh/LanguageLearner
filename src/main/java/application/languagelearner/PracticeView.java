package application.languagelearner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.concurrent.atomic.AtomicReference;

public class PracticeView {

    private Dictionary dictionary;

    public PracticeView(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Parent getView() {
        AtomicReference<String> word = new AtomicReference<>(dictionary.getRandomWord());
        GridPane layout = new GridPane();
        Label wordInstruction = new Label("Translate the word '" + word + "'");
        TextField translationField = new TextField();

        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setAlignment(Pos.CENTER);

        Button addButton = new Button("Check");

        Label feedback = new Label("");

        layout.add(wordInstruction, 0, 0);
        layout.add(translationField, 0, 1);
        layout.add(addButton, 0, 2);
        layout.add(feedback, 0, 3);

        addButton.setOnMouseClicked((event) -> {
            String translation = translationField.getText().toLowerCase();
            if (dictionary.get(word.get()).equals(translation)) {
                feedback.setText("Correct! The translation for " + word + " was " + dictionary.get(word.get()) + ".");
            } else {
                feedback.setText("Incorrect! The translation for the word '" + word + "' is '" + dictionary.get(word.get()) + "'.");
            }

            word.set(this.dictionary.getRandomWord());
            wordInstruction.setText("Translate the word '" + word + "'");
            translationField.clear();
        });

        return layout;
    }
}