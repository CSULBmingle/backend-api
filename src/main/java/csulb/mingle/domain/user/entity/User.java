package csulb.mingle.domain.user.entity;

import csulb.mingle.global.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@SoftDelete(columnName = "is_deleted")
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 128)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false, length = 50)
    private String firstname;

    @Column(nullable = false, length = 50)
    private String lastname;

    @Column(nullable = false, unique = true, length = 30)
    private String username;

    @Column(nullable = false, length = 50)
    private String major;

    @Column(nullable = false, length = 50)
    private String minor;

    @Column(name = "graduation_year", nullable = false)
    private LocalDate graduationYear;

    @Column(name = "student_id", nullable = false, length = 50)
    private String studentId;

    @Column(name = "password_changed_at")
    private LocalDateTime passwordChangedAt;

    @Builder

    private User(String email, String password, String firstname, String lastname, String username, String major, String minor, LocalDate graduationYear, String studentId, LocalDateTime passwordChangedAt) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.major = major;
        this.minor = minor;
        this.graduationYear = graduationYear;
        this.studentId = studentId;
        this.passwordChangedAt = LocalDateTime.now();
    }
}
