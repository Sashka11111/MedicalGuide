package com.kudelych.medicalguide.presentation;

import com.kudelych.medicalguide.domain.model.User;
import java.util.ArrayList;
import java.util.List;

public class Application {
  public static List<User> adminsList = new ArrayList<>();
  public static List<User> usersList = new ArrayList<>();
  public static User currentUser;

  static {
    // Додавання адміна
    adminsList.add(new User(1, "Admin", "Admin123","Admin@gmail.com", "Admin"));
    // Додавання користувача
    usersList.add(new User(2, "Vlad","Vlad123", "KudelychVlad@gmail.com",  "User"));
  }

  public static void runner() throws IllegalAccessException {
    Menu.show();
  }

  public static void main(String[] args) throws IllegalAccessException {
    runner();
  }
}
