public class Minimax {
    private static final int PLAYER = 1;
    private static final int AI = 2;
    private static final int EMPTY = 0;

    public static int minimax(int[][] board, int depth, boolean maximizingPlayer) {
        if (depth == 0 || isGameOver(board)) {
            return evaluate(board);
        }

        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (int col = 0; col < 7; col++) {
                if (isValidMove(board, col)) {
                    int[][] newBoard = makeMove(board, col, AI);
                    int eval = minimax(newBoard, depth - 1, false);
                    maxEval = Math.max(maxEval, eval);
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int col = 0; col < 7; col++) {
                if (isValidMove(board, col)) {
                    int[][] newBoard = makeMove(board, col, PLAYER);
                    int eval = minimax(newBoard, depth - 1, true);
                    minEval = Math.min(minEval, eval);
                }
            }
            return minEval;
        }
    }

    public static int findBestMove(int[][] board, int depth) {
        int bestMove = -1;
        int bestEval = Integer.MIN_VALUE;

        for (int col = 0; col < 7; col++) {
            if (isValidMove(board, col)) {
                int[][] newBoard = makeMove(board, col, AI);
                int eval = minimax(newBoard, depth - 1, true);
                if (eval > bestEval) {
                    bestEval = eval;
                    bestMove = col;
                }
            }
        }

        return bestMove;
    }

    public static int evaluate(int[][] board) {
        // Adicione sua lógica de avaliação aqui
        return 0;
    }

    private static boolean isGameOver(int[][] board) {
        // Verificar se o jogo acabou
        // Implemente a lógica para verificar se alguém venceu ou se o tabuleiro está cheio
        ConectFour game = new ConectFour();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] != 0) {
                    game.temGanhador(i, j, board[i][j]);
                    if (game.estado != 0) return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidMove(int[][] board, int col) {
        // Verificar se uma jogada é válida em uma coluna específica
        // Implemente a lógica para verificar se a coluna não está cheia
        return board[0][col] == EMPTY;
    }

    private static int[][] makeMove(int[][] board, int col, int player) {
        // Fazer uma jogada em uma coluna específica
        // Implemente a lógica para atualizar o tabuleiro após a jogada
        int[][] newBoard = new int[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        for (int i = 5; i >= 0; i--) {
            if (newBoard[i][col] == EMPTY) {
                newBoard[i][col] = player;
                break;
            }
        }
        return newBoard;
    }
}
