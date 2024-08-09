package com.sashka11111.bookkeeping.service;

import com.sashka11111.bookkeeping.domain.model.Book;
import java.util.List;
import java.util.Scanner;

public class SearchService {

  private static final String BOOK_FILE_PATH = "Data/books.json";

  public static void searchService() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Введіть назву книги:");
    String searchTitle = scanner.nextLine();

    List<Book> books = JsonDataReader.modelDataJsonReader(BOOK_FILE_PATH, Book[].class);

    boolean found = false;
    for (Book book : books) {
      // Перевіряємо, чи назва книги співпадає з введеною назвою (ігноруючи регістр)
      if (book.getTitle().equalsIgnoreCase(searchTitle)) {
        System.out.println("Назва книги: " + book.getTitle());
        System.out.println("Автор: " + book.getAuthor());
        System.out.println("Категорія: " + book.getCategory());
        System.out.println("Рік видання: " + book.getYearPublished());
        System.out.println("");
        found = true;
      }
    }

    if (!found) {
      System.out.println("Книга з назвою \"" + searchTitle + "\" не знайдена.");
    }
  }
}
