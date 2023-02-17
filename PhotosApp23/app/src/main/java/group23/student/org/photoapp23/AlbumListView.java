package group23.student.org.photoapp23;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.ContextMenu;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.View;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.AlertDialog;

import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Objects;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuCompat;
import androidx.core.view.MenuItemCompat;
import group23.student.org.photoapp23.helper.Album;

import android.widget.EditText;
import android.widget.SearchView;


public class AlbumListView extends AppCompatActivity implements Serializable
{
    private static final String TAG = "AlbumListView";
    private static final int ADD_ALBUM_CODE = 0;
    private static final int EDIT_ALBUM_CODE = 1;
    private static final int  REQUEST_CODE = 1;

    public static Manager manager;
    private ListView listView;
    public static ArrayList<Album> albums;

    public EditText input;
    public AlertDialog.Builder builder;
    public Context context = this;

    /**
     * @param savedInstanceState - The state of the previous instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_view);
        listView = findViewById(R.id.album_list);
        verifyPermissions();
    }

    /**
     * set up the view
     */
    private void setUpView()
    {
        manager = Manager.getManager(context);
        albums = manager.getAllAlbumsList();

        listView.setAdapter(
                new ArrayAdapter<>(this, R.layout.album, albums));

        registerForContextMenu(listView);

        listView.setOnItemClickListener((p, V, pos, id) ->
                openAlbum(pos));
    }

