package org.example.assignment2.level_0to2.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ScheduleResponse {

  private Long id;
  private String content;
  private String author;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

}
