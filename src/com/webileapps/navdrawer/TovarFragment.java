package com.webileapps.navdrawer;

import java.text.NumberFormat;

import ru.yandex.yandexmapkit.utils.GeoPoint;
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
	String tvStatusGPS;
	String tvStatusNet;
		
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
	      
	      serverThread = new Thread(new SocketRunnable(MainActivity.SERVER_IP, MainActivity.SERVERPORT, "quary:tovar_prices<productname>"+ProductName+"</productname>"));
		  serverThread.start();	
		  try {
				serverThread.join(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		  textView = (TextView) rootView.findViewById(R.id.ProductPrices);
	      textView.setText("от "+ MainActivity.InpusStrings.get(0) + " руб.");
	      
	      textView = (TextView) rootView.findViewById(R.id.ProductPricesRange);
	      textView.setText("От "+ MainActivity.InpusStrings.get(0) + " до " + MainActivity.InpusStrings.get(MainActivity.InpusStrings.size()-1)+ " руб.");
	      
	      textView = (TextView) rootView.findViewById(R.id.ProductOffers);
	      textView.setText(MainActivity.InpusStrings.size() + " предложений");
	      
	      serverThread = new Thread(new SocketRunnable(MainActivity.SERVER_IP, MainActivity.SERVERPORT, "quary:nearest_shop<productname>"+ProductName+"</productname><lat>"+MainActivity.lat+"</lat><lon>"+MainActivity.lon+"</lon>"));
		  serverThread.start();	
		  try {
				serverThread.join(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		  textView = (TextView) rootView.findViewById(R.id.ShopName);
		  textView.setText(MainActivity.InpusStrings.get(0));
		  
		  textView = (TextView) rootView.findViewById(R.id.TextViewAddress);
		  textView.setText(MainActivity.InpusStrings.get(1));
		  
		  textView = (TextView) rootView.findViewById(R.id.AdditionalInfo);
		  textView.setText(MainActivity.InpusStrings.get(2));
		  
		  textView = (TextView) rootView.findViewById(R.id.DistanceInfo);
		  textView.setVisibility(1);
		  Double x = Double.valueOf(MainActivity.InpusStrings.get(3)) ;
		  NumberFormat formatter = NumberFormat.getNumberInstance(); 
		  formatter.setMaximumFractionDigits(0); 
		  String s= formatter.format (x) + " км ";
		  x=x*1000;
		  formatter.setMaximumIntegerDigits(3);
		  s=s+ formatter.format (x)+" м";
		  textView.setText(s);
		  
		  //String s =  // Строка "003,333.3333".
		  
		  View view = (View) rootView.findViewById(R.id.Points);
	      view.setOnClickListener(new OnClickListener(){
	    	  public void onClick(View v)
	    	  {
	    		  Fragment fragment = new MapFragment(new GeoPoint(Double.valueOf(MainActivity.InpusStrings.get(4)),Double.valueOf(MainActivity.InpusStrings.get(5))));
	    	      getFragmentManager().beginTransaction().add(R.id.content, fragment).commit(); 
	    		  
	    	  }
	      });
	      return rootView;
	  }
	}