package org.shirakawatyu.webnotes.account.service;

import jakarta.servlet.http.HttpSession;
import org.shirakawatyu.webnotes.common.R;



public interface CodeService {
    R sendCode(HttpSession session, String mail);
}
