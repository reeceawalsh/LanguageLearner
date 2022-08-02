package application.languagelearner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.*;


public class Dictionary {

    private List<String> words;
    private Map<String, String> translations;
    private File file;

    public Dictionary(String file) {
        this.words = new ArrayList<String>();
        this.translations = new HashMap<String, String>();
        this.file = new File(file);
    }

    public Dictionary() {
        this.words = new ArrayList<String>();
        this.translations = new HashMap<String, String>();
    }

    public boolean containsWords() {
        return this.words.size() > 0;
    }

    public int size() {
        return this.translations.size();
    }

    public String get(String word) {
            return this.translations.get(word);
    }

    public void add(String word, String translation) {
        System.out.println("Adding " + word + " and " + translation);
        if (!translations.containsKey(word)) {
            this.words.add(word);
        }
        if (!translations.containsKey(translation)) {
            this.words.add(translation);
        }
        this.translations.put(word, translation);
        // Write to file has to be here so that it doesn't double write each word and translation.
        // Adds the translation first and the word second. But this is not included in the txt file, just the useable dictionary.
        if (!word.equals(translation)) {
            this.translations.put(translation, word);
        }
    }

    public void write(String word, String translation) throws FileNotFoundException {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(this.file.getName(), true));
            writer.println(word + ", " + translation);
            writer.close();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void read() throws FileNotFoundException {
        try {
            Scanner reader = new Scanner(Paths.get(this.file.getName()));

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                String parts[] = line.split(",");
                String word = parts[0].trim();
                String translation = parts[1].trim();
                add(word, translation);
            }
        } catch (Exception e) {
            System.out.println("Error message " + e.getMessage());
        }
    }

    public void remove(String word) {
        this.translations.remove(word);
        this.words.remove(word);
    }

    public void print() {
        for (String key : this.translations.keySet()) {
            System.out.println("key " + key);
        }

        for (String value : this.translations.values()) {
            System.out.println("value " + value);
        }

        for (String word : this.words) {
            System.out.println("word " + word);
        }
    }

    public String getRandomWord() {
            Random random = new Random();
            return this.words.get(random.nextInt(this.words.size()));
    }
}
