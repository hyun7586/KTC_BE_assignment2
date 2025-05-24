package org.example.assignment2.level_0to2.controller;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.assignment2.level_0to2.dto.ScheduleRequest;
import org.example.assignment2.level_0to2.dto.ScheduleResponse;
import org.example.assignment2.level_0to2.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
public class ScheduleApiController {

  private final ScheduleService scheduleService;

  // schedule list 조회
  @GetMapping("/list")
  public ResponseEntity<?> getScheduleList(
      @RequestBody ScheduleRequest request
  ){
    List<ScheduleResponse> list = scheduleService.getScheduleList(request);

    return ResponseEntity.ok(list);
  }

  // 단일 schedule 조회
  @GetMapping("/{schedule_id}")
  public ResponseEntity<?> getSchedule(
      @PathVariable(name="schedule_id") Long scheduleId
  ){
    // author, updatedAt 기준 조회도 구현
    ScheduleResponse result;

    try {
      result = scheduleService.getSchedule(scheduleId);
    }catch(Exception e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("잘못된 요청입니다");
    }

    return ResponseEntity.ok(result);
  }

  // schedule 생성
  @PostMapping("/post")
  public ResponseEntity<?> createSchedule(
      @RequestBody ScheduleRequest request
  ){
      ScheduleResponse result = scheduleService.createSchedule(request);

      return ResponseEntity.ok(result);
  }

  // schedule 수정
  @PatchMapping("/{schedule_id}")
  public ResponseEntity<?> updateSchedule(
      @PathVariable(name="schedule_id") Long scheduleId,
      @RequestBody ScheduleRequest request
  ){
    ScheduleResponse result;
    try {
      result = scheduleService.updateSchedule(scheduleId, request);
    } catch(Exception e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("잘못된 요청입니다");
    }

    return ResponseEntity.ok(result);
  }

  // schedule 삭제
  @DeleteMapping("/{schedule_id}")
  public ResponseEntity<?> deleteSchedule(
      @PathVariable(name="schedule_id") Long scheduleId,
      @RequestBody ScheduleRequest request
  ){
    try {
      scheduleService.deleteSchedule(scheduleId, request);
    } catch(Exception e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("잘못된 요청입니다");
    }

    return ResponseEntity.ok("the schedule is successfully deleted");
  }

}
