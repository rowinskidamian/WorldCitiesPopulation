package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/login")
public class LoginFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpSession session = ((HttpServletRequest)req).getSession();
        Object username = session.getAttribute("username");

        if (username != null){
            chain.doFilter(req, resp);
        } else {
            req.getServletContext().getRequestDispatcher("/login").forward(req,resp);
        }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
