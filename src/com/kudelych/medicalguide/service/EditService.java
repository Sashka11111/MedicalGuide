package com.sashka11111.bookkeeping.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sashka11111.bookkeeping.domain.model.Book;
import com.sashka11111.bookkeeping.domain.model.Category;
import com.sashka11111.bookkeeping.domain.validation.ValidationInput;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class EditService {

  private static final String BOOK_FILE_PATH = "Data/books.json";
  private static final String CATEGORY_FILE_PATH = "Data/categories.json";

  public static void editBook() {
    Scanner scanner = new Scanner(System.in);
    List<Book> books = JsonDataReader.modelDataJsonReader(BOOK_FILE_PATH, Book[].class);

    // Вивести користувачу усі книги
    System.out.println("Список доступних книг:");
    for (Book book : books) {
      System.out.println("ID книги: " + book.getId() + ", Назва: " + book.getTitle());
    }

    System.out.println("Введіть ID книги, яку хочете редагувати:");
    int idBook = ValidationInput.getValidIntInput(scanner);

    Book selectedBook = books.stream()
        .filter(book -> book.getId() == idBook)
        .findFirst()
        .orElse(null);

    if (selectedBook != null) {
      System.out.println("Бажаєте редагувати цю книгу? (Так/Ні):");
      String response = scanner.nextLine();

      if (response.equalsIgnoreCase("так")) {
        System.out.println("Введіть нову назву книги:");
        String newTitle = scanner.nextLine();
        selectedBook.setTitle(newTitle);

        System.out.println("Введіть нову категорію:");
        String newCategory = scanner.nextLine();
        selectedBook.setCategory(newCategory);

        System.out.println("Введіть нового автора:");
        String newAuthor = scanner.nextLine();
        selectedBook.setAuthor(newAuthor);

        System.out.println("Введіть новий рік видання:");
        int newYearPublished = ValidationInput.getValidIntInput(scanner);
        selectedBook.setYearPublished(newYearPublished);

        saveToFile(BOOK_FILE_PATH, books);
        System.out.println("Оновлені дані книги збережено успішно.");
      } else {
        System.out.println("Редагування скасовано.");
      }
    } else {
      System.out.println("Книгу з введеним ID не знайдено.");
    }
  }

  public static void editCategory() {
    Scanner scanner = new Scanner(System.in);
    List<Category> categories = JsonDataReader.modelDataJsonReader(CATEGORY_FILE_PATH, Category[].class);

    System.out.println("Список доступних категорій:");
    for (Category category : categories) {
      System.out.println("ID категорії: " + category.getCategoryId() + ", Назва: " + category.getName());
    }

    System.out.println("Введіть ID категорії, яку хочете редагувати:");
    int idCategory = ValidationInput.getValidIntInput(scanner);

    Category selectedCategory = categories.stream()
        .filter(category -> category.getCategoryId() == idCategory)
        .findFirst()
        .orElse(null);

    if (selectedCategory != null) {
      System.out.println("Бажаєте редагувати цю категорію? (Так/Ні):");
      String response = scanner.nextLine();

      if (response.equalsIgnoreCase("так")) {
        System.out.println("Введіть нову назву категорії:");
        String newName = scanner.nextLine();
        selectedCategory.setName(newName);

        saveToFile(CATEGORY_FILE_PATH, categories);
        System.out.println("Оновлені дані категорії збережено успішно.");
      } else {
        System.out.println("Редагування скасовано.");
      }
    } else {
      System.out.println("Категорію з введеним ID не знайдено.");
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
