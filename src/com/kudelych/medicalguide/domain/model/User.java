package com.kudelych.medicalguide.domain.model;

public class User {

  private int userId;
  private String username; // Ім'я користувача
  private String password; // Пароль користувача
  private String email; // Електронна пошта користувача
  private String role; // Роль користувача ( "Admin" або "User")

  // Конструктор за замовчуванням
  public User() {
  }

  public User(int userId, String username, String password, String email, String role) {
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.email = email;
    this.role = role;
  }

  // Методи доступу до полів
  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "User{" +
        "userId=" + userId +
        ", username='" + username + '\'' +
        ", password='" + password +
        ", role='" + role + '\'' +
        '}';
  }
}
