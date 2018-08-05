package com.ramavathubalaji.lost_found;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by RAMAVATHU BALAJI on 23-05-2018.
 */

public class Global {
    private static Global instance;

    int count;
    int countf;
    String lf=null;
    int lposts,fposts;
    String key="yo";
    Boolean update=false;
    Boolean updateafterdelete=false;
    private Global() {

    }

    public static Global getInstance() {
        if (instance == null) {
            instance = new Global();
        }
        return instance;
    }
    public int getCount()
    {
        return this.count;
    }
    public void setCount(int count)
    {
        this.count=count;
    }
    public int getCountf()
    {
        return this.countf;
    }
    public void setCountf(int countf)
    {
        this.countf=countf;
    }
    public String getukey()
    {
        return this.key;
    }
    public void setukey(String key)
    {
        this.key=key;
    }
    public int getlposts()
    {
        return this.lposts;
    }
    public void setlposts(int lposts)
    {
        this.lposts=lposts;
    }
    public int getfposts()
    {
        return this.fposts;
    }
    public void setfposts(int fposts)
    {
        this.fposts=fposts;
    }
    public String getlf()
    {
        return this.lf;
    }
    public void setlf(String lf)
    {
        this.lf=lf;
    }
    public Boolean getupdate()
    {
        return this.update;
    }
    public void setupdate(Boolean update)
    {
        this.update=update;
    }
    public Boolean getupdateafterdelete()
    {
        return this.updateafterdelete;
    }
    public void setupdateafterdelete(Boolean updateafterdelete)
    {
        this.updateafterdelete=updateafterdelete;
    }


}
