package com.ari.project.component;

import com.ari.project.domain.TransformedFile;
import com.ari.project.form.UploadForm;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Component
public class JWTTokenComponent {

    private static String HEADER;
    private static String SECRET;
    private static String PREFIX;

    @Autowired JWTTokenComponent(@Value(value = "${jwt.token.header}") String header, @Value(value = "${jwt.token.secret}") String secret, @Value(value = "${jwt.token.prefix}") String prefix){
        HEADER = header;
        SECRET = secret;
        PREFIX = prefix;
    }

    private static Date getDate(){
        Date dt = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(dt);
        cl.add(Calendar.DATE, 7);
        dt = cl.getTime();
        return dt;
    }


    public static Claims retrieveClaims(String jwtToken) throws ExpiredJwtException, MalformedJwtException, UnsupportedJwtException {
        return getAllClaimsFromToken(jwtToken);
    }

    private static Claims getAllClaimsFromToken(String jwtToken) throws ExpiredJwtException, MalformedJwtException, UnsupportedJwtException{
        return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
    }

    public static void generateToken(UploadForm uploadForm, List<TransformedFile> transformedFile) {
        String token = Jwts.builder().setId("softtekJWT")
                .claim("content", uploadForm.getContent())
                .claim("filename", uploadForm.getFilename())
                .claim("ext", uploadForm.getExt())
                .claim("type", uploadForm.getType())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(getDate())
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
        transformedFile.add(new TransformedFile("jwt", token));
    }
}
