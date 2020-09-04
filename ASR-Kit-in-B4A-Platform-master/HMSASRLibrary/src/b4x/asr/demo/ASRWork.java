package b4x.asr.demo;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.*;
import android.content.Context;
import android.util.Log;
import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.agconnect.config.LazyInputStream;

import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hms.mlsdk.asr.MLAsrListener;
import com.huawei.hms.mlsdk.asr.MLAsrRecognizer;

import android.content.Intent;
import anywheresoftware.b4a.IOnActivityResult;
import android.content.Intent;
import android.content.pm.PackageManager;
import static b4x.asr.demo.Asr.ba;
import java.io.IOException;
import java.io.InputStream;
import android.os.Bundle;
import com.huawei.hms.mlsdk.asr.*;
import com.huawei.hms.mlsdk.common.MLApplication;

@ShortName("ASRWork")
@Events(values={"AsrText (text As String)"})
public class ASRWork {
	public static String eventName = "listener";
	public static final String TAG = "ASR_Kit";
	
    public static void init (Context context){
        AGConnectServicesConfig.fromContext(context).overlayWith(new LazyInputStream(context) {
            @Override
            public InputStream get(Context context) {
                try {
                    return context.getAssets().open("agconnect-services.json");
                } catch (IOException e) {
                    e.printStackTrace();
                     BA.Log(e.toString());
                }
                return null;
            }
        });
    }
	
	private static IOnActivityResult ion;
       
        public static void ListenForAsr(final Context context) 
		{
				MLApplication.getInstance().setApiKey("API-KEY");
				MLAsrRecognizer mSpeechRecognizer = MLAsrRecognizer.createAsrRecognizer(context);
				Intent mSpeechRecognizerIntent = new Intent(MLAsrConstants.ACTION_HMS_ASR_SPEECH);
                mSpeechRecognizerIntent.putExtra(MLAsrConstants.LANGUAGE, "en-US").putExtra(MLAsrConstants.FEATURE, MLAsrConstants.FEATURE_WORDFLUX);
                mSpeechRecognizer.startRecognizing(mSpeechRecognizerIntent);
				mSpeechRecognizer.setAsrListener(new SpeechRecognitionListener());
		}
    
	protected static class SpeechRecognitionListener implements MLAsrListener 
	{
        @Override
        public void onStartListening() {
            // The recorder starts to receive speech.
			
			BA.Log("OnStartListening");
          
        }

        @Override
        public void onStartingOfSpeech() {
            // The user starts to speak, that is, the speech recognizer detects that the user starts to speak.
			
			BA.Log("OnStartingOfSpeech");
          
        }

        @Override
        public void onVoiceDataReceived(byte[] data, float energy, Bundle bundle) 
		{
            // Return the original PCM stream and audio power to the user.
			
			BA.Log("OnVoiceDataReceived");
			
         }

        @Override
        public void onState(int i, Bundle bundle) {
           
			//ba.raiseEventFromUI(this, eventName + "_asrtext", bundle.toString());
			
			  BA.Log("OnState");
          
        }

        @Override
        public void onRecognizingResults(Bundle partialResults) {
            
			// Receive the recognized text from MLAsrRecognizer.
         
		//  ba.raiseEventFromUI(this, eventName + "_asrtext", partialResults.toString());
		   
		    BA.Log("OnRecognizing");
		
        }

        @Override
        public void onResults(Bundle results) {
            // Text data of ASR.
			 ba.raiseEventFromUI(this, eventName + "_asrtext", results.toString());
			 
			 BA.Log(String.valueOf(results));
			 
			 BA.Log("OnResults");
         
        }

        @Override
        public void onError(int error, String errorMessage) {
            // Called when an error occurs in recognition.
			// ba.raiseEventFromUI(this, eventName + "_asrtext", errorMessage.toString());
			
			BA.Log("OnError");
           
        }
    } 
}








































/*import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.*;
import android.content.Context;
import android.util.Log;
import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.agconnect.config.LazyInputStream;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hms.mlsdk.asr.MLAsrListener;
import com.huawei.hms.mlsdk.asr.MLAsrRecognizer;
import android.content.Intent;
import anywheresoftware.b4a.IOnActivityResult;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;


@ShortName("ASRWork")
@Events(values={"onStartListening (text As String)"})
public class ASRWork {
		public static String eventName = "listener";
	public static final String TAG = "ASR_Kit";
   
	public static void init (Context context){
			 
        AGConnectServicesConfig.fromContext(context).overlayWith(new LazyInputStream(context) {
            @Override
            public InputStream get(Context context) {
                try {
					
                    return context.getAssets().open("agconnect-services.json");
					
                } catch (IOException e) {
                    e.printStackTrace();
                     BA.Log(e.toString());
                }
                return null;
            }
        });
		
    }
	
	private static IOnActivityResult ion;
       
        public static void ListenForAsr(final Context context) {
				MLAsrRecognizer mSpeechRecognizer = MLAsrRecognizer.createAsrRecognizer(context);
			 
				Intent mSpeechRecognizerIntent = new Intent(MLAsrConstants.ACTION_HMS_ASR_SPEECH);
                mSpeechRecognizerIntent.putExtra(MLAsrConstants.LANGUAGE, "en-US").putExtra(MLAsrConstants.FEATURE, MLAsrConstants.FEATURE_WORDFLUX);
                mSpeechRecognizer.startRecognizing(mSpeechRecognizerIntent);
				
				//ion = new IOnActivityResult() {
              @SuppressWarnings("unchecked")
                @Override
                public void ResultArrived(int resultCode, Intent data) {
              
				
                }
            };
				mSpeechRecognizer.setAsrListener(new SpeechRecognitionListener());
        }
		
		
			
	   // Use the callback to implement the MLAsrListener API and methods in the API.
    protected class SpeechRecognitionListener implements MLAsrListener {
        @Override
        public void onStartListening() {
            // The recorder starts to receive speech.
            Log.d("JAHNAVI  onStartListening", " started");
			ba.raiseEventFromUI(this, eventName + "_onStartListening", "started");
        }

        @Override
        public void onStartingOfSpeech() {
            // The user starts to speak, that is, the speech recognizer detects that the user starts to speak.
            Log.d("JAHNAVI  onStartingOfSpeech()", "onStartingOfSpeech()");
        }

        @Override
        public void onVoiceDataReceived(byte[] data, float energy, Bundle bundle) {
            // Return the original PCM stream and audio power to the user.
            Log.d("JAHNAVI  onStartingOfSpeech()", bundle.toString());

                    }

        @Override
        public void onState(int i, Bundle bundle) {
            Log.d("JAHNAVI  onState()", bundle.toString());
          //  textView.setText(bundle.toString());
        }

        @Override
        public void onRecognizingResults(Bundle partialResults) {
            // Receive the recognized text from MLAsrRecognizer.
            Log.d("JAHNAVI  onStartingOfSpeech()", "onRecognizingResults");
          //  textView.setText(partialResults.toString());
        }

        @Override
        public void onResults(Bundle results) {
            // Text data of ASR.
            Log.d("JAHNAVI", results.toString());
          //  textView.setText(results.toString());
        }

        @Override
        public void onError(int error, String errorMessage) {
            // Called when an error occurs in recognition.
            Log.d("JAHNAVI  onStartingOfSpeech()", error+" "+ errorMessage);
        }
    }
}*/