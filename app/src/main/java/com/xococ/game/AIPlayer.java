package com.xococ.game;

public class AIPlayer {
    private char aiPlayer;
    private char humanPlayer;
    
    public AIPlayer(char aiPlayer) {
        this.aiPlayer = aiPlayer;
        this.humanPlayer = (aiPlayer == GameBoard.PLAYER_X) ? GameBoard.PLAYER_O : GameBoard.PLAYER_X;
    }
    
    public int[] getBestMove(GameBoard gameBoard) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[]{-1, -1};
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard.getCell(i, j) == GameBoard.EMPTY) {
                    // Make the move
                    char[][] board = gameBoard.getBoardCopy();
                    board[i][j] = aiPlayer;
                    
                    // Calculate score
                    int score = minimax(board, 0, false);
                    
                    // Update best move if needed
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        
        return bestMove;
    }
    
    private int minimax(char[][] board, int depth, boolean isMaximizing) {
        char winner = checkWinner(board);
        
        if (winner == aiPlayer) {
            return 10 - depth;
        } else if (winner == humanPlayer) {
            return depth - 10;
        } else if (isBoardFull(board)) {
            return 0;
        }
        
        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == GameBoard.EMPTY) {
                        board[i][j] = aiPlayer;
                        int score = minimax(board, depth + 1, false);
                        board[i][j] = GameBoard.EMPTY;
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == GameBoard.EMPTY) {
                        board[i][j] = humanPlayer;
                        int score = minimax(board, depth + 1, true);
                        board[i][j] = GameBoard.EMPTY;
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }
    
    private char checkWinner(char[][] board) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != GameBoard.EMPTY && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0];
            }
        }
        
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != GameBoard.EMPTY && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return board[0][j];
            }
        }
        
        // Check diagonals
        if (board[0][0] != GameBoard.EMPTY && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }
        if (board[0][2] != GameBoard.EMPTY && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];
        }
        
        return GameBoard.EMPTY;
    }
    
    private boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == GameBoard.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
