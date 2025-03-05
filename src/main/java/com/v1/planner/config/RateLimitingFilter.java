package com.v1.planner.config;
import io.github.bucket4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimitingFilter extends OncePerRequestFilter {
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String key = request.getHeader("Authorization"); // Rate-limit by JWT Token
        if (key == null || key.isEmpty()) {
            key = request.getRemoteAddr(); // Fallback to IP-based limiting
        }

        buckets.computeIfAbsent(key, k -> Bucket4j.builder()
                .addLimit(Bandwidth.classic(5, Refill.greedy(5, Duration.ofMinutes(1)))) // 5 requests per min
                .build());

        Bucket bucket = buckets.get(key);
        if (bucket.tryConsume(1)) {
            chain.doFilter(request, response); // Allow request
        } else {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Too many requests. Try again later.");
        }
    }
}
