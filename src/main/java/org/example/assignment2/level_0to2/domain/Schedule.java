package org.example.assignment2.level_0to2.domain;


import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

  private Long id;
  private String content;
  private String author;
  private String password;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

}
