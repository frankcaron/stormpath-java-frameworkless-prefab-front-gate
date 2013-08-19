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

import StormpathShiroJavaApp.Controllers.APICommunicator.APICommunicator;
import com.stormpath.sdk.account.*;
import com.stormpath.sdk.directory.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegProcessorServlet extends HttpServlet {

    private APICommunicator regHelper = new APICommunicator();

    public void doPost (HttpServletRequest req,
                        HttpServletResponse res)
            throws ServletException, IOException
    {
        //Make registration request
        boolean accountCreated = this.regHelper.createAccount(req.getParameterMap());

        //Pass along result
        if (accountCreated) {
            //Redirect to reg page and note success
            String site = "/register.jsp?registration=true";
            res.setStatus(res.SC_ACCEPTED);
            res.sendRedirect(site);
        } else {
            //Redirect to reg page and note error
            String site = "/register.jsp?registration=false";
            res.setStatus(res.SC_BAD_REQUEST);
            res.sendRedirect(site);
        }
    }
}