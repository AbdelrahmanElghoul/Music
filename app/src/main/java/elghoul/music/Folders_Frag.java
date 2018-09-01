package elghoul.music;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Folders_Frag extends Fragment {

    Communicat communicat;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_folders_,container,false );
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        communicat = (Communicat) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
        recyclerView=getActivity().findViewById( R.id.RecyclerView );


try {
        switch (getArguments().getInt( "Type" )){
            case 1:
                Folders( new FolderAdapter( getActivity()
                        ,getResources().getStringArray( R.array.Folders )
                        ,getResources().obtainTypedArray( R.array.FoldersIcon )
                        ,communicat
                ) );
                break;
            case 2:
                Items( new ItemsAdapter( getActivity(),mSharedPreference.getFolders(),communicat ) );
                break;
        }
    }catch (Exception e){
        Log.e("Error",e.getMessage());
    }



}

    void Folders(FolderAdapter folderAdapter){

        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new GridLayoutManager( getActivity(),getResources().obtainTypedArray( R.array.FoldersIcon ).length() ) );
        recyclerView.setAdapter( folderAdapter );


    }

    void Items(ItemsAdapter itemsAdapter){

        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new LinearLayoutManager( getActivity() ) );
        recyclerView.setAdapter( itemsAdapter );
        }


 }
