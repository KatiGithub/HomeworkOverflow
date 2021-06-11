package com.homeworkoverflow.homeworkoverflowbackend.utils;

public class JwtTokenExtractor {
    public static String ExtractFromHeader(String authorizationHeader) {
        String jwtToken = authorizationHeader.substring(authorizationHeader.indexOf(" ") + 1, authorizationHeader.length());

        return jwtToken;
    }
}