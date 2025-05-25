package org.example.assignment2.level3.dto;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ScheduleRequest {

  private String content;
  private String author;
  private String password;

  // 할일 조회에 사용하는 필드: 특정 날짜의 할일목록만 조회
  private LocalDate date;


}
