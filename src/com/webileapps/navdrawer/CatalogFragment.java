package com.webileapps.navdrawer;

import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockFragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CatalogFragment extends ListFragment {
	
	static String ctlQuary;
	Thread serverThread = null;
	static int ctlquarytype;
	static ArrayAdapter<String> adapter;
	//int count = 0;
	
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		serverThread = new Thread(new SocketRunnable(MainActivity.SERVER_IP, MainActivity.SERVERPORT, ctlQuary));
		serverThread.start();	
		try {
			serverThread.join(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, MainActivity.InpusStrings);
		setListAdapter(adapter);
		//Toast.makeText(getActivity(),String.valueOf(count),Toast.LENGTH_LONG).show();
		return rootView;
	}
	
	//public void onViewCreated (View view, Bundle savedInstanceState){
		//ProgressBar pb= (ProgressBar) view.findViewById(R.id.pb);
	    //pb.setVisibility(1);
	  //  MainActivity.InpusStrings.add("fbgiodubf");
	//}
	 
	//действия при нажатии на элемент listview
	public void onListItemClick(ListView l, View v, int position, long id) {
	    super.onListItemClick(l, v, position, id);
	    //Fragment newFragment;
	    Fragment newFragment = this;
	    switch (ctlquarytype){
	    case 0:
	    	ctlQuary= "quary:subcategory<category>"+(position+1)+"</category>";
	    	ctlquarytype=1;
	    	break;
	    case 1:
	    	ctlQuary= "quary:tovarsub<subcategory>"+(MainActivity.InpusStrings.get(position))+"</subcategory>";
	    	ctlquarytype=2;
	    	break;
	    case 2:
	    	//ProductName = list.get(position);
	    	newFragment= new TovarFragment(MainActivity.InpusStrings.get(position));
	    	break;
	    	
	    }
	    ProgressBar pb= (ProgressBar) v.findViewById(R.id.pb);
	    pb.setVisibility(0);
	    //FragmentManager manager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
        this.onDestroy();
        fragmentTransaction.remove(this);
        fragmentTransaction.replace(R.id.content,newFragment);
        fragmentTransaction.addToBackStack(null);   
        fragmentTransaction.commit();
	}
}
