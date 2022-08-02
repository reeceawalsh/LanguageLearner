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
        this.words.add(word);
        this.words.add(translation);
        this.translations.put(word, translation);
        this.translations.put(translation, word);
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
        // We need to clear both this.words and this.translations before we add words and translations from a file because they may contain words from a different language already.
        this.words = new ArrayList<String>();
        this.translations = new HashMap<String, String>();
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
        this.words.remove(word);
    }

    public String getRandomWord() {
            Random random = new Random();
            return this.words.get(random.nextInt(this.words.size()));
    }

    public void toFrench() throws FileNotFoundException {
        this.file = new File("French.txt");
        read();
    }

    public void toGerman() throws FileNotFoundException {
        this.file = new File("German.txt");
        read();
    }

    public void toChinese() throws FileNotFoundException {
        this.file = new File("Chinese.txt");
        read();
    }

    // For testing purposes
    public void print() {
        for (String key : this.translations.keySet()) {
            System.out.println("Key " + key);
            System.out.println("Value " + this.translations.get(key));
        }
        for (String word : this.words) {
            System.out.println("Word  " + word);
        }
    }

    // We never want to clear the dictionary unless the user is changing languages, but we may want to clear the words list sometimes.
    public void clear() {
        for (String word : this.words) {
            this.words.remove(word);
        }
    }

}
