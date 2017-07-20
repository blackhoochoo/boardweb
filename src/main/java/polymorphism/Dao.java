package polymorphism;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Dao {
	
	
	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml"); 
		DBMS dbms = (DBMS) factory.getBean("dbms");
		dbms.start();
		dbms.connect();
		dbms.close();
		dbms.shutdown();
		factory.close();
		
	}
}
