package org.example.assignment2.level_0to2.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.assignment2.level_0to2.domain.Schedule;
import org.example.assignment2.level_0to2.dto.ScheduleMapper;
import org.example.assignment2.level_0to2.dto.ScheduleRequest;
import org.example.assignment2.level_0to2.dto.ScheduleResponse;
import org.example.assignment2.level_0to2.repository.ScheduleRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

  private final ScheduleRepositoryImpl repository;
  private final ScheduleMapper mapper;

  // schedule list 조회
  public List<ScheduleResponse> getScheduleList(ScheduleRequest request) {
    return repository.findAll(request);
  }

  // 단일 schedule 조회
  public ScheduleResponse getSchedule(Long scheduleId) {
    return repository.findById(scheduleId);
  }

  // schedule 생성
  public ScheduleResponse createSchedule(ScheduleRequest request) {
    return repository.save(request);
  }

  // schedule 수정
  public ScheduleResponse updateSchedule(Long scheduleId, ScheduleRequest request) {
    Long updatedId = repository.update(scheduleId, request);
    return repository.findById(updatedId);
  }

  // schedule 삭제
  public void deleteSchedule(Long scheduleId, ScheduleRequest request) {
    // request 안에는 비밀번호만 담겨서 옴
    // RequestParam이나 PathVariable로 비밀번호를 노출하는 건 위험하다고 생각해서 RequestBody로 전달하는 게 좋아보였음

    repository.deleteById(scheduleId, request.getPassword());
  }

}
