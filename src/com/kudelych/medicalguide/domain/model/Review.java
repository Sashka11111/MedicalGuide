package com.sashka11111.bookkeeping.domain.model;

public class Review {

  private int id;
  private String comment;
  private int mark;
  private int book;

  public Review() {
  }
  public Review(int id, String comment, int mark,int book) {
    this.id = id;
    this.comment = comment;
    this.mark = mark;
    this.book = book;
  }


  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public int getMark() {
    return mark;
  }

  public void setMark(int mark) {
    this.mark = mark;
  }
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getbook() {
    return book;
  }

  public void setbook(int book) {
    this.book = book;
  }
  @Override
  public String toString() {
    return "Review{" +
        "id=" + id + '\'' +
        "comment='" + comment + '\'' +
        ", mark=" + mark +
        ", book=" + book +
        '}';
  }
}
