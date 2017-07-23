package voyagers.videostream;

import android.app.ProgressDialog;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    // Declare variables
    ProgressDialog pDialog;
    VideoView videoview[]=new VideoView[4];
    MediaController mediacontroller[];
    Point Size;

    // Insert your Video URL
    String VideoURL[] = {"https://doc-0k-ak-docs.googleusercontent.com/docs/securesc/ha0ro937gcuc7l7deffksulhg5h7mbp1/u8mmpue2f451bs43epeh130ji0sihor8/1500804000000/16614391945995542520/*/0BxxAWol_IwlpME5TQ1lmUkFZRFk?e=download",
            "https://doc-08-ak-docs.googleusercontent.com/docs/securesc/ha0ro937gcuc7l7deffksulhg5h7mbp1/6011lnaovefm62u0cpkkbli1o72aivdg/1500804000000/16614391945995542520/*/0BxxAWol_IwlpZDdLMXdYYW5VOWc?e=download",
            "https://doc-10-ak-docs.googleusercontent.com/docs/securesc/ha0ro937gcuc7l7deffksulhg5h7mbp1/ivg0uhaskbdp33caud8jjqal879l0vmo/1500804000000/16614391945995542520/*/0BxxAWol_IwlpNkZ3TTlxcTFfUnM?e=download",
            "https://doc-10-ak-docs.googleusercontent.com/docs/securesc/ha0ro937gcuc7l7deffksulhg5h7mbp1/rm3emavc5p89frpjl4p20j37jcvb61l6/1500804000000/16614391945995542520/*/0BxxAWol_IwlpTTZTbVZuOWJZS1k?e=download"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the layout from video_main.xml
        setContentView(R.layout.activity_main);


        setTitle("Live");
        Size = new Point();
        getWindowManager().getDefaultDisplay().getSize(Size);
        videoview[0] = (VideoView) findViewById(R.id.videoView);
        videoview[1] = (VideoView) findViewById(R.id.videoView1);
        videoview[2] = (VideoView) findViewById(R.id.videoView2);
        videoview[3] = (VideoView) findViewById(R.id.videoView3);

        //Create a progressbar
        pDialog = new ProgressDialog(this);
        // Set progressbar title
        pDialog.setTitle("PLEASE WAIT");
        // Set progressbar message
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        // Show progressbar
        pDialog.show();



        // Set the media controller buttons
        if (mediacontroller == null) {
            // Start the MediaController
            mediacontroller = new MediaController[4];
            for (int i = 0; i < videoview.length; i++) {
                mediacontroller[i] = new MediaController(this);
                videoview[i].setMediaController(mediacontroller[i]);

                final Uri video = Uri.parse(VideoURL[i]);
                videoview[i].setVideoURI(video);
                videoview[i].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    // Close the progress bar and play the video
                    public void onPrepared(MediaPlayer mp) {
                        pDialog.dismiss();
                        final int i = indexof(mp);
                        mp.start();
                        if (i != -1) {
                            videoview[i].requestFocus();
                            mediacontroller[i].setAnchorView(videoview[i]);

                            // When video Screen change size.
                            mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                                @Override
                                public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                                    Log.i("size",Integer.toString(width)+"  "+Integer.toString(height));

                                    // Re-Set the videoView that acts as the anchor for the MediaController
                                    mediacontroller[i].setAnchorView(videoview[i]);
                                }
                            });
                        }
                    }
                });
            }
        }
    }

    public int indexof(MediaPlayer mp){
        int id = mp.getAudioSessionId();
        for(int i =0;i<4;i++){
            if(videoview[i].getAudioSessionId()==id)
                return i;
        }
        return -1;
    }
}

