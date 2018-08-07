package elghoul.music;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AudioFile {


    private String FilePath;
    private List<String> AudioPath;

    public String getFilePath() {
        return FilePath;
    }

    public AudioFile(String Path) {
        this.FilePath=Path;
        this.AudioPath = new ArrayList<>(  );
    }

    public List<String> getAudioPath() {
        return AudioPath;
    }

    public void setAudioPath(String audioPath) {
        AudioPath.add( audioPath );
    }
}
