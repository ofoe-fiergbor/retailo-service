package io.iamofoe.ecommerceservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${system.jwt-secrete}")
    private String JWT_SECRETE;
    @Value("${system.issuer}")
    private String JWT_ISSUER;
    @Value("${system.jwt-email-claim}")
    private String EMAIL_CLAIM;

    public String createAccessToken(String claim) {
        return JWT.create()
                .withIssuedAt(new Date())
                .withIssuer(JWT_ISSUER)
                .withClaim(EMAIL_CLAIM, claim)
                .sign(Algorithm.HMAC256(JWT_SECRETE.getBytes()));
    }

    public String getJwtClaim(String accessToken) {
        return decodeJwt(accessToken).getClaim(EMAIL_CLAIM).asString();
    }

    private DecodedJWT decodeJwt(String accessToken) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JWT_SECRETE.getBytes()))
                .withIssuer(JWT_ISSUER)
                .build();
        return jwtVerifier.verify(accessToken);
    }

}
