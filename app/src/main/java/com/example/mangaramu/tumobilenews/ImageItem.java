package com.example.mangaramu.tumobilenews;

import android.widget.ImageView;

/**
 * Created by mangaramu on 3/20/2017.
 */

public class ImageItem {
    Newsitem itemref;
    ImageView viewref;

    ImageItem(ImageView x, Newsitem y)
    {
        viewref=x;
        itemref=y;
    }

    public void setItemref(Newsitem itemref) {
        this.itemref = itemref;
    }

    public void setViewref(ImageView viewref) {
        this.viewref = viewref;
    }
}
