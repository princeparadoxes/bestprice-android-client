package com.webileapps.navdrawer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;

public class SocketAsyncTask extends AsyncTask<Void, String, Void> {
	  
	  String dstAddress;
	  int dstPort;
	  String response = "";
	  Socket socket = null;
	  InputStream inputStream;
	  OutputStream outputStream;
	  static PrintStream printStream;
	  SocketAsyncTask(String addr, int port){
	   dstAddress = addr;
	   dstPort = port;
	  }
	  
	  static void sendmessage (String message)
	  {
		  printStream.print(message);  
	  }
	  
	  
	  @Override
	  protected Void doInBackground(Void... arg0) {
	   try {
	    socket = new Socket(dstAddress, dstPort);
	    
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
	    byte[] buffer = new byte[1024];
	    
	    int bytesRead;
	    inputStream = socket.getInputStream();
	    outputStream=socket.getOutputStream();
	    printStream = new PrintStream(outputStream);
	    sendmessage("quary:category");
       
        //printStream.close();
	    
	    /*
	     * notice:
	     * inputStream.read() will block if no data return
	     */
	    while (((bytesRead = inputStream.read(buffer)) != -1) && !isCancelled())
	    {  
	    	byteArrayOutputStream.write(buffer, 0, bytesRead);
	    	response += byteArrayOutputStream.toString("UTF-8");
	        publishProgress(response);
	    }
	    sendmessage("endwhile");
	   } catch (UnknownHostException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    response = "UnknownHostException: " + e.toString();
	   } catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    response = "IOException: " + e.toString();
	   }
	return null;
	 }

	 
	  
	  protected void onProgressUpdate(String... progress)
	  {
		  String[] s = response.split("\n");
		  for(int i=0;i<s.length;i++)
		  {
			  CatalogFragment.list.add(s[i]);	  
		  }	  
		  	  
	  }
	  
	  @Override
	  protected void onPostExecute(Void result) {
		  //Lfragment.list.add(response.toString());
		  //Toast.makeText(getApplicationContext(), "Socket открыт" , Toast.LENGTH_SHORT).show();
	   super.onPostExecute(result);
	  }
	  
	  @Override
	    protected void onCancelled() {
	      super.onCancelled();
	      try {
		      socket.close();
		     } catch (IOException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		     } 

	    }
	  
	 }
