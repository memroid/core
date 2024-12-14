package id.memro;

import java.io.*;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {

    ArrayList<Card> cardsCollection = new ArrayList<>();
    private final Random rnd = new Random();
    private final Scanner console = new Scanner(System.in);

    public void runGame(int numberOfChoices) throws IOException {

        readFile();
        ArrayList<Card> cardsCollectionTemp = new ArrayList<>();
        String[] choices = new String[numberOfChoices];

        int score = 0;
        for (Card card : cardsCollection) {
            cardsCollectionTemp.addAll(cardsCollection);
            String cardSourceWord = card.getSourceWord();
            String correctAnswer = card.getTargetWord();
            System.out.println("Choose the number of the right translation for word: " + cardSourceWord);
            int correctAnswerIndex = rnd.nextInt(0, choices.length);
            for (int i = 0; i < choices.length; i++) {
                Card cardChoice = new Card(cardSourceWord, correctAnswer);
                if (i != correctAnswerIndex) {
                    while (cardChoice.getTargetWord().equals(correctAnswer)) {
                        cardChoice = getRandomCard(cardsCollectionTemp);
                    }
                }
                choices[i] = cardChoice.getTargetWord();
                cardsCollectionTemp.remove(cardChoice);
                System.out.println(i + 1 + ") " + choices[i]);
            }
            int userChoice = Integer.parseInt(console.nextLine());
            if (userChoice == correctAnswerIndex + 1) {
                System.out.println("Correct!");
                System.out.println();
                score++;
            } else {
                System.out.println("Incorrect!");
            }
        }

        System.out.println("Your score: " + score + "/" + cardsCollection.size());

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

    public Card getRandomCard(ArrayList<Card> sourceList) {
        int indexBoundary = sourceList.size();
        int randomWordIndex = rnd.nextInt(0, indexBoundary);
        return sourceList.get(randomWordIndex);
    }
}
