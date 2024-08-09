package com.sashka11111.bookkeeping.presentation;

import com.sashka11111.bookkeeping.domain.model.User;
import java.util.ArrayList;
import java.util.List;

public class Application {
  public static List<User> librariansList = new ArrayList<>();
  public static List<User> readersList = new ArrayList<>();
  public static User currentUser;

  static {
    // Додати бібліотекаря
    librariansList.add(new User("librarian", "librarian123", "librarian@example.com", "+380968893566","Бібліотекар"));
    // Додати читача
    readersList.add(new User("sashka11111", "123456", "sashka11111@gmail.com", "+380968893599","Читач"));
  }

  public static void runner() throws IllegalAccessException {
    Menu.show();
  }

  public static void main(String[] args) throws IllegalAccessException {
    String art = "   ▄▀▀▀▄▄▄▄▄▄▄▀▀▀▄  \n"
        + "   █▒▒░░░░░░░░░▒▒█  \n"
        + "    █░░█░░░░░█░░█    \n"
        + " ▄▄  █░░░▀█▀░░░█  ▄▄ \n"
        + "█░░█ ▀▄░░░░░░░▄▀ █░░█\n"
        + "█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█\n"
        + "█░░╦─╦╔╗╦─╔╗╔╗╔╦╗╔╗░░█\n"
        + "█░░║║║╠─║─║─║║║║║╠─░░█\n"
        + "█░░╚╩╝╚╝╚╝╚╝╚╝╩─╩╚╝░░█\n"
        + "█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█";
    System.out.println(art);
    runner();
  }
}
