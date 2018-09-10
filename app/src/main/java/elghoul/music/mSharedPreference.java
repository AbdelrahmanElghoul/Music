package elghoul.music;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

abstract class mSharedPreference {

    private static List<AudioFile> Folders=new ArrayList<>(  );
    private static List<AudioFile> PlayList=new ArrayList<>(  );
    private static List<String> Favourite=new ArrayList<>(  );

   final void setFolders(List<AudioFile> folders) {
        Folders = folders;
    }
   final void setPlayList(List<AudioFile> playList) {
        PlayList = playList;
    }
   final void setFavourite(List<String> favourite) {
        Favourite = favourite;
    }

    static List<AudioFile> getFolders() {
        return Folders;
    }
    static List<AudioFile> getPlayList() {
        return PlayList;
    }
    static List<String> getFavourite() {
        return Favourite;
    }

}
