package service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.team600.moalarm.MoalarmApplication;
import com.team600.moalarm.member.dto.response.ApiKeyDto;
import com.team600.moalarm.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MoalarmApplication.class)
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void testUpdateApiKey() {
        ApiKeyDto apiKeyDto = memberService.updateApiKey();
        assertNotNull(apiKeyDto);
        assertNotNull(apiKeyDto.getApiKey());
    }
}