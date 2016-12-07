package main;

import Controller.Controller;
import Views.ViewHandler;

public class Main {

	public static void main(String[] args) {
		ViewHandler viewHandler = new ViewHandler();
		Controller controller = new Controller(viewHandler);
		controller.init();
	}
}
