package com.webileapps.navdrawer;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


public class CatalogFragment extends Fragment{
	static FragmentManager manager;
	static View rootView;
	static Fragment me;
	static Context ctx;
		
	CatalogFragment() {
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ctx = getActivity();
		me=this;
	    
	  }
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	  {
		rootView = inflater.inflate(R.layout.fragment_catalog, container, false);
		
	    return rootView;
	  }
	
	public void onViewCreated (View view, Bundle savedInstanceState){
		MainActivity.ctxtovar ="catalog";
		TextView textView = (TextView) view.findViewById(R.id.header_tv);
		textView.setText("Каталог");
		ListViewFragment.ctlQuary = "quary:category";
		ListViewFragment.ctlquarytype=0;
		LoadAsyncTask watchs = new LoadAsyncTask("catalog");
		progressbar(View.VISIBLE);
		watchs.execute();
	}
	
	static void progressbar(int i){
	View pb= (ProgressBar) rootView.findViewById(R.id.pb);
	pb.setVisibility(i);	
	View lv = (View)  rootView.findViewById(R.id.list_category);
	if (i == View.VISIBLE){
		lv.setVisibility(View.INVISIBLE);
	}
	else {
		lv.setVisibility(View.VISIBLE);
	}
		
	}
	
	static void gew(){
		if (ListViewFragment.ctlquarytype== 2){
			MainActivity.InpusStrings.add("Загрузить еще");
		}
		ListViewFragment catalogFragment = new ListViewFragment();
		manager = ((Activity) ctx).getFragmentManager();
		manager.beginTransaction().replace(R.id.list_category, catalogFragment).commit();	
		
	}
	
	static void geew(){
		Fragment newFragment= new ProductFragment(MainActivity.InpusStrings.get(ListViewFragment.pos));
		manager = ((Activity) ctx).getFragmentManager();
	    FragmentTransaction fragmentTransaction = ((Activity) ctx).getFragmentManager().beginTransaction();
	   me.onDestroy();
	    fragmentTransaction.remove(me);
	    fragmentTransaction.replace(R.id.content,newFragment);
	    fragmentTransaction.addToBackStack(null);   
	    fragmentTransaction.commit();
	}
}
