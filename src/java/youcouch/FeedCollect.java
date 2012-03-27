
package youcouch;

import com.google.gdata.client.*;
import com.google.gdata.client.youtube.*;
import com.google.gdata.data.*;
import com.google.gdata.data.youtube.*;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class FeedCollect {
    
    public String getVideo(String keyword) throws Exception{
        YouTubeService service = new YouTubeService(null, Constants.devkey);
        YouTubeQuery query = new YouTubeQuery(new URL("http://gdata.youtube.com/feeds/api/videos"));
        query.setMaxResults(50);
        StringTokenizer st=new StringTokenizer(keyword);
        for(int i=0;i<st.countTokens();i++){
            Query.CategoryFilter filter = new Query.CategoryFilter();
            filter.addCategory(new Category(YouTubeNamespace.KEYWORD_SCHEME, st.nextToken()));
            query.addCategoryFilter(filter);
        }
        VideoFeed videoFeed = service.query(query, VideoFeed.class);
        List<VideoEntry> vlist=videoFeed.getEntries();
        Random r=new Random();
        VideoEntry ve=vlist.get(r.nextInt(vlist.size()));
        YouTubeMediaGroup mediaGroup = ve.getMediaGroup();
        return mediaGroup.getVideoId();
    }
}
