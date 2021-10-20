package person.birch.ftpclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication(scanBasePackages = {"person.birch.ftpclient"})
public class FtpClientApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(FtpClientApplication.class, args);
        var client = context.getBean(FtpClient.class);

        try {
            client.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
