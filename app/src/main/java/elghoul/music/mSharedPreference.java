package elghoul.music;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class mSharedPreference {

    private static List<AudioFile> Folders=new ArrayList<>(  );
    private static List<AudioFile> PlayList=new ArrayList<>(  );
    private static List<String> Favourite=new ArrayList<>(  );
    private Context context;

    mSharedPreference(Context context) {
        this.context=context;
    }

    void SaveFolders(List<AudioFile> data){
        SharedPreferences sharedPreference=context.getSharedPreferences(String.valueOf( R.string.app_name ),Context.MODE_PRIVATE );
        SharedPreferences.Editor editor=sharedPreference.edit();
        Gson gson=new Gson();
        String json=gson.toJson( data );

                Folders=data;
                editor.putString( "Folders", json );
                editor.apply();
    }
    void SavePlayList(List<AudioFile> data) {
        SharedPreferences sharedPreference = context.getSharedPreferences( String.valueOf( R.string.app_name ), Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPreference.edit();
        Gson gson = new Gson();
        String json = gson.toJson( data );

        PlayList = data;
        editor.putString( "PlayList", json );
        editor.apply();
    }
    void SaveFavourite(List<String> data) {
        SharedPreferences sharedPreference = context.getSharedPreferences( String.valueOf( R.string.app_name ), Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPreference.edit();
        Gson gson = new Gson();
        String json = gson.toJson( data );

        Favourite = data;
        editor.putString( "Favourite", json );
        editor.apply();
    }


    void LoadAll(){

        SharedPreferences sharedPreferences=context.getSharedPreferences( String.valueOf( R.string.app_name ),Context.MODE_PRIVATE );

        Gson gson=new Gson();
        String json=sharedPreferences.getString("Folders",""  );
        if(!json.isEmpty()) {
            Type type = new TypeToken<List<AudioFile>>() {
            }.getType();
            Folders = gson.fromJson( json, type );
        }
         gson=new Gson();
         json=sharedPreferences.getString("PlayList",""  );
         if(!json.isEmpty()) {
             Type  type = new TypeToken<List<AudioFile>>() {
             }.getType();
             PlayList = gson.fromJson( json, type );
         }
                      gson = new Gson();
             json = sharedPreferences.getString( "Favourite", "" );
        if(!json.isEmpty()) {
            Type  type = new TypeToken<List<String>>() {
             }.getType();
             Favourite = gson.fromJson( json, type );
         }
    }


    public static List<AudioFile> getFolders() {
        return Folders;
    }
    public static List<AudioFile> getPlayList() {
        return PlayList;
    }
    public static List<String> getFavourite() {
        return Favourite;
    }

}
