package com.kudelych.medicalguide.domain.model;

public class Medicine {
  private int id;
  private String name;
  private String category;
  private String description;
  private double price;
  private String manufacturer;

  // Конструктор
  public Medicine(int id, String name, String category, String description, double price, String manufacturer) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.description = description;
    this.price = price;
    this.manufacturer = manufacturer;
  }

  // Конструктор за замовчуванням
  public Medicine() {
  }

  // Гетери і сетери
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  // Перевизначення методу toString
  @Override
  public String toString() {
    return "Medicine{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", category=" + category +
        ", description='" + description + '\'' +
        ", price=" + price +
        ", manufacturer='" + manufacturer + '\'' +
        '}';
  }
}