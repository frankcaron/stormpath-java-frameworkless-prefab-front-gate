package StormpathShiroJavaApp.Controllers.Login;

/**
 * Created with IntelliJ IDEA.
 * User: frankcaron
 * Date: 8/13/13
 * Time: 2:08 PM
 *
 * LoginProcessor
 *
 * This helper class validates form input and interfaces with the Shiro/Stormpath impl.
 *
 */


public class LoginProcessor {
    public boolean processLogin(String username, String password) {
        if ((username != "" && password != "") && (username != null && password != null)) {
            return true;
        } else { return false; }
    }
}
