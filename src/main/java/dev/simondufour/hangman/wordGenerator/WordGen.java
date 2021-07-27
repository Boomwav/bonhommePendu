/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.simondufour.hangman.wordGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Simon Dufour
 */
public class WordGen implements WordGenerator {
    private Random r = new Random();
    private ArrayList<String> backupWordList = new ArrayList<String>();
    private ArrayList<String> wordList = new ArrayList<String>();

    public WordGen() {
        addBuiltInWords();
    }
    
    public WordGen(String[] specificWordList, boolean alsoUseBuiltWords) {
        if(alsoUseBuiltWords) {
            addBuiltInWords();
        }
        
        wordList.addAll(List.of(specificWordList));
        
        backupWordList.addAll(wordList);
    }
    
    public WordGen(String url) {
        throw new java.lang.UnsupportedOperationException("Not implemented yet");
    }
    
    private void addBuiltInWords() {
        wordList.add("PATATE");
        wordList.add("POMME");
        wordList.add("OREILLE");
        wordList.add("TRAVAUX");
        wordList.add("APPRENDRE");
        wordList.add("RECTANGLE");
        wordList.add("MAISON");
        wordList.add("VOITURE");
        
        backupWordList.addAll(wordList);
    }
    
    public int count() {
        return wordList.size();
    }
    
    public int totalCount() {
        return backupWordList.size();
    }
    
    public boolean removeWord(String word, boolean removeFromWordlist) {
        if(removeFromWordlist) {
            backupWordList.remove(word);
        }
        
        return wordList.remove(word);
    }
    
    public boolean addWord(String word) {
        backupWordList.add(word);
        return wordList.add(word);
    }
    
    public void resetWordList() {
        wordList = (ArrayList<String>)backupWordList.clone();
    }
    
    public String getRandomWord() {
        if(count() == 0) {
            System.out.println("No more words left. Resetting.");
            resetWordList();
        }
        
        int randomIndex = r.nextInt(count());
        String result = wordList.get(randomIndex);
        removeWord(result, false);
        System.out.println("Random Word: " + result);
        return result;
    }
}
