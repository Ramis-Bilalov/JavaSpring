package ram.bilal.spring.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/task_servlet")
public class TaskServlet extends HttpServlet {

    private List<Product> list;
    private AtomicInteger requestCount;

    @Override
    public void init() throws ServletException {
        list = new ArrayList<>();
        requestCount = new AtomicInteger(0);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<link rel='stylesheet' href='" + req.getContextPath() + "/styles.css'>");

        resp.getWriter().println("<meta charset=\"UTF-8\" />");
        resp.getWriter().println("<h3>Форма для добавления</h3>");

        resp.getWriter().println("<form method='post'>");
        resp.getWriter().println("Название продукта <input type='text' name='line'>");
        resp.getWriter().println("<br>Цена <input type='number' name='cost'>");
        resp.getWriter().println("<input type='submit'>");
        resp.getWriter().println("</form>");

        String line = req.getParameter("line");
        String cost = req.getParameter("cost");
        list.add(new Product(requestCount.get(), line, cost));

        for(Product s : list) {
            if(s.getTitle() != null || s.getCost() != null) {
                resp.getWriter().println("<p>" + s + "</p>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestCount.incrementAndGet();
        doGet(req, resp);
    }
}
