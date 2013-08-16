package StormpathShiroJavaApp.Controllers.Login;

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.authc.AuthenticationRequest;
import com.stormpath.sdk.authc.AuthenticationResult;
import com.stormpath.sdk.authc.UsernamePasswordRequest;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.client.ClientBuilder;
import com.stormpath.sdk.ds.DataStore;
import com.stormpath.sdk.resource.ResourceException;

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
    public Account processLogin(String username, String password) {

        //Target the properties file on my local and connect to Stormpath
        String userHome = System.getProperty("user.home");
        String path = userHome + "/.stormpath/apiKey.properties";
        Client client = new ClientBuilder().setApiKeyFileLocation(path).build();
        DataStore dataStore = client.getDataStore();

        String href = "https://api.stormpath.com/v1/applications/23nsxyo3G2kY8FtFVC3aFH";
        Application application = dataStore.getResource(href, Application.class);

        //Request authentication
        try {
            AuthenticationRequest request = new UsernamePasswordRequest(username,password);
            AuthenticationResult result = application.authenticateAccount(request);

            //Return retrieved account
            return result.getAccount();
        //Catch and report errors
        } catch (ResourceException name) {
            System.out.println("Auth error: " + name.getDeveloperMessage());
            return null;
        }

    }
}
