package web.config;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan({"web.ctrl","dao"})
public class AppConfig 
implements ApplicationListener<ApplicationContextEvent> 
{
	
	private static Logger log = LoggerFactory.getLogger(AppConfig.class);
	
	@Bean
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver res = new InternalResourceViewResolver();
		res.setPrefix("/WEB-INF/views/");
		res.setSuffix(".jsp");
		return res;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean emf() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setPersistenceUnitName("myApp");
		return emf;
	}
	
	// TODO : add a transaction manager in the context
	
	@Override
	public void onApplicationEvent(ApplicationContextEvent event) {
		log.info( "Receive application event : " + event.getClass().getSimpleName() );
		if ( !(event instanceof ContextClosedEvent) ) return;
		
		log.info("Stop EntityManagerFactory.");
		EntityManagerFactory emf = event.getApplicationContext().getBean(EntityManagerFactory.class);
		emf.close();
		
		log.info("Unregister H2 JDBC Driver.");
		try {
			Driver driver = DriverManager.getDriver("jdbc:h2:xxx");
			DriverManager.deregisterDriver(driver);
		} catch( SQLException e ) {
			log.info("Unable to unregister driver.", e);
		}
	}
}
