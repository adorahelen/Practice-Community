package kdt.hackathon.practicecommunity.service;

import kdt.hackathon.practicecommunity.dto.FeedDto;
import kdt.hackathon.practicecommunity.entitiy.Feed;
import kdt.hackathon.practicecommunity.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class FeedService {
    @Autowired
    FeedRepository feedRepository;

    @Transactional
    public Feed update(Long id, FeedDto dto){ // 기존 데이터에, 새 데이터를 붙여주어 -> 일부 데이터만 수정하도록
        Feed newfeed = feedRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found : " + id));

        // dto에 updatedAt 값을 설정
        dto.setUpdatedAt(LocalDateTime.now());

        newfeed.update(
                dto.getId(),
                dto.getTitle(),
                dto.getContent(),
                dto.getUpdatedAt(),
                dto.getCategory());

        return newfeed;
    }
}
