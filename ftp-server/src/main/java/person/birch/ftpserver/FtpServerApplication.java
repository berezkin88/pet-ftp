package person.birch.ftpserver;

import lombok.extern.slf4j.Slf4j;
import org.apache.ftpserver.ftplet.FtpException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
@Slf4j
public class FtpServerApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(FtpServerApplication.class, args);
        var server = context.getBean(FtpServerService.class);

        try {
            server.startServer();
        } catch (FtpException e) {
            e.printStackTrace();
        }

        var scanner = new Scanner(System.in);
        scanner.nextLine();

        log.info("stop server");
        server.stopServer();
        scanner.close();
    }
}
