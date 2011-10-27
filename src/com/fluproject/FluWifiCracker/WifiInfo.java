package com.fluproject.FluWifiCracker;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class WifiInfo extends Activity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.wifiinfo);
        Bundle extras = getIntent().getExtras();
        String ssid=null;
        String bssid=null;
        String security=null;
        String power=null;
        String frecuency=null;
        if (extras != null){
        	ssid = extras.getString("ssid");
        	bssid = extras.getString("bssid");
        	security = extras.getString("security");
        	power = extras.getString("power");
        	frecuency = extras.getString("frecuency");
        }
        TextView t_ssid = (TextView)findViewById(R.id.str_i_ssid);
        t_ssid.setText(ssid);
        TextView t_bssid = (TextView)findViewById(R.id.str_i_bbsid);
        t_bssid.setText(bssid);
        TextView t_security = (TextView)findViewById(R.id.str_i_sec);
        t_security.setText(security);
        TextView t_power = (TextView)findViewById(R.id.str_i_pw);
        t_power.setText(power);
        TextView t_frec = (TextView)findViewById(R.id.str_i_frec);
        t_frec.setText(frecuency);
     
        //Viendo si es crackeable:
    	TextView t_crack = (TextView)findViewById(R.id.str_i_crack);
        if (ssid.contains("_")){
	        String [] item_ssid = ssid.split("_");
	        String ssid_name = item_ssid[0];
	        String ssid_code = item_ssid[1];
	        String[] bssid_final = bssid.split(":");
	        String bssid_bueno =( bssid_final[0]+bssid_final[1]+bssid_final[2]+bssid_final[3]+bssid_final[4]+bssid_final[5]);
        	if (ssid_name.equals("JAZZTEL") || (ssid_name.equals("WLAN"))){ //Nombre crackeable.
	        	if (ssid_code.length() == 4){	//Código crackeable.
		        	KeyGenerator key = new KeyGenerator();
		        	String bssid_mayus = bssid_bueno.toUpperCase();
		        	String str_key = key.calcularClave(ssid_code, bssid_mayus);
		        	t_crack.setText(str_key);
		        	t_crack.setTextColor(Color.GREEN);
	        	}
	        	else{
	        		t_crack.setText("NO");
		        	t_crack.setTextColor(Color.RED);
	        	}
	        }
	        else{
	        	t_crack.setText("NO");
	        	t_crack.setTextColor(Color.RED);
	        }
        }
        else{
        	t_crack.setText("NO");
        	t_crack.setTextColor(Color.RED);
        }
	}
}
