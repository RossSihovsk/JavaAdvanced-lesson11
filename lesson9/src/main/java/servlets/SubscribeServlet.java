package servlets;

import dao.DAOException;
import doMain.Subscribe;
import org.apache.log4j.Logger;
import service.ISubscribeService;
import service.impl.SubscribeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/subscribe")
public class SubscribeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Logger log = Logger.getLogger(SubscribeServlet.class);

    private ISubscribeService subscribeService = SubscribeServiceImpl.getSubscribeService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("Getting fields values...");
        HttpSession session = request.getSession();
        log.trace("session.");
        Integer userId = (Integer) session.getAttribute("userID");
        log.trace("usid.  " + userId);
        String magazineID = request.getParameter("magazineID");
        log.trace("magid  " + magazineID);
        Subscribe subscribe = new Subscribe(userId, Integer.parseInt(magazineID), true, LocalDate.now(), 10);
        log.trace("try.");
        try {
            log.trace("Saving subscribe in database...");
            subscribeService.insert(subscribe);
        } catch (Exception e) {
            log.error("Creating subscribe failed!", e);
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Success");
    }



}
