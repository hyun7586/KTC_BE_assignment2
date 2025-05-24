package org.example.assignment2.level_0to2.repository;

import java.util.List;
import org.example.assignment2.level_0to2.domain.Schedule;
import org.example.assignment2.level_0to2.dto.ScheduleRequest;

public interface ScheduleRepository {

  Schedule findById(Long scheduleId);
  List<Schedule> findAll(ScheduleRequest request);
  Schedule save(ScheduleRequest request);
  Long update(Long scheduleId, ScheduleRequest request);
  void deleteById(Long scheduleId, String password);

}
