package elghoul.music;

import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.codekidlabs.storagechooser.StorageChooser;


import java.util.List;


public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    AudioData  audioData;
    RecyclerView recyclerView;
    ImageButton pause,next,previous,play;
    TextView audioname,CurrentTime,TotalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        recyclerView=findViewById( R.id.RecyclerView );
        pause=findViewById( R.id.btnPause );
        next=findViewById( R.id.btnNext );
        play=findViewById( R.id.btnPlay );
        previous=findViewById( R.id.btnPrevious );
        audioname=findViewById( R.id.audioName );
        seekBar=findViewById( R.id.SeekBar );
        CurrentTime=findViewById( R.id.CurrentTime );
        TotalTime=findViewById( R.id.TotalTime );


        mSharedPreference sharedPreference=new mSharedPreference( this );
        sharedPreference.LoadAll();

        if(sharedPreference.getFolders().isEmpty()){
        Directory();
        }
        else{
            recyclerView.setLayoutManager( new LinearLayoutManager( MainActivity.this ) );
            recyclerView.setHasFixedSize( true );
            recyclerView.setAdapter(
                    new FoldersAdapter( MainActivity.this,sharedPreference.Folders
                            ,pause,play,next,previous,audioname,seekBar,CurrentTime,TotalTime ) );
        }
        }

    public void Directory() {
      String Path;
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
                  msg(path);

                  audioData= new AudioData(MainActivity.this,path);

                  recyclerView.setLayoutManager( new LinearLayoutManager( MainActivity.this ) );
                  recyclerView.setHasFixedSize( true );
                  recyclerView.setAdapter(
                          new FoldersAdapter( MainActivity.this,audioData.getFile()
                                  ,pause,play,next,previous,audioname,seekBar,CurrentTime,TotalTime ) );
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

}

