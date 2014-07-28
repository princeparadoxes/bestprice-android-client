package com.webileapps.navdrawer;
import ru.yandex.yandexmapkit.MapController;
import ru.yandex.yandexmapkit.MapView;
import ru.yandex.yandexmapkit.OverlayManager;
import ru.yandex.yandexmapkit.map.MapEvent;
import ru.yandex.yandexmapkit.map.OnMapListener;
import ru.yandex.yandexmapkit.overlay.Overlay;
import ru.yandex.yandexmapkit.overlay.OverlayItem;
import ru.yandex.yandexmapkit.overlay.balloon.BalloonItem;
import ru.yandex.yandexmapkit.utils.GeoPoint;
import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MapFragment extends Fragment implements OnMapListener  {
    public static final String ARG__NUMBER = "number";
    GeoPoint geoPoint =null;
    public MapFragment() {
        // Empty constructor required for fragment subclasses
    }
    public MapFragment(GeoPoint s) {
    	geoPoint=s; 
    }
    
    MapController mMapController;
    LinearLayout mView;
    OverlayManager mOverlayManager;
      
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        final MapView mapView = (MapView) rootView.findViewById(R.id.map);
        mapView.showBuiltInScreenButtons(true);
        mMapController = mapView.getMapController();
        mMapController.addMapListener(this);
        if( geoPoint!=null){
        	mMapController.setPositionAnimationTo(geoPoint);
        }
        mView = (LinearLayout)rootView.findViewById(R.id.view);
        mOverlayManager = mMapController.getOverlayManager();
        mOverlayManager.getMyLocation().setEnabled(false);
                  
        return rootView;
    }
    
    public void onViewCreated(View view, Bundle savedInstanceState){
    	if( geoPoint!=null){
        	ShowMe();
        	ShowNearestShop();
        }
    }

	@Override
	public void onMapActionEvent(MapEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	 public void ShowMe(){
		Resources res = getResources();
        Overlay overlay = new Overlay(mMapController);
        final OverlayItem Me = new OverlayItem(new GeoPoint(MainActivity.lat , MainActivity.lon), res.getDrawable(R.drawable.shop));
        BalloonItem balloonMe = new BalloonItem(getActivity(),Me.getGeoPoint());
        balloonMe.setText("Вы");
        Me.setBalloonItem(balloonMe);
        overlay.addOverlayItem(Me);
        mOverlayManager.addOverlay(overlay);
	 }
	 
	 public void ShowNearestShop(){
        Resources res = getResources();
        Overlay overlay = new Overlay(mMapController);
        final OverlayItem nearestshop = new OverlayItem(geoPoint, res.getDrawable(R.drawable.shop));
        BalloonItem balloonnearestshop = new BalloonItem(getActivity(),nearestshop.getGeoPoint());
        balloonnearestshop.setText("kremlin");
        nearestshop.setBalloonItem(balloonnearestshop);
        overlay.addOverlayItem(nearestshop);
        mOverlayManager.addOverlay(overlay);
	 }

	
}

	
