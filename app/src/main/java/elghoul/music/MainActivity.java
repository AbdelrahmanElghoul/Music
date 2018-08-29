package elghoul.music;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codekidlabs.storagechooser.StorageChooser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AudioData  audioData;


    SeekBar seekBar;
    ImageButton pause,next,previous,play;
    TextView audioName,CurrentTime,TotalTime;
    FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        pause=findViewById( R.id.btnPause );
        next=findViewById( R.id.btnNext );
        play=findViewById( R.id.btnPlay );
        previous=findViewById( R.id.btnPrevious );
        audioName=findViewById( R.id.audioName );
        seekBar=findViewById( R.id.SeekBar );
        CurrentTime=findViewById( R.id.CurrentTime );
        TotalTime=findViewById( R.id.TotalTime );
        frameLayout=findViewById( R.id.FolderFrame );


        mSharedPreference sharedPreference=new mSharedPreference( this );
        sharedPreference.LoadAll();


        if(sharedPreference.getFolders().isEmpty()){
             Directory();
        }

        Folders_Frag folders_frag=new Folders_Frag();

        Bundle bundle=new Bundle();
        bundle.putInt( "Type",1 );
        folders_frag.setArguments( bundle );

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace( R.id.FolderFrame,folders_frag );
        fragmentTransaction.addToBackStack( null).commit();

        }

    public void Directory() {
      try{
        StorageChooser chooser = new StorageChooser.Builder()
                .withActivity(MainActivity.this)
                .withFragmentManager(getFragmentManager())
                .withMemoryBar(true)
                .showHidden( false )
                .allowCustomPath(true)
                .setType(StorageChooser.DIRECTORY_CHOOSER)
                .filter( StorageChooser.FileType.AUDIO )
                .build();

        chooser.show();

          chooser.setOnSelectListener(new StorageChooser.OnSelectListener() {
              @Override
              public void onSelect(String path) {
                  audioData= new AudioData(MainActivity.this,path);
                      mSharedPreference sharedPreference=new mSharedPreference( MainActivity.this );
                      sharedPreference.Save(audioData.getFile() ,1 );
              }
          });



    }catch (Exception e){
           msg(e.getMessage());
      }
    }

    void msg(String message) {
        Toast.makeText( this, message, Toast.LENGTH_SHORT ).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(audioData!=null){
              mSharedPreference sharedPreference=new mSharedPreference( this );
              sharedPreference.Save(audioData.getFile() ,1 );
    }
}

    public void Previousbtn(View view) {
        if(mMediaPlayer.Previous( this )){
            audioName.setText(mMediaPlayer.getAudioName() );
        }
    }

    public void Nextbtn(View view) {
        if(mMediaPlayer.Next( this )){
            audioName.setText(mMediaPlayer.getAudioName() );
        }
    }

    public void Playbtn(View view) {
       if(mMediaPlayer.Play()){
           audioName.setText(mMediaPlayer.getAudioName() );
           play.setVisibility( View.GONE );
           pause.setVisibility( View.VISIBLE );
       }
    }

    public void Pausebtn(View view) {
       if( mMediaPlayer.Pause()){
           audioName.setText(mMediaPlayer.getAudioName() );
           pause.setVisibility( View.GONE );
           play.setVisibility( View.VISIBLE );
       }
    }


    @Override
    public void onBackPressed() {
        Log.e("FragCount", String.valueOf( getFragmentManager().getBackStackEntryCount() ) );
        if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
             getSupportFragmentManager().popBackStackImmediate() ;
        } else {
            super.onBackPressed();
            
        }
       // moveTaskToBack( true );
    }

}



