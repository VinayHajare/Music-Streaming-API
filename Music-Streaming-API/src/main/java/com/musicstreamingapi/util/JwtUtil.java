package com.musicstreamingapi.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtUtil {
	
	@Value("${jwt.secret}")
	private String SECRET_KEY;
	
	@Value("${jwt.expiration-time-ms}")
	private int EXPIRATION_TIME_MS;
	
	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, username);
	}
	
	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder()
				.claims(claims)
				.subject(subject)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
				.signWith(getSignInKey())
				.compact();
	}
	
	public String extractUsername(String token) {
		return Jwts.parser()
				.verifyWith(getSignInKey())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
            Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (SignatureException e) {
            LoggerUtil.logError("Invalid JWT signature: "+e.getMessage());
        } catch (MalformedJwtException e) {
        	LoggerUtil.logError("Invalid JWT token: "+ e.getMessage());
        } catch (ExpiredJwtException e) {
        	LoggerUtil.logError("JWT token is expired: "+ e.getMessage());
        } catch (UnsupportedJwtException e) {
        	LoggerUtil.logError("JWT token is unsupported: "+ e.getMessage());
        } catch (IllegalArgumentException e) {
        	LoggerUtil.logError("JWT claims string is empty: "+ e.getMessage());
        }

        return false;
	}
	
	private SecretKey getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
