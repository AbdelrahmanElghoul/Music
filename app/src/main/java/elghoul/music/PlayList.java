package elghoul.music;

import java.util.ArrayList;
import java.util.List;

public class PlayList {

    List<String> All(List<AudioFile> Folders){
        List<String> all=new ArrayList<>(  );
        for (AudioFile audioFile:Folders) {
            all.addAll( audioFile.getAudioPath() );
        }
        return all;
    }

}
