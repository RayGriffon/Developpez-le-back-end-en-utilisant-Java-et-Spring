package com.openclassrooms.chatop.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "USERS", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, length = 255)
  private String email;

  @Column(nullable = false, length = 255)
  private String name;

  @Column(nullable = false, length = 255)
  private String password;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
  private List<Rental> rentals;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Message> messages;

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = LocalDateTime.now();
  }

  public Integer getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public List<Rental> getRentals() {
    return rentals;
  }

  public List<Message> getMessages() {
    return messages;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public void setRentals(List<Rental> rentals) {
    this.rentals = rentals;
  }

  public void setMessages(List<Message> messages) {
    this.messages = messages;
  }
}
