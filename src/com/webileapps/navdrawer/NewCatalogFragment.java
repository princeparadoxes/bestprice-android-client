package com.webileapps.navdrawer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NewCatalogFragment extends Fragment{
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	  }
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	  {
		View rootView = inflater.inflate(R.layout.fragment_catalog, container, false);
		
	    return rootView;
	  }
	
	public void onViewCreated (View view, Bundle savedInstanceState){
		 TextView textView = (TextView) view.findViewById(R.id.header_tv);
		 textView.setText("bgi");
		 CatalogFragment catalogFragment = new CatalogFragment();
		 CatalogFragment.ctlQuary = "quary:category";
		 CatalogFragment.ctlquarytype=0;
		 getFragmentManager().beginTransaction().replace(R.id.list_category, catalogFragment).commit();
		 
	}
}
