package YenloBE.YenloBE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages={"YenloBE.YenloBE.Service", "YenloBE.YenloBE.Controller", "YenloBE.YenloBE.Security"})
public class YenloBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(YenloBeApplication.class, args);
	}

}
