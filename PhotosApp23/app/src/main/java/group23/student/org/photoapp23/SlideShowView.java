package group23.student.org.photoapp23;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.R.layout;
import android.widget.TextView;
import android.view.MenuItem;
import java.util.ArrayList;
import android.content.Context;
import android.app.Dialog;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import group23.student.org.photoapp23.helper.Album;
import group23.student.org.photoapp23.helper.Photo;

public class SlideShowView extends AppCompatActivity {

    static final String PHOTO_INDEX = "photoIndex";
    static final String PHOTO_NAME = "photoName";
    static final String ALBUM_INDEX = "albumIndex";

    int photoIndex, alIndex;
    String imgIndex;

    Album currentAlbum;
    Photo currentPhoto;
    ImageView currentImage;
    ListView tagsList;
    ArrayAdapter<String> arrayAdapterTags;
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_show_view);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            imgIndex = bundle.getString(PHOTO_NAME);
            alIndex = bundle.getInt(ALBUM_INDEX);
            photoIndex = bundle.getInt(PHOTO_INDEX);
            currentAlbum = AlbumListView.albums.get(alIndex);
            currentPhoto = AlbumListView.albums.get(alIndex).getPhoto(imgIndex);
            currentImage.setImageBitmap(currentPhoto.getImage());
        }

    }
    private void populateListView() {
        String[][] tagKeyValue = currentPhoto.getTagsWithKeyValues();
        ArrayList<String> keyAndValueTogether = new ArrayList<String>();

        if (tagKeyValue[0].length == 0)
        {
            tagsList = (ListView) findViewById(R.id.tagslist);
            tagsList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            arrayAdapterTags = new ArrayAdapter<String>(this, layout.simple_list_item_1, keyAndValueTogether);
            tagsList.setAdapter(arrayAdapterTags);
            return;
        }


        for (int i = 0; i < tagKeyValue[0].length; i++) {
            keyAndValueTogether.add(tagKeyValue[0][i] + ": " + tagKeyValue[1][i]);
        }

        tagsList = (ListView) findViewById(R.id.tagslist);
        tagsList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        tagsList.setSelection(0);
        arrayAdapterTags = new ArrayAdapter<String>(this, layout.simple_list_item_1, keyAndValueTogether);
        tagsList.setAdapter(arrayAdapterTags);
    }

    private void fillPicture()
    {
        ImageView iv = (ImageView) findViewById(R.id.slideshowImage);
        iv.setImageBitmap(currentPhoto.getImage());
    }

    private String[] keyValueTogether(){

        String[][] tagKeyValue = currentPhoto.getTagsWithKeyValues();
        String[] keyAndValueTogether = new String[tagKeyValue[0].length];
        for (int i = 0; i < tagKeyValue[0].length; i++) {
            keyAndValueTogether[i] = tagKeyValue[0][i] + ": " + tagKeyValue[1][i];
        }
        return keyAndValueTogether;
    }
}
