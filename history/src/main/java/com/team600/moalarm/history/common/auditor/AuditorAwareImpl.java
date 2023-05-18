package com.team600.moalarm.history.common.auditor;

import com.team600.moalarm.history.util.HttpRequestUtil;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;

@Slf4j
public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        return Optional.of(HttpRequestUtil.getMemberId());
    }

}