    /**
     * verify that the application has the users permissions to access the photos
     */
    private void verifyPermissions()
    {
        Log.d(TAG, "verifyPermissions: asking user for permissions");
        String [] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[0]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this.getApplicationContext(),
                        permissions[1]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this.getApplicationContext(),
                        permissions[2]) == PackageManager.PERMISSION_GRANTED){
            setUpView();
        }else{
            ActivityCompat.requestPermissions(AlbumListView.this, permissions, REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        verifyPermissions();
    }

    private void openAlbum(int pos)
    {
        Intent intent = new Intent(AlbumListView.this, PhotosListView.class);
        intent.putExtra("index", pos);
        startActivity(intent);
    }

    /**
     * add new album
     */
    private void addAlbum()
    {
        Intent intent = new Intent(this, AddEditAlbum.class);
        startActivityForResult(intent, ADD_ALBUM_CODE);
    }

    /**
     * @param pos - position of the album in the album list
     */
    private void rename(int pos)
    {
        Bundle bundle = new Bundle();
        Album selectedAlbum = albums.get(pos);

        bundle.putInt(AddEditAlbum.ALBUM_INDEX, pos);
        bundle.putString(AddEditAlbum.ALBUM_NAME, selectedAlbum.getName());

        Intent intent = new Intent(this, AddEditAlbum.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, EDIT_ALBUM_CODE);
    }

    /**
     *
     * @param pos - position of the album in the album list
     */
    public void delete(int pos)
    {
        manager.deleteAlbum(this.albums.get(pos).getName());
        albums = manager.getAllAlbumsList();
        listView.setAdapter(
                new ArrayAdapter<>(this, R.layout.album, albums));
    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpView();
        albums = manager.getAllAlbumsList();
        manager.writeApp(context);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.writeApp(context);
    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.writeApp(context);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode != RESULT_OK) {
            return;
        }
        Bundle bundle = intent.getExtras();

        if(bundle == null)
            return;

        String name = bundle.getString(AddEditAlbum.ALBUM_NAME);
        int index = bundle.getInt(AddEditAlbum.ALBUM_INDEX);

        if (requestCode == EDIT_ALBUM_CODE && !manager.albumExists(name)) {
            Album al = this.albums.get(index);
            manager.editAlbumName(al.getName(), name);

        }else{
            if (requestCode == ADD_ALBUM_CODE) {
                manager.createAlbum(name);

            }
        }
        // redo the adapter to reflect change^K
        manager.writeApp(context);
        albums = manager.getAllAlbumsList();
        listView = findViewById(R.id.album_list);
        listView.setAdapter(new ArrayAdapter<>(this, R.layout.album, albums));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.album_actions, menu);
        MenuItem searchItem = menu.findItem(R.id.photoSearch);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle item selection
        if (item.getItemId() == R.id.create) {
            addAlbum();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
        if(v.getId() == R.id.album_list){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle(this.albums.get(info.position).getName());
            String []menuItems = getResources().getStringArray(R.array.albumMenu);
            for (int i =0; i < menuItems.length; i++){
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        if(item.getTitle().equals("Rename"))
        {
            AdapterContextMenuInfo info =  (AdapterContextMenuInfo)item.getMenuInfo();
            rename(info.position);
            manager.writeApp(context);
            albums = manager.getAllAlbumsList();
            listView = findViewById(R.id.album_list);
            listView.setAdapter(
                    new ArrayAdapter<>(this, R.layout.album, albums));
        }
        else if(item.getTitle().equals("Delete"))
        {
            AdapterContextMenuInfo info =  (AdapterContextMenuInfo)item.getMenuInfo();
            manager.deleteAlbum(albums.get(info.position).getName());
            manager.writeApp(context);
            albums = manager.getAllAlbumsList();
            listView = findViewById(R.id.album_list);
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            listView.setAdapter(
                    new ArrayAdapter<>(this, R.layout.album, albums));
        }
        else
            return false;
        return true;
    }
}

class Manager implements Serializable
{
    private static final long serialVersionUID = 0L;
    public Map<String, Album> albums;
    private static Manager privateObject;
    private static final String storeFile = "albums.ser";

    private Manager(Context context)
    {
        try
        {
            Manager obj = readApp(context);
            this.albums = Objects.requireNonNull(obj).albums;
        }
        catch(Exception e)
        {
            //IO or ClassNotFound or FileNotFound Exception
            this.albums = new HashMap<>();
        }
    }

    /**
     * @return object of Manager
     */
    private static Manager readApp(Context context)
    {
        Manager allAlbums;
        try {
            FileInputStream fis = context.openFileInput(storeFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            allAlbums = (Manager) ois.readObject();

            if (allAlbums.albums == null) {
                allAlbums.albums = new HashMap<>();
            }
            fis.close();
            ois.close();
        } catch (ClassNotFoundException | NullPointerException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
        return allAlbums;
    }

    /**
     * @param context current context
     */
    public void writeApp(Context context)
    {
        ObjectOutputStream oos;
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(storeFile, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Album getAlbum(String albumName)
    {
        return (this.albums.get(albumName));
    }

    /**
     *
     * @return returns the List of Albums for this user
     */
    public ArrayList<Album> getAllAlbumsList()
    {
        ArrayList<Album> allAlbumsList = new ArrayList<>();
        for(String albumName : this.albums.keySet())
        {
            allAlbumsList.add(this.albums.get(albumName));
        }
        return allAlbumsList;
    }

    /**
     *
     * @param albumName - name of the album
     * @return true if album name is unique and added to the Albums of the Manager
     *         false otherwise
     */
    public boolean createAlbum(String albumName)
    {
        return (this.albums.putIfAbsent(albumName, new Album(albumName)) == null);
    }

    /**
     *
     * @param albumName - name of the album
     */
    public void deleteAlbum(String albumName)
    {
        this.albums.remove(albumName);
    }

    /**
     *
     * @param currName the current name
     * @param newName change the current name to this name
     */
    public void editAlbumName(String currName, String newName)
    {
        Album a = this.getAlbum(currName);
        Album b = this.getAlbum(newName);

        //false if currAlbum is null or an album with newName already exist
        if(a == null || b != null)
            return;
        a.setName(newName);
        this.albums.put(newName, a);
        this.albums.remove(currName);
    }

    /**
     * @param albumName - name of the album to be checked
     * @return true if it exists, or else false by default
     */
    public boolean albumExists(String albumName)
    {
        return this.albums.containsKey(albumName);
    }

    /**
     *
     * @return returns the one and only Manager static object
     */
    public static Manager getManager(Context context)
    {
        if(privateObject != null)
        {
            return privateObject;
        }
        else
        {
            privateObject = new Manager(context);
            return privateObject;
        }
    }
}