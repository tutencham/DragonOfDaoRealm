package data.db;

import android.content.Context;

/**
 * Created by Grzesiek on 2017-09-14.
 */

public class Test {
    private static Test ourInstance;

    public static Test getInstance(Context context) {
        if(ourInstance==null){
            ourInstance = new Test(context);
        }
        return ourInstance;
    }

    private Test(Context context) {
    }
}
