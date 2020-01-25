package michealcob.ts.gidimobile;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppConstants {
    public static final String PREF_NAME = "michealcob.ts.gidimobile";
    public static final long DB_VERSION = 0;
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static final String APP_NAME = "KOLs";

    public static final String PROFILE_URL = "https://kols-ignite-multimedia.s3-ap-southeast-1.amazonaws.com/profileimages/";
    public static final String IMAGE_URL = "https://kols-ignite-multimedia.s3-ap-southeast-1.amazonaws.com/small/";
    public static final String PREVIEW_URL = "https://kols-ignite-multimedia.s3-ap-southeast-1.amazonaws.com/previewimage/";
    public static final String ORIGINAL_URL ="https://kols-ignite-multimedia.s3-ap-southeast-1.amazonaws.com/original/";
    public static final String VIDEO_URL = "https://kols-ignite-multimedia.s3-ap-southeast-1.amazonaws.com/video/";

    private static Activity mFirstActivity;

    private AppConstants() {
        // This utility class is not publicly instantiable
    }

    public static void initiate(Activity firstActivity) {
        mFirstActivity = firstActivity;
        SCREEN_WIDTH = 0;
        SCREEN_HEIGHT = 0;
        WindowManager w = firstActivity.getWindowManager();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point size = new Point();
            w.getDefaultDisplay().getSize(size);
            SCREEN_WIDTH = size.x;
            SCREEN_HEIGHT = size.y;
        } else {
            Display d = w.getDefaultDisplay();
            SCREEN_WIDTH = d.getWidth();
            SCREEN_HEIGHT = d.getHeight();
        }

        //here for any app specific protocol
        //ProductConstants.initiate();
    }

    public static Activity getFirstActivity() {
        return mFirstActivity;
    }

    public static String extractYTId(String ytUrl) {
        String url = "";

        final String regex = "v=([^\\s&#]*)";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(ytUrl);

        if(matcher.find()) {
            url = matcher.group(1);
        }
        return url;
    }

    public static String snapShotUrl(String youtubeId){
        return  "https://img.youtube.com/vi/"+youtubeId+"/default.jpg";
        //return url;
    }


    final String youTubeUrlRegEx = "^(https?)?(://)?(www.)?(m.)?((youtube.com)|(youtu.be))/";
    final String[] videoIdRegex = { "\\?vi?=([^&]*)","watch\\?.*v=([^&]*)", "(?:embed|vi?)/([^/?]*)", "^([A-Za-z0-9\\-]*)"};



    public String extractVideoIdFromUrl(String url) {
        String youTubeLinkWithoutProtocolAndDomain = youTubeLinkWithoutProtocolAndDomain(url);

        for(String regex : videoIdRegex) {
            Pattern compiledPattern = Pattern.compile(regex);
            Matcher matcher = compiledPattern.matcher(youTubeLinkWithoutProtocolAndDomain);

            if(matcher.find()){
                return matcher.group(1);
            }
        }

        return null;
    }

    private String youTubeLinkWithoutProtocolAndDomain(String url) {
        Pattern compiledPattern = Pattern.compile(youTubeUrlRegEx);
        Matcher matcher = compiledPattern.matcher(url);

        if(matcher.find()){
            return url.replace(matcher.group(), "");
        }
        return url;
    }
}


