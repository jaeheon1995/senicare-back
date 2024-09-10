package com.korit.senicare.filter;

import java.io.IOException;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.korit.senicare.provider.JwtProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

// JWt 검증 및 Security Context에 접근제어자 등록 필터
// - request 의 header에서 토큰 추출 검증
// - security context에 접근제어자 정보 등록
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        try {
            // requset 객체에서 bearer토큰 값 추출
            String token = parseBaererToken(request);
            if(token==null) {
                filterChain.doFilter(request, response);
                return;
            }
            // 토큰검증
            String userId = jwtProvider.validate(token);
            if(userId ==null){
                filterChain.doFilter(request, response);
                return;
            }

            // security constext 에 등록
        setContext(request, userId);

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

    // security context 생성 및 등록
    private void setContext(HttpServletRequest request,String userId){
        // 접근주체에 대한 인증토큰생성
        AbstractAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(userId,null,AuthorityUtils.NO_AUTHORITIES);
        
        // 생성한 인증 토큰이 어떤 요청에 대한 내용인지 상세정보 추가
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // 빈 security context생성
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        // 생성한 빈 security context에 authenticationToken 주입
        securityContext.setAuthentication(authenticationToken);
        // 생성한 security context등록
        SecurityContextHolder.setContext(securityContext);
    }
    
}
