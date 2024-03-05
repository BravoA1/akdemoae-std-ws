package af.cmr.indyli.akdemia.ws.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import af.cmr.indyli.akdemia.ws.security.SecurityConfig;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Import({SecurityConfig.class})
@ComponentScan({"af.cmr.indyli.akdemia.business.*","af.cmr.indyli.akdemia.ws.*"})
public class AkdemiaStdWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkdemiaStdWsApplication.class, args);
	}

}
