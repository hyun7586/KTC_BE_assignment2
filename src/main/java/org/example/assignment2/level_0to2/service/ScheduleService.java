package org.example.assignment2.level_0to2.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.assignment2.level_0to2.dto.ScheduleRequest;
import org.example.assignment2.level_0to2.dto.ScheduleResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

  // schedule list 조회
  public List<ScheduleResponse> getScheduleList() {
    return null;
  }

  // 단일 schedule 조회
  public ScheduleResponse getSchedule(Long scheduleId) {
    return null;
  }

  // schedule 생성
  public ScheduleResponse createSchedule(ScheduleRequest request) {
    return null;
  }

  // schedule 수정
  public ScheduleResponse updateSchedule(Long scheduleId, ScheduleRequest request) {
    return null;
  }

  // schedule 삭제
  public void deleteSchedule(Long scheduleId, ScheduleRequest request) {
    //
  }

}
