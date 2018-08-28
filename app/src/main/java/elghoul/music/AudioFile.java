package elghoul.music;


import java.util.ArrayList;
import java.util.List;


public class AudioFile{


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



}
