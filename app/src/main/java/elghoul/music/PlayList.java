package elghoul.music;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import elghoul.music.DataBase.Load;

public class PlayList {

    private List<AudioFile> PlayList;
    private static int Index=-1;

    public PlayList(Context context){
        PlayList= Load.PlayList(context);
     }

    public  void AddToList(String path,int index){
        Index=index;
        PlayList.get( Index ).setAudioPath( path );
    }
    public  void AddToList(List<String> path,int index){
        Index=index;
        PlayList.get( Index ).getAudioPath().addAll( path );
    }
    public  void RemoveFromList(int index){
      PlayList.get( Index ).getAudioPath().remove( index );
    }

    public  void RemoveList(int index){
        Index=index;
        PlayList.remove( Index );
    }
    public  void NewList(String name){
        PlayList.add( new AudioFile(name) );
    }

    public  List<AudioFile> getPlayList(){
        return PlayList;
    }

    public  List<String> All(List<AudioFile> Folders){
        List<String> all=new ArrayList<>(  );
        for (AudioFile audioFile:Folders) {
            all.addAll( audioFile.getAudioPath() );
        }
        return all;
    }

}
