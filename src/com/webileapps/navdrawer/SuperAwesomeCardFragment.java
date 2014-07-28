/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.webileapps.navdrawer;

import ru.yandex.yandexmapkit.MapController;
import ru.yandex.yandexmapkit.map.OnMapListener;
import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class SuperAwesomeCardFragment extends SherlockFragment { //implements OnMapListener{

	private static final String ARG_POSITION = "position";

	

	public static SuperAwesomeCardFragment newInstance(int position) {
		SuperAwesomeCardFragment f = new SuperAwesomeCardFragment();
		Bundle b = new Bundle();
		b.putInt(ARG_POSITION, position);
		f.setArguments(b);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int position = getArguments().getInt(ARG_POSITION);
	}

	 MapController mMapController;
	    LinearLayout mView;
	    
	 /*public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
	        final MapView mapView = (MapView) rootView.findViewById(R.id.map);
	        
	        mapView.showBuiltInScreenButtons(true);

	        mMapController = mapView.getMapController();
	        // add listener
	        mMapController.addMapListener(this);

	        mView = (LinearLayout)rootView.findViewById(R.id.view);
	                   
	        return rootView;
	    }
	 @Override
		public void onMapActionEvent(MapEvent arg0) {
			// TODO Auto-generated method stub
			
		}*/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		FrameLayout fl = new FrameLayout(getActivity());
		fl.setLayoutParams(params);

		final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());

		TextView v = new TextView(getActivity());
		params.setMargins(10, 10, 10, 10);
		v.setLayoutParams(params);
		v.setLayoutParams(params);
		v.setGravity(Gravity.CENTER);
		v.setBackgroundResource(R.drawable.background_card);
		
		Button b = new Button(getActivity());
		b.setLayoutParams(params);
		b.setGravity(Gravity.LEFT);
		b.setText("Ok");
		 /*b.setOnClickListener(new OnClickListener() {
   	      public void onClick(View v) {
   	    	MainActivity.count=MainActivity.count+1;
   	    	if(MainActivity.data.toString().equals("")==false) MainActivity.count+=100;
   	    	if (position==1)	
   	    	{
   	    		((TextView) v).setText(MainActivity.data + MainActivity.log+MainActivity.count);	
   	    		//WatchSocket.out.println("uiiguguiguiguiguip");
   	    		}
   	    	  	    	
   			else	((TextView) v).setText("CARD " + (position + 1)); 
   	      }
   	      
   	    });*/
		 //if (position==2)
		// {
			 
		// }
		// if (position==1)	((TextView) v).setText(MainActivity.data+MainActivity.log+MainActivity.count);	
		//	else	((TextView) v).setText("CARD " + (position + 1)); 

		fl.addView(v);fl.addView(b);
		return fl;
	}

}