package com.xococ.game;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    private GameBoard gameBoard;
    private AIPlayer aiPlayer;
    private boolean vsAI;
    private Button[][] buttons;
    private TextView tvStatus;
    private Button btnReset;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        vsAI = getIntent().getBooleanExtra("VS_AI", false);
        
        gameBoard = new GameBoard();
        if (vsAI) {
            aiPlayer = new AIPlayer(GameBoard.PLAYER_O);
        }
        
        tvStatus = findViewById(R.id.tvStatus);
        btnReset = findViewById(R.id.btnReset);
        
        buttons = new Button[3][3];
        buttons[0][0] = findViewById(R.id.btn00);
        buttons[0][1] = findViewById(R.id.btn01);
        buttons[0][2] = findViewById(R.id.btn02);
        buttons[1][0] = findViewById(R.id.btn10);
        buttons[1][1] = findViewById(R.id.btn11);
        buttons[1][2] = findViewById(R.id.btn12);
        buttons[2][0] = findViewById(R.id.btn20);
        buttons[2][1] = findViewById(R.id.btn21);
        buttons[2][2] = findViewById(R.id.btn22);
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int row = i;
                final int col = j;
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onCellClicked(row, col);
                    }
                });
            }
        }
        
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
        
        updateUI();
    }
    
    private void onCellClicked(int row, int col) {
        if (gameBoard.isGameOver()) {
            return;
        }
        
        if (gameBoard.makeMove(row, col)) {
            buttons[row][col].setText(String.valueOf(gameBoard.getCurrentPlayer()));
            
            char winner = gameBoard.checkWinner();
            if (winner != GameBoard.EMPTY) {
                showGameOver("Player " + winner + " wins!");
                return;
            }
            
            if (gameBoard.isFull()) {
                showGameOver("It's a draw!");
                return;
            }
            
            gameBoard.switchPlayer();
            updateUI();
            
            // AI's turn
            if (vsAI && gameBoard.getCurrentPlayer() == GameBoard.PLAYER_O) {
                disableButtons();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        makeAIMove();
                    }
                }, 500);
            }
        }
    }
    
    private void makeAIMove() {
        int[] move = aiPlayer.getBestMove(gameBoard);
        
        if (move[0] != -1 && move[1] != -1) {
            gameBoard.makeMove(move[0], move[1]);
            buttons[move[0]][move[1]].setText(String.valueOf(gameBoard.getCurrentPlayer()));
            
            char winner = gameBoard.checkWinner();
            if (winner != GameBoard.EMPTY) {
                showGameOver("Player " + winner + " wins!");
                return;
            }
            
            if (gameBoard.isFull()) {
                showGameOver("It's a draw!");
                return;
            }
            
            gameBoard.switchPlayer();
            updateUI();
            enableButtons();
        }
    }
    
    private void updateUI() {
        if (vsAI) {
            if (gameBoard.getCurrentPlayer() == GameBoard.PLAYER_X) {
                tvStatus.setText("Your turn (X)");
            } else {
                tvStatus.setText("AI's turn (O)");
            }
        } else {
            tvStatus.setText("Player " + gameBoard.getCurrentPlayer() + "'s turn");
        }
    }
    
    private void showGameOver(String message) {
        tvStatus.setText(message);
        disableButtons();
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    
    private void resetGame() {
        gameBoard.reset();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        updateUI();
    }
    
    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
    
    private void enableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard.getCell(i, j) == GameBoard.EMPTY) {
                    buttons[i][j].setEnabled(true);
                }
            }
        }
    }
}
