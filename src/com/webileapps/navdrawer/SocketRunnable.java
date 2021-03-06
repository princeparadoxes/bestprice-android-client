package com.webileapps.navdrawer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

class SocketRunnable implements Runnable {
  String dstAddress;
  int dstPort;
  String dstMessage;
  String response = "";
  Socket socket = null;
  InputStream inputStream;
  OutputStream outputStream;
  PrintStream printStream;
  
  SocketRunnable(String addr, int port,String message){
  dstAddress = addr;
  dstPort = port;
  dstMessage = message;
  }
  public void run() {
	  try {
		  socket = new Socket(dstAddress, dstPort);
		  ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
		  byte[] buffer = new byte[1024];
		  int bytesRead;
		  try {
			  inputStream = socket.getInputStream();
			  outputStream=socket.getOutputStream();
			  printStream = new PrintStream(outputStream);
			  printStream.print(dstMessage);  
			  while (((bytesRead = inputStream.read(buffer)) != -1))
			  {  
				  byteArrayOutputStream.write(buffer, 0, bytesRead);
				  response += byteArrayOutputStream.toString("UTF-8");
				  String[] s = response.split("\n");
				  for(int i=0;i<s.length;i++)
				  {
					  MainActivity.InpusStrings.add(s[i]);	  
				  }
			  }
		  }catch (IOException e) {
			  e.printStackTrace();
		  }
		  try {
			  socket.close();
		  } catch (IOException e) {
			  e.printStackTrace();
		  } 
		  } catch (UnknownHostException e) {
			  e.printStackTrace();
		  } catch (IOException e) {
			  e.printStackTrace();
		  }
  }
}   
