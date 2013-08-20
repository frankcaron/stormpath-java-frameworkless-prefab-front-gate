package StormpathShiroJavaApp.Controllers.APICommunicator;

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.authc.AuthenticationRequest;
import com.stormpath.sdk.authc.AuthenticationResult;
import com.stormpath.sdk.authc.UsernamePasswordRequest;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.client.ClientBuilder;
import com.stormpath.sdk.ds.DataStore;
import com.stormpath.sdk.directory.*;
import com.stormpath.sdk.resource.ResourceException;

import javax.servlet.http.HttpSession;
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

    //Helper function to authenticate an account
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

    //Helper function to create an account
    public String createAccount( Map params) {

        //Target the properties file on my local and connect to Stormpath
        String userHome = System.getProperty("user.home");
        String path = userHome + this.apiKey;
        Client client = new ClientBuilder().setApiKeyFileLocation(path).build();
        DataStore dataStore = client.getDataStore();
        Directory directory = client.getDataStore().getResource(this.directoryURL, Directory.class);

        //Compose account object
        Account account = client.getDataStore().instantiate(Account.class);
        account.setGivenName(((String [])params.get("firstName"))[0]);
        account.setSurname(((String [])params.get("lastName"))[0]);
        account.setUsername(((String [])params.get("username"))[0]);
        account.setEmail(((String [])params.get("email"))[0]);
        account.setPassword(((String [])params.get("credential"))[0]);

        //Eddit account
        try {
            System.out.println("Creating account");
            directory.createAccount(account);
            return "true";
        } catch (ResourceException name) {
            return name.getMessage();
        }

    }

    public Account editAccount(String href, Map params) {

        //Target the properties file on my local and connect to Stormpath
        String userHome = System.getProperty("user.home");
        String path = userHome + this.apiKey;
        Client client = new ClientBuilder().setApiKeyFileLocation(path).build();
        Account account = client.getDataStore().getResource(href, Account.class);

        //Update the account object
        account.setGivenName(((String [])params.get("firstName"))[0]);
        account.setSurname(((String [])params.get("lastName"))[0]);
        account.setUsername(((String [])params.get("username"))[0]);
        account.setEmail(((String [])params.get("email"))[0]);

        //Register account
        try {
            System.out.println("Saving account");
            account.save();
            return account;
        } catch (ResourceException name) {
            System.out.println("Error saving edits: " + name.getDeveloperMessage());
            return null;
        }
    }
}
