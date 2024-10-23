package csulb.mingle.global.util.redis;

public class RedisConstants {
    public static final String UNVERIFIED_PREFIX = "unverified:";
    public static final String VERIFIED_PREFIX = "verified:";
    public static final long VERIFIED_CODE_DURATION_MINUTES = 10;
    public static final long VERIFIED_USER_DURATION_MINUTES = 30;

    private RedisConstants() {
    }
}

