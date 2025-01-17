package cc.realtec.real.xxljob.controller.interceptor;

import cc.realtec.real.xxljob.core.util.FtlUtil;
import cc.realtec.real.xxljob.core.util.I18nUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Component
public class CookieInterceptor implements AsyncHandlerInterceptor {
    @Override
    public void postHandle(@NonNull HttpServletRequest request,
                           @NonNull
                           HttpServletResponse response,
                           @NonNull
                           Object handler,
                           ModelAndView modelAndView) throws Exception {

        // cookie
        if (modelAndView != null && request.getCookies() != null && request.getCookies().length > 0) {
            HashMap<String, Cookie> cookieMap = new HashMap<>();
            for (Cookie ck : request.getCookies()) {
                cookieMap.put(ck.getName(), ck);
            }
            modelAndView.addObject("cookieMap", cookieMap);
        }

        // static method
        if (modelAndView != null) {
            modelAndView.addObject("I18nUtil", FtlUtil.generateStaticModel(I18nUtil.class.getName()));
        }
    }
}
