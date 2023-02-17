package group23.student.org.photoapp23;

import androidx.appcompat.app.AppCompatActivity;
import group23.student.org.photoapp23.helper.Album;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import group23.student.org.photoapp23.helper.Photo;
import group23.student.org.photoapp23.helper.PhotoAdapter;

public class PhotosListView extends AppCompatActivity
{
    Context context = this;
    int selection;
    private GridView gridView;
    private PhotoAdapter gVAdapter;
    private PhotoAdapter adapter;
    private Album currentAlbum;
    private List<Photo> photos;
    private static Photo copied;
    private static final int SELECT_PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photos_view);

        gridView = findViewById(R.id.photo_list);
        adapter = new PhotoAdapter(this, getImages());

        gridView.setAdapter(adapter);

        int index = getIntent().getIntExtra("index", 0);
        currentAlbum = AlbumListView.albums.get(index);

        photos = currentAlbum.getAllPhotosList();
        gridView = findViewById(R.id.photo_list);

        registerForContextMenu(gridView);
        gridView.setOnItemClickListener((p, V, pos, id) ->
                displayPhoto(pos));
    }

    private void addPhoto()
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_PHOTO);
        gridView.setAdapter(adapter);
    }

    private void pastePhoto()
    {
        if(copied != null)
        {
            currentAlbum.addPhoto(copied);
            photos = currentAlbum.getAllPhotosList();
            AlbumListView.manager.writeApp(context);
            adapter = new PhotoAdapter(this, getImages());
            gridView.setAdapter(adapter);
            Toast.makeText(getApplicationContext(), "Photo Pasted!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "No Photo Copied!", Toast.LENGTH_LONG).show();
        }
    }

    public void displayPhoto(int pos)
    {
        Bundle bundle = new Bundle();
        Photo curr = photos.get(pos);
        bundle.putInt(PhotoDisplay.PHOTO_INDEX, pos);
        bundle.putString(PhotoDisplay.PHOTO_NAME, curr.getCaption());
        bundle.putInt(PhotoDisplay.ALBUM_INDEX, findAlbumIndex(currentAlbum.getName()));

        Intent intent = new Intent(this, PhotoDisplay.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
        {
            Uri selectedImage = data.getData();

            ImageView iv = new ImageView(this);

            iv.setImageURI(selectedImage);

            BitmapDrawable drawable = (BitmapDrawable) iv.getDrawable();
            Bitmap selectedImageGal = drawable.getBitmap();

            Photo photoToAdd = new Photo();
            photoToAdd.setImage(selectedImageGal);

            File f = new File(Objects.requireNonNull(selectedImage).getPath());
            String pathID = f.getAbsolutePath();
            String filename = pathToFileName(selectedImage);
            photoToAdd.setCaption(filename);

            currentAlbum.addPhoto(photoToAdd);
            photos = currentAlbum.getAllPhotosList();
            AlbumListView.manager.writeApp(context);
            adapter = new PhotoAdapter(this, getImages());
            gridView.setAdapter(adapter);
            Toast.makeText(getApplicationContext(), "Photo Added!", Toast.LENGTH_LONG).show();
        }
    }

    public int findAlbumIndex(String name){

        for(int i = 0; i < Manager.getManager(context).albums.size(); i++) {
            if(AlbumListView.albums.get(i).getName().equalsIgnoreCase(name)){
                return i;
            }
        }
        return -1;
    }
    private String pathToFileName(Uri selectedImage)
    {
        String filename = "not found";
        String[] column = {MediaStore.MediaColumns.DISPLAY_NAME};

        ContentResolver contentResolver = getApplicationContext().getContentResolver();

        @SuppressLint("Recycle") Cursor cursor = contentResolver.query(selectedImage, column,
                null, null, null);

        if(cursor != null)
        {
            try
            {
                if (cursor.moveToFirst())
                {
                    filename = cursor.getString(0);
                }
            } catch (Exception ignored){

            }
        }
        return filename;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.photo_actions,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.addPhoto:
                addPhoto();
                return true;
            case R.id.pastePhoto:
                pastePhoto();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        if(v.getId() == R.id.photo_list)
        {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle(this.photos.get(info.position).getCaption());
            String []menuItems = getResources().getStringArray(R.array.photosMenu);
            for (int i =0; i < menuItems.length; i++)
            {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        if(item.getTitle().equals("Copy"))
        {
            AdapterView.AdapterContextMenuInfo info =  (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
            copied = this.photos.get(info.position);
        }
        else if(item.getTitle().equals("Delete"))
        {
            AdapterView.AdapterContextMenuInfo info =  (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
            currentAlbum.deletePhoto(this.photos.get(info.position).getCaption());
            this.photos.remove(info.position);
        }
        else
            return false;

        this.photos = currentAlbum.getAllPhotosList();
        AlbumListView.manager.writeApp(context);
        adapter = new PhotoAdapter(this, getImages());
        gridView.setAdapter(adapter);
        return true;
    }

    public ArrayList getImages()
    {
        int index = getIntent().getIntExtra("index", 0);
        return AlbumListView.albums.get(index).getAllPhotosList();
    }
}
