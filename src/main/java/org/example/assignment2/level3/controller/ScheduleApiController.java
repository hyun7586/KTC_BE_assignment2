package org.example.assignment2.level3.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.assignment2.level3.dto.ScheduleRequest;
import org.example.assignment2.level3.dto.ScheduleResponse;
import org.example.assignment2.level3.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    List<ScheduleResponse> list;
    try {
      list = scheduleService.getScheduleList(request);
    } catch(IllegalArgumentException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("입력한 날짜 형식이 잘못되었습니다");
    }

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
    }catch(IllegalArgumentException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("존재하지 않는 일정입니다");
    } catch(Exception e){
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
    ScheduleResponse result;
    try {
      result = scheduleService.createSchedule(request);
    } catch(Exception e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("잘못된 요청입니다");
    }

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
    } catch(IllegalArgumentException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("비밀번호가 틀렸거나, 존재하지 않는 일정입니다");
    } catch (Exception e){
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
    } catch(IllegalArgumentException e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("비밀번호가 틀렸거나, 존재하지 않는 일정입니다");
    } catch (Exception e){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("잘못된 요청입니다");
    }

    return ResponseEntity.ok("삭제가 완료되었습니다");
  }

}
