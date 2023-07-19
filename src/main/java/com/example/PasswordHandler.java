package com.example;

import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;

/**
 * @author chs
 */
public class PasswordHandler implements CallbackHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordHandler.class);

    @Override
    public void handle(Callback[] callbacks) {
        try {
            for (Callback callback : callbacks) {
                WSPasswordCallback wsPasswordCallback = (WSPasswordCallback) callback;
                wsPasswordCallback.setPassword("huhu");
            }
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            throw e;
        }
    }
}
