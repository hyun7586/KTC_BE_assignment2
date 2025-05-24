package org.example.assignment2.level_0to2.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.example.assignment2.level_0to2.dto.ScheduleRequest;
import org.example.assignment2.level_0to2.dto.ScheduleResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class ScheduleRepositoryImpl implements ScheduleRepository {

  private final JdbcTemplate template;

  public ScheduleRepositoryImpl(DataSource dataSource) {
    this.template = new JdbcTemplate(dataSource);
  }


  @Override
  public ScheduleResponse findById(Long scheduleId) {
    String sql = "SELECT * FROM schedule WHERE id = " + scheduleId;
    return template.queryForObject(sql, (rs, rowNum) -> ScheduleResponse.builder()
        .id(rs.getLong("id"))
        .content(rs.getString("content"))
        .author(rs.getString("author"))
        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
        .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
        .build());
  }

  @Override
  public List<ScheduleResponse> findAll(ScheduleRequest request) {
    String ssql = "SELECT * FROM schedule ";
    List<String> conditions = new ArrayList<>();

    if (request.getAuthor() != null) {
      conditions.add("author = " + request.getAuthor() + " ");
    }

    if (request.getDate() != null) {
      conditions.add("updated_at >= '" + request.getDate() + " 00:00:00' AND updated_at < '"
          + request.getDate().plusDays(1) + " 00:00:00' ");
    }

    if (!conditions.isEmpty()) {
      ssql += "WHERE ";
      ssql += conditions.get(0) + " ";

      if (conditions.size() == 2) {
        ssql += "AND " + conditions.get(1) + " ";
      }

    }

    ssql += "ORDER BY updated_at DESC";

    log.info("sql query: " + ssql);

    return template.query(ssql,
        (rs, rowNum) ->
            ScheduleResponse.builder()
                .id(rs.getLong("id"))
                .content(rs.getString("content"))
                .author(rs.getString("author"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
                .build());
  }

  @Override
  public ScheduleResponse save(ScheduleRequest request) {
    String sql = "INSERT INTO schedule (content, author, password, created_at, updated_at)"
        + " VALUES (?, ?, ?, ?, ?)";

    KeyHolder keyHolder = new GeneratedKeyHolder();
    LocalDateTime now = LocalDateTime.now();

    template.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, request.getContent());
      ps.setString(2, request.getAuthor());
      ps.setString(3, request.getPassword());
      ps.setTimestamp(4, Timestamp.valueOf(now));
      ps.setTimestamp(5, Timestamp.valueOf(now));
      return ps;
    }, keyHolder);

    // 자동 생성된 ID 추출
    Long generatedId = keyHolder.getKey().longValue();

    return ScheduleResponse.builder()
        .id(generatedId)
        .content(request.getContent())
        .author(request.getAuthor())
        .createdAt(now)
        .updatedAt(now)
        .build();
  }

  // 수정된 레코드의 id만 리턴 -> service 계층에서 다시 findById() 호출하면서 수정된 레코드 조회
  @Override
  public Long update(Long scheduleId, ScheduleRequest request) {
    String sql = "UPDATE schedule SET author = ?, content = ?, updated_at = ? WHERE id = ? AND "
        + "password = ?";
    LocalDateTime now = LocalDateTime.now();

    int updated = template.update(sql,
        request.getAuthor(),
        request.getContent(),
        now,
        scheduleId,
        request.getPassword()
    );

    if (updated == 0) {
      throw new IllegalArgumentException("비밀번호가 일치하지 않거나 일정이 존재하지 않습니다");
    }

    return scheduleId;
  }

  @Override
  public void deleteById(Long scheduleId, String password) {
    String sql = "DELETE FROM schedule WHERE id = ? AND password = ?";
    int deleted = template.update(sql, scheduleId, password);

    // query로 영향을 받은 수가 0이라면? 삭제가 되지 않은 것
    if (deleted == 0) {
      throw new IllegalArgumentException("비밀번호가 일치하지 않거나 일정이 존재하지 않습니다.");
    }
  }
}
