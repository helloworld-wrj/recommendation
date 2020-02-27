package or.wr.bookrecommendationsystem.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class OrdinaryUserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        //这里的User是登陆时放入session的
        Object user = session.getAttribute("user");
        //如果session中没有user，表示没登陆
        if (user == null) {
            //设置未登录则跳转到login页面
            StringBuffer url = request.getRequestURL();
            String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length())
                    .append(request.getServletContext().getContextPath()).append("/login").toString();
            //System.out.println(tempContextUrl);
            response.sendRedirect(tempContextUrl);
//            System.out.println("preUrl: /user--- ordinary User " + "被拦截" + " because user is null");
            return false;
        }
        return true;

    }
}
