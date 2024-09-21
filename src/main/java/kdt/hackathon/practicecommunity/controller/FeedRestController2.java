package kdt.hackathon.practicecommunity.controller;

import kdt.hackathon.practicecommunity.dto.FeedDto;
import kdt.hackathon.practicecommunity.entitiy.Feed;
import kdt.hackathon.practicecommunity.service.FeedRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FeedRestController2 {
    @Autowired
    private FeedRestService feedRestService;

    @GetMapping("/api/feeds")
    public List<Feed> getAllFeeds() {return feedRestService.index();}

    @GetMapping("/api/feeds/{id}")
    public Feed getFeedById(@PathVariable Long id) {
        return feedRestService.show(id);
    }
    @PostMapping("/api/feeds")
    public ResponseEntity<Feed> createFeed(@RequestBody FeedDto dto) {
        Feed created = feedRestService.create(dto);
        return (created != null) ? // 생성되면 정상
                ResponseEntity.status(HttpStatus.OK).body(created)
                : // 실패하면 오류
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
    @PatchMapping("/api/feeds/{id}")
    public ResponseEntity<Feed> updateFeed(@PathVariable Long id,
                                           @RequestBody FeedDto dto) {
        Feed update = feedRestService.update(id, dto);
        return (update != null) ?
                ResponseEntity.status(HttpStatus.OK).body(update)
                :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/api/feeds/{id}")
    public ResponseEntity<Feed> deleteFeed(@PathVariable Long id) {
        Feed delete = feedRestService.delete(id);
        return (delete != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
