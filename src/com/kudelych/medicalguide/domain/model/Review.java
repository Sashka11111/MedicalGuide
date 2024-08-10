package com.kudelych.medicalguide.domain.model;

public class Review {

  private int id;
  private String medicine;
  private String user;
  private int mark;
  private String comment;

  // Конструктор
  public Review(int id, String medicine, String user, int mark, String comment) {
    this.id = id;
    this.medicine = medicine;
    this.user = user;
    this.mark = mark;
    this.comment = comment;
  }

  // Пустий конструктор
  public Review() {}

  // Гетери і сетери
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getMedicine() {
    return medicine;
  }

  public void setMedicine(String medicine) {
    this.medicine = medicine;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public int getMark() {
    return mark;
  }

  public void setMark(int mark) {
    this.mark = mark;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  // Перевизначення методу toString
  @Override
  public String toString() {
    return "Review{" +
        "id=" + id +
        ", medicine=" + medicine +
        ", user=" + user +
        ", mark=" + mark +
        ", comment='" + comment + '\'' +
        '}';
  }
}
