import files.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Backup.backupFiles("src/files/");

        int[] gameBoard = {0, 1, 2, 1, 0, 2, 1, 2, 0};
        try {
            TicTacToe.saveState(gameBoard, "gamestate.bin");
            System.out.println("Состояние сохранено в файл gamestate.bin.");
        } catch (IOException e) {
            System.err.println("IO Error: " + e.getMessage());
        }

        try {
            int[] readGameBoard = TicTacToe.loadState("gamestate.bin");
            System.out.println("Состояние, прочитанное из файла gamestate.bin:");
            for (int i = 0; i < readGameBoard.length; i++) {
                System.out.print(readGameBoard[i] + " ");
                if ((i + 1) % 3 == 0) System.out.println();
            }
        } catch (IOException e) {
            System.err.println("IO Error: " + e.getMessage());
        }
    }
}
