package selfie.selfieclick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import static android.speech.SpeechRecognizer.createSpeechRecognizer;
import static android.speech.SpeechRecognizer.isRecognitionAvailable;

public class SelfieActivity extends Activity {

  private static final int REQUEST_CODE = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
    startActivity(intent);
  }

  @Override
  protected void onStart() {
    super.onStart();
    if(isRecognitionAvailable(this)) {
      Intent recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
      recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,"en");
      recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

      SpeechRecognizer speechRecognizer = createSpeechRecognizer(this);
      speechRecognizer.startListening(recognizerIntent);
      startActivityForResult(recognizerIntent,REQUEST_CODE);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.selfie, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    return id == R.id.action_settings || super.onOptionsItemSelected(item);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    switch (requestCode){
      case REQUEST_CODE: if (resultCode == RESULT_OK && data != null) {

        ArrayList<String> voiceInputs = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        for (String voiceInput : voiceInputs) {
          if("Click".equalsIgnoreCase(voiceInput)){

          }
        }
      }
    }
  }
}
