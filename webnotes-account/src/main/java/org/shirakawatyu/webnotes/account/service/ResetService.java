package org.shirakawatyu.webnotes.account.service;

import org.shirakawatyu.webnotes.common.R;

public interface ResetService {
    R resetPassword(String email, String password);
}
