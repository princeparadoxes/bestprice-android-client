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

import android.support.v4.app.Fragment;
import android.content.res.Configuration;
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
	
	//переменные по сокету
	String socketstatus="";
	static String slushatelstatus="";
	static String packstatus="ooo";
	static String data="";
	static boolean soedstatus=false;
	static String log = new String("");
	static int count=0;
	static SocketAsyncTask socketAsyncTask;

	static Socket socket;
	//static String read;
	static PrintWriter out;
	static BufferedReader in;
	static int SERVERPORT = 33333;
	static String SERVER_IP = "192.168.1.104";
	

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

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				getSupportActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}
		//startSocketAsyncTask();
		socketAsyncTask = new SocketAsyncTask(SERVER_IP, SERVERPORT);
		socketAsyncTask.execute();

	}
	
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
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	private void selectItem(int position) {
		SherlockFragment fragment = new MapFragment();
		switch (position) {
		case 0:
			
			getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
			break;
		case 1:
			getSupportFragmentManager().beginTransaction().add(R.id.content, PageSlidingTabStripFragment.newInstance(),	PageSlidingTabStripFragment.TAG).commit();
			break;
		case 2:
			CatalogFragment catalogFragment = new CatalogFragment();
			getFragmentManager().beginTransaction().replace(R.id.content, catalogFragment).commit();
			break;
		case 3:
			SherlockFragment settingsFragment = new SettingsFragment();
			getSupportFragmentManager().beginTransaction().add(R.id.content, settingsFragment).commit();
			break;	
		default:
			getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
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