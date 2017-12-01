package DatabaseHelpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.SyncStateContract;

/**
 * Created by Preetika on 24-04-2016.
 */
public class PgContact {

    String pgName,pgRent,pgType,pgAddress, pgLocation,pgPhone;
    byte [] photo;
    int id;


    public  PgContact(){}


    public PgContact(int id,String pgName, String pgLocation, String pgAddress, String pgPhone, String pgType , byte[] photo, String pgRent) {
        this.id=id;
        this.pgName = pgName;
        this.pgLocation = pgLocation;
        this.pgAddress = pgAddress;
        this.pgPhone = pgPhone;
        this.pgType = pgType;
        this.photo = photo;
        this.pgRent = pgRent;
    }



    public PgContact(int id,String pgName, String pgLocation, String pgAddress, String pgPhone, String pgType , String pgRent) {
        this.id=id;
        this.pgName = pgName;
        this.pgLocation = pgLocation;
        this.pgAddress = pgAddress;
        this.pgPhone = pgPhone;
        this.pgType = pgType;
        //this.photo = photo;
        this.pgRent = pgRent;
    }


    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }



    public void setPgName(String pgName) { this.pgName=pgName; }
    public String getPgName()  { return  this.pgName;  }
    public void setPgRent(String pgRent) { this.pgRent=pgRent;   }
    public String getpgRent()  { return  this.pgRent;  }
    public void setPgType(String pgType) { this.pgType=pgType;  }
    public String getpgType()   { return  this.pgType;  }

    public void setPgAddress(String pgAddress){ this.pgAddress=pgAddress;    }
    public String getPgAddress(){return this.pgAddress;}

    public void setPgLocation(String pgLocation)  {  this.pgLocation=pgLocation; }
    public String getpgLocation() { return  this.pgLocation;}

    public void setPgPhone(String pgPhone) { this.pgPhone=pgPhone; }
    public String getPgPhone()  { return  this.pgPhone;  }

    public byte[] getPhoto(){
        return this.photo;
    }

    public void setPhoto(byte[] photo){
        this.photo=photo;
    }



















}

