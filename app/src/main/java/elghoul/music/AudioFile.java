package elghoul.music;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;


public class AudioFile implements Parcelable {


    private String File; //Folder Path  / Playlist name
    private List<String> AudioPath;

    public AudioFile(String Path) {
        this.File=Path;
        this.AudioPath = new ArrayList<>(  );
    }

    public String getFile() {
        return File;
    }

    public List<String> getAudioPath() {
        return AudioPath;
    }

    public void setAudioPath(String audioPath) {
        AudioPath.add( audioPath );
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString( this.File );
        dest.writeStringList( this.AudioPath );
    }

    protected AudioFile(Parcel in) {
        this.File = in.readString();
        this.AudioPath = in.createStringArrayList();
    }

    public static final Parcelable.Creator<AudioFile> CREATOR = new Parcelable.Creator<AudioFile>() {
        @Override
        public AudioFile createFromParcel(Parcel source) {
            return new AudioFile( source );
        }

        @Override
        public AudioFile[] newArray(int size) {
            return new AudioFile[size];
        }
    };
}
