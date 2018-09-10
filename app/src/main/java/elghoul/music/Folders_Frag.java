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
import android.widget.Toast;

public class Folders_Frag extends Fragment {

    FragmentStarter fragment;
    RecyclerView recyclerView;
    myPlayer player;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_folders_,container,false );
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        fragment = (FragmentStarter) context;
        player=(myPlayer)context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
        recyclerView=getActivity().findViewById( R.id.RecyclerView );


try {
        switch (getArguments().getString( "Type" ).toLowerCase()){
            case "icons":
                Icons( new IconsAdapter( getActivity()
                        ,getResources().getStringArray( R.array.Folders )
                        ,getResources().obtainTypedArray( R.array.FoldersIcon )
                        , fragment,player
                ) );
                break;
            case "folders":
                Folders( new FoldersAdapter( getActivity(),mSharedPreference.getFolders(), player ) );
                Log.e( "Fragment", String.valueOf( mSharedPreference.getFolders().size() ) );
                break;
            default:
                Toast.makeText( getContext(), "Error Occured", Toast.LENGTH_SHORT ).show();
        }
    }catch (Exception e){
        Log.e("Error",e.getMessage());
    }



}

    private void Icons(IconsAdapter iconsAdapter){

        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new GridLayoutManager( getActivity(),getResources().obtainTypedArray( R.array.FoldersIcon ).length() ) );
        recyclerView.setAdapter( iconsAdapter );


    }

    private void Folders(FoldersAdapter foldersAdapter){

        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new LinearLayoutManager( getActivity() ) );
        recyclerView.setAdapter( foldersAdapter );
        }

 }
