package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static DinnerConstructor dc;
    static Scanner scanner;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    return;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();

        dc.addNewDish(dishType, dishName);
    }

    private static void generateDishCombo() {
        System.out.println("Начинаем конструировать обед...");

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos = scanner.nextInt();
        scanner.nextLine();

        //проверка что введено положительное число
        if (numberOfCombos <= 0) {
            System.out.println("Введено некорректное значение, количество наборов должно быть больше нуля.");
            return;
        }

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        String nextItem = scanner.nextLine();
        ArrayList<String> dishTypesList = new ArrayList<>();

        //реализуйте ввод типов блюд
        while (!nextItem.isEmpty()) {
            if (dc.checkType(nextItem)) {
                dishTypesList.add(nextItem);
            } else {
                System.out.println("Данный тип блюда отсутствует базе данных, введите другой вариант:");
            }
            nextItem = scanner.nextLine();
        }

        // Проверка что список типов блюд не пустой
        if (dishTypesList.isEmpty()) {
            System.out.println("Не введено ни одного типа блюда");
        }

        // Генерирация комбинации блюд
        ArrayList<ArrayList<String>> combos = dc.createListOfCombos(numberOfCombos, dishTypesList);

        // Вывод на экран
        for (int i = 0; i < combos.size(); i++) {
            System.out.println("Комбо " + (i + 1));
            System.out.println(combos.get(i));
        }
    }
}
