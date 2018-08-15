package elghoul.music;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AudioFile {


    private String File; //Folder Path  / Playlist name
    private List<String> AudioPath;

    public String getFile() {
        return File;
    }

    public AudioFile(String Path) {
        this.File=Path;
        this.AudioPath = new ArrayList<>(  );
    }

    public List<String> getAudioPath() {
        return AudioPath;
    }

    public void setAudioPath(String audioPath) {
        AudioPath.add( audioPath );
    }
}
