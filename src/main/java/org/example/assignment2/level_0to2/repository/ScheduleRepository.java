package org.example.assignment2.level_0to2.repository;

import java.util.List;
import org.example.assignment2.level_0to2.domain.Schedule;
import org.example.assignment2.level_0to2.dto.ScheduleRequest;
import org.example.assignment2.level_0to2.dto.ScheduleResponse;

public interface ScheduleRepository {

  ScheduleResponse findById(Long scheduleId);
  List<ScheduleResponse> findAll(ScheduleRequest request);
  ScheduleResponse save(ScheduleRequest request);
  Long update(Long scheduleId, ScheduleRequest request);
  void deleteById(Long scheduleId, String password);

}
