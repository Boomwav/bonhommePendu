/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.simondufour.hangman.game;

import dev.simondufour.hangman.ui.Observer;
import dev.simondufour.hangman.wordGenerator.WordGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Simon Dufour
 */
public class Hangman {
    private List<Observer> observers = new ArrayList<>();
    private WordGenerator wg;
    private String playerName = "";
    private int score = 0;
    private String currentMysteryWord;
    private String encodedMysteryWord;
    private ArrayList<String> letterHistory = new ArrayList<String>();
    private int nbError = 0;
    
    public Hangman(WordGenerator wg) {
        this.wg = wg;
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public void setPlayerName(String newName) {
        this.playerName = newName;
        for (Observer observer : this.observers) {
            observer.updatePlayerName(this.playerName);
        }
    }
    
    public void addObserver(Observer obs) {
        this.observers.add(obs);
    }

    public void removeObserver(Observer channel) {
        this.observers.remove(channel);
    }
    
    public void randomScore() {
        this.setScore((new Random()).nextInt(100));
    }

    private void setScore(int score) {
        this.score = score;
        for (Observer observer : this.observers) {
            observer.updateScore(this.score);
        }
    }
    
    private void setMysteryWord(String mysteryWord) {
        letterHistory.clear();
        currentMysteryWord = mysteryWord;
        encodedMysteryWord = encodeMysteryWord(currentMysteryWord, letterHistory);
        refreshMysteryWord();
    }
    
    private void refreshMysteryWord() {
         for (Observer observer : this.observers) {
            observer.updateMysteryWord(encodedMysteryWord);
        }
    }
    
    public void addLetter(String letter) {
        letterHistory.add(letter.toUpperCase());
        encodedMysteryWord = encodeMysteryWord(currentMysteryWord, letterHistory);
        refreshMysteryWord();
        
        if(isError(letter)) {
            nbError++;
            
            if(nbError >= 6) {
                System.out.println("Game lost");
            }
        } else {
            incrementScore(1);
        }
        
        if(isWin()) {
            incrementScore(5);
        }
        
    }
    
    private boolean isWin() {
        return encodedMysteryWord.indexOf("_") <= -1;
    }
    
    private boolean isError(String letter) {
        return currentMysteryWord.indexOf(letter) <= -1;
    }
    
    private String encodeMysteryWord(String word, ArrayList<String> letters) {
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < word.length(); i++) {
            String letter = Character.toString(word.charAt(i));
            
            if(letters.contains(letter)) {
                sb.append(letter);
            } else {
                sb.append("_ ");
            }
        }
        
        System.out.println(sb.toString());
        
        return sb.toString();
    }
    
    private void incrementScore(int scoreToAdd) {
        int newScore = this.score + scoreToAdd;
        setScore(newScore);
    }
    
    public void startNewGame() {
        this.setScore(0);
        this.nbError = 0;
        wg.resetWordList();
        setMysteryWord(wg.getRandomWord());
    }
}
