package elghoul.music;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FoldersAdapter extends RecyclerView.Adapter<FoldersAdapter.FolderViewHolder>{

    private List<AudioFile> audioFile;
    private Context context;
    private ImageButton play,next,previous;
    TextView audioname;
    private SeekBar seekBar;
   myMediaPlayer player;
    private int j=0;

    FoldersAdapter(Context context, List<AudioFile> audioFile, ImageButton play, ImageButton next, ImageButton previous, TextView audioname, SeekBar  seekBar) {
        this.audioFile = audioFile;
        this.context = context;
        this.play = play;
        this.next = next;
        this.previous = previous;
        this.audioname = audioname;
        this.seekBar=seekBar;

    }

    @NonNull
    @Override
    public FolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate( R.layout.file,parent,false );
        return new FolderViewHolder( view );
    };

    @Override
    public void onBindViewHolder(@NonNull FolderViewHolder holder, final int position) {
        holder.imageView.setImageResource( R.drawable.folder );
        holder.AudioCount.setText( String.valueOf(audioFile.get( position ).getAudioPath().size()) );

        holder.Name.setText(  myMediaPlayer.SetAudioName( audioFile.get( position ).getFilePath() ));

        ArrayAdapter<String> adapter=new ArrayAdapter<>(context,android.R.layout.simple_list_item_1, new String[]{"Add to list" ,"Shuffle" ,"Remove" } );
        holder.spinner.setAdapter( adapter );
        holder.spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1){

                    player=new myMediaPlayer(    context,audioFile.get( position ).getAudioPath(),play,audioname,seekBar,0 );
                   play.setEnabled( true );
                   previous.setEnabled( true );
                   next.setEnabled( true );

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        } );


play.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       player.btnPlay();
    }
});
next.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       player.btnNext();
    }
} );
previous.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       player.btnPrevious();
    }
} );

seekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {




    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if(myMediaPlayer.playing) {


            myMediaPlayer.exoPlayer.seekTo( i );//Value working  but seek into 0

            Log.e("Duration", String.valueOf( myMediaPlayer.exoPlayer.getDuration() ) );
            Log.e("SeekBar", String.valueOf( i ) );

        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if(myMediaPlayer.playing)
        myMediaPlayer.exoPlayer.seekTo( 0 );
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        player.btnNext();
    }
} );
    }

    @Override
    public int getItemCount() {
       return audioFile.size();
    }

    class FolderViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout relativeLayout;
        ImageView imageView;
        TextView Name;
        Spinner spinner;
        TextView AudioCount;
        public FolderViewHolder(View itemView) {
            super( itemView );
            imageView=itemView.findViewById( R.id.icon );
            Name=itemView.findViewById( R.id.txtName );
            relativeLayout=itemView.findViewById( R.id.layout );
            AudioCount=itemView.findViewById( R.id.audioCount );
            spinner=itemView.findViewById( R.id.FileSpinner );
        }
    }
}
