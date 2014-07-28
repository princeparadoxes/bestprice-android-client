package com.webileapps.navdrawer;
import ru.yandex.yandexmapkit.MapController;
import ru.yandex.yandexmapkit.MapView;
import ru.yandex.yandexmapkit.map.MapEvent;
import ru.yandex.yandexmapkit.map.OnMapListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.SherlockFragment;

public class MapFragment extends SherlockFragment implements OnMapListener  {
    public static final String ARG__NUMBER = "number";

    public MapFragment() {
        // Empty constructor required for fragment subclasses
    }
    
    MapController mMapController;
    LinearLayout mView;
      
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
		
	}
}

	
