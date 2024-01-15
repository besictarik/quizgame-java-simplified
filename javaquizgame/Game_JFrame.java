/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaquizgame;

import java.awt.CardLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

/**
 *
 * @author Tarik
 */
public class Game_JFrame extends javax.swing.JFrame {
    
    private final String[] questions = {"Question 1?", "Question 2?", "Question 3?"};
    private final String[][] options = {{"10", "20", "30", "40", "10"}, {"10", "20", "30", "40", "20"}, {"10", "20", "30", "40", "40"}};
    private final ArrayList<Game> leaderboard = new ArrayList<>();
    private static final int NUMBER_OF_ANSWERS = 4;

    private int index = 0;
    private Task[] tasks;
    private Game game;
    
    private final ButtonGroup answerButtonsGroup = new ButtonGroup();
    private final CardLayout cardLayout;
    /**
     * Creates new form GAME_JFRAME
     */
    public Game_JFrame() {
        initComponents();
        initAnswerButtons();
        initTasks();
        cardLayout = (CardLayout)(ParentPanel.getLayout());
    }
    
//    Init functions
    private void initAnswerButtons() {
        answerButtonsGroup.add(optionOneRadioButton_);
        answerButtonsGroup.add(optionTwoRadioButton_);
        answerButtonsGroup.add(optionThreeRadioButton_);
        answerButtonsGroup.add(optionFourRadioButton_);
    }
    private void initTasks() {
        try {
            loadQuestions();
        } catch(FileNotFoundException error) {
            System.out.println("Failed to load file. Will proceed with local questions.");
            tasks = new Task[questions.length];
            for (int i = 0; i < questions.length; i++) {
                tasks[i] = new Task(questions[i], options[i], options[i][4]);
            }
        } finally {
            System.out.println("Taskes inited.");
        }
    }
    
//    Load questions
    private void loadQuestions() throws FileNotFoundException {
        File file = new File("./javaquizgame/questions.txt");
        Scanner scanner;
        scanner = new Scanner(file);
        
        int numberOfQuestions = Integer.parseInt(scanner.nextLine());
        tasks = new Task[numberOfQuestions];
        
        for (int i = 0; i < numberOfQuestions; i++) {
            String question = scanner.nextLine();
            String[] answers = new String[NUMBER_OF_ANSWERS];
            for (int j = 0; j < NUMBER_OF_ANSWERS; j++) {
                answers[j] = scanner.nextLine();
            }
            String correctAnswer = scanner.nextLine();
            tasks[i] = new Task(question, answers, correctAnswer);
        }
        printTasks();
    }
    
//    For debugging purposes only
    private void printTasks() {
        for(Task task: tasks) {
            System.out.println(task.getQuestion()+"\n"+Arrays.toString(task.getAnswers())+"\n"+task.getCorrectAnswer()+"\n\n");
        }
    }
    
//    UI handling
    private void displayTask(int index) {
        label_question.setText(tasks[index].getQuestion());
        optionOneRadioButton_.setText(tasks[index].getAnswers()[0]);
        optionTwoRadioButton_.setText(tasks[index].getAnswers()[1]);
        optionThreeRadioButton_.setText(tasks[index].getAnswers()[2]);
        optionFourRadioButton_.setText(tasks[index].getAnswers()[3]);
    }
    private void resetGameUI() {
        index = 0;
        answerButtonsGroup.clearSelection();
        textfield_username.setText("");
        nextQuestionButton_.setText("NEXT");
    }
    
//    Answers handling
    private void checkPreviousSelection() {
        String[] playerAnswers = game.getAnswers();
        String lastAnswer = playerAnswers[index];
        if (lastAnswer == null) return;
        
        String[] taskAnswers = tasks[index].getAnswers();
        if (lastAnswer.equals(taskAnswers[0])) {
            optionOneRadioButton_.setSelected(true);
            return;
        }
        if (lastAnswer.equals(taskAnswers[1])) {
            optionTwoRadioButton_.setSelected(true);
            return;
        }
        if (lastAnswer.equals(taskAnswers[2])) {
            optionThreeRadioButton_.setSelected(true);
            return;
        }
        optionFourRadioButton_.setSelected(true);
    }
    private void handleSelectedAnswer(JRadioButton button) {
        final String selectedAnswer = button.getText();
        game.setAnswer(selectedAnswer, index);
    }
    
//    Final score functions
    private int getFinalScore() {
        int score = 0;
        String[] playerAnswers = game.getAnswers();
        for (int i = 0; i < game.getAnswers().length; i++) {
            if (playerAnswers[i].equals(tasks[i].getCorrectAnswer())) {
                score++;
            }
        }
        return score;
    }
    private String getFormatedFinalScore() {
        return getFinalScore()+"/"+tasks.length;
    }
    
//    Leaderboard handling
    private void updateLeaderboard() {
        if (doesExistInLeaderBoard()) return;
        
        leaderboard.add(game);
        refreshLeaderboardUI();
    }
    private boolean doesExistInLeaderBoard() {
        for (Game game: leaderboard) {
            if (this.game.getPlayerName().equals(game.getPlayerName())) return true;
        }
        return false;
    }
    private void refreshLeaderboardUI() {
        String newStats = "<html>";
        for (Game game : leaderboard) {
            String tail = game.getPlayerName()+" "+game.getScore()+"<br>";
            newStats += tail;
        }
        newStats += "</html>";
        label_leaderboard.setText(newStats);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button_next1 = new javax.swing.JButton();
        ParentPanel = new javax.swing.JPanel();
        MenuPanel = new javax.swing.JPanel();
        startButton_ = new javax.swing.JButton();
        textfield_username = new javax.swing.JTextField();
        label_warning = new javax.swing.JLabel();
        leaderboardButton_ = new javax.swing.JButton();
        QuizPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        label_question = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        optionOneRadioButton_ = new javax.swing.JRadioButton();
        optionTwoRadioButton_ = new javax.swing.JRadioButton();
        optionThreeRadioButton_ = new javax.swing.JRadioButton();
        optionFourRadioButton_ = new javax.swing.JRadioButton();
        nextQuestionButton_ = new javax.swing.JButton();
        previousQuestionButton_ = new javax.swing.JButton();
        ResultsPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        label_result = new javax.swing.JLabel();
        menuResultsButton_ = new javax.swing.JButton();
        LeaderboardPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        label_leaderboard = new javax.swing.JLabel();
        menuLeaderboardButton_ = new javax.swing.JButton();

        button_next1.setBackground(new java.awt.Color(51, 102, 255));
        button_next1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button_next1.setForeground(new java.awt.Color(255, 255, 255));
        button_next1.setText("NEXT");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        ParentPanel.setLayout(new java.awt.CardLayout());

        MenuPanel.setBackground(new java.awt.Color(255, 255, 255));

        startButton_.setBackground(new java.awt.Color(51, 102, 255));
        startButton_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        startButton_.setForeground(new java.awt.Color(255, 255, 255));
        startButton_.setText("START GAME");
        startButton_.setAlignmentY(0.0F);
        startButton_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButton_ActionPerformed(evt);
            }
        });

        textfield_username.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textfield_username.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textfield_username.setToolTipText("Your name");
        textfield_username.setAlignmentX(0.0F);
        textfield_username.setAlignmentY(0.0F);
        textfield_username.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        label_warning.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        label_warning.setForeground(new java.awt.Color(255, 0, 0));
        label_warning.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_warning.setAlignmentY(0.0F);

        leaderboardButton_.setBackground(new java.awt.Color(51, 102, 255));
        leaderboardButton_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        leaderboardButton_.setForeground(new java.awt.Color(255, 255, 255));
        leaderboardButton_.setText("LEADERBOARD");
        leaderboardButton_.setAlignmentY(0.0F);
        leaderboardButton_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaderboardButton_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MenuPanelLayout = new javax.swing.GroupLayout(MenuPanel);
        MenuPanel.setLayout(MenuPanelLayout);
        MenuPanelLayout.setHorizontalGroup(
            MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MenuPanelLayout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(textfield_username, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MenuPanelLayout.createSequentialGroup()
                        .addGap(321, 321, 321)
                        .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_warning)
                            .addComponent(startButton_, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(leaderboardButton_, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(271, Short.MAX_VALUE))
        );
        MenuPanelLayout.setVerticalGroup(
            MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(textfield_username, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(startButton_, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(leaderboardButton_, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addComponent(label_warning)
                .addGap(125, 125, 125))
        );

        ParentPanel.add(MenuPanel, "MenuPanel");

        QuizPanel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(51, 102, 255));

        label_question.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_question.setForeground(new java.awt.Color(255, 255, 255));
        label_question.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_question.setText("QUESTION_PLACEHOLDER");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_question, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_question, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        optionOneRadioButton_.setBackground(new java.awt.Color(255, 255, 255));
        optionOneRadioButton_.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        optionOneRadioButton_.setText("ANSWER1_PLACEHOLDER");
        optionOneRadioButton_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionOneRadioButton_ActionPerformed(evt);
            }
        });

        optionTwoRadioButton_.setBackground(new java.awt.Color(255, 255, 255));
        optionTwoRadioButton_.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        optionTwoRadioButton_.setText("ANSWER2_PLACEHOLDER");
        optionTwoRadioButton_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionTwoRadioButton_ActionPerformed(evt);
            }
        });

        optionThreeRadioButton_.setBackground(new java.awt.Color(255, 255, 255));
        optionThreeRadioButton_.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        optionThreeRadioButton_.setText("ANSWER3_PLACEHOLDER");
        optionThreeRadioButton_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionThreeRadioButton_ActionPerformed(evt);
            }
        });

        optionFourRadioButton_.setBackground(new java.awt.Color(255, 255, 255));
        optionFourRadioButton_.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        optionFourRadioButton_.setText("ANSWER4_PLACEHOLDER");
        optionFourRadioButton_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionFourRadioButton_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(optionOneRadioButton_, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
                    .addComponent(optionTwoRadioButton_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(optionThreeRadioButton_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(optionFourRadioButton_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(optionOneRadioButton_)
                .addGap(18, 18, 18)
                .addComponent(optionTwoRadioButton_)
                .addGap(18, 18, 18)
                .addComponent(optionThreeRadioButton_)
                .addGap(18, 18, 18)
                .addComponent(optionFourRadioButton_)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        nextQuestionButton_.setBackground(new java.awt.Color(51, 102, 255));
        nextQuestionButton_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        nextQuestionButton_.setForeground(new java.awt.Color(255, 255, 255));
        nextQuestionButton_.setText("NEXT");
        nextQuestionButton_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextQuestionButton_ActionPerformed(evt);
            }
        });

        previousQuestionButton_.setBackground(new java.awt.Color(51, 102, 255));
        previousQuestionButton_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        previousQuestionButton_.setForeground(new java.awt.Color(255, 255, 255));
        previousQuestionButton_.setText("GO BACK TO PREVIOUS QUESTION");
        previousQuestionButton_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousQuestionButton_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout QuizPanelLayout = new javax.swing.GroupLayout(QuizPanel);
        QuizPanel.setLayout(QuizPanelLayout);
        QuizPanelLayout.setHorizontalGroup(
            QuizPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QuizPanelLayout.createSequentialGroup()
                .addGroup(QuizPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(QuizPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(QuizPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(QuizPanelLayout.createSequentialGroup()
                                .addGap(0, 43, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(QuizPanelLayout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(previousQuestionButton_, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(nextQuestionButton_, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        QuizPanelLayout.setVerticalGroup(
            QuizPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QuizPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(QuizPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(previousQuestionButton_, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextQuestionButton_, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        ParentPanel.add(QuizPanel, "QuizPanel");

        ResultsPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 102, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("YOUR RESULT");

        label_result.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        label_result.setForeground(new java.awt.Color(51, 102, 255));
        label_result.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        menuResultsButton_.setBackground(new java.awt.Color(51, 102, 255));
        menuResultsButton_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        menuResultsButton_.setForeground(new java.awt.Color(255, 255, 255));
        menuResultsButton_.setText("GO BACK");
        menuResultsButton_.setAlignmentY(0.0F);
        menuResultsButton_.setMargin(new java.awt.Insets(2, 25, 2, 25));
        menuResultsButton_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuResultsButton_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ResultsPanelLayout = new javax.swing.GroupLayout(ResultsPanel);
        ResultsPanel.setLayout(ResultsPanelLayout);
        ResultsPanelLayout.setHorizontalGroup(
            ResultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(label_result, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
            .addGroup(ResultsPanelLayout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(menuResultsButton_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(323, 323, 323))
        );
        ResultsPanelLayout.setVerticalGroup(
            ResultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ResultsPanelLayout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_result)
                .addGap(130, 130, 130)
                .addComponent(menuResultsButton_, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(177, Short.MAX_VALUE))
        );

        ParentPanel.add(ResultsPanel, "ResultsPanel");

        LeaderboardPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(51, 102, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TOP RESULTS");
        jLabel1.setAlignmentY(0.0F);

        label_leaderboard.setBackground(new java.awt.Color(51, 102, 255));
        label_leaderboard.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label_leaderboard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_leaderboard.setText("No results recorded.");
        label_leaderboard.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        label_leaderboard.setAlignmentY(0.0F);

        menuLeaderboardButton_.setBackground(new java.awt.Color(51, 102, 255));
        menuLeaderboardButton_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        menuLeaderboardButton_.setForeground(new java.awt.Color(255, 255, 255));
        menuLeaderboardButton_.setText("GO BACK");
        menuLeaderboardButton_.setAlignmentY(0.0F);
        menuLeaderboardButton_.setMargin(new java.awt.Insets(2, 25, 2, 25));
        menuLeaderboardButton_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLeaderboardButton_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LeaderboardPanelLayout = new javax.swing.GroupLayout(LeaderboardPanel);
        LeaderboardPanel.setLayout(LeaderboardPanelLayout);
        LeaderboardPanelLayout.setHorizontalGroup(
            LeaderboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(label_leaderboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(LeaderboardPanelLayout.createSequentialGroup()
                .addGap(339, 339, 339)
                .addComponent(menuLeaderboardButton_, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addGap(340, 340, 340))
        );
        LeaderboardPanelLayout.setVerticalGroup(
            LeaderboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LeaderboardPanelLayout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(label_leaderboard, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(menuLeaderboardButton_, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146))
        );

        ParentPanel.add(LeaderboardPanel, "LeaderboardPanel");

        getContentPane().add(ParentPanel, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void previousQuestionButton_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousQuestionButton_ActionPerformed
        // TODO add your handling code here:
        if (index == 0) return;
        displayTask(--index);
        checkPreviousSelection();
        if (index != tasks.length - 1 && nextQuestionButton_.getText().equals("Finish and See The Results")) {
            nextQuestionButton_.setText("NEXT");
        }
    }//GEN-LAST:event_previousQuestionButton_ActionPerformed

    private void nextQuestionButton_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextQuestionButton_ActionPerformed
        // Display new question
        if (answerButtonsGroup.getSelection() == null) {
            return;
        }
        
        index++;

        // Last question submit
        if (index == tasks.length) {
            game.setScore(getFinalScore());
            updateLeaderboard();
            label_result.setText(getFormatedFinalScore());
            cardLayout.show(ParentPanel, "ResultsPanel");
            return;
        }

        if (index == tasks.length - 1) {
            nextQuestionButton_.setText("Finish and See The Results");
        }

        answerButtonsGroup.clearSelection();
        displayTask(index);
        checkPreviousSelection();
    }//GEN-LAST:event_nextQuestionButton_ActionPerformed

    private void optionFourRadioButton_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionFourRadioButton_ActionPerformed
        // TODO add your handling code here:
        handleSelectedAnswer(optionFourRadioButton_);
    }//GEN-LAST:event_optionFourRadioButton_ActionPerformed

    private void optionThreeRadioButton_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionThreeRadioButton_ActionPerformed
        // TODO add your handling code here:
        handleSelectedAnswer(optionThreeRadioButton_);
    }//GEN-LAST:event_optionThreeRadioButton_ActionPerformed

    private void optionTwoRadioButton_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionTwoRadioButton_ActionPerformed
        // TODO add your handling code here:
        handleSelectedAnswer(optionTwoRadioButton_);
    }//GEN-LAST:event_optionTwoRadioButton_ActionPerformed

    private void optionOneRadioButton_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionOneRadioButton_ActionPerformed
        // TODO add your handling code here:
        handleSelectedAnswer(optionOneRadioButton_);
    }//GEN-LAST:event_optionOneRadioButton_ActionPerformed

    private void startButton_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButton_ActionPerformed
        // TODO add your handling code here:
        String username = textfield_username.getText().toUpperCase();
        if (username.isEmpty()) {
            label_warning.setText("Enter username!");
            return;
        }
        label_warning.setText("");
        game = new Game(username, tasks.length);
        index = 0;
        displayTask(index);
        cardLayout.show(ParentPanel, "QuizPanel");
    }//GEN-LAST:event_startButton_ActionPerformed

    private void leaderboardButton_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaderboardButton_ActionPerformed
        // TODO add your handling code here:
        cardLayout.show(ParentPanel, "LeaderboardPanel");
    }//GEN-LAST:event_leaderboardButton_ActionPerformed

    private void menuLeaderboardButton_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLeaderboardButton_ActionPerformed
        // TODO add your handling code here:
        cardLayout.show(ParentPanel, "MenuPanel");
    }//GEN-LAST:event_menuLeaderboardButton_ActionPerformed

    private void menuResultsButton_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuResultsButton_ActionPerformed
        // TODO add your handling code here:
        game = null;
        resetGameUI();
        cardLayout.show(ParentPanel, "MenuPanel");
    }//GEN-LAST:event_menuResultsButton_ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Game_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Game_JFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LeaderboardPanel;
    private javax.swing.JPanel MenuPanel;
    private javax.swing.JPanel ParentPanel;
    private javax.swing.JPanel QuizPanel;
    private javax.swing.JPanel ResultsPanel;
    private javax.swing.JButton button_next1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel label_leaderboard;
    private javax.swing.JLabel label_question;
    private javax.swing.JLabel label_result;
    private javax.swing.JLabel label_warning;
    private javax.swing.JButton leaderboardButton_;
    private javax.swing.JButton menuLeaderboardButton_;
    private javax.swing.JButton menuResultsButton_;
    private javax.swing.JButton nextQuestionButton_;
    private javax.swing.JRadioButton optionFourRadioButton_;
    private javax.swing.JRadioButton optionOneRadioButton_;
    private javax.swing.JRadioButton optionThreeRadioButton_;
    private javax.swing.JRadioButton optionTwoRadioButton_;
    private javax.swing.JButton previousQuestionButton_;
    private javax.swing.JButton startButton_;
    private javax.swing.JTextField textfield_username;
    // End of variables declaration//GEN-END:variables
}