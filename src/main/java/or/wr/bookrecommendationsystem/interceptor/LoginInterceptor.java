package or.wr.bookrecommendationsystem.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)   {
    }

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

            //这个方法返回false表示忽略当前请求，如果一个用户调用了需要登陆才能使用的接口，如果他没有登陆这里会直接忽略掉
            //当然你可以利用response给用户返回一些提示信息，告诉他没登陆
            return false;
        } else if (user.toString().equals("test01") ||user.toString().equals("w1285528700@163.com")) {
            System.out.println("放行");
            return true;    //如果session里有user，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
        }
        else {
            System.out.println("被拦截");
            StringBuffer url = request.getRequestURL();
            String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length())
                    .append(request.getServletContext().getContextPath()).append("/interceptTest").toString();
            System.out.println(tempContextUrl);
            response.sendRedirect(tempContextUrl);
            return false;
        }

    }

}
