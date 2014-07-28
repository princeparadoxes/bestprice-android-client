package com.webileapps.navdrawer;

import java.text.NumberFormat;

import ru.yandex.yandexmapkit.utils.GeoPoint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProductFragment extends Fragment {
	static TextView textView;
	Layout layout;
	Thread serverThread = null;
	static String ProductName;
	String tvStatusGPS;
	String tvStatusNet;
	static View rootView;
	static FragmentManager manager;
	static Context ctx;
	static LoadAsyncTask watchs;
	static View v;
	
	ProductFragment (String temp){
		ProductName = temp;
	}	
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    ctx=getActivity();
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	  {
		
		rootView = inflater.inflate(R.layout.fragment_tovar, container, false);
		textVisibility(View.INVISIBLE);
		  	      
	      textView = (TextView) rootView.findViewById(R.id.headerproduct);
	      textView.setText(ProductName);
	      
	      ListViewFragment.ctlQuary="quary:tovar_prices<productname>"+ProductName+"</productname>";
	      watchs = new LoadAsyncTask("productprice");
	      watchs.execute();
	      
	      return rootView;
	  }
	
	static void productprice(){
		textView = (TextView) rootView.findViewById(R.id.ProductPricesRange);
	    textView.setText("От "+ MainActivity.InpusStrings.get(0) + " до " + MainActivity.InpusStrings.get(MainActivity.InpusStrings.size()-1)+ " руб.");
	    textView.setVisibility(View.VISIBLE); 
	    
	    textView = (TextView) rootView.findViewById(R.id.ProductOffers);
	    textView.setText(MainActivity.InpusStrings.size() + " предложений");
	    textView.setVisibility(View.VISIBLE); 
	   
	    watchs = new LoadAsyncTask("productcoor");
	    ListViewFragment.ctlQuary="quary:nearest_shop<productname>"+ProductName+"</productname><lat>"+MainActivity.lat+"</lat><lon>"+MainActivity.lon+"</lon>";
	    watchs.execute();
	}
	
	static void productcoor(){
	  textView = (TextView) rootView.findViewById(R.id.ShopName);
	  textView.setText(MainActivity.InpusStrings.get(0));
	  textView.setVisibility(View.VISIBLE); 
	  
	  textView = (TextView) rootView.findViewById(R.id.TextViewAddress);
	  textView.setText(MainActivity.InpusStrings.get(1));
	  textView.setVisibility(View.VISIBLE); 
	  
	  textView = (TextView) rootView.findViewById(R.id.AdditionalInfo);
	  textView.setText(MainActivity.InpusStrings.get(2));
	  textView.setVisibility(View.VISIBLE); 
	  
	  textView = (TextView) rootView.findViewById(R.id.DistanceInfo);
	  Double x = Double.valueOf(MainActivity.InpusStrings.get(3)) ;
	  NumberFormat formatter = NumberFormat.getNumberInstance(); 
	  formatter.setMaximumFractionDigits(0); 
	  String s= formatter.format (x) + " км ";
	  x=x*1000;
	  formatter.setMaximumIntegerDigits(3);
	  s=s+ formatter.format (x)+" м";
	  textView.setText(s);
	  textView.setVisibility(View.VISIBLE); 
	  
	  View view = (View) rootView.findViewById(R.id.Points);
	  view.setOnClickListener(new OnClickListener(){
		  public void onClick(View v)
		  {
			  Fragment fragment = new MapFragment(new GeoPoint(Double.valueOf(MainActivity.InpusStrings.get(4)),Double.valueOf(MainActivity.InpusStrings.get(5))));
			  manager = ((Activity) ctx).getFragmentManager();
			  manager.beginTransaction().add(R.id.content, fragment).commit(); 
		  }
	  });
	  watchs = new LoadAsyncTask("productminimalprice");
	  ListViewFragment.ctlQuary="quary:nearest_shop<productname>"+ProductName+"</productname><lat>"+MainActivity.lat+"</lat><lon>"+MainActivity.lon+"</lon>";
	  watchs.execute();
	}
	
	static  void productminimalprice(){
		textView = (TextView) rootView.findViewById(R.id.minimalptice_ShopName);
		textView.setText(MainActivity.InpusStrings.get(0));
		textView.setVisibility(View.VISIBLE); 
		  
		textView = (TextView) rootView.findViewById(R.id.minimalptice_TextViewAddress);
		textView.setText(MainActivity.InpusStrings.get(1));
		textView.setVisibility(View.VISIBLE); 
		  
		textView = (TextView) rootView.findViewById(R.id.minimalptice_AdditionalInfo);
		textView.setText(MainActivity.InpusStrings.get(2));
		textView.setVisibility(View.VISIBLE); 
		  
		textView = (TextView) rootView.findViewById(R.id.minimalptice_DistanceInfo);
		Double x = Double.valueOf(MainActivity.InpusStrings.get(3)) ;
		NumberFormat formatter = NumberFormat.getNumberInstance(); 
		formatter.setMaximumFractionDigits(0); 
		String s= formatter.format (x) + " км ";
		x=x*1000;
		formatter.setMaximumIntegerDigits(3);
		s=s+ formatter.format (x)+" м";
		textView.setText(s);
		textView.setVisibility(View.VISIBLE); 
		  
		View view = (View) rootView.findViewById(R.id.Points);
		view.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Fragment fragment = new MapFragment(new GeoPoint(Double.valueOf(MainActivity.InpusStrings.get(4)),Double.valueOf(MainActivity.InpusStrings.get(5))));
				manager = ((Activity) ctx).getFragmentManager();
				manager.beginTransaction().add(R.id.content, fragment).commit(); 
			}
		});
	}
	
	static void progressbar(int j,int i){
		View pb = null;
		if(j==0){
			pb= (ProgressBar) rootView.findViewById(R.id.pbtovar1);
		}
		if(j==1){
			pb= (ProgressBar) rootView.findViewById(R.id.pbtovar2);
		}
		if(j==2){
			pb= (ProgressBar) rootView.findViewById(R.id.pbtovar3);
		}
		pb.setVisibility(i);	
	}
	
	static void textVisibility(int visib){
		v = (View) rootView.findViewById(R.id.ProductPricesRange);
		v.setVisibility(visib);
		v = (View) rootView.findViewById(R.id.ProductOffers);
		v.setVisibility(visib);
		v = (View) rootView.findViewById(R.id.ShopName);
		v.setVisibility(visib);
		v = (View) rootView.findViewById(R.id.AdditionalInfo);
		v.setVisibility(visib);
		v = (View) rootView.findViewById(R.id.DistanceInfo);
		v.setVisibility(visib);
		v = (View) rootView.findViewById(R.id.TextViewAddress);
		v.setVisibility(visib);	
	}
}