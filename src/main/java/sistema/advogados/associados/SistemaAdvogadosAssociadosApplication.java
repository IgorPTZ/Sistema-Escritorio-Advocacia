package sistema.advogados.associados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EntityScan(basePackages="sistema.advogados.associados.model")
@ComponentScan(basePackages= {"sistema.*"})
@EnableJpaRepositories(basePackages= {"sistema.advogados.associados.repository"})
@EnableTransactionManagement
@EnableWebMvc
public class SistemaAdvogadosAssociadosApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
				
		SpringApplication.run(SistemaAdvogadosAssociadosApplication.class, args);
		
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		//System.out.println(encoder.encode("teste321"));
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		registry.addViewController("/login").setViewName("/login");
		
		registry.setOrder(Ordered.LOWEST_PRECEDENCE);
	}
}
