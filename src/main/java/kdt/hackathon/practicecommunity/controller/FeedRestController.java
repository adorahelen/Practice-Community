//package kdt.hackathon.practicecommunity.controller;
//
//import kdt.hackathon.practicecommunity.dto.FeedDto;
//import kdt.hackathon.practicecommunity.entitiy.Feed;
//import kdt.hackathon.practicecommunity.repository.FeedRepository;
//import kdt.hackathon.practicecommunity.service.FeedService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Slf4j
//@RestController
//public class FeedRestController {
//    // api 송수신의 경우, 탈렌드 & 포스트맨에서 확인 할 수 있다.
//    //  *  다만, 기존의 진행했던 "스프링 부트 블로그" 프로젝트와는 다르게 여기선 사용하지 않는다.
//    // ? -> 이전에는 자바스크립트를 통해 CRUD(Http Method)를 api 만으로 진행 했고(+타임리프)
//    //      view 는 데이터를 표시하기만 했음
//    // => 기존에는 뷰랑 데이터 처리를 분리하였다면, 여기서는 통합하여 진행했다고 보면 될꺼 같다.
//
//    @Autowired
//    FeedService feedService;
//    @Autowired
//    FeedRepository feedRepository;
//
//    @GetMapping("/api/feeds")
//    public List<Feed> getAllFeeds() {
//        return feedRepository.findAll();
//    }
//
//    @GetMapping("/api/feeds/{id}")
//    public Feed getFeedById(@PathVariable Long id) {
//        return feedRepository.findById(id).orElse(null);
//    }
//
//    @PostMapping("/api/feeds")
//    public Feed addFeed(@RequestBody FeedDto dto) {
//        Feed todoSaved = dto.toEntity();
//        return feedRepository.save(todoSaved);
//    }
//
//    // 반환형을 딘순히 엔티티로 하면 안된다. X
//    // => ResponseEntity<너의엔티티> 이렇게 해야만, 반환하는 데이터에 상태 코드를 실어 보낼 수 있다.
//    @PatchMapping("/api/feeds/{id}")
//    public ResponseEntity<Feed> updateFeed(@PathVariable Long id,
//                                           @RequestBody FeedDto dto) {
//        // Controller
//
//        // Service =====================
//        Feed todoUpdated = dto.toEntity();
//        Feed target = feedRepository.findById(id).orElse(null);
//
//        if (target == null || id != todoUpdated.getId()) {// 비정상 응답
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        target.patch(todoUpdated);
//        Feed updated = feedRepository.save(target); // 정상 응답 + 타이틀이랑 컨텐츠만 수정 가능하게끔 (부분)
//        // ===================== Service
//
//        // Controller
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//
//        // 1. ResponseEntity : Rest 컨트롤의 반환형, 즉 Rest API 의 응답을 위해 사용하는 클래스
//        // 2. HttpStatus : Http 상태 코드를 관리하는 클래스로, 다양한 Enum 타입과 관련한 메서드를 가짐
//    }
//
//    @DeleteMapping("/api/feeds/{id}")
//    public ResponseEntity<Feed> deleteFeed(@PathVariable Long id) {
//        Feed target = feedRepository.findById(id).orElse(null);
//        if (target == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//        feedRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).body(null);
//        // == return ResponseEntity.status(HttpStatus.OK).build();
//    }
//
//
//
//}
