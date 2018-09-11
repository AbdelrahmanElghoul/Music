package elghoul.music;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import java.util.List;

 class Save{

     private SharedPreferences sharedPreference;
    Save(Context context) {
         sharedPreference=context.getSharedPreferences(String.valueOf( R.string.app_name ),Context.MODE_PRIVATE );
    }

    void Folders(List<AudioFile> data){
        SharedPreferences.Editor editor=sharedPreference.edit();
        Gson gson=new Gson();
        String json=gson.toJson( data );

        editor.putString( "Folders", json );
        editor.apply();
    }
    void PlayList(List<AudioFile> data) {
       SharedPreferences.Editor editor = sharedPreference.edit();
        Gson gson = new Gson();
        String json = gson.toJson( data );

        editor.putString( "PlayList", json );
        editor.apply();
    }
    void Favourite(List<String> data) {
     SharedPreferences.Editor editor = sharedPreference.edit();
        Gson gson = new Gson();
        String json = gson.toJson( data );

        editor.putString( "Favourite", json );
        editor.apply();
    }

}