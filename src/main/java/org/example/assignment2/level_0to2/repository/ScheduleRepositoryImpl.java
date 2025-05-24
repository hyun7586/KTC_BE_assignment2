package org.example.assignment2.level_0to2.repository;

import java.util.List;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.example.assignment2.level_0to2.domain.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

  private final JdbcTemplate template;

  public ScheduleRepositoryImpl(DataSource dataSource){
    this.template=new JdbcTemplate(dataSource);
  }


  @Override
  public Schedule findById(Long scheduleId) {
    String sql = "SELECT * FROM schedule WHERE id = ?";


    return null;
  }

  @Override
  public List<Schedule> findAll() {
    return null;
  }

  @Override
  public Schedule save(Schedule schedule) {
    return null;
  }

  @Override
  public Schedule update(Long scheduleId, Schedule schedule) {
    return null;
  }

  @Override
  public void deleteById(Long scheduleId) {

  }
}
