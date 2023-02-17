package group23.student.org.photoapp23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AddEditAlbum extends AppCompatActivity
{
    public static final String ALBUM_NAME = "albumName";
    public static final String ALBUM_INDEX = "albumIndex";
    private int albumIndex;
    private EditText albumName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_album);

        // get the field
        albumName = findViewById(R.id.albumName);

        // see if info was passed in to populate fields
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            albumIndex = bundle.getInt(ALBUM_INDEX);
            albumName.setText(bundle.getString(ALBUM_NAME));
        }
    }

    public void cancel(View view)
    {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void save()
    {
        String name = albumName.getText().toString();
        // gather the data from text field

        if (name.length() == 0)
        {
            Toast.makeText(getApplicationContext(), "Invalid value name! Try again please!",
                    Toast.LENGTH_LONG).show();
        }

        else
        {
            Bundle bundle = new Bundle();
            bundle.putInt(ALBUM_INDEX, albumIndex);
            bundle.putString(ALBUM_NAME,name);

            // send back to caller
            Intent intent = new Intent();
            intent.putExtras(bundle);
            setResult(RESULT_OK,intent);
            finish(); // pops activity from the call stack, returns to parent
        }
    }
}
