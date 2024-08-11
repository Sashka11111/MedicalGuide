package com.kudelych.medicalguide.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.kudelych.medicalguide.domain.model.Medicine;
import com.kudelych.medicalguide.domain.validation.ValidationInput;
import com.kudelych.medicalguide.service.util.JsonDataReader;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MedicineService {

  private static final String MEDICINE_FILE_PATH = "Data/medicines.json";
  private static List<Medicine> medicines;

  // Метод для відображення всіх лікарських засобів
  public static void main(String[] args) {
    medicines = JsonDataReader.modelDataJsonReader(MEDICINE_FILE_PATH, Medicine[].class);
    displayMedicines(medicines);
  }

  public static void displayMedicines(List<Medicine> medicineList) {
    if (medicineList.isEmpty()) {
      System.out.println("Список лікарських засобів порожній.");
    } else {
      System.out.println("Список лікарських засобів:");
      for (Medicine medicine : medicineList) {
        // Виведення інформації про лікарський засіб
        System.out.println("Назва: " + medicine.getName());
        System.out.println("Ціна: " + medicine.getPrice());
        System.out.println("Категорія : " + medicine.getCategory());
        System.out.println("Опис: " + medicine.getDescription());
        System.out.println("Виробник: " + medicine.getManufacturer());
        System.out.println(); // Для розділення між записами
      }
    }
  }

  public static void addMedicine() {
    Scanner scanner = new Scanner(System.in);
    medicines = JsonDataReader.modelDataJsonReader(MEDICINE_FILE_PATH, Medicine[].class);

    // Знайти максимальний ID лікарського засобу
    int maxMedicineId = medicines.stream()
        .mapToInt(Medicine::getId)
        .max()
        .orElse(0);

    // Новий ID лікарського засобу буде на одиницю більше за максимальний
    int newMedicineId = maxMedicineId + 1;

    System.out.println("Додавання нового лікарського засобу:");

    String name;
    do {
      System.out.println("Введіть назву лікарського засобу:");
      name = scanner.nextLine();
      if (ValidationInput.isEmpty(name)) {
        System.out.println("Назва не може бути порожньою.");
        continue;
      }
      if (!ValidationInput.isValidName(name)) {
        System.out.println("Назва повинна мiстити буквенний символ.");
      }
    } while (!ValidationInput.isValidName(name));

    double price;
    do {
      System.out.println("Введіть ціну (можна вводити як цілі, так і дробові числа):");
      String input = scanner.nextLine();

      try {
        // Спробуємо перетворити введення на число
        price = Double.parseDouble(input);
        if (!ValidationInput.isValidPrice(price)) {
          System.out.println("Ціна не відповідає вимогам. Спробуйте знову.");
          continue;
        }
        break; // вихід з циклу, якщо всі перевірки пройдено

      } catch (NumberFormatException e) {
        System.out.println("Невірний формат ціни. Будь ласка, введіть число.");
      }
    } while (true);

    String category;
    do {
      System.out.println("Введіть категорію:");
      category = scanner.nextLine();
      if (ValidationInput.isEmpty(category)) {
        System.out.println("Категорія не може бути порожньою.");
      } else if(!ValidationInput.isValidName(category)) {
        System.out.println("Категорія повинна мiстити буквенний символ.");
      } else {
        break;
      }
    } while (true);

    String description;
    do {
      System.out.println("Введіть опис лікарського засобу:");
      description = scanner.nextLine();

      if (ValidationInput.isEmpty(description)) {
        System.out.println("Опис не може бути порожнім.");
      } else if (!ValidationInput.isValidName(description)) {
        System.out.println("Опис повинен мiстити буквенний символ.");
      } else {
        break; // Вихід з циклу, якщо виробник валідний
      }
    } while (true); // Повторювати, поки введене значення не буде валідним

    String manufacturer;
    do {
      System.out.println("Введіть виробника лікарського засобу:");
      manufacturer = scanner.nextLine();

      if (ValidationInput.isEmpty(manufacturer)) {
        System.out.println("Назва виробника не може бути порожньою.");
      } else if (!ValidationInput.isValidName(manufacturer)) {
        System.out.println("Назва виробника повинна містити буквенний символ.");
      } else {
        break; // Вихід з циклу, якщо виробник валідний
      }
    } while (true); // Повторювати, поки введене значення не буде валідним

    Medicine newMedicine = new Medicine();
    newMedicine.setId(newMedicineId);
    newMedicine.setName(name);
    newMedicine.setPrice(price);
    newMedicine.setCategory(category);
    newMedicine.setDescription(description);
    newMedicine.setManufacturer(manufacturer);
    medicines.add(newMedicine);

    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
      objectMapper.writeValue(new File(MEDICINE_FILE_PATH), medicines);
      System.out.println("Новий лікарський засіб додано успішно.");
    } catch (IOException e) {
      System.out.println("Помилка при додаванні нового лікарського засобу: " + e.getMessage());
    }
  }
}
