package com.sashka11111.bookkeeping.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sashka11111.bookkeeping.domain.model.Category;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CategoryService {

  private static final String CATEGORIES_FILE_PATH = "Data/categories.json";
  private static List<Category> categories;

  public static void main(String[] args) {
    categories = JsonDataReader.modelDataJsonReader(CATEGORIES_FILE_PATH, Category[].class);
    displayCategories(categories);
  }

  public static void displayCategories(List<Category> categories) {
    if (categories.isEmpty()) {
      System.out.println("Список категорій порожній.");
    } else {
      System.out.println("Список категорій:");
      for (Category category : categories) {
        System.out.println("ID категорії: " + category.getCategoryId());
        System.out.println("Назва: " + category.getName());
        System.out.println(); // Для розділення між записами
      }
    }
  }

  public static void addCategory() {
    Scanner scanner = new Scanner(System.in);
    categories = JsonDataReader.modelDataJsonReader(CATEGORIES_FILE_PATH, Category[].class);

    // Знайти максимальний ID категорії
    int maxCategoryId = categories.stream()
        .mapToInt(Category::getCategoryId)
        .max()
        .orElse(0);

    // Новий ID категорії буде на одиницю більше за максимальний
    int newCategoryId = maxCategoryId + 1;

    // Запитати користувача про дані нової категорії
    System.out.println("Додавання нової категорії");

    System.out.println("Введіть назву категорії:");
    String name = scanner.nextLine();

    // Створюємо новий об'єкт категорії
    Category newCategory = new Category();
    newCategory.setCategoryId(newCategoryId);
    newCategory.setName(name);

    // Додаємо нову категорію до списку
    categories.add(newCategory);

    // Зберегти оновлені дані у файлі JSON
    saveCategoriesToJson();

    System.out.println("Нову категорію додано успішно.");
  }

  private static void saveCategoriesToJson() {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
      objectMapper.writeValue(new File(CATEGORIES_FILE_PATH), categories);
    } catch (IOException e) {
      System.out.println("Error saving categories to JSON file: " + e.getMessage());
    }
  }
}
