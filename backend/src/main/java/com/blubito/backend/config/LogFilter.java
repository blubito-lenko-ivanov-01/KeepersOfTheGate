package com.blubito.backend.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class LogFilter extends OncePerRequestFilter {
    private static final String REQUEST_ID = "request-id";
    public static final String TRACKING_ID = "request.tracking.id";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws
            ServletException, IOException {
        final long start = System.currentTimeMillis();

        String headerId = request.getHeader(REQUEST_ID);
        String trackingId = headerId != null ? headerId : UUID.randomUUID().toString();

        MDC.put(TRACKING_ID, trackingId);

        StringBuilder sb = new StringBuilder();
        sb.append("- request params: ");

        request.getParameterMap().forEach((key, value) -> sb.append("[")
                .append(key)
                .append("=")
                .append(value[0])
                .append("] "));

        if (shouldLog(request)) {
            log.debug("Processing {} '{}' request {}", request.getMethod(), request.getRequestURI(), sb.length() > 19 ? sb.toString().trim() : "");
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            long end = System.currentTimeMillis();

            if (shouldLog(request)) {
                log.debug("Processing {} '{}' response - status: [{}], content-type: [{}], process time: ({}ms)", request.getMethod(),
                        request.getRequestURI(),
                        response.getStatus(),
                        response.getContentType(),
                        end - start);
            }

            MDC.remove(TRACKING_ID);
        }
    }

    private boolean shouldLog(final HttpServletRequest request) {
        return !request.getRequestURI().contains("actuator");
    }
}
