package elghoul.music;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class AudioData implements Parcelable {


    private List<AudioFile> File;

    AudioData(Context context, String Path) {
        this.File = new ArrayList<>();
        Data( Path );
        mSharedPreference sharedPreference=new mSharedPreference( context );
    }

    private void Data(String Path) {
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
                    Data( file1.getPath() );
                }
            }
        }


    }

    public List<AudioFile> getFile() {
        return File;
    }

    public void setFile(List<AudioFile> file) {
        File = file;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList( this.File );
    }

    protected AudioData(Parcel in) {
        this.File = in.createTypedArrayList( AudioFile.CREATOR );
    }

    public static final Parcelable.Creator<AudioData> CREATOR = new Parcelable.Creator<AudioData>() {
        @Override
        public AudioData createFromParcel(Parcel source) {
            return new AudioData( source );
        }

        @Override
        public AudioData[] newArray(int size) {
            return new AudioData[size];
        }
    };
}
