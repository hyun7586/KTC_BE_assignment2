package org.example.assignment2.level_0to2.repository;

import java.util.List;
import org.example.assignment2.level_0to2.domain.Schedule;

public interface ScheduleRepository {

  Schedule findById(Long scheduleId);
  List<Schedule> findAll();
  Schedule save(Schedule schedule);
  Schedule update(Long scheduleId, Schedule schedule);
  void deleteById(Long scheduleId);

}
