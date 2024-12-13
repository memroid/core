package id.memro;
import java.io.*;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
public class Game {
    ArrayList<Card> cardsCollection = new ArrayList<>();
    Random rnd = new Random();
    public void runGame() throws IOException {
        readFile();
    }
    public void readFile() throws IOException {

        try (Scanner fileReader = new Scanner(Paths.get("C:/dev/memroid/core/id.memro.core/src/main/resources/dictionary.txt"))) {
            String sourceWord;
            String targetWord;
            while (fileReader.hasNextLine()) {
                sourceWord = fileReader.next();
                targetWord = fileReader.next();
                cardsCollection.add(new Card(sourceWord, targetWord));
            }
        }
    }
}
