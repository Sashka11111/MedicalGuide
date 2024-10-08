package com.kudelych.medicalguide.presentation;

import com.kudelych.medicalguide.domain.validation.UserInputHandler;
import com.kudelych.medicalguide.service.operations.AuthorizationService;
import com.kudelych.medicalguide.service.CategoryService;
import com.kudelych.medicalguide.service.UserService;
import com.kudelych.medicalguide.service.operations.DeleteService;
import com.kudelych.medicalguide.service.operations.EditService;
import com.kudelych.medicalguide.service.MedicineService;
import com.kudelych.medicalguide.service.operations.RegistrationService;
import com.kudelych.medicalguide.service.ReviewService;
import com.kudelych.medicalguide.service.operations.SearchService;

public class Menu {

  public Menu() {
  }

  public static void show() throws IllegalAccessException {
    UserInputHandler userInputHandler = new UserInputHandler();
    while (true) {
      if (Application.currentUser == null) {
        String art = "╭╮╭╮╭┳━━━┳╮╱╱╭━━━┳━━━┳━╮╭━┳━━━╮\n"
            + "┃┃┃┃┃┃╭━━┫┃╱╱┃╭━╮┃╭━╮┃┃╰╯┃┃╭━━╯\n"
            + "┃┃┃┃┃┃╰━━┫┃╱╱┃┃╱╰┫┃╱┃┃╭╮╭╮┃╰━━╮\n"
            + "┃╰╯╰╯┃╭━━┫┃╱╭┫┃╱╭┫┃╱┃┃┃┃┃┃┃╭━━╯\n"
            + "╰╮╭╮╭┫╰━━┫╰━╯┃╰━╯┃╰━╯┃┃┃┃┃┃╰━━╮\n"
            + "╱╰╯╰╯╰━━━┻━━━┻━━━┻━━━┻╯╰╯╰┻━━━╯\n";

        System.out.println(art);
        System.out.println("1) Реєстрація");
        System.out.println("2) Авторизація");
        System.out.println("0) Вихід");

        int choice = userInputHandler.promptUserForInteger("Ваш вибір: ");

        switch (choice) {
          case 1:
            RegistrationService.registration();
            break;
          case 2:
            AuthorizationService.authorization();
            break;
          case 0:
            System.out.println("Дякую за використання.");
            System.exit(0);
            break;
          default:
            System.out.println("Невірний вибір. Спробуйте ще раз.");
            break;
        }
        continue; // Повернення на початок циклу
      }

      String userRole = Application.currentUser.getRole();

      if ("".equals(userRole)) {
        System.out.println("1) Реєстрація");
        System.out.println("2) Авторизація");
      } else {
        String art = "             {_________}\n"
            + "              )=======(\n"
            + "             /         \\\n"
            + "            | _________ |\n"
            + "            ||   _     ||\n"
            + "            ||  |_)    ||\n"
            + "            ||  | \\/   ||\n"
            + "      __    ||    /\\   ||\n"
            + " __  (_|)__ |'---------'|\n"
            + "(_|)    (_|)`-.........-'\n";

        System.out.println(art);
        System.out.println("1) Перегляд даних");
        System.out.println("2) Пошук лікарського засобу");
        System.out.println("3) Залишити відгук на лікарський засіб");

        if ("Admin".equals(userRole)) {
          System.out.println("4) Додавання даних");
          System.out.println("5) Редагування даних");
          System.out.println("6) Видалення даних");
        }
      }

      System.out.println("0) Вихід з головного меню");

      int choice = userInputHandler.promptUserForInteger("Ваш вибір: ");

      switch (choice) {
        case 1:
          if ("".equals(userRole)) {
            RegistrationService.registration();
          } else {
            showViewMenu();
          }
          break;
        case 2:
          if ("".equals(userRole)) {
            AuthorizationService.authorization();
          } else {
            SearchService.searchService();
          }
          break;
        case 3:
          if (!"".equals(userRole)) {
            ReviewService.addReview();
          }
          break;
        case 4:
          if ("Admin".equals(userRole)) {
            showAddMenu();
          }
          break;
        case 5:
          if ("Admin".equals(userRole)) {
            showEditMenu();
          }
          break;
        case 6:
          if ("Admin".equals(userRole)) {
            showDeleteMenu();
          }
          break;
        case 0:
          Application.currentUser = null; // Вихід з головного меню
          break;
        default:
          System.out.println("Невірний вибір. Спробуйте ще раз.");
          break;
      }
    }
  }

  private static void showViewMenu() throws IllegalAccessException {
    System.out.println("1) Переглянути лікарські засоби");
    System.out.println("2) Переглянути категорії");
    System.out.println("3) Переглянути відгуки");
    System.out.println("4) Переглянути мої дані");
    System.out.println("5) Назад");

    int choice = new UserInputHandler().promptUserForInteger("Ваш вибір: ");

    switch (choice) {
      case 1:
        MedicineService.main(new String[]{});
        break;
      case 2:
        CategoryService.main(new String[]{});
        break;
      case 3:
        ReviewService.main(new String[]{});
        break;
      case 4:
        UserService.displayUserInfo(Application.currentUser);
        break;
      case 5:
        return;
      default:
        System.out.println("Невірний вибір. Спробуйте ще раз.");
        break;
    }
  }

  private static void showAddMenu() {
    System.out.println("1) Додати лікарський засіб");
    System.out.println("2) Додати категорію");
    System.out.println("3) Назад");

    int choice = new UserInputHandler().promptUserForInteger("Ваш вибір: ");

    switch (choice) {
      case 1:
        MedicineService.addMedicine();
        break;
      case 2:
        CategoryService.addCategory();
        break;
      case 3:
        return;
      default:
        System.out.println("Невірний вибір. Спробуйте ще раз.");
        break;
    }
  }

  private static void showEditMenu() {
    System.out.println("1) Редагувати лікарський засіб");
    System.out.println("2) Редагувати категорію");
    System.out.println("3) Назад");

    int choice = new UserInputHandler().promptUserForInteger("Ваш вибір: ");

    switch (choice) {
      case 1:
        EditService.editMedicine();
        break;
      case 2:
        EditService.editCategory();
        break;
      case 3:
        return;
      default:
        System.out.println("Невірний вибір. Спробуйте ще раз.");
        break;
    }
  }
  private static void showDeleteMenu() {
    System.out.println("1) Видалити лікарський засіб");
    System.out.println("2) Видалити категорію");
    System.out.println("3) Видалити відгук");
    System.out.println("4) Видалити користувача");
    System.out.println("5) Назад");

    int choice = new UserInputHandler().promptUserForInteger("Ваш вибір: ");

    switch (choice) {
      case 1:
        DeleteService.deleteMedicine();
        break;
      case 2:
        DeleteService.deleteCategory();
        break;
      case 3:
        DeleteService.deleteReview();
        break;
      case 4:
        DeleteService.deleteUser();
        break;
      case 5:
        return;
      default:
        System.out.println("Невірний вибір. Спробуйте ще раз.");
        break;
    }
  }

}
