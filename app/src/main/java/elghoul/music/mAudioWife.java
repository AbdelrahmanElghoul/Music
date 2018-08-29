package elghoul.music;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.AbstractMap;
import java.util.List;

import nl.changer.audiowife.AudioWife;

public abstract class mAudioWife{


    private static AudioWife audioWife;
    private static List<String> mediaPath;
    private static boolean playing=false;
    private static int index;


    static void setList(List<String> mediaPath){
        mAudioWife.mediaPath=mediaPath;
    }

    static void setPlayer(final Context context,int Index){
        if(playing){
            audioWife.release();
             audioWife.init(context, Uri.parse( mediaPath.get( index ) ) );
            audioWife.play();

        }
        else{
        playing=true;
             audioWife.init(context, Uri.parse( mediaPath.get( index ) ) );
            audioWife.play();

        }
    }

     static void Next(Context context){
        if(!playing)
            return;
        audioWife.release();
        index=(++index)%mediaPath.size();

        setPlayer( context,index );

    }

     static void Previous(Context context){
       if(!playing)
           return;
        audioWife.release();
        index--;
        if(index<0){
            index=mediaPath.size()-1;
        }

         setPlayer( context,index );
    }

     static  String SetName(String name){
        String[] arrName=name.split( "/" );
        return arrName[arrName.length-1];
    }


}
