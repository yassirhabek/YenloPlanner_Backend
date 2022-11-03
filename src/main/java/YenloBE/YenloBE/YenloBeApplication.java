package YenloBE.YenloBE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"YenloBE.YenloBE.Service", "YenloBE.YenloBE.Controller"})
public class YenloBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(YenloBeApplication.class, args);
	}

}
