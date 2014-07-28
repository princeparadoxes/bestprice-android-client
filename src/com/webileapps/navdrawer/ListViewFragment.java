package com.webileapps.navdrawer;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewFragment extends ListFragment {
	static String ctlQuary;
	Thread serverThread = null;
	static int ctlquarytype;
	static ArrayAdapter<String> adapter;
	static int pos;
	static View rootView;
	
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_list, container, false);
		adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, MainActivity.InpusStrings);
		setListAdapter(adapter);
		ListView lv = (ListView) rootView.findViewById(R.id.listView1);
		/*lv.setOnScrollListener(new EndlessScrollListener() {

	        @Override
	        public void onLoadMore(int page, int totalItemsCount) {
	            // TODO Auto-generated method stub
	            new LoadAsyncTask("loadmore").execute();

	        }
	    });	*/	
		//Toast.makeText(getActivity(),String.valueOf(count),Toast.LENGTH_LONG).show();
		return rootView;
	}
	
	//действия при нажатии на элемент listview
	public void onListItemClick(ListView l, View v, int position, long id) {
		pos=position;
		super.onListItemClick(l, v, position, id);
	    LoadAsyncTask watchs = new LoadAsyncTask("catalog");
	    switch (ctlquarytype){
	    case 0:
	    	
	    	ctlQuary= "quary:subcategory<category>"+(position+1)+"</category>";
	    	ctlquarytype=1;
	    	CatalogFragment.progressbar(View.VISIBLE);
		    watchs.execute();
		    MainActivity.limit=0;
	    	break;
	    case 1:
	    	MainActivity.subcategory = MainActivity.InpusStrings.get(position);
	    	ctlQuary= "quary:tovarsub<subcategory>"+MainActivity.subcategory+"</subcategory><limit>0</limit>";
	    	ctlquarytype=2;
	    	CatalogFragment.progressbar(View.VISIBLE);
		    watchs.execute();
		    MainActivity.limit=20;
	    	break;
	    case 2:
	    	if (MainActivity.InpusStrings.get(position).equals("Загрузить еще")){
	    		MainActivity.InpusStrings.remove(position);
	    		ctlQuary= "quary:tovarsub<subcategory>"+MainActivity.subcategory+"</subcategory><limit>"+String.valueOf(MainActivity.limit)+"</limit>";
	    		//CatalogFragment.progressbar(View.VISIBLE);
	    		watchs = new LoadAsyncTask("loadmore");
			    watchs.execute();
			    MainActivity.limit=MainActivity.limit +20;
	    	}
	    	else{
	    		if (MainActivity.ctxtovar.equals("catalog") ==true){
	    			CatalogFragment.geew();	
	    		}
	    		if (MainActivity.ctxtovar.equals("search") ==true){
	    			SearchFragment.geew();	
	    		}
	    	}
	    	break;
	    	
	    }
	    
	}
}
