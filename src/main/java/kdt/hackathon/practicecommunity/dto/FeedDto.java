package kdt.hackathon.practicecommunity.dto;

import kdt.hackathon.practicecommunity.entitiy.Category;
import kdt.hackathon.practicecommunity.entitiy.Feed;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FeedDto {
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Category category;


//    public Feed toEntity() {
//        return new Feed(title, content, createdAt, updatedAt, category);
//    } -> FeedDto(title=asdasdsa, content=dasdadd, createdAt=null, updatedAt=null, category=null)

    public Feed toEntity() {
        LocalDateTime now = LocalDateTime.now();
        return new Feed(title, content, now, now, category); // 현재 시간으로 생성 및 업데이트 시간 설정
    }
}
