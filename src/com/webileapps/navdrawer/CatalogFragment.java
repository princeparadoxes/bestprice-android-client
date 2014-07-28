package com.webileapps.navdrawer;

import java.util.ArrayList;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CatalogFragment extends ListFragment {
	static ArrayList<String> list = new ArrayList<String>();
	String data[] = new String[] { "один", "два", "три", "четыре" };
	
	public CatalogFragment() {
	        
	    }
	 
	 @Override
	 
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			
		//try {
			//PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(MainActivity.socket.getOutputStream())),true);
			//BufferedReader in = new BufferedReader(new InputStreamReader(MainActivity.socket.getInputStream()));
		     //MainActivity.out.println("quary:category");
			
			//Toast.makeText(getActivity(), "act "+MainActivity.read, Toast.LENGTH_SHORT).show();
		//} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		 		
	        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, list);
	        setListAdapter(adapter);
                    
	        return rootView;
	    }
	 
	 //действия при нажатии на элемент listview
	 public void onListItemClick(ListView l, View v, int position, long id) {
		    super.onListItemClick(l, v, position, id);
		       	try 
		    	{
		       		SocketAsyncTask.sendmessage("position = " +position);
				} 
		       	catch (Exception e) 
		       	{
					e.printStackTrace();
				}
		    Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_SHORT).show();
		  }
}
