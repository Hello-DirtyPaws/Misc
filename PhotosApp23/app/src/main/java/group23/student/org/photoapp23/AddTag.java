package group23.student.org.photoapp23;

import androidx.appcompat.app.AppCompatActivity;
import group23.student.org.photoapp23.helper.Album;
import group23.student.org.photoapp23.helper.Photo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddTag extends AppCompatActivity
{
    static final String PHOTO_INDEX = "photoIndex";
    static final String PHOTO_NAME = "photoName";
    static final String ALBUM_INDEX = "albumIndex";

    int photoIndex, alIndex;
    String imgIndex;

    Album currentAlbum;
    Photo currentPhoto;

    Button addTag;
    Button cancelAdd;
    Spinner tagType;
    EditText tagValue;
    Context context = this;

    ListView tagsList;
    ArrayAdapter<String> arrayAdapterTags;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_tag);

        EditText tagValue = findViewById(R.id.tagValue);
        addTag = findViewById(R.id.dialogOK);
        cancelAdd = findViewById(R.id.dialogCancel);
        tagType = findViewById(R.id.dialog_spinner);
        tagsList = findViewById(R.id.tagslist);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            imgIndex = bundle.getString(PHOTO_NAME);
            alIndex = bundle.getInt(ALBUM_INDEX);
            photoIndex = bundle.getInt(PHOTO_INDEX);
            currentAlbum = AlbumListView.albums.get(alIndex);
            currentPhoto = currentAlbum.getPhoto(imgIndex);
        }
    }

    public void cancel(View view) {
        finish();
    }

    public void add(View view)
    {
        if(tagValue.getText().toString().trim().isEmpty())
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("Invalid");
            alert.setMessage("Must input at least one character");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                {
                    return;
                }
            });
            alert.show();
        } else {
            currentPhoto.addTag(tagType.getSelectedItem().toString().trim(),tagValue.getText().toString().toLowerCase());
            populateListView();
            AlbumListView.manager.writeApp(context);
        }
    }
    private void populateListView() {
        String[][] tagKeyValue = currentPhoto.getTagsWithKeyValues();
        ArrayList<String> keyAndValueTogether = new ArrayList<String>();

        if (tagKeyValue[0].length == 0)
        {
            tagsList = (ListView) findViewById(R.id.tagslist);
            tagsList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            arrayAdapterTags = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, keyAndValueTogether);
            tagsList.setAdapter(arrayAdapterTags);
            return;
        }


        for (int i = 0; i < tagKeyValue[0].length; i++) {
            keyAndValueTogether.add(tagKeyValue[0][i] + ": " + tagKeyValue[1][i]);
        }

        tagsList = (ListView) findViewById(R.id.tagslist);
        tagsList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        tagsList.setSelection(0);
        arrayAdapterTags = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, keyAndValueTogether);
        tagsList.setAdapter(arrayAdapterTags);
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
