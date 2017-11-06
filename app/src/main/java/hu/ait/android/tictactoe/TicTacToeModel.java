package hu.ait.android.tictactoe;

public class TicTacToeModel {

    private static TicTacToeModel ticTacToeModel = null;

    private TicTacToeModel() {

    }

    public static TicTacToeModel getTicTacToeModelInstance() {
        if (ticTacToeModel == null) {
            ticTacToeModel = new TicTacToeModel();
        }
        return ticTacToeModel;
    }

    public static final int EMPTY = 0;
    public static final int CIRCLE = 1;
    public static final int CROSS = 2;

    private int[][] model = {
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY}
    };

    private int nextPlayer = CIRCLE;

    public void switchNextPlayer() {
        nextPlayer = (nextPlayer == CIRCLE) ? CROSS : CIRCLE;

        /*
        if(nextPlayer == CIRCLE){
            nextPlayer = CROSS;
        } else {
            nextPlayer = CIRCLE;
        }
        */
    }

    public int getNextPlayer() {
        return nextPlayer;
    }

    public void setFieldContent(int x, int y, int content) {
        model[x][y] = content;
    }

    public int getFieldContent(int x, int y) {
        return model[x][y];
    }

    public void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                TicTacToeModel.getTicTacToeModelInstance().setFieldContent(i, j, 0);
            }
        }

        nextPlayer = CIRCLE;
    }

    public boolean isWinner(TicTacToeModel currentTicTac, int currentX, int currentY) {

        //check col
        for (int i = 0; i < 3; i++) {
            if (currentTicTac.getFieldContent(currentX, i)
                    != currentTicTac.getFieldContent(currentX, currentY))
                break;
            if (i == 2) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (currentTicTac.getFieldContent(i, currentY)
                    != currentTicTac.getFieldContent(currentX, currentY))
                break;
            if (i == 2) {
                return true;
            }
        }

        if (currentX == currentY){
            for (int i = 0; i < 3; i++) {
                if(currentTicTac.getFieldContent(i,i)
                        != currentTicTac.getFieldContent(currentX,currentY)){
                    break;
                }
                if (i == 2) {
                    return true;
                }
            }
        }

        if(currentX + currentY == 2){
            for(int i = 0;i<3;i++){
                if(currentTicTac.getFieldContent(i, 2-i)
                        != currentTicTac.getFieldContent(currentX,currentY))
                    break;
                if(i == 2){
                    return true;
                }
            }
        }

        //check draw
//        if(moveCount == (n^2 - 1)){
//            //report draw
//        }

        return false;
    }
}
