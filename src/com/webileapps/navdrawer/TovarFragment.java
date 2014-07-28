package com.webileapps.navdrawer;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class TovarFragment extends Fragment {
	TextView textView;
	Layout layout;
	Thread serverThread = null;
	String ProductName;
	
	TovarFragment (String temp){
		ProductName = temp;
	}
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	            
	  }
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	  {
	      View rootView = inflater.inflate(R.layout.fragment_tovar, container, false);
	      textView = (TextView) rootView.findViewById(R.id.headerproduct);
	      textView.setText(ProductName);
	      
	      this.serverThread = new Thread(new SocketRunnable(MainActivity.SERVER_IP, MainActivity.SERVERPORT, "quary:tovar_prices<"+ProductName+">"));
		  this.serverThread.start();	
		  try {
				this.serverThread.join(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		  textView = (TextView) rootView.findViewById(R.id.ProductPrices);
	      textView.setText("от "+ MainActivity.InpusStrings.get(0) + " руб.");
	      
	      textView = (TextView) rootView.findViewById(R.id.ProductPricesRange);
	      textView.setText("От "+ MainActivity.InpusStrings.get(0) + " до " + MainActivity.InpusStrings.get(MainActivity.InpusStrings.size()-1)+ " руб.");
	      
	      textView = (TextView) rootView.findViewById(R.id.ProductOffers);
	      textView.setText(MainActivity.InpusStrings.size() + " предложений");
	      View view = (View) rootView.findViewById(R.id.Points);
	      view.setOnClickListener(new OnClickListener(){
	    	  public void onClick(View v)
	    	  {
	    		  Fragment fragment = new MapFragment();
	    	      getFragmentManager().beginTransaction().add(R.id.content, fragment).commit(); 
	    	  }
	      });
	      return rootView;
	  }

	

}
