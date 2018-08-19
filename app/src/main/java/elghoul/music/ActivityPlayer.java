package elghoul.music;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityPlayer extends AppCompatActivity {

    View frag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_player );

        frag=findViewById( R.id.PlayerFrame );

    Intent intent=getIntent();

        ItemsAdapter itemsAdapter=intent.getParcelableExtra( "Adapter" );

        FragmentManager manager = getFragmentManager();
        FolderFragment fragment = new FolderFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable( "Adapter", itemsAdapter );
        fragment.setArguments( bundle );

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace( R.id.player_layout, fragment );
        transaction.commit();


    }
}
