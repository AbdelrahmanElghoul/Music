package elghoul.music;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import nl.changer.audiowife.AudioWife;

public abstract class mAudioWife extends AppCompatActivity implements myPlayer{

    TextView audioName;
    SeekBar seekBar;
    ImageButton pause,next,previous,play;
    TextView CurrentTime,TotalTime;

   private static List<String> mediaPath;
   private static Deque<Integer> History=new ArrayDeque<>();

   private static int index;
   boolean Random=false;

    void Next(){
       index=new Index().getNext( Random,index,mediaPath.size() ).getIndex();
       History.push( index );

        StartPlayer( mediaPath,index );

        AudioWife.getInstance().addOnCompletionListener( new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Next();
            }
        } );

        Log.e( "Next Index", String.valueOf( index ) );
    }

    void Previous(){
        index=new Index().getPrevious( index,History,mediaPath.size() ).getIndex();

        StartPlayer( mediaPath,index );
        Log.e( "Previous Index", String.valueOf( index ) );
    }

    @Override
    public void StartPlayer(List<String> list,int Index) {
        try {
            mediaPath=list;

            AudioWife.getInstance().release();
            AudioWife.getInstance().init(this, Uri.parse( list.get( Index ) ) )
                    .setPlayView( play )
                    .setPauseView( pause )
                    .setSeekBar( seekBar )
                    .setTotalTimeView( TotalTime )
                    .setRuntimeView( CurrentTime )
                    .play();

            audioName.setText( Name.SetName( mediaPath.get( 0 ) ) );

        }catch (Exception e){
            Toast.makeText( this, e.getMessage(), Toast.LENGTH_SHORT ).show();
            Next();
        }
    }

}
