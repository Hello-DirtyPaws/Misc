package group23.student.org.photoapp23.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import group23.student.org.photoapp23.R;
import group23.student.org.photoapp23.helper.Photo;

public class PhotoAdapter extends ArrayAdapter
{
    private Context context;
    private ArrayList data;

    public PhotoAdapter(Context context, ArrayList data){
        super(context, R.layout.photos_view, data);
        this.context = context;
        this.data = data;
    }


    @Override
    public View getView(int position, View currentView, ViewGroup parent){

        if (currentView == null){
            LayoutInflater i = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            currentView = i.inflate(R.layout.content_photo_layout, parent, false);
        }

        ImageView iv = currentView.findViewById(R.id.imageView);
        TextView tv = currentView.findViewById(R.id.image_Title);

        Photo photo = (Photo) data.get(position);
        iv.setImageBitmap(photo.getImage());
        tv.setText(photo.getCaption());
        return currentView;
    }

}
