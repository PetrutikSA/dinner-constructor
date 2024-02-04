package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DinnerConstructor {
    HashMap<String, ArrayList<String>> allDishes = new HashMap<>();

    public void addNewDish(String dishType, String dishName) {
        ArrayList<String> currentTypeDishes;
        if (checkType(dishType)) {
            currentTypeDishes = allDishes.get(dishType);
        } else {
            currentTypeDishes = new ArrayList<>();
            allDishes.put(dishType, currentTypeDishes);
        }
        currentTypeDishes.add(dishName);
    }

    public boolean checkType(String dishType) {
        return allDishes.containsKey(dishType);
    }

    public boolean checkDish(String dishType, String dishName) {
        // Вначале проверяется присутствует ли данный тип в базе и если да, то проверяется наличие самого блюда
        return checkType(dishType) && allDishes.get(dishType).contains(dishName);
    }

    public ArrayList<ArrayList<String>> createListOfCombos(int numberOfCombos, ArrayList<String> dishTypesList) {
        ArrayList<ArrayList<String>> combos = new ArrayList<>();
        Random random = new Random();
        int attempts = 0; //переменная проверят количество некдачных попыток создать новое уникальное комбо
        // если 50 вариантов неудачные, принимается допущение, что больше уникальных вариантов не осталось
        while (combos.size() < numberOfCombos && attempts < 50) {
            ArrayList<String> currentCombo = new ArrayList<>();
            for (String dishType : dishTypesList) {
                ArrayList<String> allDishesOfChosenType = allDishes.get(dishType);
                int randomIndex = random.nextInt(allDishesOfChosenType.size());
                String randomDish = allDishesOfChosenType.get(randomIndex);
                currentCombo.add(randomDish);
            }

            // Проверка: есть ли данное комбо уже в списке комбо
            boolean isComboAlreadyExist = false;
            for (ArrayList<String> comboFromList : combos) {
                ArrayList<String> tempComboFromList = new ArrayList<>(comboFromList);
                for (String dish : currentCombo) {
                    if (!tempComboFromList.remove(dish)) {
                        break;
                    }
                }
                if (tempComboFromList.isEmpty()) {
                    isComboAlreadyExist = true;
                    break;
                }
            }
            if (isComboAlreadyExist) {
                attempts++;
            } else {
                combos.add(currentCombo);
                attempts = 0;
            }
        }
        return combos;
    }
}