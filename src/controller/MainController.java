package controller;

import view.LoginView;
import view.MainView;
import view.RegisView;

public class MainController {

	private RegisView regisView;
	private LoginView loginView;
	private MainView mainView;
	
	public MainController(MainView mainView) {
		this.mainView = mainView;
	}
	
	public MainController(RegisView regisView) {
		this.regisView = regisView;
	}
	
	public MainController(LoginView loginView) {
		this.loginView = loginView;
	}
}
