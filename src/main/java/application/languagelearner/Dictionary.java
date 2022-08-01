package application.languagelearner;
import java.util.*;


public class Dictionary {

    private List<String> words;
    private Map<String, String> translations;

    public Dictionary() {
        this.words = new ArrayList<String>();
        this.translations = new HashMap<String, String>();
    }

    public boolean containsWords() {
        return this.words.size() > 1;
    }

    public String get(String word) {
            return this.translations.get(word);
    }

    public void add(String word, String translation) {
        if (!translations.containsKey(word)) {
            this.words.add(word);
        }
        this.translations.put(word, translation);
    }

    public String getRandomWord() {
            Random random = new Random();
            return this.words.get(random.nextInt(this.words.size()));
    }
}
