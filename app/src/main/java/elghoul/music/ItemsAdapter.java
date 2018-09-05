package elghoul.music;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private myPlayer player;
    private List<AudioFile> audioFile;
    private Context context;

    ItemsAdapter(Context context, List<AudioFile> audioFile,myPlayer player) {
        this.audioFile = audioFile;
        this.context = context;
        this.player = player;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate( R.layout.file,parent,false );
        return new ItemViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        holder.imageView.setImageResource( R.drawable.folder );
        holder.AudioCount.setText(String.valueOf(  audioFile.get( position ).getAudioPath().size()));

        holder.Name.setText(  mAudioWife.SetName( audioFile.get( position ).getFile() ));

        ArrayAdapter<String> adapter=new ArrayAdapter<>(context,android.R.layout.simple_list_item_1, new String[]{"Add to list" ,"Play" ,"Remove" } );
        holder.spinner.setAdapter( adapter );
        holder.spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i==1){
                        player.StartPlayer( audioFile.get( position ).getAudioPath() );
                  }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        } );

    }

    @Override
    public int getItemCount() {
       return audioFile.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout relativeLayout;
        ImageView imageView;
        TextView Name;
        Spinner spinner;
        TextView AudioCount;
        public ItemViewHolder(View itemView) {
            super( itemView );
            imageView=itemView.findViewById( R.id.icon );
            Name=itemView.findViewById( R.id.txtName );
            relativeLayout=itemView.findViewById( R.id.layout );
            AudioCount=itemView.findViewById( R.id.audioCount );
            spinner=itemView.findViewById( R.id.FileSpinner );
        }
    }

}
