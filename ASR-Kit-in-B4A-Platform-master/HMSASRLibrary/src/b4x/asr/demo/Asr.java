package b4x.asr.demo;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.*;
import com.huawei.hmf.tasks.Task;

import android.content.Intent;
import anywheresoftware.b4a.IOnActivityResult;
import android.util.Log;
import b4x.asr.demo.ASRWork;
import com.huawei.hms.mlsdk.asr.*;


@Version(1.0f)
@ShortName("Asr")
@DependsOn(values={"agconnect-core-1.0.1.300.aar",
"tasks-1.3.1.302.aar",
"network-common-4.0.2.300.aar",
"network-grs-4.0.2.300.aar",
"okhttp-3.12.0.jar",
"okio-1.15.0.jar",
"ml-computer-agc-inner-2.0.1.300.aar",
"ml-computer-cloud-base-inner-2.0.1.300.aar",
"ml-computer-commonutils-inner-2.0.1.300.aar",
"ml-computer-ha-inner-2.0.1.300.aar",
"ml-computer-grs-inner-2.0.1.300.aar",
"ml-computer-net-2.0.1.300.aar",
"ml-computer-voice-asr-plugin-2.0.1.300.aar",
"ml-computer-vision-cloud-2.0.1.300.aar",
"ml-computer-voice-asr-2.0.1.300.aar"
})
			
					
@Permissions(values={"android.permission.INTERNET",
"android.permission.WRITE_EXTERNAL_STORAGE",
"android.permission.ACCESS_NETWORK_STATE",
"android.permission.RECORD_AUDIO",
"android.permission.READ_EXTERNAL_STORAGE",
"android.permission.CHANGE_WIFI_STATE",
"android.permission.ACCESS_WIFI_STATE",
"android.permission.CHANGE_CONFIGURATION",
"android.permission.WAKE_LOCK"})

public class Asr {
	public static BA ba;
	
	public static final String TAG = "Asr_Kit";


    public static void ListenForAsr(BA ba) {
		ASRWork.ListenForAsr(ba.context);
    }

	public static void initAsr(BA xba) {
		ba = xba;
	
    }
	
 
	
	
}