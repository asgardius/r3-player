package asgardius.page.r3player;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.upstream.HttpDataSource;

public class MainActivity extends AppCompatActivity {
    private String stationname;
    private String trackname;
    public int stationid = 0;
    private boolean isplaying = false;
    private int secretcount = 0;
    private TextView sn;
    private TextView tn;
    private ImageView logo;
    private WifiManager.WifiLock mWifiLock;
    private PowerManager.WakeLock mWakeLock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // create Wifi and wake locks
        mWifiLock = ((WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE)).createWifiLock(WifiManager.WIFI_MODE_FULL_HIGH_PERF, "Transistor:wifi_lock");
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        mWakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Transistor:wake_lock");
        //Radio station name indication object
        sn = (TextView)findViewById(R.id.stationname);
        tn = (TextView)findViewById(R.id.trackname);
        logo = (ImageView)findViewById(R.id.logo);
        //Initializing ExoPlayer backend
        ExoPlayer player = new ExoPlayer.Builder(this).build();

        Button stopplayer = (Button)findViewById(R.id.stop);
        stopplayer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (isplaying) {
                    // Stop playback
                    player.stop();
                    tn.setText("");
                } else {
                    //Trigger easter egg
                    if (secretcount >= 8) {
                        secretcount = 0;
                        viewsecret();
                    }
                    else {
                        secretcount = secretcount + 1;
                        //System.out.println("Secret " + secretcount);
                    }
                }
            }
        });
        Button gotonews = (Button)findViewById(R.id.r3news);
        gotonews.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                goToR3news();
            }
        });
        Button st1play = (Button)findViewById(R.id.st1button);
        st1play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (stationid!=1) {
                    //Get radio station url
                    MediaItem mediaItem = MediaItem.fromUri(getResources().getString(R.string.st1url));
                    player.setMediaItem(mediaItem);
                    //Set current station name
                    stationname = getResources().getString(R.string.st1name);
                    // Prepare the player.
                    player.prepare();
                    // Start the playback.
                    player.play();
                    stationid = 1;
                }
            }
        });
        Button st4play = (Button)findViewById(R.id.st4button);
        st4play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (stationid!=4) {
                    //Get radio station url
                    MediaItem mediaItem = MediaItem.fromUri(getResources().getString(R.string.st4url));
                    player.setMediaItem(mediaItem);
                    stationname = getResources().getString(R.string.st4name);
                    // Prepare the player.
                    player.prepare();
                    // Start the playback.
                    player.play();
                    stationid = 4;
                }
            }
        });
        Button st3play = (Button)findViewById(R.id.st3button);
        st3play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (stationid!=3) {
                    //Get radio station url
                    MediaItem mediaItem = MediaItem.fromUri(getResources().getString(R.string.st3url));
                    player.setMediaItem(mediaItem);
                    //Set current station name
                    stationname = getResources().getString(R.string.st3name);
                    // Prepare the player.
                    player.prepare();
                    // Start the playback.
                    player.play();
                    stationid = 3;
                }
            }
        });
        Button st2play = (Button)findViewById(R.id.st2button);
        st2play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (stationid!=2) {
                    //Get radio station url
                    MediaItem mediaItem = MediaItem.fromUri(getResources().getString(R.string.st2url));
                    player.setMediaItem(mediaItem);
                    //Set current station name
                    stationname = getResources().getString(R.string.st2name);
                    // Prepare the player.
                    player.prepare();
                    // Start the playback.
                    player.play();
                    stationid = 2;
                }
            }
        });
        Button st5play = (Button)findViewById(R.id.st5button);
        st5play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (stationid!=5) {
                    //Get radio station url
                    MediaItem mediaItem = MediaItem.fromUri(getResources().getString(R.string.st5url));
                    player.setMediaItem(mediaItem);
                    //Set current station name
                    stationname = getResources().getString(R.string.st5name);
                    // Prepare the player.
                    player.prepare();
                    // Start the playback.
                    player.play();
                    stationid = 5;
                }
            }
        });
        Button spanishstart = (Button)findViewById(R.id.st6button);
        spanishstart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (stationid!=6) {
                    //Get radio station url
                    MediaItem mediaItem = MediaItem.fromUri(getResources().getString(R.string.st6url));
                    player.setMediaItem(mediaItem);
                    //Set current station name
                    stationname = getResources().getString(R.string.st6name);
                    // Prepare the player.
                    player.prepare();
                    // Start the playback.
                    player.play();
                    stationid = 6;
                }
            }
        });
        //int test = Player.STATE_IDLE;
        //System.out.println("Player state "+test);
        player.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(@Player.State int state) {
                if (state == 3) {
                    // Active playback.
                    isplaying = true;
                    //Station 5 does not display metadata
                    if (stationid==5) {
                        tn.setText(getResources().getString(R.string.noinfo));
                        logo.setImageResource(R.drawable.buho);
                    } else {
                        logo.setImageResource(R.drawable.asteroid);
                    }
                    sn.setText(stationname);
                    //Acquiring WakeLock and WifiLock if not held
                    if (!mWifiLock.isHeld()) {
                        mWifiLock.acquire();
                        //System.out.println("WifiLock acquired");
                    }
                    if (!mWakeLock.isHeld()) {
                        mWakeLock.acquire();
                        //System.out.println("WakeLock acquired");
                    }
                } else if (state == 2) {
                    // Buffering.
                    isplaying = true;
                    sn.setText(R.string.buffering);
                    tn.setText("");
                    //Acquiring WakeLock and WifiLock if not held
                    if (!mWifiLock.isHeld()) {
                        mWifiLock.acquire();
                        //System.out.println("WifiLock acquired");
                    }
                    if (!mWakeLock.isHeld()) {
                        mWakeLock.acquire();
                        //System.out.println("WakeLock acquired");
                    }
                } else {
                    //Player inactive
                    secretcount = 0;
                    isplaying = false;
                    sn.setText(R.string.stationinfo);
                    stationid = 0;
                    logo.setImageResource(R.drawable.telescope);
                    //tn.setText("");
                    //Releasing WifiLock and WakeLock if held
                    if (mWifiLock.isHeld()) {
                        mWifiLock.release();
                        //System.out.println("WifiLock released");
                    }
                    if (mWakeLock.isHeld()) {
                        mWakeLock.release();
                        //System.out.println("WakeLock released");
                    }
                    // Not playing because playback is paused, ended, suppressed, or the player
                    // is buffering, stopped or failed. Check player.getPlayWhenReady,
                    // player.getPlaybackState, player.getPlaybackSuppressionReason and
                    // player.getPlaybackError for details.
                }
            }
            public void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
                //Station 5 does not display metadata
                if (stationid!=5) {
                    trackname = (String) mediaMetadata.title;
                    tn.setText(trackname);
                    //System.out.println(trackname);
                }
            }

            public void onPlayerError(PlaybackException error) {
                Throwable cause = error.getCause();
                if (cause instanceof HttpDataSource.HttpDataSourceException) {
                    // An HTTP error occurred.
                    tn.setText(R.string.connfail);
                    //System.out.println("Playback error F");
                }
            }


        });
    }

    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void goToR3news() {

        Intent intent = new Intent(this, R3news.class);

        startActivity(intent);

    }

    private void viewsecret() {

        Intent intent = new Intent(this, Secret.class);

        startActivity(intent);

    }
}