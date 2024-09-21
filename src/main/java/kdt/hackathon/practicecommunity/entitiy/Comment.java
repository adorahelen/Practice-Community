package kdt.hackathon.practicecommunity.entitiy;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    // #=============================================

    // 게시글 (번호)아이디 (외래키1) *******
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed;

    // 작성자 아이디 (유저 테이블 이름 - 외래키2)
    //      * 게시판 <-> 댓글 이상 없을시 (조건 부여하기 : 둘다 작성자 아이디가 있어야 함 ULID)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // ================================================

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate // save when Entity Created
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate // save when Entity Modified
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @Builder
    public Comment(String content,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {

        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }


    // 댓글 업데이트 메소드
    public void update(
                       String content,
                       LocalDateTime updatedAt
                        ) {
        this.content = content;
        this.updatedAt = updatedAt;

    }
    // 댓글 고유 아이디 (오토 인크리먼트)
    // 게시글 아이디 (외래키1)
    // 작성자 아이디 (유저 테이블 이름 - 외래키2)

    // 댓글 내용 (제목 X)
    // 댓글 작성일
    // 댓글 수정일

}
