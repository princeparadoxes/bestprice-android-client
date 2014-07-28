package com.webileapps.navdrawer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;

public class SocketAsyncTask extends AsyncTask<Void, Void, Void> {
	  
	  
	  SocketAsyncTask(String addr, int port){
	 
	  }
	  
	  @Override
	  protected Void doInBackground(Void... arg0) {
	  
	        //publishProgress(response);
	  
	  return null;
	 }

	 
	  
	  protected void onProgressUpdate(Void... progress)
	  {
		 
	  }
	  
	  @Override
	  protected void onPostExecute(Void result) {
		 
	   super.onPostExecute(result);
	  }
	  
	  @Override
	    protected void onCancelled() {
	      super.onCancelled();
	      }
	  
	 }
