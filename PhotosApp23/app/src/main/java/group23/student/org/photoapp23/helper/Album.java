package group23.student.org.photoapp23.helper;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Album implements Serializable
{
    private static final long serialVersionUID = 0L;
    private String name;
    private static Photo copiedPhoto;
    private static Album copiedPhotoFrom;
    private Map<String, Photo> photos;

    /**
     * @param name the name of the album
     */
    public Album(String name)
    {
        this.name = name;
        this.photos = new HashMap<>();
    }

    /**
     * DEEP COPY
     * @param a, new album created using album 'a'
     */
    public Album(Album a)
    {
        this.name = a.name;
        this.photos = new HashMap<>();
        this.photos.putAll(a.photos);
    }

    /**
     * DEEP COPY
     * creates a new Album object using the provided
     *  albumName name, and
     *  list of photos
     * @param name ~ name of the album
     * @param photos ~ list of photos in album
     */
    public Album(String name, List<Photo> photos)
    {
        this(name);
        this.photos = new HashMap<>();

        for(Photo p : photos)
        {
            this.photos.put(p.getCaption(), p);
        }
    }

    //************* Setters & Getters *********************

    /**
     * @param name the name of the album
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param image_title ~the name of the photo
     * @return the Photo object with the specified photoPath if exist
     * 		   otherwise null
     */
    public Photo getPhoto(String image_title)
    {
        return this.photos.get(image_title);
    }

    /**
     * @return returns the copiedPhoto object
     */
    public static Photo getCopiedPhoto()
    {
        return copiedPhoto;
    }

    /**
     *
     * @return returns the copiedPhotoFrom object
     */
    public static Album getCopiedPhotoFrom()
    {
        return copiedPhotoFrom;
    }

    /**
     *
     * @param p, assigns the Photo p to copiedPhoto object
     */
    public static void setCopiedPhoto(Photo p)
    {
        copiedPhoto = p;
    }

    /**
     *
     * @param p, assigns the Album p to copiedPhotoFrom object
     */
    public static void setCopiedPhotoFrom(Album p)
    {
        copiedPhotoFrom = p;
    }

    /**
     *
     * @return returns a List of all the Photos in this Album
     */
    public ArrayList<Photo> getAllPhotosList()
    {
        ArrayList<Photo> allPhotosList = new ArrayList<>();
        for(String photo : this.photos.keySet())
        {
            allPhotosList.add(this.photos.get(photo));
        }
        return allPhotosList;
    }

    //************* Setters & Getters *********************

    /**
     *
     * @param p ~ a Photo object passed to add to the album
     * @return true if Photo p is added to this Album successfully
     * 			otherwise false
     */
    public boolean addPhoto(Photo p)
    {
        if(!this.photos.containsValue(p.getCaption())){
            this.photos.put(p.getCaption(), p);
            return true;
        }else
            return false;
    }

    /**
     *
     * @param image_title ~the name of the photo
     */
    public void deletePhoto(String image_title)
    {
        this.photos.remove(image_title);
    }


    /**
     * creates a new Photo object for the copied/moved photo to be pasted
     *  in some other Album, keeping track of its current location
     *
     * @param copiedPhoto ~  the copied photo object
     * @return boolean ~ True always, but false when if photo is not copied
     */
    public boolean movePhoto(Photo copiedPhoto)
    {
        //when photo is pasted, it will delete that photo from this Album
        copiedPhotoFrom = this;

        copiedPhoto = this.photos.get(copiedPhoto.getCaption());
        return copiedPhoto != null;
    }

    /**
     *
     * @return 0 for successful paste
     * 		   1 if Photo already exist in the destination Album
     * 		  -1 if Nothing to paste
     */
    public int pastePhoto()
    {
        //-1 --> Nothing to paste
        if(copiedPhoto == null) return -1;

        //1 --> copiedPhoto already exist in the album
        if(!this.addPhoto(copiedPhoto)) return 1;

        //for copy --> copiedPhotoFrom = null
        //for move --> copiedPhotoFrom = source Album
        if(copiedPhotoFrom != null)
        {
            //removing photo from the source Album
            copiedPhotoFrom.deletePhoto(copiedPhoto.getCaption());
        }

        //reset
        copiedPhoto = null;
        copiedPhotoFrom = null;

        //0 --> successful paste
        return 0;
    }

    /**
     * Remove all images from the album
     */
    public void clear(){
        this.photos.clear();
    }
    /**
     * @return String which displays the details of it
     */
    @SuppressLint("DefaultLocale")
    @Override
    public String toString()
    {
        return this.name.trim();
    }
}
