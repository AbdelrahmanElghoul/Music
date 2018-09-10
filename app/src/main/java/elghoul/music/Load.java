package elghoul.music;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

class Load {

    private SharedPreferences sharedPreferences;
    private static List<AudioFile> Folders=new ArrayList<>(  );
    private static List<AudioFile> PlayList=new ArrayList<>(  );
    private static List<String> Favourite=new ArrayList<>(  );


    private Load(Context context){
        sharedPreferences=context.getSharedPreferences( String.valueOf( R.string.app_name ),Context.MODE_PRIVATE );
    }

    private List<AudioFile> Folders(){
        Gson gson=new Gson();
        String json=sharedPreferences.getString("Folders",""  );
        if(!json.isEmpty()) {
            Type type = new TypeToken<List<AudioFile>>() {
            }.getType();
            return  ( gson.fromJson( json, type ) );
        }
        return new ArrayList<>(  );
    }
    private List<AudioFile> PlayList() {
        Gson gson=new Gson();
        String json=sharedPreferences.getString("PlayList",""  );
        if(!json.isEmpty()) {
            Type  type = new TypeToken<List<AudioFile>>() {
            }.getType();
            return ( gson.fromJson( json, type ) );
        }
        return new ArrayList<>(  );
    }
    private List<String> Favourite() {
        Gson  gson = new Gson();
        String json = sharedPreferences.getString( "Favourite", "" );
        if(!json.isEmpty()) {
            Type  type = new TypeToken<List<String>>() {
            }.getType();
            return ( gson.fromJson( json, type ) );
        }
        return new ArrayList<>(  );
    }


    static List<AudioFile> Folders(Context context) {
        if(Folders.isEmpty())
            return new Load(context).Folders();
        else
            return Folders;
    }
    static List<AudioFile> PlayList(Context context) {
       if(PlayList.isEmpty())
           return new Load(context).PlayList();
       else
           return PlayList;
    }
    static List<String> Favourite(Context context) {
        if(Favourite.isEmpty())
            return new Load(context).Favourite();
        else
            return Favourite;
    }

}