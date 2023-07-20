package com.example;

import javax.security.auth.login.FailedLoginException;
import java.util.List;

import org.apache.openejb.core.security.jaas.LoginProvider;

public class LogMeInProvider implements LoginProvider {

    @Override
    public List<String> authenticate(String user, String password) throws FailedLoginException {
            return List.of();
    }
}