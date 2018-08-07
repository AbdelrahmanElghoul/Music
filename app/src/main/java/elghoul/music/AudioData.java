package elghoul.music;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class AudioData {


    private Context context;
    private  List<AudioFile>File;

    AudioData(Context context, String Path) {
        this.context = context;
        this.File = new ArrayList<>(  );
        Data( Path );
        msg( "Done" );
    }

    private void Data(String Path) {
        Boolean Directory=false;
        File file = new File( Path );
        File[] files = file.listFiles();
        AudioFile audioFile=new AudioFile( Path );
        for (int i = 0; i < files.length; i++) {
                if(files[i].isDirectory()){
                    Directory=true;
                }
                else if(files[i].isFile() && files[i].toString().endsWith( ".mp3" )){
                   audioFile.setAudioPath( files[i].getPath() );
                }

        }
        if(!audioFile.getAudioPath().isEmpty()){
        File.add( audioFile );
        }
        if(Directory){
            for (int i=0;i<files.length;i++){
                if(files[i].isDirectory()){
                    Data( files[i].getPath() );
                }
            }
        }


        }




    public List<AudioFile> getFile() {
        return File;
    }

    void msg(String message) {
        Toast.makeText( context, message, Toast.LENGTH_SHORT ).show();
    }
}
