package elghoul.music;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;



public class Player {

    private List<String> mediaPath;
    private ImageButton pause;
    private Context context;
    TextView audioname;
    private SeekBar seekBar;

    static MediaPlayer mediaPlayer;
    static boolean playing=false;
    private int index;



    public Player( Context context,List<String> mediaPath, ImageButton pause,TextView audioname,SeekBar seekBar,int index) {
        this.mediaPath = mediaPath;
        this.pause = pause;
        this.context = context;
        this.index=index;
        this.audioname=audioname;
        this.seekBar=seekBar;

         mediaPlayer=MediaPlayer.create(context, Uri.parse( this.mediaPath.get( index  )) );
            btnPlay();
        audioname.setText( SetAudioName( this.mediaPath.get( index  ) ) );
        PlayList( );


         playing=true;

    }

    public void setSeekBar(){

seekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        Toast.makeText( context,String.valueOf( i ),Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        mediaPlayer.seekTo( 0 );
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
            PlayList();
    }
} );

    }
    public void btnNext(){
        mediaPlayer.release();
        if(index==mediaPath.size()-1){
            index=-1;
        }
        mediaPlayer= MediaPlayer.create( context, Uri.parse( mediaPath.get( ++index )  ) );
        mediaPlayer.start();
        audioname.setText( SetAudioName( this.mediaPath.get( index  ) ) );

        PlayList( );
    }

    public  void btnPlay(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            pause.setImageResource(  R.drawable.play );
        }
        else{
            mediaPlayer.start();
            pause.setImageResource( R.drawable.pause );
        }

    }

    public void btnPrevious(){

        mediaPlayer.release();
        if(index==0){
            index=mediaPath.size();
        }
        mediaPlayer= MediaPlayer.create( context, Uri.parse( mediaPath.get( --index ) ) );
        mediaPlayer.start();
        audioname.setText( SetAudioName( this.mediaPath.get( index  ) ) );

        PlayList(  );

    }

    public void PlayList(){

        mediaPlayer.setNextMediaPlayer( MediaPlayer.create( context,Uri.parse(mediaPath.get( ( index=((++index)%mediaPath.size() )) ) ) ));

        SetAudioName( mediaPath.get( index ) );


    }

  static  String SetAudioName(String name){
        String[] arrName=name.split( "/" );
        return arrName[arrName.length-1];

    }


}
