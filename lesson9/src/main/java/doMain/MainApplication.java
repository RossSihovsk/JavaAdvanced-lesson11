package doMain;

import dao.DAOException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import service.IMagazineService;
import service.IUserService;
import service.impl.MagazineServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainApplication {

    public static void main(String[] args) throws Exception {



        IMagazineService magazineService = MagazineServiceImpl.getMagazineService();


            List<Magazine> magazines = null;

            magazines = magazineService.readAll();


            for (Magazine m : magazines){
                System.out.println(m);
            }
       /* Logger log = Logger.getLogger(MainApplication.class);
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        log.trace("Starting application...");

        List<User> userList = new ArrayList<>();
        userList.add(new User("Діма", "Шевченко", "dima@gmail.com", "11111", "USER"));
        userList.add(new User("Олег", "Скуратко", "oleg@gmail.com", "12345", "USER"));


        IUserService userService = new UserServiceImpl();

        userList.forEach(user -> {
            try {
                System.out.println(userService.insert(user));
            } catch (Exception e) {
                log.error("Error occurred!", e);
                e.printStackTrace();
            }
        });
        userService.updateByID(new User(2, "Тест", "Тест", "test@gmail.com", "54321", "АDMIN"));
        System.out.println(userService.readByID(2));
        System.out.println(userService.readByEmail("dima@gmail.com"));
*/

    }
}
