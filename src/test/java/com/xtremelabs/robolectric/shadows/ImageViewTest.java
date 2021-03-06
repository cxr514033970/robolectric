package com.xtremelabs.robolectric.shadows;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.widget.ImageView;
import com.xtremelabs.robolectric.R;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.WithTestDefaultsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.xtremelabs.robolectric.Robolectric.visualize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(WithTestDefaultsRunner.class)
public class ImageViewTest {
    private ImageView imageView;

    @Before
    public void setUp() throws Exception {
        Resources resources = Robolectric.application.getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(resources,
                R.drawable.an_image);
        imageView = new ImageView(Robolectric.application);
        imageView.setImageBitmap(bitmap);
    }

    @Test
    public void shouldDrawWithImageMatrix() throws Exception {
        imageView.setImageMatrix(new Matrix());
        assertEquals("Bitmap for resource:drawable/an_image",
                visualize(imageView));

        Matrix matrix = new Matrix();
        matrix.setTranslate(15, 20);
        imageView.setImageMatrix(matrix);
        assertEquals("Bitmap for resource:drawable/an_image at (15,20)",
                visualize(imageView));
    }

    @Test
    public void shouldCopyMatrixSetup() throws Exception {
        Matrix matrix = new Matrix();
        matrix.setTranslate(15, 20);
        imageView.setImageMatrix(matrix);
        assertEquals("Bitmap for resource:drawable/an_image at (15,20)",
                visualize(imageView));

        matrix.setTranslate(30, 40);
        assertEquals("Bitmap for resource:drawable/an_image at (15,20)",
                visualize(imageView));

        imageView.setImageMatrix(matrix);
        assertEquals("Bitmap for resource:drawable/an_image at (30,40)",
                visualize(imageView));
    }

    @Test
    public void testSetImageResource_drawable() {
        imageView.setImageResource(R.drawable.l0_red);
        assertTrue("Drawable", imageView.getDrawable() instanceof Drawable);
        assertFalse("LayerDrawable",
                imageView.getDrawable() instanceof LayerDrawable);
    }

    @Test
    public void testSetImageResource_layerDrawable() {
        imageView.setImageResource(R.drawable.rainbow);
        assertTrue("Drawable", imageView.getDrawable() instanceof Drawable);
        assertTrue("LayerDrawable",
                imageView.getDrawable() instanceof LayerDrawable);
    }
}
