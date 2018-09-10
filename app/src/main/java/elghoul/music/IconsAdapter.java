package elghoul.music;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

class IconsAdapter extends RecyclerView.Adapter<IconsAdapter.FolderViewHolder>{

    private FragmentStarter fragment;
    private myPlayer player;
    private Context context;
    String[] Folders;
    private TypedArray Icons;

    IconsAdapter(Context context, String[] Folders, TypedArray Icons, FragmentStarter fragments, myPlayer player) {
        this.context = context;
        this.Folders=Folders;
        this.Icons=Icons;
        this.player=player;
        this.fragment=fragments;
    }

    @NonNull
    @Override

    public FolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate( R.layout.folder,parent,false );
        return new FolderViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull FolderViewHolder holder, final int position) {


        //int height = (Resources.getSystem().getDisplayMetrics().heightPixels);
        holder.imageView.getLayoutParams().width = (Resources.getSystem().getDisplayMetrics().widthPixels) / 3;

        holder.textView.setText( Folders[position] );
        holder.imageView.setImageResource( Icons.getResourceId( position, -1 ) );

        holder.imageView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    switch (position) {
                        case 0:
                            player.StartPlayer( new PlayList().All( Load.Folders(context) ),0 );
                            break;
                        case 1:
                            fragment.OpenFragment( "folders" );
                            break;
                        default:
                            Toast.makeText( context, "clicked", Toast.LENGTH_SHORT ).show();
                            break;
                    }
            }
        } );

    }

    @Override
    public int getItemCount() {
        return Folders.length;
    }

    class FolderViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        FolderViewHolder(View itemView) {
            super( itemView );
            imageView=itemView.findViewById( R.id.FolderIcon );
            textView=itemView.findViewById( R.id.FolderName );
        }
    }
}