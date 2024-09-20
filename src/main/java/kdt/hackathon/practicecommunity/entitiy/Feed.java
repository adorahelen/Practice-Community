package kdt.hackathon.practicecommunity.entitiy;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="feeds")
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // prime key auto 1+
    @Column(name = "id", updatable = false)
    private Long id;

    // 외래키 (작성자 아이디 : 이름)

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate // save when Entity Created
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate // save when Entity Modified
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Builder
    public Feed(Long id,
                String title,
                String content,
                LocalDateTime createdAt,
                LocalDateTime updatedAt,
                Category category

    )
    {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.category = category;
    }

    // 게시글 업데이트 메소드
    public void update(String title,
                       String content,
                       LocalDateTime updatedAt,
                       Category category) {
        this.title = title;
        this.content = content;
        this.updatedAt = updatedAt;
        this.category = category;
    }
}


    // 게시글 아이디 (기본키 : 오토 인크리먼트)
    // 작성자 아이디 (외래키) - 이름으로 (인터넷 실명제)
    // 게시글 제목
    // 게시글 내용
    // 게시 작성일
    // 게시 수정일
    // 게시글 분류 (카테고리)

