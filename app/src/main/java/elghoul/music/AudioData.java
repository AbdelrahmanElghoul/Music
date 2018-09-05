package elghoul.music;

import android.app.Activity;
import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

import com.codekidlabs.storagechooser.StorageChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AudioData{

    private List<AudioFile> File;

    AudioData(Activity activity) {
        this.File = new ArrayList<>();
        Directory( activity );
        Log.e( "Filter","Done" );
        Log.e( "Size", String.valueOf( getFile().size() ) );
    }

    public List<AudioFile> getFile() {
        return File;
    }

    private void Directory(final Activity activity) {
        try{
            StorageChooser chooser = new StorageChooser.Builder()
                    .withActivity(activity)
                    .withFragmentManager(activity.getFragmentManager())
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
                    Filter( path );
                    new mSharedPreference( activity.getApplicationContext()).Save(  getFile(),1);

                }
            });
        }catch (Exception e){
            Log.e("Directory",e.getMessage());
        }
    }

    private void Filter(String Path) {
        Boolean Directory = false;
        File file = new File( Path );
        File[] files = file.listFiles();
        AudioFile audioFile = new AudioFile( Path );
        for (java.io.File file2 : files) {
            if (file2.isDirectory()) {
                Directory = true;
            } else if (file2.isFile() && file2.toString().endsWith( ".mp3" )) {
                audioFile.setAudioPath( file2.getPath() );
            }

        }
        if (!audioFile.getAudioPath().isEmpty()) {
            File.add( audioFile );
        }
        if (Directory) {
            for (java.io.File file1 : files) {
                if (file1.isDirectory()) {
                    Filter( file1.getPath() );
                }
            }
        }


    }

}
