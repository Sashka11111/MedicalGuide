package com.sashka11111.bookkeeping.domain.model;

public class User {

  private int userId;
  private String username; // Ім'я користувача
  private String password; // Пароль користувача
  private String email; // Електронна пошта користувача
  private String phoneNumber; // Номер телефону користувача
  private String role; // Роль користувача (наприклад, "Бібліотекар" або "Читач")

  // Конструктор за замовчуванням
  public User() {
  }

  public User(int userId, String username, String password, String email, String phoneNumber, String role) {
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.role = role;
  }

  public User(String username, String password, String email, String phoneNumber, String role) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.phoneNumber = phoneNumber;
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

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
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
        ", phoneNumber='" + phoneNumber + '\'' +
        ", password='" + password + '\'' +
        ", role='" + role + '\'' +
        '}';
  }
}
