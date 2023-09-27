import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
         Scanner sc = new Scanner(System.in);
        //переменные
        // меню игры

        boolean gameP = false;
        do {
            boolean format = false;
            String game_control_str;
            Integer game_control = null;
            boolean start = false;
            List<String> words = new ArrayList<>();
            do {
                do {
                    System.out.println("-------------------");
                    System.out.println("| 1 - начать игру |");
                    System.out.println("| 2 - выход       |");
                    System.out.println("| 3 - автор       |");
                    System.out.println("-------------------");

                    try {
                        game_control_str = sc.nextLine();
                        game_control = Integer.parseInt(game_control_str);
                    } catch (NumberFormatException e) {
                        System.out.println("Некоректный ввод, попробуйте снова!");
                        game_control_str = null;
                    }

                    if (game_control != null) {
                        format = true;
                    }
                } while (format == false);

                if (game_control == 1) {
                    System.out.println("Начало игры");
                    start = true;
                } else if (game_control == 2) {
                    System.out.println("Конец игры");
                    System.exit(1);
                } else if (game_control == 3) {
                    format = false;
                    System.out.println("-----------------");
                    System.out.println("|               |");
                    System.out.println("| tg - @smska20 |");
                    System.out.println("|               |");
                    System.out.println("-----------------");
                } else {
                    System.out.println("Некоректный ввод, попробуйте снова!");
                    format = false;
                    game_control = null;
                }
            } while (start == false);

            //чтение списка слов из файла

            String fileName = "russian_nouns.txt";
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    words.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // выбор случайного cлова и перевод в массив char

            int n = (int) (Math.random() * 5000);
            String main_word = words.get(n);
            char[] result = main_word.toCharArray();

            //игра
            Thread.sleep(2500);
            System.out.println("Последние приготовления");
            Thread.sleep(2000);
            System.out.println("Игра начинается");
            boolean gameIsOver = false;
            int errors = 0;
            char element;
            String[] copyOfMainWord = new String[result.length];
            for(int i = 0; i < copyOfMainWord.length; i++){
                copyOfMainWord[i] = String.valueOf(result[i]);
            }

            //Создание результирующего массива и заполнение его

            String[] progress = new String[result.length];
            for (int i = 0; i < progress.length; i++) {
                progress[i] = "*";
            }

            //начало игры
            boolean test = false;
            do {
                System.out.println("Введите букву");
                element = sc.next().charAt(0);
                for (int y = 0; y < progress.length; y++) {       //проверка соответсвий
                    if (Character.toLowerCase(element) == result[y]) {
                        progress[y] = String.valueOf(Character.toLowerCase(element));
                        test = true;
                    }
                }

                if (test == false) {
                    errors++;
                    switch(errors){
                        case (1):
                            System.out.println("*----*");
                            System.out.println("     |");
                            System.out.println("     |");
                            System.out.println("     |");
                            System.out.println("    =====");
                            break;
                        case(2):
                            System.out.println("*----*");
                            System.out.println(" O   |");
                            System.out.println("     |");
                            System.out.println("     |");
                            System.out.println("    =====");
                            break;
                        case(3):
                            System.out.println("*----*");
                            System.out.println(" O   |");
                            System.out.println(" |   |");
                            System.out.println("     |");
                            System.out.println("    =====");
                            break;
                        case(4):
                            System.out.println("*----*");
                            System.out.println(" O    |");
                            System.out.println("/|    |");
                            System.out.println("      |");
                            System.out.println("    =====");
                            break;
                        case(5):
                            System.out.println("*----*");
                            System.out.println(" O   |");
                            System.out.println("/|\\  |");
                            System.out.println("     |");
                            System.out.println("    =====");
                            break;
                        case(6):
                            System.out.println("*----*");
                            System.out.println(" O   |");
                            System.out.println("/|\\  |");
                            System.out.println("/ \\  |");
                            System.out.println("    =====");
                            break;
                    }

                    System.out.println("Попробуйте еще");
                    System.out.println("Колличество ошибок: " + errors);
                } else if (test == true) {
                    System.out.println("Вы отгадали!");
                    test = false;
                }

                for (int y = 0; y < progress.length; y++) {
                    System.out.print(progress[y]);
                }
                System.out.println("");

                boolean gameWin = false;
                /* int open = 0;*/

                if(Arrays.equals(progress,copyOfMainWord) == true){
                    gameWin = true;
                }

                if (errors == 6) {
                    System.out.println("Вы проиграли");
                    gameIsOver = true;
                }
                if (gameWin == true){
                    System.out.println("Вы победили!");
                    gameIsOver = true;
                }


            } while (gameIsOver == false);
        } while(gameP == false);
    }
}

