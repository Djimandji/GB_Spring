package ru.geekbrains;

import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter wr = resp.getWriter();
        if (req.getParameter("id") != null) {
            Product pr = productRepository.findById(Long.parseLong(req.getParameter("id")));
            wr.println("<th>Id</th>");
            wr.println(req.getParameter("id"));
            wr.println("<th>Name</th>");
            wr.println("<th>" + pr.getName() + "</th>");

        } else if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            wr.println("<table>");
            wr.println("<tr>");
            wr.println("<th>Id</th>");
            wr.println("<th>Name</th>");
            wr.println("<th>Path</th>");
            wr.println("</tr>");

            for (Product product : productRepository.findAll()) {

                wr.println("<tr>");
                wr.println("<td>" + product.getId() + "</td>");
                wr.println("<td>" + product.getName() + "</td>");
                wr.println("<td>" + "<a href>"  + req.getContextPath() + "/product?id=" + product.getId() + "</a>" + "</td>");
                wr.println("</tr>");
            }
            wr.println("</table>");
        }
    }
}
