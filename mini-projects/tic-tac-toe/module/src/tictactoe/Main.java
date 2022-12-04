package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class TicTacToe {

    public static void main(String[] args) throws IOException {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        char[][] array = new char[3][3];
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                array[i][j]=' ';
            }
        }
        printBoard(array);

        boolean flag = false;
        int a = 0;
        while (a==0) {
            try {
                String position = br.readLine();
                int x = Integer.parseInt(position.substring(0,1));
                int y = Integer.parseInt(position.substring(2));

                if(x<1 || x>3 || y<1 || y>3){
                    throw new Exception("Coordinates should be from 1 to 3!");
                }else {
                    if(array[x-1][y-1]!=' '){
                        throw new Exception("This cell is occupied! Choose another one!");
                    }else {
                        if (flag==true){
                            array[x-1][y-1]='X';
                            printBoard(array);
                        }else {
                            array[x-1][y-1]='O';
                            printBoard(array);
                        }
                        boolean gameOver = checkWinner(array);
                        if (gameOver){
                            break;
                        }else {
                            flag = !flag;
                        }
                    }
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
                System.out.println("You should enter numbers!");
            }catch (Exception e) {
                if (e.getMessage().equals("Coordinates should be from 1 to 3!")) {
                    System.out.println(e.getMessage());
                }else{
                    System.out.println(e.getMessage());
                }
            }
        }

    }

    //This code checks takes an array as parameter and looks for is anybody win.
    //Method returns boolean value, if that value is false game is going to resume
    //cause if anybody win the game or game is draw method going to return true
    //and that is finish the game.
    private static boolean checkWinner(char[][] array) {
        boolean winX = false;
        boolean winO = false;
        int count=0;

        if(array[0][0] == 'X' && array[0][1] == 'X' && array[0][2] == 'X') winX = true;
        if(array[1][0] == 'X' && array[1][1] == 'X' && array[1][2] == 'X') winX = true;
        if(array[2][0] == 'X' && array[2][1] == 'X' && array[2][2] == 'X') winX = true;

        if(array[0][0] == 'X' && array[1][0] == 'X' && array[2][0] == 'X') winX = true;
        if(array[0][1] == 'X' && array[1][1] == 'X' && array[2][1] == 'X') winX = true;
        if(array[0][2] == 'X' && array[1][2] == 'X' && array[2][2] == 'X') winX = true;

        if(array[0][0] == 'X' && array[1][1] == 'X' && array[2][2] == 'X') winX = true;
        if(array[0][2] == 'X' && array[1][1] == 'X' && array[2][0] == 'X') winX = true;

        if(array[0][0] == 'O' && array[0][1] == 'O' && array[0][2] == 'O') winO = true;
        if(array[1][0] == 'O' && array[1][1] == 'O' && array[1][2] == 'O') winO = true;
        if(array[2][0] == 'O' && array[2][1] == 'O' && array[2][2] == 'O') winO = true;

        if(array[0][0] == 'O' && array[1][0] == 'O' && array[2][0] == 'O') winO = true;
        if(array[0][1] == 'O' && array[1][1] == 'O' && array[2][1] == 'O') winO = true;
        if(array[0][2] == 'O' && array[1][2] == 'O' && array[2][2] == 'O') winO = true;

        if(array[0][0] == 'O' && array[1][1] == 'O' && array[2][2] == 'O') winO = true;
        if(array[0][2] == 'O' && array[1][1] == 'O' && array[2][0] == 'O') winO = true;
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if (array[i][j]!=' '){
                    count++;
                }
            }
        }
        if (winX==true && winO==false){
            System.out.println("X wins");
            return true;
        } else if (winO==true && winX==false) {
            System.out.println("O wins");
            return true;
        }else if(count==9){
            System.out.println("draw");
            return true;
        }else {
            return false;
        }
    }

    // This code takes an array as parameter and returns visualize of the given array.
    // Can be used as a current view of the game board.
    public static void printBoard(char[][] array){
        System.out.println("---------");
        for (int i=0;i<3;i++){
            for (int j=0;j<1;j++){
                System.out.println("| " + array[i][j] + " " + array[i][j+1] + " " + array[i][j+2] + " |");
            }
        }
        System.out.println("---------");
    }
}
