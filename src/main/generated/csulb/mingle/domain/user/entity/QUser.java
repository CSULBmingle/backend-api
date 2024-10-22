package csulb.mingle.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1122880016L;

    public static final QUser user = new QUser("user");

    public final csulb.mingle.global.common.entity.QBaseTimeEntity _super = new csulb.mingle.global.common.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final StringPath firstname = createString("firstname");

    public final DatePath<java.time.LocalDate> graduationYear = createDate("graduationYear", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lastname = createString("lastname");

    public final StringPath major = createString("major");

    public final StringPath minor = createString("minor");

    public final StringPath password = createString("password");

    public final DateTimePath<java.time.LocalDateTime> passwordChangedAt = createDateTime("passwordChangedAt", java.time.LocalDateTime.class);

    public final StringPath studentId = createString("studentId");

    public final StringPath username = createString("username");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

