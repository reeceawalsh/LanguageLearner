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

public class InputView {

    private Dictionary dictionary;

    public InputView(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Parent getView() {
        GridPane layout = new GridPane();

        Label wordInstruction = new Label("Word");
        TextField wordField = new TextField();
        Label translationInstruction = new Label("Translation");
        TextField translationField = new TextField();
        Label error = new Label();

        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setAlignment(Pos.CENTER);

        Button addButton = new Button("Add");
        // Stops the button from consuming mouse events and allows it to become the default button.
        addButton.setSkin(new ButtonSkin(addButton) {
            {
                this.consumeMouseEvents(false);
            }
        });
        // The button can now be pressed with the enter key.
        addButton.setDefaultButton(true);

        layout.add(wordInstruction, 0, 0);
        layout.add(wordField, 0, 1);
        layout.add(translationInstruction, 0, 2);
        layout.add(translationField, 0, 3);
        layout.add(addButton, 0, 4);
        layout.add(error, 0, 6);

        addButton.setOnAction((event) -> {
            String word = wordField.getText();
            String translation = translationField.getText();

            if (!translationField.getText().isEmpty()) {
                this.dictionary.add(word.toLowerCase(), translation.toLowerCase());
                error.setText("");
                wordField.clear();
            } else {
                error.setText("Please input a translation.");
            }

            translationField.clear();
            wordField.isFocused();
        });

        return layout;
    }
}