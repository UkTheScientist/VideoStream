package voyagers.videostream;

import android.app.ProgressDialog;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    // Declare variables
    ProgressDialog pDialog;
    VideoView videoview[]=new VideoView[4];
    MediaController mediacontroller[];
    Point Size;

    // Insert your Video URL
    String VideoURL[] = {"https://r16---sn-n4v7knel.googlevideo.com/videoplayback?ratebypass=yes&source=youtube&mv=m&mime=video%2Fmp4&signature=78003CD3855ABEADE0E5C91D9A58F6431CA1C655.502A97C5C1796675358DF66FA5E6E509D4533E4C&ms=au&dur=319.924&id=o-AK601cwW44yhV_nIbLxP8iKV9b6j0jbdF1xeDYO_kAdC&initcwndbps=1760000&pl=24&itag=18&ip=159.253.144.86&mm=31&mn=sn-n4v7knel&mt=1500370312&expire=1500392028&ipbits=0&ei=_NVtWavwC6qg1wK5nobABg&lmt=1431952563845801&clen=18646079&key=yt6&sparams=clen%2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cexpire&gir=yes&requiressl=yes&title=Top+5+Tech+Myths%21",
            "https://r9---sn-o097zne7.googlevideo.com/videoplayback?source=youtube&ratebypass=yes&lmt=1500158093945801&expire=1500389055&ms=au&mt=1500367376&mv=m&ip=159.253.144.86&clen=12946978&id=o-ANwbMTmFIrGuP4nEVh5MQP5nyBhUwcxczMoigcgHxHCQ&sparams=clen%2Cdur%2Cei%2Cgir%2Cid%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cexpire&gir=yes&mime=video%2Fmp4&signature=852B42B854E57AC8749816545E554F959678BF6A.539DABA93973D1290C9C66B96AAB6D11BF1273D5&ei=X8ptWdbDIdDB1gKd26KYCA&requiressl=yes&itag=18&dur=159.149&ipbits=0&pl=24&mm=31&mn=sn-o097zne7&key=yt6&title=New+York+City+vs+Climate+Change+%26+Pollution+-+Stats+%26+Facts",
            "https://r6---sn-n4v7kn7l.googlevideo.com/videoplayback?ratebypass=yes&requiressl=yes&sparams=clen%2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cexpire&gir=yes&key=yt6&clen=27782959&lmt=1500084908540126&ip=159.253.144.86&ei=isptWYvIJsOG1gLo56OAAQ&expire=1500389098&mt=1500367428&mn=sn-n4v7kn7l&ipbits=0&mm=31&itag=18&dur=338.825&pl=24&id=o-APSh2xUHyGcItc3LRyHTADSIzoemOtUI7ZN68y54zrWN&initcwndbps=1287500&mv=m&source=youtube&signature=99175C60A7E0DB4008DEDB95FE34D4A21E89AA47.9C4CEF4BD9BC27FE634D7B5F875C20E3C61752FF&ms=au&mime=video%2Fmp4&title=Formula+E%27s+First+Ever+Wet+Race%3F+-+Qualcomm+New+York+City+ePrix",
            "https://r5---sn-o097znlr.googlevideo.com/videoplayback?ms=au&ei=1sptWdvIMoSx1gLD2IPIBw&mv=m&source=youtube&pl=24&dur=294.429&lmt=1419032667078579&ip=159.253.144.86&id=o-ALwcbekTrmQBj_9qtCfLg9xe4SMcTgY7FnZpT0oOQLTP&mn=sn-o097znlr&mm=31&gir=yes&mt=1500367482&key=yt6&expire=1500389174&sparams=clen%2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Crequiressl%2Csource%2Cexpire&itag=36&mime=video%2F3gpp&initcwndbps=1287500&ipbits=0&signature=4945EA65E1D3631D5ED99EDE2D50E28A9C0716FC.288967E633EFB03EFCBAF84A735D5D8DE8CC70F9&requiressl=yes&clen=8310530&title=Why+Lewis+Hamilton%27s+Mercedes+was+the+dominant+F1+car+of+2014&ratebypass=yes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the layout from video_main.xml
        setContentView(R.layout.activity_main);


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

