package elghoul.music;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

import nl.changer.audiowife.AudioWife;

public abstract class mAudioWife extends AppCompatActivity implements myPlayer{

    TextView audioName;
    SeekBar seekBar;
    ImageButton pause,next,previous,play;
    TextView CurrentTime,TotalTime;

   private List<String> mediaPath;
   private Deque<Integer> History=new ArrayDeque<>();

    int index;
    boolean Random=false;

    void Next(boolean Random){
       index=new Index().getNext( Random,index,mediaPath.size() ).getIndex();

        AudioWife.getInstance().release();
        AudioWife.getInstance().init( this, Uri.parse( mediaPath.get( index ) ) ).play();
        audioName.setText( mAudioWife.SetName( mediaPath.get( index ) ) );
        History.push( index );

        Log.e( "Next Index", String.valueOf( index ) );
    }

    void Previous(){
        index=new Index().getPrevious( index,History,mediaPath.size() ).getIndex();

        AudioWife.getInstance().release();
        AudioWife.getInstance().init( this, Uri.parse( mediaPath.get( index ) ) ).play();
        audioName.setText( mAudioWife.SetName( mediaPath.get( index ) ) );

        Log.e( "Previous Index", String.valueOf( index ) );
    }

    static  String SetName(String name){
        String[] arrName=name.split( "/" );
        return arrName[arrName.length-1];
    }

    @Override
    public void StartPlayer(List<String> list) {
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

            audioName.setText( mAudioWife.SetName( mediaPath.get( 0 ) ) );

        }catch (Exception e){
            Log.e( "Player Error", e.getMessage());
        }
    }


}
