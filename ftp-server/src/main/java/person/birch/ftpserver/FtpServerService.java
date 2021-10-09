package person.birch.ftpserver;

import lombok.extern.slf4j.Slf4j;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.ClearTextPasswordEncryptor;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
@Slf4j
public class FtpServerService {
    private final FtpServerFactory serverFactory = new FtpServerFactory();
    private final ListenerFactory factory = new ListenerFactory();
    private final FtpServer server = serverFactory.createServer();

    public FtpServerService() {
        init();
    }

    private void init() {
        var userManagerFactory = new PropertiesUserManagerFactory();
        userManagerFactory.setFile(new File("/Users/oleksandrberezkin/Documents/javaprojects/pet/pet-ftp/ftp-server/src/main/resources/users.properties"));
        userManagerFactory.setPasswordEncryptor(new ClearTextPasswordEncryptor());
        managerUser();
    }

    private void managerUser() {
        var manager = serverFactory.getUserManager();
        var user = getUser();
        try {
            manager.save(user);
            log.info("user created");
            var users = manager.getAllUserNames();
            for (var usr : users) {
                log.info(usr);
            }
        } catch (FtpException e) {
            log.error("failed to create a user", e);
            e.printStackTrace();
        }
    }

    private BaseUser getUser() {
        var user = new BaseUser();
        user.setName("test");
        user.setPassword("test");
        user.setHomeDirectory("/Users/oleksandrberezkin/Documents/javaprojects/pet/pet-ftp/ftp-server/src/main/resources");
        user.setAuthorities(List.of(new WritePermission()));
        user.setEnabled(true);
        return user;
    }

    public void startServer() throws FtpException {
        // set the port of the listener
        factory.setPort(2221);

        // replace the default listener
        serverFactory.addListener("default", factory.createListener());

        // start the server
        server.start();
    }

    public void stopServer() {
        if (!server.isStopped()) {
            server.stop();
        }
    }
}
