package elghoul.music;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.codekidlabs.storagechooser.StorageChooser;

import java.util.List;
import nl.changer.audiowife.AudioWife;

public class MainActivity extends mAudioWife implements Communicat{

    AudioData  audioData;

    SeekBar seekBar;
    ImageButton pause,next,previous,play;
    TextView CurrentTime,TotalTime;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        next=findViewById( R.id.btnNext );
        previous=findViewById( R.id.btnPrevious );

        pause=findViewById( R.id.btnPause );
        play=findViewById( R.id.btnPlay );
        seekBar=findViewById( R.id.SeekBar );
        CurrentTime=findViewById( R.id.CurrentTime );
        TotalTime=findViewById( R.id.TotalTime );
        audioName=findViewById( R.id.audioName );

        frameLayout=findViewById( R.id.FolderFrame );

        AudioWife.getInstance().addOnCompletionListener( new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Next();
            }
        } );

        mSharedPreference sharedPreference=new mSharedPreference( this );
        sharedPreference.LoadAll();

        if(mSharedPreference.getFolders().isEmpty()){
             Directory();
        }
         OpenFragment(1);
        }

    @Override
    protected void onStop() {
        super.onStop();
        if(audioData!=null){
              mSharedPreference sharedPreference=new mSharedPreference( this );
              sharedPreference.Save(audioData.getFile() ,1 );
    }
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
            Log.e("Directory",e.getMessage());
        }
    }

    public void Previousbtn(View view) {
        Previous();
    }

    public void Nextbtn(View view) {
        Next();
    }

    @Override
    public void OpenFragment(int Type){

        FragmentManager fragmentManager=getSupportFragmentManager();
        Folders_Frag folders_frag = new Folders_Frag();

        Bundle bundle = new Bundle();
        bundle.putInt( "Type",Type );
        folders_frag.setArguments( bundle );

        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace( R.id.FolderFrame, folders_frag );
        fragmentTransaction.addToBackStack( null ).commit();
    }

    @Override
    public void onBackPressed() {
       Log.e("FragCount", String.valueOf( getSupportFragmentManager().getBackStackEntryCount() ) );
        if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
           OpenFragment( 1 );
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void Player(List<String> list) {
        try {
            mediaPath=list;

            AudioWife.getInstance().release();
                AudioWife.getInstance().init(this, Uri.parse( list.get( 0 ) ) )
                    .setPlayView( play )
                    .setPauseView( pause )
                    .setSeekBar( seekBar )
                    .setTotalTimeView( TotalTime )
                    .setRuntimeView( CurrentTime )
                    .play();

            audioName.setText( mAudioWife.SetName( mediaPath.get( index ) ) );

        }catch (Exception e){
            Log.e( "Player Error", e.getMessage());
        }
    }
}



