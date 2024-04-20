package files;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TicTacToe {
    public static void saveState(int[] board, String filePath) throws IOException {
        if (board.length != 9) {
            throw new IllegalArgumentException("Поле должно содержать 9 клеток.");
        }

        int packedState = 0;

        for (int i = 0; i < board.length; i++) {
            if (board[i] < 0 || board[i] > 3) {
                throw new IllegalArgumentException("Значения клеток должны быть от 0 до 3.");
            }
            packedState |= (board[i] << (2 * i));
        }

        try (DataOutputStream dos = new DataOutputStream(Files.newOutputStream(Paths.get(filePath)))) {
            dos.writeByte((packedState >>> 16) & 0xFF);
            dos.writeByte((packedState >>> 8) & 0xFF);
            dos.writeByte(packedState & 0xFF);
        }
    }

    public static int[] loadState(String filePath) throws IOException {
        int[] board = new int[9];
        int packedState = 0;

        try (DataInputStream dis = new DataInputStream(Files.newInputStream(Paths.get(filePath)))) {
            packedState |= (dis.readUnsignedByte() << 16);
            packedState |= (dis.readUnsignedByte() << 8);
            packedState |= dis.readUnsignedByte();
        }

        for (int i = 0; i < board.length; i++) {
            board[i] = (packedState >>> (2 * i)) & 0x03;
        }

        return board;
    }
}
