package elghoul.music;


import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.PlayerMessage;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;

import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

public  class myExoPlayer implements ExoPlayer.EventListener{

    private List<String> mediaPath;
    private ImageButton pause;
    private Context context;
    TextView audioname;
    private SeekBar seekBar;
    static boolean playing=false;
    private int index;

  public static SimpleExoPlayer exoPlayer;

    public myExoPlayer(Context context, List<String> mediaPath, ImageButton pause, TextView audioname, SeekBar seekBar, int index) {
        this.mediaPath = mediaPath;
        this.pause = pause;
        this.context = context;
        this.audioname = audioname;
        this.seekBar = seekBar;
        this.index = index;

        InsializingExoPlayer();
        playing=true;



    }



    private void InsializingExoPlayer(){

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory( bandwidthMeter );
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        exoPlayer= ExoPlayerFactory.newSimpleInstance(  context,trackSelector,new DefaultLoadControl(  ) );
        btnPlay();
        exoPlayer.addListener( this );
        exoPlayer.prepare(settingMedia( index ));
        exoPlayer.setPlayWhenReady(true);// to play video when ready. Use false to pause a video

        audioname.setText( SetAudioName( this.mediaPath.get( index  ) ) );
        seekBar.setMax( (MediaPlayer.create( context, Uri.parse( mediaPath.get( index ) ) )).getDuration() );
        PlayCycle();

        Log.e( "Duration", String.valueOf( seekBar.getMax() ) );



        //PlayList();

    }

private MediaSource settingMedia(int i){

    DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context , Util.getUserAgent(context, String.valueOf( R.string.app_name ) ));
    ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
    seekBar.setMax( (MediaPlayer.create( context, Uri.parse( mediaPath.get( i ) ) )).getDuration() );
    return new ExtractorMediaSource( Uri.parse(mediaPath.get( i )),dataSourceFactory,extractorsFactory,null,null,null);

}

private void PlayList(){
    /*    exoPlayer.prepare( settingMedia((++index)%mediaPath.size() ));
        if(exoPlayer.getCurrentPosition()==exoPlayer.getDuration()){
            exoPlayer.setPlayWhenReady( true );
            audioname.setText( SetAudioName( this.mediaPath.get( index  ) ) );
        }
*/
}



    public void btnNext(){
       exoPlayer.release();
        if(index==mediaPath.size()-1){
            index=-1;
        }
        exoPlayer.prepare(settingMedia( ++index ));
        exoPlayer.setPlayWhenReady(true);
        audioname.setText( SetAudioName( this.mediaPath.get( index  ) ) );

        PlayList( );
    }

    public  void btnPlay(){

        exoPlayer.setPlayWhenReady(!exoPlayer.getPlayWhenReady());
        if(exoPlayer.getPlayWhenReady()){
          pause.setImageResource(  R.drawable.pause );
          PlayCycle();
        }
        else{
          pause.setImageResource( R.drawable.play );
        }

    }

    void PlayCycle(){
        seekBar.setProgress( (int) exoPlayer.getCurrentPosition() );
        if(exoPlayer.getPlayWhenReady()){
            new Runnable() {
                @Override
                public void run() {
                    PlayCycle();
                }
            };
        }
    }


    public void btnPrevious(){
        exoPlayer.release();
        if(index==0){
            index=mediaPath.size();
        }
        exoPlayer.prepare(settingMedia( --index ));
        exoPlayer.setPlayWhenReady(true);
        audioname.setText( SetAudioName( this.mediaPath.get( index  ) ) );
        PlayList(  );

    }

    static  String SetAudioName(String name){
        String[] arrName=name.split( "/" );
        return arrName[arrName.length-1];
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
        Log.e("Player Error",error.getMessage());
    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }



}

class media implements ExoPlayer{
    @Override
    public Looper getPlaybackLooper() {
        return null;
    }

    @Override
    public void prepare(MediaSource mediaSource) {

    }

    @Override
    public void prepare(MediaSource mediaSource, boolean resetPosition, boolean resetState) {

    }

    @Override
    public PlayerMessage createMessage(PlayerMessage.Target target) {
        return null;
    }

    @Override
    public void sendMessages(ExoPlayerMessage... messages) {

    }

    @Override
    public void blockingSendMessages(ExoPlayerMessage... messages) {

    }

    @Override
    public void setSeekParameters(@Nullable SeekParameters seekParameters) {

    }

    @Nullable
    @Override
    public VideoComponent getVideoComponent() {
        return null;
    }

    @Nullable
    @Override
    public TextComponent getTextComponent() {
        return null;
    }

    @Override
    public void addListener(Player.EventListener listener) {

    }

    @Override
    public void removeListener(Player.EventListener listener) {

    }

    @Override
    public int getPlaybackState() {
        return 0;
    }

    @Nullable
    @Override
    public ExoPlaybackException getPlaybackError() {
        return null;
    }

    @Override
    public void setPlayWhenReady(boolean playWhenReady) {

    }

    @Override
    public boolean getPlayWhenReady() {
        return false;
    }

    @Override
    public void setRepeatMode(int repeatMode) {

    }

    @Override
    public int getRepeatMode() {
        return 0;
    }

    @Override
    public void setShuffleModeEnabled(boolean shuffleModeEnabled) {

    }

    @Override
    public boolean getShuffleModeEnabled() {
        return false;
    }

    @Override
    public boolean isLoading() {
        return false;
    }

    @Override
    public void seekToDefaultPosition() {

    }

    @Override
    public void seekToDefaultPosition(int windowIndex) {

    }

    @Override
    public void seekTo(long positionMs) {

    }

    @Override
    public void seekTo(int windowIndex, long positionMs) {

    }

    @Override
    public void setPlaybackParameters(@Nullable PlaybackParameters playbackParameters) {

    }

    @Override
    public PlaybackParameters getPlaybackParameters() {
        return null;
    }

    @Override
    public void stop() {

    }

    @Override
    public void stop(boolean reset) {

    }

    @Override
    public void release() {

    }

    @Override
    public int getRendererCount() {
        return 0;
    }

    @Override
    public int getRendererType(int index) {
        return 0;
    }

    @Override
    public TrackGroupArray getCurrentTrackGroups() {
        return null;
    }

    @Override
    public TrackSelectionArray getCurrentTrackSelections() {
        return null;
    }

    @Nullable
    @Override
    public Object getCurrentManifest() {
        return null;
    }

    @Override
    public Timeline getCurrentTimeline() {
        return null;
    }

    @Override
    public int getCurrentPeriodIndex() {
        return 0;
    }

    @Override
    public int getCurrentWindowIndex() {
        return 0;
    }

    @Override
    public int getNextWindowIndex() {
        return 0;
    }

    @Override
    public int getPreviousWindowIndex() {
        return 0;
    }

    @Nullable
    @Override
    public Object getCurrentTag() {
        return null;
    }

    @Override
    public long getDuration() {
        return 0;
    }

    @Override
    public long getCurrentPosition() {
        return 0;
    }

    @Override
    public long getBufferedPosition() {
        return 0;
    }

    @Override
    public int getBufferedPercentage() {
        return 0;
    }

    @Override
    public boolean isCurrentWindowDynamic() {
        return false;
    }

    @Override
    public boolean isCurrentWindowSeekable() {
        return false;
    }

    @Override
    public boolean isPlayingAd() {
        return false;
    }

    @Override
    public int getCurrentAdGroupIndex() {
        return 0;
    }

    @Override
    public int getCurrentAdIndexInAdGroup() {
        return 0;
    }

    @Override
    public long getContentPosition() {
        return 0;
    }
}