package elghoul.music;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Display;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class mSharedPreference {

    private static List<AudioFile> Folders;
    private static List<AudioFile> PlayList;
    private static List<String> Favourite;
    private Context context;

    mSharedPreference(Context context) {
        Folders =new ArrayList<>( );
        PlayList =new ArrayList<>(  );
        Favourite = new ArrayList<>(  );
        this.context=context;
    }

/**
*  type 1 = Folders
*  type 2 = PlayList
*  type 3 = Favourite
* */
    void Save(List<AudioFile> data,final int type){
        SharedPreferences sharedPreference=context.getSharedPreferences(String.valueOf( R.string.app_name ),Context.MODE_PRIVATE );
        SharedPreferences.Editor editor=sharedPreference.edit();
        Gson gson=new Gson();
        String json=gson.toJson( data );

        switch (type) {
            case 1:
                editor.putString( "Folders", json );
                editor.apply();
                break;

            case 2:
                editor.putString( "PlayList", json );
                editor.apply();
                break;

            case 3:
                editor.putString( "Favourite", json );
                editor.apply();
                break;
        }
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
