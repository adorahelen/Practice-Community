package kdt.hackathon.practicecommunity.dto;

import kdt.hackathon.practicecommunity.entitiy.Category;
import kdt.hackathon.practicecommunity.entitiy.Feed;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FeedDto {
    private Long id; // 수정때 필요
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
        return new Feed(id, title, content, now, now, category); // 현재 시간으로 생성 및 업데이트 시간 설정
    }
    // 프론트(new.mustache)에서 보내는거 주석처리 후, 백엔드에서 시간 할당
}
