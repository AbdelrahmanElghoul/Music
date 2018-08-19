package elghoul.music;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> implements Parcelable {

    mAudioWife mAudioWife;
    private List<AudioFile> audioFile;
    private Context context;
    private ImageButton play,next,previous,pause;
    TextView audioname,CurrentTime,TotalTime;
    private SeekBar seekBar;
    myExoPlayer player;


    ItemsAdapter(Context context, List<AudioFile> audioFile,ImageButton pause, ImageButton play
            , ImageButton next, ImageButton previous, TextView audioname, SeekBar  seekBar
            ,TextView CurrentTime,TextView TotalTime) {

        this.audioFile = audioFile;
        this.context = context;
        this.play = play;
        this.next = next;
        this.pause=pause;
        this.previous = previous;
        this.audioname = audioname;
        this.seekBar=seekBar;
        this.CurrentTime=CurrentTime;
        this.TotalTime= TotalTime;

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

        holder.Name.setText(  myExoPlayer.SetAudioName( audioFile.get( position ).getFile() ));

        ArrayAdapter<String> adapter=new ArrayAdapter<>(context,android.R.layout.simple_list_item_1, new String[]{"Add to list" ,"Shuffle" ,"Remove" } );
        holder.spinner.setAdapter( adapter );
        holder.spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1){

                    if(elghoul.music.mAudioWife.playing){
                        mAudioWife.audioWife.release();
                    }
                  mAudioWife= new mAudioWife(audioFile.get( position ).getAudioPath(),context,pause,play,next,previous,audioname,seekBar,0,CurrentTime,TotalTime);
                  }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        } );

/*
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
        if(myExoPlayer.playing) {

            myExoPlayer.exoPlayer.seekTo( i );
            Log.e("Player" , String.valueOf( myExoPlayer.exoPlayer.getPlayWhenReady() ) );
            Log.e( "Max", String.valueOf( seekBar.getMax() ) );
            Log.e("Duration", String.valueOf( myExoPlayer.exoPlayer.getDuration() ) );
            Log.e( "Seek", String.valueOf( i ) );

            }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if(myExoPlayer.playing)
        myExoPlayer.exoPlayer.seekTo( 0 );
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        player.btnNext();
    }
} );*/
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable( (Parcelable) this.mAudioWife, flags );
        dest.writeList( this.audioFile );

    }

    protected ItemsAdapter(Parcel in) {
        this.mAudioWife = in.readParcelable( elghoul.music.mAudioWife.class.getClassLoader() );
        this.audioFile = new ArrayList<AudioFile>();
        in.readList( this.audioFile, AudioFile.class.getClassLoader() );



    }

    public static final Creator<ItemsAdapter> CREATOR = new Creator<ItemsAdapter>() {
        @Override
        public ItemsAdapter createFromParcel(Parcel source) {
            return new ItemsAdapter( source );
        }

        @Override
        public ItemsAdapter[] newArray(int size) {
            return new ItemsAdapter[size];
        }
    };
}
