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
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        Account account = (Account) req.getSession().getAttribute("account");
        if (req.getSession().getAttribute("cart") == null) {
            Cart cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        if (account == null && (req.getRequestURL().toString().contains("/profile") ||
                req.getRequestURL().toString().contains("/shop"))) {
            res.sendRedirect("/");
            return;
        }
        if (account != null) {
            if (req.getRequestURL().toString().contains("/registration") ||
                    req.getRequestURL().toString().contains("/login") ||
                    req.getRequestURL().toString().contains("/logout")) {
                res.sendRedirect("/");
                return;
            }
            if (account.getType() != Account.Type.BUSINESS && req.getRequestURL().toString().contains("/shop")) {
                res.sendRedirect("/");
                return;
            }
        }
        chain.doFilter(req, res);
    }
}
