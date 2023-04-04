package org.shirakawatyu.webnotes.account.service;

import org.shirakawatyu.webnotes.common.R;

public interface RegisterService {
    R register(String username, String password, String email);
}
