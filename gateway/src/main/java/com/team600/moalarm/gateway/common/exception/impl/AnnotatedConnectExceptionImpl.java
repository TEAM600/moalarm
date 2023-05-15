package com.team600.moalarm.gateway.common.exception.impl;

import org.bouncycastle.jce.provider.AnnotatedException;

public class AnnotatedConnectExceptionImpl extends AnnotatedException {

    public AnnotatedConnectExceptionImpl(String string) {
        super(string);
    }
}
