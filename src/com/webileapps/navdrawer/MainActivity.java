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


import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class MainActivity extends SherlockFragmentActivity {
  //переменные класса
	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mPlanetTitles;
	static Context ctx;
	
	//переменные по сокету
	String socketstatus="";
	static String slushatelstatus="";
	static String packstatus="ooo";
	static String data="";
	static boolean soedstatus=false;
	static String log = new String("");
	static int count=0;
	static SocketAsyncTask socketAsyncTask;
	static ProgressDialog dialog;
	static ArrayList<String> InpusStrings = new ArrayList<String>();
	private LocationManager locationManager;
	static Double lat=54.80623493407336;
	static Double lon=73.33253860473633;
	
	static Socket socket;
	//static String read;
	static PrintWriter out;
	static BufferedReader in;
	static int SERVERPORT = 33333;
	static String SERVER_IP = "192.168.1.101";
	
	//дейтсвия при создании 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//ресурсы для navigationdrawer
		mTitle = mDrawerTitle = getTitle();
		mPlanetTitles = getResources().getStringArray(R.array.planets_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// затемнение основы при вызове меню
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,	GravityCompat.START);
		
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item, mPlanetTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

	
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		
		mDrawerToggle = new ActionBarDrawerToggle(this, 
		mDrawerLayout, 
		R.drawable.ic_drawer, 
		R.string.drawer_open, 
		R.string.drawer_close 
		) {
			public void onDrawerClosed(View view) {
				getSupportActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); 
			}

			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); 
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}
		//locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		//locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 10, 10, locationListener);
	    //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * 10, 10, locationListener);
 }
	

	
	private LocationListener locationListener = new LocationListener() {

	    @Override
	    public void onLocationChanged(Location location) {
	      lat = location.getLatitude();
	      lon = location.getLongitude();
	    }

	    @Override
	    public void onProviderDisabled(String provider) {
	    }

	    @Override
	    public void onProviderEnabled(String provider) {
	    }
	    
	    @Override
	    public void onStatusChanged(String provider, int status, Bundle extras) {
	    }
	  };
	
	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(
			com.actionbarsherlock.view.MenuItem item) {

		switch (item.getItemId()) {

		case android.R.id.home: {
			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				mDrawerLayout.openDrawer(mDrawerList);
			}
			break;
		}

		case R.id.action_contact:
			

		}

		return super.onOptionsItemSelected(item);
	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}
	

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	void selectItem(int position) {
		Fragment fragment = new MapFragment();
		switch (position) {
		case 0:
			
			getFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
			
			break;
		case 1:
			getSupportFragmentManager().beginTransaction().add(R.id.content, PageSlidingTabStripFragment.newInstance(),	PageSlidingTabStripFragment.TAG).commit();
			break;
		case 2:
			NewCatalogFragment newcatalogFragment = new NewCatalogFragment();
			getFragmentManager().beginTransaction().replace(R.id.content, newcatalogFragment).commit();
			
			break;
		case 3:
			//Fragment fragmen = new BalloonOverlayActivity();
			//getFragmentManager().beginTransaction().replace(R.id.content, fragmen).commit();
			SherlockFragment settingsFragment = new SettingsFragment();
			getSupportFragmentManager().beginTransaction().add(R.id.content, settingsFragment).commit();
			break;	
		default:
			getFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
			break;
		}

		mDrawerLayout.closeDrawer(mDrawerList);
	}

	// Вызывается перед выходом из "полноценного" состояния.
    @Override
    public void onDestroy(){
    	//if (socketAsyncTask == null) return;
    	socketAsyncTask.cancel(false);
    	//socketAsyncTask.cancel(false);
        // Очистите все ресурсы. Это касается завершения работы
        // потоков, закрытия соединений с базой данных и т. д.
        super.onDestroy();
    }	
    
    
}