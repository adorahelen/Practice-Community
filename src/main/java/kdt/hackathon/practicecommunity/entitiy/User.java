package kdt.hackathon.practicecommunity.entitiy;

import com.github.f4b6a3.ulid.Ulid; // ULID 사용

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;


@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User {

    @Id
    @Column(name = "id")
    private String id;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid email format")
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "Invalid phone number format. Expected: 010-XXXX-XXXX")
    private String phoneNumber;
    @NotNull
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid birthdate format. Expected: YYYY-MM-DD")
    private String birthDate;
    private String role;

//    @Pattern: 이메일, 전화번호, 생년월일 필드에 정규식을 추가하여 입력값이 특정 형식에 맞는지 확인합니다.
//	•	이메일: example@google.com 형식.
//  •	전화번호: 010-1234-5678 형식.
//	•	생년월일: 1999-01-01 형식.

    // # 피드나 코멘트가 지워져도, 유저는 영향을 받지 않지만,
    //      유저가 지워지면 연관된 피드와 코멘트는 삭제된다.

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Feed> feeds;

    // ==================================================
    @Builder
    public User(String email, // 로그인 아이디
                String password, // 로그인 비밀번호
                String name, // 외래키 - (피드, 코멘트)
                String phoneNumber,
                String birthDate,
                String role
    )
    {
        this.id = Ulid.fast().toString();
        // 각 User 객체가 생성될 때마다 고유한 ULID가 기본키로 자동 생성
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.role = role;
    }
}
