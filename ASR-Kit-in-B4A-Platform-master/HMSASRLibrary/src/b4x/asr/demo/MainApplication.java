package b4x.asr.demo;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.*;
import android.app.Application;
import android.content.Context;
import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.agconnect.config.LazyInputStream;
import java.io.IOException;
import java.io.InputStream;


@ShortName("MainApplication")
public class MainApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
		init(this);
    }
	
	private void init (Context context){
        AGConnectServicesConfig config = AGConnectServicesConfig.fromContext(context);
        config.overlayWith(new LazyInputStream(context) {
            public InputStream get(Context context) {
                try {
                    return context.getAssets().open("agconnect-services.json");
                } catch (IOException e) {
                    return null;
                }
            }
        });
    }
}
