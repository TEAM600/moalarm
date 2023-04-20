package service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.team600.moalarm.MoalarmApplication;
import com.team600.moalarm.apikey.dto.response.ApiKeyDto;
import com.team600.moalarm.apikey.service.ApiKeyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MoalarmApplication.class)
public class ApiKeyServiceTest {

    @Autowired
    private ApiKeyService apiKeyService;

    @Test
    public void testUpdateApiKey() {
        String memberId = null;
        ApiKeyDto apiKeyDto = apiKeyService.refreshApiKey(memberId);
        assertNotNull(apiKeyDto);
        assertNotNull(apiKeyDto.getApiKey());
    }
}