package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcController {
	
	public ProcController() {
		super();
	}

	public void os() {
		//System.getProperty retorna como string uma propriedade do sistema
		String osName = System.getProperty("os.name"); //retorna nome do sistema
		String osVersion = System.getProperty("os.version"); //retorna versão do sistema
		String osArch = System.getProperty("os.arch"); //retorna arquitetura
		System.out.println("SO: " +osName+" - Version: " +osVersion +" Arquitetura: "+osArch);
		
	}
	/*
	Try...catch
	Try{
	...
	}catch(Exception e){
	...
	}
	Tenta fazer algo, caso ocorra uma exceção ele tratará dentro do catch
	o Exception é generalista
	 */
	public void callProcess(String command) {
		try {
			Runtime.getRuntime().exec(command);
//			ProcessBuilder pb = new Processbuilder();
			
		} catch (IOException e) { //o IOException pega exceções de arquivo
			// TODO Auto-generated catch block
			//O comando abaixo imprime o erro, não tem tratamento
//			e.printStackTrace = imprime totalmente o erro
//			e.getMessage() imprime a primeira linha de erro
			
			//contains() procura pela string que você enviar
			if(e.getMessage().contains("740")) {
				//concatena strings mais rapidamente, utilizando o mesmo espaço de memoria
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd /c");
				buffer.append(" ");
				buffer.append(command);
				//.append adiciona coisas ao buffer
				try {
					Runtime.getRuntime().exec(buffer.toString());
					//.toString transforma o tipo de buffer para String
				} catch (IOException e1) {//e1 pois a variavel e já está usada
					// TODO Auto-generated catch block
					System.err.println(e1.getMessage());
				}
			}else {
				System.err.println("Commando inválido");
			}
		}
	}

	public void readProcess(String command) {
		try {
			Process p;
			p = Runtime.getRuntime().exec(command);
			InputStream stream = p.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);
			String line = buffer.readLine();
			
			while (line !=null) {
				System.out.println(line);
				line = buffer.readLine();
				
			}
			buffer.close();
			reader.close();
			stream.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		
	}

	public void killProcess(String param) {
		String cmPid = "TASKKILL /PID";
		String cmNome = "TASKKILL /IM";
		int pid = 0;
		StringBuffer buffer = new StringBuffer();
		
		try {
			pid = Integer.parseInt(param);
			buffer.append(cmPid);
			buffer.append(" ");
			buffer.append(pid);
		}catch(NumberFormatException e) { //NumberFormatException gera um erro caso o formato seja invalido (ex: transformar A em um numero)
			buffer.append(cmNome);
			buffer.append(" ");
			buffer.append(param);
		}
		callProcess(buffer.toString());
	}
}
