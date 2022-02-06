package io.codewithmos;

import java.util.Stack;

public class Soduko {
    private static int ROW_SIZE = 9;
    private static int COLUMN_SIZE = 9;
    private static int BLOCK_SIZE = 3;
    private static Stack<int[]> stack = new Stack<int[]>();

   private static  int  board2[][] = {
           {7, 8, 0, 4, 0, 0, 1, 2, 0},
           {6, 0, 0, 0, 7, 5, 0, 0, 9},
           {0, 0, 0, 6, 0, 1, 0, 7, 8},
           {0, 0, 7, 0, 4, 0, 2, 6, 0},
           {0, 0, 1, 0, 5, 0, 9, 3, 0},
           {9, 0, 4, 0, 6, 0, 0, 0, 5},
           {0, 7, 0, 3, 0, 0, 0, 1, 2},
           {1, 2, 0, 0, 0, 7, 4, 0, 0},
           {0, 4, 9, 2, 0, 6, 0, 0, 7}
   };
    private static int  board[][] = {
            {6, 7, 0, 0, 4, 8, 0, 1, 0},
            {3, 5, 0, 0, 0, 1, 0, 4, 7},
            {0, 1, 0, 7, 2, 0, 6, 8, 0},
            {8, 0, 3, 0, 0, 0, 1, 6, 9},
            {0, 0, 7, 9, 1, 0, 8, 3, 0},
            {0, 9, 6, 4, 8, 3, 0, 0, 0},
            {0, 0, 9, 1, 0, 4, 3, 0, 8},
            {4, 8, 1, 0, 0, 0, 7, 0, 6},
            {7, 0, 0, 8, 6, 2, 0, 0, 1}
    };

    public static void main(String args[]){
      // displayBoard(playRandom(Soduko.board2,1,0,0));
        displayBoard(playRandom(Soduko.board,1,0,0));
    }

    private static int[][] playRandom(int[][] board,int number,int row, int column){
        for (int x = row ; x < ROW_SIZE; x++){
            for (int y = column ;y < COLUMN_SIZE; y++){
                if (board[x][y]  == 0 ){
                    for ( int i = number; i < 10; i++){
                        if (!isInRow(board,i, x) &&  !isInColumn (board,i, y) && !isInBlock(board,i,x,y) ){
                            board[x][y] = i;
                            int[] temp = {x,y,i};
                            stack.push(temp);
                            number = 1;
                            break;
                        }
                        //backtracking
                        if (i >= 9) {
                            int numberInput = 1;
                            int[] elements;
                            backtrack(board);
                            if (!stack.empty()){
                                elements = stack.pop();
                                board[elements[0]][elements[1]] = 0;
                                numberInput =  elements[2];
                                playRandom( board,numberInput + 1 ,elements[0],elements[1]);
                            }else{
                                return board;
                            }

                        }

                    }
                }
            }
            column = 0;
        }
        return board;
    }
    private static int[][] backtrack (int[][] board){
        int[] elements;
        elements = stack.peek();
        while (!stack.empty() && elements[2] == 9){
            board[elements[0]][elements[1]] = 0;
            stack.pop();
            elements = stack.peek();
        }
        return board;
    }

    private static boolean isInRow(int[][] board, int number, int row){
        for (int column = 0; column < COLUMN_SIZE ; column++){
            if (board[row][column] == number){
            	System.out.println("number is in isInRow..."+ row +","+column);
                return true;
            }
        }
        return false;
    }

    private static boolean isInColumn(int[][] board,int number, int column){
        for (int row = 0; row < ROW_SIZE ; row++){
            if (board[row][column] == number){
            	System.out.println("number is in isInColumn..."+ row +","+column);
                return true;
            }
        }
        return false;
    }

    private static boolean isInBlock(int[][] board,int number,int row, int column){

        int rowStart = (row / BLOCK_SIZE) * BLOCK_SIZE;
        int columnStart = (column / BLOCK_SIZE) * BLOCK_SIZE;
        int rowEnd = (rowStart + BLOCK_SIZE) - 1;
        int columnEnd = (columnStart + BLOCK_SIZE) - 1;
        
        for (int x = rowStart; x <= rowEnd ; x++){
            for (int y = columnStart; y <= columnEnd ; y++) {
                if (board[x][y] == number) {
                	System.out.println("number is in isInBlock..."+ x +","+y);
                    return true;
                }
            }
        }
        return false;
    }


    static void displayBoard(int [][] board){
        String rowStr = "";
        for (int row = 0; row < board.length; row++){
            for (int column = 0;column < board[row].length; column++){
                rowStr += board[row][column]+ " ";
                if (column != 0 && (column +1) % 3 == 0  && column != board[row].length - 1 )
                    rowStr += "| ";
            }
            if (row != 0 && (row+1) % 3 == 0 && row != board.length - 1){
                rowStr += "\n----------------------";
            }
            rowStr +=  "\n";
        }
        System.out.println(rowStr);
    }
}
