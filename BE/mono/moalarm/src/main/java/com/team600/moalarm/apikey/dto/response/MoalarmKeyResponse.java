package com.team600.moalarm.apikey.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class MoalarmKeyResponse extends KeyResponse {

    private String moalarmKey;
}
