package elghoul.music;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.FolderViewHolder>{

    private Context context;
    String[] Folders;
    private TypedArray Icons;

    FolderAdapter(Context context, String[] Folders, TypedArray Icons) {
        this.context = context;
        this.Folders=Folders;
        this.Icons=Icons;
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

        int width = (Resources.getSystem().getDisplayMetrics().widthPixels);
        //int height = (Resources.getSystem().getDisplayMetrics().heightPixels);

        holder.imageView.getLayoutParams().width = width / 3;
        //holder.imageView.getLayoutParams().height=height/12;


        holder.textView.setText( Folders[position] );
        holder.imageView.setImageResource( Icons.getResourceId( position, -1 ) );

        holder.imageView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(position==1) {
                    Activity activity = (Activity) context;
                    FragmentManager fragmentManager=((FragmentActivity)activity).getSupportFragmentManager();
                    Folders_Frag folders_frag = new Folders_Frag();

                    Bundle bundle = new Bundle();
                    bundle.putInt( "Type",2 );
                    folders_frag.setArguments( bundle );

                    FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
                    fragmentTransaction.replace( R.id.FolderFrame, folders_frag );
                    fragmentTransaction.commit();

                }
                else
                    Toast.makeText( context, "clicked", Toast.LENGTH_SHORT ).show();
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