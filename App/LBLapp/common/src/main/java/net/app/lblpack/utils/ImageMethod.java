package net.app.lblpack.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ImageMethod {
    public static Bitmap shotListView(ListView listview) {
        ListAdapter adapter = listview.getAdapter();
        int itemscount = adapter.getCount();
        int allitemsheight = 0;
        List<Bitmap> bmps = new ArrayList<Bitmap>();
        for (int i = 0; i < itemscount; i++) {
            View childView = adapter.getView(i, null, listview);
            childView.measure(View.MeasureSpec.makeMeasureSpec(listview.getWidth(), View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            childView.layout(0, 0, childView.getMeasuredWidth(), childView.getMeasuredHeight());
            childView.setDrawingCacheEnabled(true);
            childView.buildDrawingCache();
            bmps.add(childView.getDrawingCache());
            allitemsheight += childView.getMeasuredHeight();
        }
        Bitmap bigbitmap = Bitmap.createBitmap(listview.getMeasuredWidth(), allitemsheight, Bitmap.Config.ARGB_8888);
        Canvas bigcanvas = new Canvas(bigbitmap);
        Paint paint = new Paint();
        int iHeight = 0;
        for (int i = 0; i < bmps.size(); i++) {
            Bitmap bmp = bmps.get(i);
            bigcanvas.drawBitmap(bmp, 0, iHeight, paint);
            iHeight += bmp.getHeight();
            bmp.recycle();
            bmp = null;
        }
        return bigbitmap;
    }


}
