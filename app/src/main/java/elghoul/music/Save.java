package elghoul.music;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import java.util.List;

 class Save{

    private final Context context;

    Save(Context context) {
        this.context = context;
    }

    void Folders(List<AudioFile> data){
        SharedPreferences sharedPreference=context.getSharedPreferences(String.valueOf( R.string.app_name ),Context.MODE_PRIVATE );
        SharedPreferences.Editor editor=sharedPreference.edit();
        Gson gson=new Gson();
        String json=gson.toJson( data );

        editor.putString( "Folders", json );
        editor.apply();
    }
    void PlayList(List<AudioFile> data) {
        SharedPreferences sharedPreference = context.getSharedPreferences( String.valueOf( R.string.app_name ), Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPreference.edit();
        Gson gson = new Gson();
        String json = gson.toJson( data );

        editor.putString( "PlayList", json );
        editor.apply();
    }
    void Favourite(List<String> data) {
        SharedPreferences sharedPreference = context.getSharedPreferences( String.valueOf( R.string.app_name ), Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPreference.edit();
        Gson gson = new Gson();
        String json = gson.toJson( data );

        //  Favourite = data;
        editor.putString( "Favourite", json );
        editor.apply();
    }

}