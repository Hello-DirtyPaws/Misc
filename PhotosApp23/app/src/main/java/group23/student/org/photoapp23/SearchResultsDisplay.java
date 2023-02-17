package group23.student.org.photoapp23;

import androidx.appcompat.app.AppCompatActivity;
import group23.student.org.photoapp23.helper.Album;
import group23.student.org.photoapp23.helper.Photo;
import group23.student.org.photoapp23.helper.PhotoAdapter;

import android.content.Context;
import android.os.Bundle;
import android.widget.GridView;

import java.util.List;

public class SearchResultsDisplay extends AppCompatActivity
{
    static final String PHOTO_INDEX = "photoIndex";
    static final String ALBUM_INDEX = "albumIndex";
    static final String PHOTO_NAME = "photoName";
    static final String ALBUM_NAME = "albumName";

    Context context = this;
    private GridView gridView;
    private PhotoAdapter adapter;
    private List<Photo> photos;
    private static Photo copied;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photos_view);



    }
}
