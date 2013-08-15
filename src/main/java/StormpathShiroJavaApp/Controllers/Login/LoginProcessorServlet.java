package StormpathShiroJavaApp.Controllers.Login;

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

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import com.stormpath.sdk.client.*;
import com.stormpath.sdk.tenant.Tenant;
import com.stormpath.sdk.application.*;
import com.stormpath.sdk.directory.*;
import com.stormpath.sdk.account.*;

public class LoginProcessorServlet extends HttpServlet {
    public void doPost (HttpServletRequest req,
                       HttpServletResponse res)
            throws ServletException, IOException
    {


        //Process
        boolean authSuccess = false;
        LoginProcessor logginer = new LoginProcessor();
        authSuccess = logginer.processLogin(req.getParameter("username"), req.getParameter("credential"));

        //Target the properties file on my local and connect to STormpath
        String path = System.getProperty("user.home") + "/.stormpath/apiKey.properties";
        Client client = new ClientBuilder().setApiKeyFileLocation(path).build();

        Tenant tenant = client.getCurrentTenant();
        ApplicationList applications = tenant.getApplications();
        DirectoryList directories = tenant.getDirectories();
        Directory myDirectory = null;

        //Find the appropriate directory
        for (Directory tempDirectory : directories) {
            if (tempDirectory.getName().equals("JavaSampleAppusers")) {
                myDirectory = tempDirectory;
            }
        }

        AccountList users = myDirectory.getAccounts();

        //Test
        PrintWriter out = res.getWriter();
        //out.println(req.getParameter("username"));
        //out.println(path);
        //out.println(authSuccess)

        out.println("Directory: " + myDirectory.getName());
        for (Account user : users) {
            out.println("Username: " + user.getUsername());
        }

        //Take resulting action
        if (authSuccess) {
            //Redirect to site page
            String site = "/main.jsp" ;
            res.setStatus(res.SC_ACCEPTED);
            //res.sendRedirect(site);
        }  else {
            //Redirect back to log in page and note the error
            String site = "/index.jsp?loggedin=false" ;
            res.setStatus(res.SC_UNAUTHORIZED);
            //res.sendRedirect(site);
        }
    }
}