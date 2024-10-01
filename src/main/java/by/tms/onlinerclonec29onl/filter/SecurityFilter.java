package by.tms.onlinerclonec29onl.filter;


import by.tms.onlinerclonec29onl.model.Account;
import by.tms.onlinerclonec29onl.model.Cart;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebFilter(servletNames = "dispatcher")
public class SecurityFilter extends HttpFilter {
//TODO: заменить стандартную страницу Tomcat с ошибкой 404 на свою
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        final String url = req.getRequestURL().toString();
        Account account = (Account) req.getSession().getAttribute("account");
        if (req.getSession().getAttribute("cart") == null) {
            Cart cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        if (account == null && (url.contains("/profile") ||
                url.contains("/shop") ||
                url.contains("/logout") ||
                url.contains("/admin"))) {
            res.sendRedirect("/error");
            return;
        }
        if (account != null) {
            if (url.contains("/registration") ||
                    url.contains("/login")) {
                res.sendRedirect("/error");
                return;
            }
            if (account.getRole() == Account.Role.ADMIN) {
                chain.doFilter(req, res);
                return;
            }
            if (account.getRole() == Account.Role.USER) {
                if(url.contains("/admin")) {
                    res.sendRedirect("/error");
                    return;
                }
                if (account.getType() != Account.Type.BUSINESS && url.contains("/shop")) {
                    res.sendRedirect("/error");
                    return;
                }
            }
        }
        chain.doFilter(req, res);
    }
}
