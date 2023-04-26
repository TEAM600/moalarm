package service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.team600.moalarm.MoalarmApplication;
import com.team600.moalarm.apikey.dto.response.MoalarmKeyResponse;
import com.team600.moalarm.apikey.service.ApiKeyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MoalarmApplication.class)
public class ApiKeyServiceTest {

    @Autowired
    private ApiKeyService<MoalarmKeyResponse> moalarmKeyService;

    @Test
    public void testUpdateApiKey() {
        long memberId = 1L;
        MoalarmKeyResponse moalarmKeyResponse = moalarmKeyService.refreshApiKey(memberId);
        assertNotNull(moalarmKeyResponse);
        assertNotNull(moalarmKeyResponse.getMoalarmKey());
    }
}