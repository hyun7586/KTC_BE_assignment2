package org.example.assignment2.level_0to2.dto;

import org.example.assignment2.level_0to2.domain.Schedule;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {

  public ScheduleResponse toResponse(Schedule entity){
    return ScheduleResponse.builder()
        .content(entity.getContent())
        .author(entity.getAuthor())
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        .build();
  }

  // date는 어떻게 처리?
  public Schedule toEntity(ScheduleRequest request){
    return Schedule.builder()
        .content(request.getContent())
        .author(request.getAuthor())
        .password(request.getPassword())
        .build();
  }

}
