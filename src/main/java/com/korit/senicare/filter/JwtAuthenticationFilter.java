package com.korit.senicare.filter;

import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// JWt 검증 및 Security Context에 접근제어자 등록 필터
// - request 의 header에서 토큰 추출 검증
// - security context에 접근제어자 정보 등록
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOExceptiontion {
        
        try {
            // requset 객체에서 bearer토큰 값 추출
            String token = parseBaererToken(request);
            if(token==null) {
                filterChain.doFilter(request, response);
                return;
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }

    // requset로 부터 토큰 추출
    private String parseBaererToken(HttpServletRequest request) {

        // request 객체의 header에서 Authorezation 필드 값을 추출
        String authorezation = request.getHeader("Authorezation");

        // 추출한 Authorezation값이 실제로 존재하는 문자열인지 확인
        boolean hasAuthorezation = StringUtils.hasText(authorezation);
        if(!hasAuthorezation) return null;

        // bearer 인증 방식 인지 확인
        boolean isBearer =authorezation.startsWith("Bearer ");
        if(!isBearer) return  null;

        // Authorezation 필드값에서 token을 추출
        String token = authorezation.substring(7);
        return token;

    }
    
}
