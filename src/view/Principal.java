package view;
import controller.ProcController;

public class Principal {

	public static void main(String[] args) {
		ProcController pCont = new ProcController();
//		pCont.os();
//	String command = "TASKLIST /FO TABLE";
//	pCont.callProcess(command);
	
//	String command = "PING -t10 www.google.com.br";
	
	
//	pCont.readProcess("tracert fateczl.edu.br");
//	pCont.readProcess(command);
		
	String param = "notepad.exe";
	pCont.killProcess(param);
	
	}

}
