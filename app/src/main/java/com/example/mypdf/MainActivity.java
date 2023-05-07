package com.example.mypdf;



import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.pdf_btn);
        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PdfDocument  myPdfDocument = new PdfDocument();
                Paint myPaint = new Paint();

                PdfDocument.PageInfo myPageinfo = new PdfDocument.PageInfo.Builder(400,600,1).create();
                PdfDocument.Page myPage = myPdfDocument.startPage(myPageinfo);
                Canvas canvas = myPage.getCanvas();
                canvas.drawText("Welcome to Create PDF",40,50,myPaint);
                myPdfDocument.finishPage(myPage);

                String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
                File file = new File(pdfPath,"In01Pdf.pdf");
                try {
                    myPdfDocument.writeTo(new FileOutputStream(file));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                myPdfDocument.close();
                Toast.makeText(MainActivity.this, "Create Pdf Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }
}