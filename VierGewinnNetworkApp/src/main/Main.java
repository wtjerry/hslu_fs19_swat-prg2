package main;

import Controller.Controller;
import Views.Interfaces.ViewHandler;
import Views.ViewHandlerImpl;

public class Main {

	public static void main(String[] args) {
		ViewHandler viewHandler = new ViewHandlerImpl();
		Controller controller = new Controller(viewHandler);
		controller.init();
	}
}
