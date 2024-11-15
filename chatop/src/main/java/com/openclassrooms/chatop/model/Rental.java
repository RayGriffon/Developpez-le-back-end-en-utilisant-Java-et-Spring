package com.openclassrooms.chatop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "RENTALS")
@Getter
@Setter
public class Rental {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, length = 255)
  private String name;

  private BigDecimal surface;
  private BigDecimal price;

  @Column(length = 255)
  private String picture;

  @Column(length = 2000)
  private String description;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @ManyToOne
  @JoinColumn(name = "owner_id", nullable = false)
  private User owner;

  @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL)
  private List<Message> messages;

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = LocalDateTime.now();
  }
}
