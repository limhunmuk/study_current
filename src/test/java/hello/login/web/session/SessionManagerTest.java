package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class SessionManagerTest {

    SessionManager manager = new SessionManager();

    @Test
    public void sessionTest(){

        //세션 생성
        MockHttpServletResponse response = new MockHttpServletResponse();

        Member member = new Member();
        manager.createSession(member, response);

        // 요청에 응답 쿠키 저장
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        Object result = manager.getSession(request);
        Assertions.assertThat(result).isEqualTo(member);

        System.out.println("lhm test");

        // 세션 삭제
        manager.expire(request);
        Member resultMember = (Member) manager.getSession(request);
        Assertions.assertThat(resultMember).isNull();
    }

}