package com.kudelych.medicalguide.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sashka11111.bookkeeping.domain.model.Book;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class BookService {

  private static final String BOOK_FILE_PATH = "Data/medicines.json";
  private static List<Book> books;

  // Метод для відображення всіх книг
  public static void main(String[] args) {
    books = JsonDataReader.modelDataJsonReader(BOOK_FILE_PATH, Book[].class);
    displayBooks(books);
  }

  public static void displayBooks(List<Book> bookList) {
    if (bookList.isEmpty()) {
      System.out.println("Список книг порожній.");
    } else {
      System.out.println("Список книг:");
      for (Book book : bookList) {
        System.out.println("ID книги: " + book.getId());
        System.out.println("Назва: " + book.getTitle());
        System.out.println("Автор: " + book.getAuthor());
        System.out.println("Рік видання: " + book.getYearPublished());
        System.out.println("Категорія: " + book.getCategory());
        System.out.println(); // Для розділення між записами
      }
    }
  }

  public static void addBook() {
    Scanner scanner = new Scanner(System.in);
    books = JsonDataReader.modelDataJsonReader(BOOK_FILE_PATH, Book[].class);

    // Знайти максимальний ID книги
    int maxBookId = books.stream()
        .mapToInt(Book::getId)
        .max()
        .orElse(0);

    // Новий ID книги буде на одиницю більше за максимальний
    int newBookId = maxBookId + 1;

    System.out.println("Додавання нової книги:");

    System.out.println("Введіть назву книги:");
    String title = scanner.nextLine();

    System.out.println("Введіть автора книги:");
    String author = scanner.nextLine();

    System.out.println("Введіть рік видання:");
    int yearPublished = scanner.nextInt();
    scanner.nextLine(); // Споживаємо залишок рядка

    System.out.println("Введіть категорію книги:");
    String category = scanner.nextLine();

    Book newBook = new Book();
    newBook.setId(newBookId); // Встановлюємо новий ID книги
    newBook.setTitle(title);
    newBook.setAuthor(author);
    newBook.setYearPublished(yearPublished);
    newBook.setCategory(category);
    books.add(newBook);

    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
      objectMapper.writeValue(new File(BOOK_FILE_PATH), books);
      System.out.println("Нову книгу додано успішно.");
    } catch (IOException e) {
      System.out.println("Помилка при додаванні нової книги: " + e.getMessage());
    }
  }
}
