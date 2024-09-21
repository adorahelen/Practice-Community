package kdt.hackathon.practicecommunity.service;

import kdt.hackathon.practicecommunity.dto.FeedDto;
import kdt.hackathon.practicecommunity.entitiy.Feed;
import kdt.hackathon.practicecommunity.repository.FeedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedRestService {
    private static final Logger log = LoggerFactory.getLogger(FeedRestService.class);
    @Autowired
    private FeedRepository feedRepository;


    public List<Feed> index() {
        return feedRepository.findAll();
    }
    public Feed show(Long id) {
        return feedRepository.findById(id).orElse(null);
    }
    public Feed create(FeedDto dto) {
        Feed feed = dto.toEntity();
        // 기존에 데이터가 존재하면, 덮어씌우지 못하도록 null 반환시키기
        if (feed.getId() != null) {
            return null;
        }
        return feedRepository.save(feed);
    }
    public Feed update(Long id, FeedDto dto) {
        Feed feed = dto.toEntity();
        Feed target = feedRepository.findById(id).orElse(null);

        if (target == null || id != feed.getId()) {
            log.info("Feed with id {} not found", id);
            return null;
        }
        target.patch(feed);
        Feed result = feedRepository.save(target);
        return result;
    }

    public Feed delete(Long id) {
        Feed target = feedRepository.findById(id).orElse(null);
        if (target == null) {
            return null;
        }

        feedRepository.delete(target);
        return target;
    }

}
