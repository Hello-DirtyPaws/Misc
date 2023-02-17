package group23.student.org.photoapp23.helper;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.*;
import java.util.Objects;

public class Photo implements Serializable
{
    private static final long serialVersionUID = 0L;
    transient private Bitmap image;
    private String caption;
    private Map<String, ArrayList<String>> tags = new HashMap<>();


    @SuppressWarnings("UnusedAssignment")
    public String[] getTagsAsString()
    {
        String[] tagsAsSingleString = (String[]) tags.values().toArray();
        return tagsAsSingleString;
    }

    public String[][] getTagsWithKeyValues()
    {
        int tagCount = 0;

        ArrayList<String> loc = tags.get("location");
        ArrayList<String> per = tags.get("person");

        if (loc != null) tagCount += loc.size();
        if (per != null) tagCount += per.size();

        String[][] tagsArray = new String[2][tagCount];

        int j = 0;

        if (loc != null) {
            for(int i = 0; i < loc.size(); i++) { tagsArray[0][j] = "location";
                tagsArray[1][j] = loc.get(i); j++;
            }
        }

        if (per != null) {
            for(int i = 0; i < per.size(); i++) { tagsArray[0][j] = "person";
                tagsArray[1][j] = per.get(i); j++;
            }
        }

        return tagsArray;

    }

    public void removeTag(String key, String value){
        getListWithKey(key).remove(value);
    }

    public ArrayList<String> getListWithKey(String key) {
        return tags.get(key);
    }

    public void addTag(String key, String value)
    {
        if (tags.containsKey(key)){
            if (Objects.requireNonNull(tags.get(key)).contains(value)) {
                return;
            }
            Objects.requireNonNull(tags.get(key)).add(value);
        } else {
            ArrayList<String> arrList = new ArrayList<>();
            arrList.add(value);
            tags.put(key, arrList);

        }
    }

    public ArrayList<String> personTags()
    {
        return tags.get("person");
    }
    public ArrayList<String> locationTags()
    {
        return tags.get("location");
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }


    public String getCaption() {
        return this.caption;
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }


    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException
    {
        ois.defaultReadObject();
        int b;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        while((b = ois.read()) != -1)
            byteStream.write(b);
        byte[] bitmapBytes = byteStream.toByteArray();
        image = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
    }

    private void writeObject(ObjectOutputStream oos) throws IOException
    {
        oos.defaultWriteObject();
        if(image != null){
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 0, byteStream);
            byte[] bitmapBytes = byteStream.toByteArray();
            oos.write(bitmapBytes, 0, bitmapBytes.length);
        }
    }
}