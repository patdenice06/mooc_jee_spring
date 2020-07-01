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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
// TODO : enable JPA Repositories and scan correct package ...
@EnableJpaRepositories("dao")
@ComponentScan({"web.ctrl"})
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
	
	// TODO : This bean should be name 'entityManagerFactory'
	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean emf() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setPersistenceUnitName("myApp");
		return emf;
	}
	
	// TODO : This bean should be name 'transactionManager'
	@Bean(name="transactionManager")
	public PlatformTransactionManager txManager( EntityManagerFactory emf ) {
		JpaTransactionManager txManager = new JpaTransactionManager(emf);
		txManager.setPersistenceUnitName("myApp");
		return txManager;
	}
	
	@Override
	public void onApplicationEvent(ApplicationContextEvent event) {
		log.info( "Receive application event : " + event.getClass().getSimpleName() );
		if ( !(event instanceof ContextClosedEvent) ) return;
		
		log.info("Stop EntityManagerFactory.");
		EntityManagerFactory emf = event.getApplicationContext().getBean(EntityManagerFactory.class);
		emf.close();
		
		log.info("Unregister H2 JDBC Driver.");
		try {
			Driver driver = DriverManager.getDriver("jdbc:h2:/media/patrick/DATA/edu/j2e-spring/wokspace/week5/sc16/openFlightsDB");
			DriverManager.deregisterDriver(driver);
		} catch( SQLException e ) {
			log.info("Unable to unregister driver.", e);
		}
	}
}
