/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.simondufour.hangman.ui;

/**
 *
 * @author Simon Dufour
 */
public interface Observer {
    public void updatePlayerName(String playerName);
    public void updateScore(int score);
    public void updateMysteryWord(String mysteryWord);
}
