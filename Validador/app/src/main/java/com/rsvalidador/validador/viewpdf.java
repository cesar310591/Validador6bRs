package com.rsvalidador.validador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.validador.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class viewpdf extends AppCompatActivity {
private PDFView pdfView;
private File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpdf);
        pdfView = findViewById(R.id.pdfView);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
file = new File(bundle.getString("path", ""));
        }

        pdfView.fromFile(file)
                .enableSwipe(true)
                .swipeHorizontal(true)
                .enableDoubletap(true)
                .enableAntialiasing(true)
                .load();
    }
}
