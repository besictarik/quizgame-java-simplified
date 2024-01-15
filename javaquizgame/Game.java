/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaquizgame;

/**
 *
 * @author Tarik
 */
public class Game {
    private final String playerName;
    private final String[] answers;
    private int score;
    
    public Game(String playerName, int numberOfQuestions) {
        this.playerName = playerName;
        this.answers = new String[numberOfQuestions];
    }
    
    public void setAnswer(String answer, int index) {
        answers[index] = answer;
    }
    public String getPlayerName() {
        return playerName;
    }
    public String[] getAnswers() {
        return answers;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }
}
