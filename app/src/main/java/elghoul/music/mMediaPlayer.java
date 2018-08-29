package elghoul.music;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public abstract class mMediaPlayer {

    private static MediaPlayer mediaPlayer;
    private static List<String> mediaPath;
    private static boolean playing=false;
    private static int index;


    static void setList(List<String> mediaPath){
       mMediaPlayer.mediaPath=mediaPath;
    }

    static void setPlayer(final Context context, int Index){
        if(playing){
            mediaPlayer.release();
            mediaPlayer.create(context, Uri.parse( mediaPath.get( index ) ) );
            mediaPlayer.start();
        }
        else{
            playing=true;
            mediaPlayer=MediaPlayer.create(context, Uri.parse( mediaPath.get( index ) ) );
            mediaPlayer.start();

        }
    }

    static boolean Next(Context context){
        if(!playing)
            return false;
        mediaPlayer.release();
        index=(++index)%mediaPath.size();

        setPlayer( context,index );
        return true;

    }

    static boolean Previous(Context context){
        if(!playing)
            return false;
        mediaPlayer.release();
        index--;
        if(index<0){
            index=mediaPath.size()-1;
        }

        setPlayer( context,index );
        return true;
    }

    static boolean Play(){
        if(!playing)
            return false;
        mediaPlayer.start();
        return true;

    }

    static boolean Pause(){
        if(!playing)
            return false;
        mediaPlayer.pause();
        return true;
    }

    static String getAudioName(){
        return SetName(mediaPath.get( index ));
    }

    static  String SetName(String name){
        String[] arrName=name.split( "/" );
        return arrName[arrName.length-1];
    }
}
