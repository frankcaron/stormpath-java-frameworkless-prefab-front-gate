package StormpathShiroJavaApp.Controllers.APICommunicator;

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.authc.AuthenticationRequest;
import com.stormpath.sdk.authc.AuthenticationResult;
import com.stormpath.sdk.authc.UsernamePasswordRequest;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.client.ClientBuilder;
import com.stormpath.sdk.ds.DataStore;
import com.stormpath.sdk.resource.ResourceException;
import com.stormpath.sdk.directory.Directory;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: frankcaron
 * Date: 8/13/13
 * Time: 2:08 PM
 *
 * APICommunicator
 *
 * This helper class validates form input and interfaces with the Shiro/Stormpath impl.
 *
 */

public class APICommunicator {

    private String apiKey;
    private String applicationURL;
    private String directoryURL;

    public APICommunicator() {
         /*
        //Load up properties to connect
        InputStream is = getServletContext().getResourceAsStream("application.properties");
        Properties p  = new Properties();
        try {
            p.load(is);
            this.apiKey = p.getProperty("apiKey");
            this.applicationURL = p.getProperty("applicationURL");
            System.out.println("Api Key: " + this.apiKey);
            is.close();
        } catch (Exception e) {
            System.out.println("IOError: " + e);
        }
        */

        this.applicationURL = "https://api.stormpath.com/v1/applications/5gFCU2FrexxBG4ZVNxXHVX";
        this.apiKey = "/.stormpath/apiKey.properties";
        this.directoryURL = "https://api.stormpath.com/v1/directories/5gGJeswnJXUWsRJfJOOd0p";

    }

    public Account processLogin(String username, String password) {

        //Target the properties file on my local and connect to Stormpath
        String userHome = System.getProperty("user.home");
        String path = userHome + this.apiKey;
        Client client = new ClientBuilder().setApiKeyFileLocation(path).build();
        DataStore dataStore = client.getDataStore();
        Application application = dataStore.getResource(this.applicationURL, Application.class);

        //Request authentication
        try {
            AuthenticationRequest request = new UsernamePasswordRequest(username,password);
            AuthenticationResult result = application.authenticateAccount(request);
            Account myAccount = result.getAccount();

            //Return retrieved account
            return result.getAccount();
        //Catch and report errors
        } catch (ResourceException name) {
            System.out.println("Auth error: " + name.getDeveloperMessage());
            return null;
        }

    }

    public boolean createAccount(Map params) {

        //Target the properties file on my local and connect to Stormpath
        String userHome = System.getProperty("user.home");
        String path = userHome + this.apiKey;
        Client client = new ClientBuilder().setApiKeyFileLocation(path).build();
        DataStore dataStore = client.getDataStore();
        Directory directory = client.getDataStore().getResource(this.directoryURL, Directory.class);

        //Request authentication
        try {
            System.out.println("Email: " + params.get("email"));

            Account account = client.getDataStore().instantiate(Account.class);
            account.setGivenName(params.get("firstName").toString());
            account.setSurname(params.get("lastName").toString());
            account.setUsername(params.get("username").toString());
            account.setEmail(params.get("email").toString());
            account.setPassword(params.get("credential").toString());
            directory.createAccount(account);
            return true;
        } catch (ResourceException name) {
            System.out.println("Reg error: " + name.getDeveloperMessage());
            return false;
        }

    }
}
