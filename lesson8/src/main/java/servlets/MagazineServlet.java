package servlets;

import doMain.Magazine;
import org.apache.log4j.Logger;
import service.IMagazineService;
import service.impl.MagazineServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class MagazineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IMagazineService magazineService = MagazineServiceImpl.getMagazineService();

    private Logger log = Logger.getLogger(MagazineServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.trace("Getting fields values from Magazine creation Form...");
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String publishDate = request.getParameter("publishDate");
        String subscribePrice = request.getParameter("subscribePrice");

        try {
            log.trace("Saving magazine in database...");
            magazineService.insert(new Magazine(title, description, LocalDate.parse(publishDate), Integer.parseInt(subscribePrice)));
        } catch (Exception e) {
            log.error("Creating magazine failed!", e);
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Журнал \"" + title + "\" додано в базу данних!");
    }
}
