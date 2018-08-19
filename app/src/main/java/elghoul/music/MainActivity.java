package elghoul.music;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.codekidlabs.storagechooser.StorageChooser;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    AudioData  audioData;
    RecyclerView recyclerView;
    ImageButton pause,next,previous,play;
    TextView audioName,CurrentTime,TotalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        recyclerView=findViewById( R.id.RecyclerView );
        pause=findViewById( R.id.btnPause );
        next=findViewById( R.id.btnNext );
        play=findViewById( R.id.btnPlay );
        previous=findViewById( R.id.btnPrevious );
        audioName=findViewById( R.id.audioName );
        seekBar=findViewById( R.id.SeekBar );
        CurrentTime=findViewById( R.id.CurrentTime );
        TotalTime=findViewById( R.id.TotalTime );



        mSharedPreference sharedPreference=new mSharedPreference( this );
        sharedPreference.LoadAll();

        if(sharedPreference.getFolders().isEmpty()){
        Directory();
        }
        else{
            recyclerView.setLayoutManager( new GridLayoutManager( MainActivity.this,2 ) );
            recyclerView.setHasFixedSize( true );
            recyclerView.setAdapter( new FolderAdapter( this,getResources().getStringArray(R.array.Folders ),getResources().getIntArray( R.array.FoldersIcon ) ));
          /*  recyclerView.setAdapter(
                    new ItemsAdapter( MainActivity.this,sharedPreference.Folders
                            ,pause,play,next,previous,audioName,seekBar,CurrentTime,TotalTime ) );
       */


        }
        }

    public void Directory() {
      String Path;
      try{
        StorageChooser chooser = new StorageChooser.Builder()
                .withActivity(MainActivity.this)
                .withFragmentManager(getFragmentManager())
                .withMemoryBar(true)
                .showHidden( false )
                .allowCustomPath(true)
                .setType(StorageChooser.DIRECTORY_CHOOSER)
                .filter( StorageChooser.FileType.AUDIO )
                .build();

        chooser.show();

          chooser.setOnSelectListener(new StorageChooser.OnSelectListener() {
              @Override
              public void onSelect(String path) {
                  msg(path);

                  audioData= new AudioData(MainActivity.this,path);

                  recyclerView.setLayoutManager( new LinearLayoutManager( MainActivity.this ) );
                  recyclerView.setHasFixedSize( true );
                  recyclerView.setAdapter(
                          new ItemsAdapter( MainActivity.this,audioData.getFile()
                                  ,pause,play,next,previous,audioName,seekBar,CurrentTime,TotalTime ) );
              }
          });



    }catch (Exception e){
           msg(e.getMessage());
      }
    }

    void msg(String message) {
        Toast.makeText( this, message, Toast.LENGTH_SHORT ).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(audioData!=null){
        mSharedPreference sharedPreference=new mSharedPreference( this );
        sharedPreference.Save(audioData.getFile() ,1 );
    }
}

}

class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.FolderViewHolder>{

    Context context;
    String[] Folders;
    int[] Icons;

    public FolderAdapter(Context context,String[] Folders,int[] Icons) {
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
    public void onBindViewHolder(@NonNull FolderViewHolder holder, int position) {
        holder.textView.setText( Folders[position] );
        holder.imageView.setImageResource(Icons[position]  );

        Log.e("Respurce", String.valueOf( Icons[position] ) );

    }

    @Override
    public int getItemCount() {
       return Folders.length;
    }

    class FolderViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        public FolderViewHolder(View itemView) {
            super( itemView );
            imageView=itemView.findViewById( R.id.FolderIcon );
            textView=itemView.findViewById( R.id.FolderName );
        }
    }
}

