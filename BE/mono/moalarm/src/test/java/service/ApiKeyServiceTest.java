package service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.team600.moalarm.MoalarmApplication;
import com.team600.moalarm.apikey.dto.response.MoalarmKeyResponse;
import com.team600.moalarm.apikey.service.MoalarmKeyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MoalarmApplication.class)
public class ApiKeyServiceTest {

    @Autowired
    private MoalarmKeyService moalarmKeyService;

    @Test
    public void testUpdateApiKey() {
        String memberId = null;
        MoalarmKeyResponse moalarmKeyResponse = moalarmKeyService.refreshApiKey(memberId);
        assertNotNull(moalarmKeyResponse);
        assertNotNull(moalarmKeyResponse.getMoalarmKey());
    }
}