package com.example.mangaramu.tumobilenews;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.net.URL;

/**
 * Created by mangaramu on 3/20/2017.
 */

public class ImageLoadThread extends Thread{

    ImageItem Imagei;
    Handler placetosend;

    ImageLoadThread(ImageItem x, Handler y)
    {
        Imagei = x;
        placetosend = y;
    }

    @Override
    public void run() {


        try {
            Imagei.itemref.setNewsimage(BitmapFactory.decodeStream(((URL)new URL(Imagei.itemref.newsimagelink)).openStream()));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Message m = Message.obtain();
        m.obj=Imagei;
        m.setTarget(placetosend);
        m.sendToTarget();
    }
}
