package person.birch.ftpclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FtpConfig {
    @Value("${ftp.client.server}")
    private String server;
    @Value("${ftp.client.port}")
    private Integer port;
    @Value("${ftp.client.user}")
    private String user;
    @Value("${ftp.client.password}")
    private String password;

    @Bean
    public FtpClient getFtpClient() {
        return new FtpClient(server, port, user, password);
    }
}
