// package com.homeworkoverflow.homeworkoverflowbackend.shared;

// import java.io.IOException;

// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// @Component
// public class Filter extends OncePerRequestFilter {
    
//     @Override
//     protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//         httpServletResponse.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//         httpServletResponse.addHeader("Access-Control-Allow-Methods", "POST, GET");
//         httpServletResponse.addHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With, Access-Control-Allow-Origin, Access-Control-Allow-Methods");

//         filterChain.doFilter(httpServletRequest, httpServletResponse);
//     }
// }
