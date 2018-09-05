package elghoul.music;

import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Switch;

import nl.changer.audiowife.AudioWife;

public class MainActivity extends mAudioWife implements FragmentStarter {

    ImageButton Shuffle;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        next=findViewById( R.id.btnNext );
        previous=findViewById( R.id.btnPrevious );
        Shuffle=findViewById( R.id.Shufflebtn );

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
                Next(Random);
            }
        } );

        mSharedPreference sharedPreference=new mSharedPreference( this );
        sharedPreference.LoadAll();

        if(mSharedPreference.getFolders().isEmpty()){
            new AudioData(  this);
        }         OpenFragment(1);

        }
        /*
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
    */

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
            getSupportFragmentManager().popBackStackImmediate();
           OpenFragment( 1 );
        } else {
            super.onBackPressed();
        }
    }

    public void Previousbtn(View view) {
        Previous();
    }

    public void Nextbtn(View view) {
        Next(Random);
    }

    public void ShuffleBtn(View view) {
        if(Random){
            Shuffle.setBackgroundColor(Color.parseColor( "#91d5e1" )  );
            Random=false;
        }else{
            Shuffle.setBackgroundColor(Color.parseColor( "#91999b" ));
            Random=true;
        }

    }
}



