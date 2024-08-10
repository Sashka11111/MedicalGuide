package com.kudelych.medicalguide.service.operations;

import com.kudelych.medicalguide.domain.model.Medicine;
import com.kudelych.medicalguide.service.util.JsonDataReader;
import java.util.List;
import java.util.Scanner;

public class SearchService {

  private static final String MEDICINES_FILE_PATH = "Data/medicines.json";

  public static void searchService() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Введіть назву лікарського засобу:");
    String searchName = scanner.nextLine();

    List<Medicine> medicines = JsonDataReader.modelDataJsonReader(MEDICINES_FILE_PATH, Medicine[].class);

    boolean found = false;
    for (Medicine medicine : medicines) {
      // Перевіряємо, чи назва книги співпадає з введеною назвою (ігноруючи регістр)
      if (medicine.getName().equalsIgnoreCase(searchName)) {
        System.out.println("Назва лікарського засобу: " + medicine.getName());
        System.out.println("Категорія: " + medicine.getCategory());
        System.out.println("Опис: " + medicine.getDescription());
        System.out.println("Ціна: " + medicine.getPrice());
        System.out.println("Виробник: " + medicine.getManufacturer());
        System.out.println("");
        found = true;
      }
    }

    if (!found) {
      System.out.println("Лікарський засіб з назвою \"" + searchName + "\" не знайдено.");
    }
  }
}
