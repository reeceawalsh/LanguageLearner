package application.languagelearner;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.ButtonSkin;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import java.io.PrintWriter;

public class InputView {

    private Dictionary dictionary;

    public InputView(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Parent getView() {

        // Initialize elements.
        GridPane layout = new GridPane();
        Label wordInstruction = new Label("Word or Phrase");
        TextField wordField = new TextField();
        Label translationInstruction = new Label("Translation");
        TextField translationField = new TextField();
        Label error = new Label();

        // Add word button.
        Button addButton = new Button("Add");

        // Stops the button from consuming mouse events and allows it to become the default button that can be activated with enter.
        addButton.setSkin(new ButtonSkin(addButton) {
            {
                this.consumeMouseEvents(false);
            }
        });
        addButton.setDefaultButton(true);

        // Layout of elements.
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setAlignment(Pos.CENTER);
        layout.add(wordInstruction, 0, 0);
        layout.add(wordField, 0, 1);
        layout.add(translationInstruction, 0, 2);
        layout.add(translationField, 0, 3);
        layout.add(addButton, 0, 4);
        layout.add(error, 0, 6);

        // Button event.
        addButton.setOnAction((event) -> {
            String word = wordField.getText();
            String translation = translationField.getText();
            // Check to see if the field has text in it.
            if (!translationField.getText().isEmpty()) {
                // Add it to the dictionary and word list.
                this.dictionary.add(word, translation);
                try {
                    // Add it to the text file.
                    this.dictionary.write(word, translation);
                } catch (Exception e) {
                    System.out.println("Error " + e.getMessage());
                }
                // Change the error message then it doesn't display anything and clear the text field to input again.
                error.setText("");
                wordField.clear();
            } else {
                error.setText("Please input a translation.");
            }
            // Clear the text field and make the word field active.
            translationField.clear();
            wordField.requestFocus();
        });

        return layout;
    }

}