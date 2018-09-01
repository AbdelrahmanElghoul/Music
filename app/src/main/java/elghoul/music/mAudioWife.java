package elghoul.music;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import java.util.List;
import nl.changer.audiowife.AudioWife;

public abstract class mAudioWife extends AppCompatActivity{

    TextView audioName;

    List<String> mediaPath;

    int index;

    void Next(){
        index=(++index)%mediaPath.size();
        AudioWife.getInstance().release();
        AudioWife.getInstance().init( this, Uri.parse( mediaPath.get( index ) ) ).play();
        audioName.setText( mAudioWife.SetName( mediaPath.get( index ) ) );

    }

    void Previous(){
        index--;
        if(index<0){
            index=mediaPath.size()-1;
        }
        AudioWife.getInstance().release();
        AudioWife.getInstance().init( this, Uri.parse( mediaPath.get( index ) ) ).play();
        audioName.setText( mAudioWife.SetName( mediaPath.get( index ) ) );

    }

    static  String SetName(String name){
        String[] arrName=name.split( "/" );
        return arrName[arrName.length-1];
    }

}
