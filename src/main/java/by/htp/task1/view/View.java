package by.htp.task1.view;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.htp.task1.controller.Controller;
import by.htp.task1.resources.ResourceParameter;

public final class View {
	public static void main(String[] args){
		PropertyConfigurator.configure(ResourceParameter.LOG4J_CONFIGURATION_PATH);
	
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(ResourceParameter.APPLICATION_CONTEXT_PATH);
		Controller controller = context.getBean(Controller.class); 
		context.close();
		
		String response = null;
		
//		##Initialization DB connection##
		response = controller.executeAction("initialization_source ");
		PrintResponse.out(response);
	
		
//		##Sign up user
		response = controller.executeAction("sign_up Тузов_Андрей 12345678");
		PrintResponse.out(response);

		
// 		##Sign in user
		response = controller.executeAction("sign_in Тузов_Андрей 12345678");
		PrintResponse.out(response);

		response = controller.executeAction("sign_in Тузов_Андрей 12345679");
		PrintResponse.out(response);
		
// 		##Add new book
//		Example: add_new_book Title Genre Author Year Quantity
		response = controller.executeAction("add_new_book MyBook Action Pandora_Box 2017 1");
		PrintResponse.out(response);

		
// 		##Add edit book
//		Example: add_edit_book Title Genre Author Year Quantity idBook
		response = controller.executeAction("add_edit_book MyBook Action other 2017 2 1");
		PrintResponse.out(response);

		
//		##Get booklist
		response = controller.executeAction("get_booklist ");
		PrintResponse.out(response);

		
// 		##Remove book
//		Example: remove_book idBook
		response = controller.executeAction("remove_book 1");
		PrintResponse.out(response);
		
		
//		##Destroy DB connection##
		response = controller.executeAction("destroy_source ");
		PrintResponse.out(response);
	
	}
}
