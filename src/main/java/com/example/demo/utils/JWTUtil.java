package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.entity.User;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * JWT工具类.
 *
 * @author Mei Ruoxiao
 * @since 2020/8/20
 */
@Slf4j
public class JWTUtil {
    /**
     * 密钥
     */
    private static final String SECRET_KEY = "84413681423712";
    /**
     * token过期时间(单位：豪秒)
     */
    public static final long TOKEN_EXPIRE_TIME = 24 * 60 * 60 * 1000;
    /**
     * 签发人
     */
    private static final String ISSUER = "issuer";


    /**
     * token生成器
     *
     * @param userId 用户Id
     * @param role   角色
     * @return token
     */
    private static String generateToken(final long userId,
                                        final Integer role) {
        Date now = new Date();
        // 算法
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        String token =
                JWT.create()
                        .withIssuer(ISSUER)
                        .withIssuedAt(now)
                        .withExpiresAt(new Date(now.getTime() + TOKEN_EXPIRE_TIME))
                        .withClaim("role", role)
                        .withClaim("userId", userId)
                        .sign(algorithm);
        log.info("generateToken: 生成的token为 [{}]", token);
        return token;
    }


    /**
     * 生成token
     *
     * @param user 员工信息
     * @return 生成后的token
     */
    public static String generateToken(User user, int role) {
        return generateToken(
                user.getId(),
                role);
    }

    /**
     * 验证token
     *
     * @param token token
     * @return true/false
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.warn("verify:[{}]", e.getMessage());
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 解析token（从token获取UserId）
     *
     * @param token token
     * @return ID
     */
    public static Long getUserId(String token) {
        try {
            return JWT.decode(token).getClaim("userId").asLong();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static TokenEntity getTokenEntity(final String token) {
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setUserId(getUserId(token));
        tokenEntity.setVerified(verify(token));
        return tokenEntity;
    }

    @Data
    @ToString
    public static class TokenEntity {
        private Integer role;
        private Long userId;
        private boolean verified;
    }
}
