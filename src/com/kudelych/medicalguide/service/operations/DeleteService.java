package com.kudelych.medicalguide.service.operations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.kudelych.medicalguide.domain.model.Category;
import com.kudelych.medicalguide.domain.model.Medicine;
import com.kudelych.medicalguide.domain.model.Review;
import com.kudelych.medicalguide.domain.model.User;
import com.kudelych.medicalguide.domain.validation.ValidationInput;
import com.kudelych.medicalguide.service.util.JsonDataReader;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DeleteService {

  private static final String MEDICINE_FILE_PATH = "Data/medicines.json";
  private static final String CATEGORY_FILE_PATH = "Data/categories.json";
  private static final String REVIEWS_FILE_PATH = "Data/reviews.json";
  private static final String USER_FILE_PATH = "Data/users.json";

  public static void deleteMedicine() {
    Scanner scanner = new Scanner(System.in);
    List<Medicine> medicines = JsonDataReader.modelDataJsonReader(MEDICINE_FILE_PATH, Medicine[].class);

    System.out.println("Список доступних лікарських засобів:");
    for (Medicine medicine : medicines) {
      System.out.println("ID лікарського засобу: " + medicine.getId() + ", Назва: " + medicine.getName());
    }

    System.out.println("Введіть ID лікарського засобу для видалення:");
    int medicineId = ValidationInput.getValidIntInput(scanner);

    Medicine selectedMedicine = medicines.stream()
        .filter(medicine -> medicine.getId() == medicineId)
        .findFirst()
        .orElse(null);

    if (selectedMedicine != null) {
      medicines.remove(selectedMedicine);
      saveToFile(MEDICINE_FILE_PATH, medicines);
      System.out.println("Лікарський засіб успішно видалено.");
    } else {
      System.out.println("Лікарський засіб з введеним ID не знайдено.");
    }
  }

  public static void deleteCategory() {
    Scanner scanner = new Scanner(System.in);
    List<Category> categories = JsonDataReader.modelDataJsonReader(CATEGORY_FILE_PATH, Category[].class);

    System.out.println("Список доступних категорій:");
    for (Category category : categories) {
      System.out.println("ID категорії: " + category.getCategoryId() + ", Назва: " + category.getName());
    }

    System.out.println("Введіть ID категорії, яку хочете видалити:");
    int idCategory = ValidationInput.getValidIntInput(scanner);

    Category selectedCategory = categories.stream()
        .filter(category -> category.getCategoryId() == idCategory)
        .findFirst()
        .orElse(null);

    if (selectedCategory != null) {
      categories.remove(selectedCategory);
      saveToFile(CATEGORY_FILE_PATH, categories);
      System.out.println("Категорію успішно видалено.");
    } else {
      System.out.println("Категорію з введеним ID не знайдено.");
    }
  }

  public static void deleteReview() {
    Scanner scanner = new Scanner(System.in);
    List<Review> reviews = JsonDataReader.modelDataJsonReader(REVIEWS_FILE_PATH, Review[].class);

    System.out.println("Список доступних відгуків:");
    for (Review review : reviews) {
      System.out.println("ID відгуку: " + review.getId() + ", Коментар: " + review.getComment());
    }

    System.out.println("Введіть ID відгуку, який хочете видалити:");
    int idReview = ValidationInput.getValidIntInput(scanner);

    Review selectedReview = reviews.stream()
        .filter(review -> review.getId() == idReview)
        .findFirst()
        .orElse(null);

    if (selectedReview != null) {
      reviews.remove(selectedReview);
      saveToFile(REVIEWS_FILE_PATH, reviews);
      System.out.println("Відгук успішно видалено.");
    } else {
      System.out.println("Відгук з введеним ID не знайдено.");
    }
  }

  public static void deleteUser() {
    Scanner scanner = new Scanner(System.in);
    List<User> users = JsonDataReader.modelDataJsonReader(USER_FILE_PATH, User[].class);

    System.out.println("Список користувачів:");
    for (User user : users) {
      System.out.println("ID користувача: " + user.getUserId() + ", Ім'я: " + user.getUsername());
    }

    System.out.println("Введіть ID користувача, якого хочете видалити:");
    int idUser = ValidationInput.getValidIntInput(scanner);

    User selectedUser = users.stream()
        .filter(user -> user.getUserId() == idUser)
        .findFirst()
        .orElse(null);

    if (selectedUser != null) {
      users.remove(selectedUser);
      saveToFile(USER_FILE_PATH, users);
      System.out.println("Користувача успішно видалено.");
    } else {
      System.out.println("Користувача з введеним ID не знайдено.");
    }
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
}
