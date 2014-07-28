package com.webileapps.navdrawer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

public class LoadAsyncTask extends AsyncTask<Void, Void, Void> {
	
	Thread serverThread = null;
	String type;
	LoadAsyncTask(String Type){
		  type=Type;
	 }
	  
	  @Override
	  protected Void doInBackground(Void... arg0) {
		  if((type.equals("loadmore")==false)){
			  MainActivity.InpusStrings.clear();
			  
		  }
		  serverThread = new Thread(new SocketRunnable(MainActivity.SERVER_IP, MainActivity.SERVERPORT, ListViewFragment.ctlQuary));
			serverThread.start();	
			try {
				serverThread.join(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		 	
		 	//publishProgress(response);
	  
	  return null;
	 }

	 
	  
	  protected void onProgressUpdate(Void... progress)
	  {
		 
	  }
	  
	  @Override
	  protected void onPostExecute(Void result) {
	  super.onPostExecute(result);
		  if (type.equals("catalog")==true){
			  CatalogFragment.gew();
			  CatalogFragment.progressbar(View.INVISIBLE);
		  }
		  if (type.equals("productprice")==true){
			  ProductFragment.productprice();
			  ProductFragment.progressbar(0,View.INVISIBLE);
		  }
		  if (type.equals("productcoor")==true){
			  ProductFragment.productcoor();
			  ProductFragment.progressbar(1,View.INVISIBLE);
		  }
		  if (type.equals("search")==true){
			  SearchFragment.gewsearch();
			  SearchFragment.progressbar(View.INVISIBLE);
		  }
		  if (type.equals("productminimalprice")==true){
			  ProductFragment.productminimalprice();
			  ProductFragment.progressbar(2,View.INVISIBLE);
		  }
	  }
	  
	  @Override
	    protected void onCancelled() {
	      super.onCancelled();
	      }
	  
	 }
