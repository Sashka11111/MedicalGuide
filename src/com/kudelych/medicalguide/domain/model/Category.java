package com.kudelych.medicalguide.domain.model;

public class Category {

  private int categoryId;
  private String name;

  // Конструктор за замовчуванням
  public Category() {
  }

  public Category(int categoryId, String name) {
    this.categoryId = categoryId;
    this.name = name;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Category{" +
        "id=" + categoryId +
        ", name='" + name + '\'' +
        '}';
  }
}