package com.webileapps.navdrawer;

import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockFragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CatalogFragment extends ListFragment {
	
	static String ctlQuary;
	Thread serverThread = null;
	static int ctlquarytype;
	//int count = 0;
		 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//count=count+1;
		MainActivity.dialog.show();
		this.serverThread = new Thread(new SocketRunnable(MainActivity.SERVER_IP, MainActivity.SERVERPORT, ctlQuary));
		this.serverThread.start();	
		try {
			this.serverThread.join(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, MainActivity.InpusStrings);
		setListAdapter(adapter);
		MainActivity.dialog.dismiss();
		//Toast.makeText(getActivity(),String.valueOf(count),Toast.LENGTH_LONG).show();
		return rootView;
	}
	 
	//действия при нажатии на элемент listview
	public void onListItemClick(ListView l, View v, int position, long id) {
	    super.onListItemClick(l, v, position, id);
	    //Fragment newFragment;
	    Fragment newFragment = this;
	    switch (ctlquarytype){
	    case 0:
	    	ctlQuary= "quary:subcategory<"+(position+1)+">";
	    	ctlquarytype=1;
	    	break;
	    case 1:
	    	ctlQuary= "quary:tovarsub<"+(MainActivity.InpusStrings.get(position))+">";
	    	ctlquarytype=2;
	    	break;
	    case 2:
	    	//ProductName = list.get(position);
	    	newFragment= new TovarFragment(MainActivity.InpusStrings.get(position));
	    	break;
	    	
	    }
	    //FragmentManager manager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
        this.onDestroy();
        fragmentTransaction.remove(this);
        fragmentTransaction.replace(R.id.content,newFragment);
        fragmentTransaction.addToBackStack(null);   
        fragmentTransaction.commit();
	}
}
