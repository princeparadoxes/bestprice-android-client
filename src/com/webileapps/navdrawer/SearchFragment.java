package com.webileapps.navdrawer;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SearchFragment extends Fragment{
	static FragmentManager manager;
	static View rootView;
	static Fragment me;
	static Context ctx;
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ctx = getActivity();
		me=this;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
		rootView = inflater.inflate(R.layout.fragment_search, container, false);
		return rootView;
	}
	
	public void onViewCreated (View view, Bundle savedInstanceState){
		 MainActivity.limit = 0;
		 ListViewFragment.ctlquarytype = 2;
		 MainActivity.ctxtovar ="search";
		 TextView textView = (TextView) view.findViewById(R.id.header_search);
		 textView.setText("Поиск");
		 final EditText editText = (EditText) view.findViewById(R.id.editTextSearch);
		 
		 ImageButton button = (ImageButton) view.findViewById(R.id.imageButton1);
		 button.setOnClickListener(new OnClickListener() {
			 public void onClick(View v)
			 {
				 ListViewFragment.ctlQuary = "quary:search<search>"+editText.getText()+"</search><limit>"+String.valueOf(MainActivity.limit)+"</limit>";
				 LoadAsyncTask watchs = new LoadAsyncTask("search");
				 progressbar(View.VISIBLE);
				 watchs.execute();
			 }
		 });
	}
	
	static void progressbar(int i){
		View pb= (ProgressBar) rootView.findViewById(R.id.pb_search);
		pb.setVisibility(i);	
		View lv = (View)  rootView.findViewById(R.id.list_category_search);
		if (i == View.VISIBLE){
			lv.setVisibility(View.INVISIBLE);
		}
		else {
			lv.setVisibility(View.VISIBLE);
		}
	}
	
	static void gewsearch(){
		if (ListViewFragment.ctlquarytype== 2){
			MainActivity.InpusStrings.add("Загрузить еще");
		}
		ListViewFragment catalogFragment = new ListViewFragment();
		manager = ((Activity) ctx).getFragmentManager();
		manager.beginTransaction().replace(R.id.list_category_search, catalogFragment).commit();	
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
