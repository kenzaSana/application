package com.example.kenzack.applicationtest.View;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.kenzack.applicationtest.R;
import com.example.kenzack.applicationtest.model.MyApplication;
import com.example.kenzack.applicationtest.model.Session;
import com.example.kenzack.applicationtest.model.Utilisateur;
import com.example.kenzack.applicationtest.service.ImageStorageService;

public class impoter_image extends AppCompatActivity {

    private static int LAOD_IMage_Results = 1;
    static final int Reqest_image_capture = 1;
    private Button gallerie, appareil, publier;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impoter_image);
        image = (ImageView) findViewById(R.id.imageView);
        gallerie = (Button) findViewById(R.id.button5);
        appareil = (Button) findViewById(R.id.button6);
        appareil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, Reqest_image_capture);
            }
        });

        gallerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, LAOD_IMage_Results);

            }
        });

    }
    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        if (requestCode == LAOD_IMage_Results && resultCode == RESULT_OK && data != null) {
            Uri pickedImage = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            final String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
            image.setImageBitmap(BitmapFactory.decodeFile(imagePath));
            final Drawable drawable = image.getDrawable();
            cursor.close();
            ImageStorageService iSS = new ImageStorageService();
            Utilisateur proprietaire = getSession().getUtilisateur();
            iSS.storeImage(proprietaire, imagePath);

        } else if (requestCode == Reqest_image_capture && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            image.setImageBitmap(photo);
        }
    }
    private Session getSession(){
        MyApplication myApplication = (MyApplication)this.getApplication();
        return myApplication.getSession();
    }

}
