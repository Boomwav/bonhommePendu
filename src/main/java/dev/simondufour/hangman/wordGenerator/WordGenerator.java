/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.simondufour.hangman.wordGenerator;

/**
 *
 * @author Simon Dufour
 */
public interface WordGenerator {
    public String getRandomWord();
    public void resetWordList();
}
