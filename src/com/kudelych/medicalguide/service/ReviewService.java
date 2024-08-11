package com.kudelych.medicalguide.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.kudelych.medicalguide.domain.model.Review;
import com.kudelych.medicalguide.domain.model.User;
import com.kudelych.medicalguide.domain.model.Medicine;
import com.kudelych.medicalguide.domain.validation.ValidationInput;
import com.kudelych.medicalguide.presentation.Application;
import com.kudelych.medicalguide.service.util.JsonDataReader;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ReviewService {

  private static final String REVIEWS_FILE_PATH = "Data/reviews.json";
  private static final String MEDICINES_FILE_PATH = "Data/medicines.json";
  private static List<Review> reviews;
  private static List<Medicine> medicines;

  public static void main(String[] args) {
    reviews = JsonDataReader.modelDataJsonReader(REVIEWS_FILE_PATH, Review[].class);
    medicines = JsonDataReader.modelDataJsonReader(MEDICINES_FILE_PATH, Medicine[].class);
    displayReviews(reviews);
  }

  public static void displayReviews(List<Review> reviews) {
    if (reviews.isEmpty()) {
      System.out.println("Список відгуків порожній.");
    } else {
      System.out.println("Список відгуків:");
      for (Review review : reviews) {
        System.out.println("Лікарський засіб: " + review.getMedicine());
        System.out.println("Користувач: " + review.getUser());
        System.out.println("Оцінка: " + review.getMark());
        System.out.println("Коментар: " + review.getComment());
        System.out.println(); // Для розділення між записами
      }
    }
  }

  public static void addReview() {
    Scanner scanner = new Scanner(System.in);
    reviews = JsonDataReader.modelDataJsonReader(REVIEWS_FILE_PATH, Review[].class);
    medicines = JsonDataReader.modelDataJsonReader(MEDICINES_FILE_PATH, Medicine[].class);

    System.out.println("Додавання нового відгуку:");

    // Введення даних нового відгуку
    System.out.print("Введіть назву лікарського засобу: ");
    String medicineName = scanner.nextLine();
    Medicine selectedMedicine = findMedicineByName(medicineName);
    if (selectedMedicine == null) {
      System.out.println("Лікарський засіб не знайдено.");
      return;
    }

    User currentUser = Application.currentUser; // Використання поточного користувача
    if (currentUser == null) {
      System.out.println("Вам потрібно авторизуватися.");
      return;
    }

    int mark;
    while (true) {
      System.out.print("Введіть оцінку (1-5): ");
      if (scanner.hasNextInt()) {
        mark = scanner.nextInt();
        scanner.nextLine(); // Скидання нового рядка після числового вводу
        if (ValidationInput.isValidMark(mark)) {
          break;  // Вихід з циклу, якщо оцінка валідна
        } else {
          System.out.println("Невірна оцінка. Введіть значення від 1 до 5.");
        }
      } else {
        System.out.println("Будь ласка, введіть ціле число.");
        scanner.nextLine(); // Очищення буфера сканера
      }
    }

    String comment;
    while (true) {
      System.out.print("Введіть коментар: ");
      comment = scanner.nextLine();

      if (ValidationInput.isEmpty(comment)) {
        System.out.println("Коментар не може бути порожнім.");
      } else if (!ValidationInput.isValidName(comment)) {
        System.out.println("Коментар повинен мiстити беквенні символи.");
      } else {
        break; // Вихід з циклу, якщо коментар валідний
      }
    }

    Review newReview = new Review();
    newReview.setId(generateUniqueId());
    newReview.setMedicine(selectedMedicine.getName());
    newReview.setUser(currentUser.getUsername());
    newReview.setMark(mark);
    newReview.setComment(comment);

    reviews.add(newReview);

    // Зберегти оновлені дані у файлі JSON
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
      objectMapper.writeValue(new File(REVIEWS_FILE_PATH), reviews);
      System.out.println("Новий відгук додано успішно.");
    } catch (IOException e) {
      System.out.println("Помилка при додаванні нового відгуку: " + e.getMessage());
    }
  }

  private static int generateUniqueId() {
    int nextId = 1;
    for (Review review : reviews) {
      if (review.getId() >= nextId) {
        nextId = review.getId() + 1;
      }
    }
    return nextId;
  }

  private static Medicine findMedicineByName(String name) {
    for (Medicine medicine : medicines) {
      if (medicine.getName().equalsIgnoreCase(name)) {
        return medicine;
      }
    }
    return null;
  }
}
