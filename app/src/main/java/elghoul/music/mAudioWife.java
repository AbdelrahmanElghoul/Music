package elghoul.music;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.AbstractMap;
import java.util.List;

import nl.changer.audiowife.AudioWife;

public class mAudioWife {
       AudioWife audioWife;
    private List<String> mediaPath;
    private Context context;
    TextView audioname;
   static boolean playing=false;
    private int index;

    public mAudioWife(final List<String> mediaPath, final Context context, ImageButton pause
            , ImageButton play, ImageButton next, ImageButton previous, final TextView audioname
            , SeekBar seekBar, int index2, final TextView CurrentTime, TextView TotalTime) {
        this.mediaPath = mediaPath;
        this.context = context;
        this.audioname = audioname;
        this.index = index2;
        playing=true;



            this.audioWife=AudioWife.getInstance()
                    .init(context, Uri.parse( mediaPath.get( index ) ) )
                    .setPlayView( play )
                .setPauseView( pause )
                .setRuntimeView( CurrentTime )
                .setTotalTimeView( TotalTime )
                .setSeekBar( seekBar );
            audioname.setText( myMediaPlayer.SetAudioName(mediaPath.get( index ) ) );
            audioWife.play();


        AudioWife.getInstance().addOnCompletionListener( new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
               Next();
            }
        });

next.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Next();
    }
} );
previous.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Previous();
    }
} );

    }

    private void Next(){
        audioWife.release();
        index=(++index)%mediaPath.size();
        audioWife.init( context, Uri.parse( mediaPath.get(  index ) ) );
        audioWife.play();
        audioname.setText( SetAudioName( this.mediaPath.get( index  ) ) );

    }

    private void Previous(){
        audioWife.release();
        index--;
        if(index<0){
            index=mediaPath.size()-1;
        }
        audioWife.init( context, Uri.parse( mediaPath.get(  index ) ) );
        audioWife.play();
        audioname.setText( SetAudioName( this.mediaPath.get( index  ) ) );

    }

    static  String SetAudioName(String name){
        String[] arrName=name.split( "/" );
        return arrName[arrName.length-1];

    }




}
