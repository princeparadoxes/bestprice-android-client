package com.webileapps.navdrawer;

import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class SettingsFragment extends SherlockFragment {
    
  EditText Server;
  static String addServer;
  EditText Port;
  static int addPort;
  EditText login;
  static String Login;
  EditText password;
  static String Password;
  Button Ok;
  TextView textview;
  View view;
  static boolean internet=false;
    
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
            
  }
  
    
  public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
  {
	  View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
      Server = (EditText) rootView.findViewById(R.id.Server);
      Server.setText(MainActivity.SERVER_IP);
      Port = (EditText) rootView.findViewById(R.id.Port);
      Port.setText(Integer.toString(MainActivity.SERVERPORT));
      Ok = (Button) rootView.findViewById(R.id.btnOk);
      //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, MainActivity.InpusStrings);
     	
      Ok.setOnClickListener(new OnClickListener()       {
    	  public void onClick(View v)
    	  {
    		MainActivity.SERVER_IP= Server.getText().toString();
    	    MainActivity.SERVERPORT= Integer.parseInt(Port.getText().toString());
    	  }
      });
      return rootView;
  }
}