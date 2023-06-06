package ru.geekbrain.lesson2;
import java.util.Random;
import java.util.Scanner;

public class Program {

    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = '0';
    private static final char DOT_EMPTY = '*';

    private static final Scanner SCANNER = new Scanner(System.in);
    private static char[][] field;
    private static final Random random = new Random();
    private static int fieldSizeX;
    private static int fieldSizeY;
    static int   count = 4;
    static int win_number = 0;

    public static void main(String[] args) {
        while (true) {
            initialize();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (gameCheck(DOT_HUMAN, "Вы победили")) break;
                System.out.println("Ход компьютера ");
                aiTurn();
                printField();
                if (gameCheck(DOT_AI, "Вы победил")) break;
            }
            System.out.println("Желаете сыграть еще раз? (Y - да)");
            if (!SCANNER.next().equalsIgnoreCase("Y"))
                break;
        }
    }
    /**
     *
     */
    private static void initialize() {
        fieldSizeX = 5;
        fieldSizeY = 5;

        field = new char[fieldSizeX][fieldSizeY];

        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                field[x][y] = DOT_EMPTY;

            }
        }
    }
    /**
     *
     */
    private static void printField() {

        System.out.print("+");
        for (int i = 0; i < fieldSizeY * 2 + 1; i++) {
            System.out.print((i % 2 == 0) ? "-" : i / 2 + 1);

        }
        System.out.println("");
        for (int i = 0; i < fieldSizeX; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < fieldSizeY; j++) {
                System.out.print(field[i][j] + "|");
            }

            System.out.println("");
        }
        System.out.println("");
        for (int i = 0; i < fieldSizeY * 2 + 1; i++) {
            System.out.print("_");

        }
        System.out.println("");
    }

    private static void humanTurn() {
        int x, y;
        do {
            System.out.print("Введите координаты хода Х и У (от 1 до 5 ) через пробел ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;

        }
        while (!isCellValid(x,y) || !isCellEmpty(x,y));
        field[x][y] = DOT_HUMAN;
    }

    static boolean isCellEmpty(int x ,int y){
        return  field [x][y] == DOT_EMPTY;

    }
    static  boolean isCellValid(int x, int y){
        return x >= 0 && x < fieldSizeX && y >=0 && y < fieldSizeY;
    }
    private static void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        }
        while (!isCellEmpty(x,y));
        field[x][y] = DOT_AI;
    }
    static  boolean checkWin(char c ) {
        for (int y = 0 ; y < fieldSizeY;y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[x][y] == c) {
                    win_number++;
                    if (win_number == count) return true;
                }
                else win_number = 0;

            }
        }
            for (int x = 0 ; x < fieldSizeX;x++) {
                for (int y = 0; y < fieldSizeY; y++) {
                    if (field[x][y] == c) {
                        win_number++;
                        if (win_number == count) return true;

                    }
                    else win_number = 0;
                }
            }

        for (int x = 0 ; x < fieldSizeX;x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (checkDiagon(x, y, c)) {
                    return true;
                } else win_number = 0;
            }
        }
        return false;
    }
    static boolean checkDiagon(int x,int y,char c ){
        while(x >=0) {

            if (field[x][y] == c && x > 0 && y < fieldSizeY) {
                ++win_number;
                if (win_number == count) {
                    return true;

                }
                x--;
                y++;
            }
            else x --;

        }
        return false;
    }

    static boolean cheekDraw(){
        for (int x = 0;x < fieldSizeX ; x++){
            for (int y = 0;y < fieldSizeY ; y++) {
                if (isCellEmpty(x,y)) return false;
            }

        }
        return  true;
    }
    static boolean gameCheck(char c, String str){
        if (checkWin(c)) {
            System.out.println(str);
            return true;
        }
         if  (cheekDraw()){
            System.out.println("Ничья");
            return  true;
        }
        return false ;

    }

}

