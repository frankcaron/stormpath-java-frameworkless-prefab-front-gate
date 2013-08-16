package StormpathShiroJavaApp.Controllers.Registration;

/**
 * Created with IntelliJ IDEA.
 * User: frankcaron
 * Date: 8/13/13
 * Time: 3:04 PM
 * To change this template use File | Settings | File Templates.
 *
 * LoginProcessorServlet
 *
 * This Servlet handles the form post and utilizes our authentication and helper classes to process auth.
 */

import StormpathShiroJavaApp.Controllers.Login.LoginProcessor;
import com.stormpath.sdk.account.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegProcessorServlet extends HttpServlet {

    private RegProcessor register = new RegProcessor();

    public void doPost (HttpServletRequest req,
                       HttpServletResponse res)
            throws ServletException, IOException
    {

    }
}