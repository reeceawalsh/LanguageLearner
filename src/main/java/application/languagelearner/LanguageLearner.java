package application.languagelearner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LanguageLearner extends Application {

    private Dictionary dictionary;
    @Override
    public void init() throws Exception {
        // 1. Create the dictionary that the application uses
        this.dictionary = new Dictionary();
        dictionary.add("Hi", "Salut");
        dictionary.add("Thank you", "Merci");
        dictionary.add("Bye", "Au revoir");
        dictionary.add("Great", "Super");
        dictionary.add("How are you?", "ca va");
        dictionary.add("Good", "Bonne");
        dictionary.add("Perfect", "Parfait");
        dictionary.add("I am", "Je suis");
        dictionary.add("School", "Ecole");
        dictionary.add("Coding", "Codeage");
        dictionary.add("Computer Science", "Informatique");
        dictionary.add("Blue", "Bleu");
        dictionary.add("Cat", "Chat");
        dictionary.add("Black", "Noir");
        dictionary.add("Dog", "Chien");
        dictionary.add("Orange", "Orange");
        dictionary.add("Potato", "Pomme de terre");
        dictionary.add("Beach", "La Plage");
        dictionary.add("Girl", "Fille");
        dictionary.add("Seafood", "Fruit de mer");
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