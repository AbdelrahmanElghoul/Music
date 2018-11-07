package elghoul.music;

import elghoul.music.Index.Index;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import nl.changer.audiowife.AudioWife;

public abstract class mAudioWife extends AppCompatActivity implements mPlayer {

    TextView audioName;
    SeekBar seekBar;
    ImageButton pause,next,previous,play;
    TextView CurrentTime,TotalTime;

   private static List<String> mediaPath;
   private static Deque<Integer> History=new ArrayDeque<>();

   private static int index;
   boolean Random=false;

   void seekBarPlayPause(MotionEvent event){
        float startX=-1;
        float startY=-1;
        boolean Motion=false;

               switch (event.getAction()) {

                   case MotionEvent.ACTION_DOWN: {
                       startX = event.getX();
                       startY = event.getY();
break;
                   }
                   case MotionEvent.ACTION_UP: {
                       float endX = event.getX();
                       float endY = event.getY();
                       if(ClickEvent( startX, endX, startY, endY )){
                           Log.e( "Motion", "Clicked" );
                       }
                       else{
                           Log.e( "Motion", "Moved" );
                       }
                       break;
                   }
               }



   }

   boolean ClickEvent(float startX, float endX, float startY, float endY) {
      int CLICK_ACTION_THRESHOLD=200;
       float differenceX = Math.abs(startX - endX);
       float differenceY = Math.abs(startY - endY);
       Log.e("AngelStartX",String.valueOf( startX ));
       Log.e("AngelendX",String.valueOf( endX ));
       Log.e("AngelStartY",String.valueOf( startY ));
       Log.e("AngelendY",String.valueOf( endY ));
       return (differenceX <5 && differenceY <5);
   }

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

    @SuppressLint("NewApi")
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
            audioName.setText( Name.SetName( mediaPath.get( index ) ) );

        }catch (Exception e){
            Toast.makeText( this, e.getMessage(), Toast.LENGTH_SHORT ).show();
        }
    }

    List<String> getMediaPath(){
        return mediaPath;
    }
}
