package com.jbs.vikingdodge;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AndroidApplication {
	
	private RelativeLayout.LayoutParams adParams;
	private RelativeLayout layout;
	private AdView adView;
	private boolean adsVisible;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = new RelativeLayout(this);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		
		Main game = new Main(true);
		
		View gameView = initializeForView(game, false);
		layout.addView(gameView);
		
		adView = new AdView(this);
		adView.setAdSize(AdSize.BANNER);
		adView.setAdUnitId("ca-app-pub-8697472503307660/5091179514");
		
		adParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

		AdRequest adRequest = new AdRequest.Builder()/*.addTestDevice("4C8FC579095D03DD95068B8D2D505EF0")*/.build();
		
		adView.setVisibility(View.VISIBLE);
		adView.setBackgroundColor(Color.TRANSPARENT);
		adView.loadAd(adRequest);
		
		layout.addView(adView, adParams);
		
		setContentView(layout);
    }
}