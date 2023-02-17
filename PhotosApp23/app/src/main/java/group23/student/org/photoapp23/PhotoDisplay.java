package group23.student.org.photoapp23;

import androidx.appcompat.app.AppCompatActivity;
import group23.student.org.photoapp23.helper.Album;
import group23.student.org.photoapp23.helper.Photo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

public class PhotoDisplay extends AppCompatActivity {
    static final String PHOTO_INDEX = "photoIndex";
    static final String PHOTO_NAME = "photoName";
    static final String ALBUM_INDEX = "albumIndex";

    private int alIndex, photoIndex;
    private String imgIndex;
    Context context = this;

    private ImageView currentImage;
    private Photo currentPhoto;
    private Album currAlbum;
    private RelativeLayout dispPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_display);

        currentImage = findViewById(R.id.currImage);
        dispPhoto = findViewById(R.id.dispPhoto);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            imgIndex = bundle.getString(PHOTO_NAME);
            alIndex = bundle.getInt(ALBUM_INDEX);
            photoIndex = bundle.getInt(PHOTO_INDEX);
            currAlbum = AlbumListView.albums.get(alIndex);
            currentPhoto = currAlbum.getPhoto(imgIndex);
            currentImage.setImageBitmap(currentPhoto.getImage());
        }

        registerForContextMenu(dispPhoto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.display_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.slideshow:
                slideshow(photoIndex);
                return true;
            case R.id.move:
                movePhoto(photoIndex);
                return true;
            case R.id.addTag:
                addTag(photoIndex);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void slideshow(int pos)
    {
        Bundle bundle = new Bundle();
        Photo curr = AlbumListView.albums.get(alIndex).getPhoto(imgIndex);

        bundle.putInt(SlideShowView.PHOTO_INDEX, pos);
        bundle.putString(SlideShowView.PHOTO_NAME, curr.getCaption());
        bundle.putInt(SlideShowView.ALBUM_INDEX, findAlbumIndex(currAlbum.getName()));

        Intent intent = new Intent(PhotoDisplay.this, SlideShowView.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void addTag(int pos)
    {
        Intent intent = new Intent(PhotoDisplay.this, AddTag.class);
        intent.putExtra("album_index", alIndex);
        intent.putExtra("photo_index", pos);
        startActivity(intent);
    }

    private void movePhoto(int pos)
    {

    }

    private void deleteTag()
    {

    }
    public int findAlbumIndex(String name){

        for(int i = 0; i < Manager.getManager(context).albums.size(); i++) {
            if(AlbumListView.albums.get(i).getName().equalsIgnoreCase(name)){
                return i;
            }
        }
        return -1;
    }
}
