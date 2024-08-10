package com.kudelych.medicalguide.service.operations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.kudelych.medicalguide.domain.model.Category;
import com.kudelych.medicalguide.domain.model.Medicine;
import com.kudelych.medicalguide.domain.validation.ValidationInput;
import com.kudelych.medicalguide.service.util.JsonDataReader;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class EditService {

  private static final String MEDICINE_FILE_PATH = "Data/medicines.json";
  private static final String CATEGORY_FILE_PATH = "Data/categories.json";

  public static void editMedicine() {
    Scanner scanner = new Scanner(System.in);
    List<Medicine> medicines = JsonDataReader.modelDataJsonReader(MEDICINE_FILE_PATH, Medicine[].class);

    System.out.println("Список доступних лікарських засобів:");
    for (Medicine medicine : medicines) {
      System.out.println("ID лікарського засобу: " + medicine.getId() + ", Назва: " + medicine.getName());
    }

    int medicineId = getValidMedicineId(scanner, medicines);
    Medicine selectedMedicine = getMedicineById(medicineId, medicines);

    if (selectedMedicine != null) {
      if (confirmEdit(scanner, "лікарський засіб")) {
        selectedMedicine.setName(getValidInput(scanner, "назву лікарського засобу", ValidationInput::isValidName));
        selectedMedicine.setCategory(getValidInput(scanner, "категорію", ValidationInput::isValidName));
        selectedMedicine.setDescription(getValidInput(scanner, "опис лікарського засобу", ValidationInput::isValidName));
        selectedMedicine.setPrice(getValidPrice(scanner));
        selectedMedicine.setManufacturer(getValidInput(scanner, "виробника лікарського засобу", ValidationInput::isValidName));

        saveToFile(MEDICINE_FILE_PATH, medicines);
        System.out.println("Оновлені дані лікарського засобу збережено успішно.");
      } else {
        System.out.println("Редагування скасовано.");
      }
    } else {
      System.out.println("Лікарський засіб з введеним ID не знайдено.");
    }
  }

  public static void editCategory() {
    Scanner scanner = new Scanner(System.in);
    List<Category> categories = JsonDataReader.modelDataJsonReader(CATEGORY_FILE_PATH, Category[].class);

    System.out.println("Список доступних категорій:");
    for (Category category : categories) {
      System.out.println("ID категорії: " + category.getCategoryId() + ", Назва: " + category.getName());
    }

    int idCategory = getValidCategoryId(scanner, categories);
    Category selectedCategory = getCategoryById(idCategory, categories);

    if (selectedCategory != null) {
      if (confirmEdit(scanner, "категорію")) {
        selectedCategory.setName(getValidInput(scanner, "нову назву категорії", ValidationInput::isValidName));
        saveToFile(CATEGORY_FILE_PATH, categories);
        System.out.println("Оновлені дані категорії збережено успішно.");
      } else {
        System.out.println("Редагування скасовано.");
      }
    } else {
      System.out.println("Категорію з введеним ID не знайдено.");
    }
  }

  private static int getValidMedicineId(Scanner scanner, List<Medicine> medicines) {
    System.out.println("Введіть ID лікарського засобу, для редагування:");
    return ValidationInput.getValidIntInput(scanner);
  }

  private static int getValidCategoryId(Scanner scanner, List<Category> categories) {
    System.out.println("Введіть ID категорії, яку хочете редагувати:");
    return ValidationInput.getValidIntInput(scanner);
  }

  private static boolean confirmEdit(Scanner scanner, String itemName) {
    System.out.println("Бажаєте редагувати цей " + itemName + "? (Так/Ні):");
    String response = scanner.nextLine();
    return response.equalsIgnoreCase("Так");
  }

  private static String getValidInput(Scanner scanner, String inputField, ValidationFunction validationFunction) {
    String input;
    do {
      System.out.println("Введіть " + inputField + ":");
      input = scanner.nextLine();
      if (ValidationInput.isEmpty(input) || !validationFunction.validate(input)) {
        System.out.println("Нове значення повинно мати буквенний символ та не бути порожнім.");
      } else {
        break;
      }
    } while (true);
    return input;
  }

  private static double getValidPrice(Scanner scanner) {
    double price;
    do {
      System.out.println("Введіть ціну (можна вводити як цілі, так і дробові числа):");
      String input = scanner.nextLine();
      try {
        price = Double.parseDouble(input);
        if (ValidationInput.isValidPrice(price)) {
          break;
        } else {
          System.out.println("Ціна не відповідає вимогам. Спробуйте знову.");
        }
      } catch (NumberFormatException e) {
        System.out.println("Невірний формат ціни. Будь ласка, введіть число.");
      }
    } while (true);
    return price;
  }

  private static <T> void saveToFile(String filePath, List<T> dataList) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
      objectMapper.writeValue(new File(filePath), dataList);
    } catch (IOException e) {
      System.out.println("Помилка при збереженні оновлених даних: " + e.getMessage());
    }
  }

  private static Medicine getMedicineById(int id, List<Medicine> medicines) {
    return medicines.stream()
        .filter(medicine -> medicine.getId() == id)
        .findFirst()
        .orElse(null);
  }

  private static Category getCategoryById(int id, List<Category> categories) {
    return categories.stream()
        .filter(category -> category.getCategoryId() == id)
        .findFirst()
        .orElse(null);
  }

  @FunctionalInterface
  public interface ValidationFunction {
    boolean validate(String input);
  }
}
