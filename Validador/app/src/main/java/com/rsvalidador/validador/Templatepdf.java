package com.rsvalidador.validador;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;


import com.example.validador.R;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;


public class Templatepdf {
    private Context context;
    private  File pdffile;
    private Document document;
    private PdfWriter pdfWriter;
    private Paragraph paragraph;
    private Font fTitle = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
    private Font fsubTitle = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private Font fText = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    private Font frfcbuenoText = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.GREEN);
    private Font frfcmaloText = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.RED);
    private Font fsolText = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);


    public Templatepdf(Context context) {

        this.context = context;

    }



    public void Opendocument(String nombre){

        createfile(nombre);
        try{

         document = new Document(PageSize.A4);
         pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pdffile));
         document.open();

        }
        catch (Exception e){
            Log.e("Opendocument", e.toString());
        }

    }


    private void createfile(String nombre){

        File folder = new File(Environment.getExternalStorageDirectory().toString(), "RS");

        if(!folder.exists()){
            folder.mkdir();
        }
        pdffile = new File(folder, nombre +".pdf");

    }

public  void closedocument(){

        document.close();
}

public void addData(String title, String subject, String author){
        document.addTitle(title);
        document.addSubject(subject);
        document.addAuthor(author);


}


public void addTitlesrfcbueno(String title, String subtitle, String date){
    try{

        paragraph = new Paragraph();
    addChild(new Paragraph(title, fTitle));
    addChild(new Paragraph(subtitle, frfcbuenoText));
    addChild(new Paragraph(date, fsubTitle));

    paragraph.setSpacingAfter(30);
    document.add(paragraph);
    }
    catch (Exception e){
        Log.e("addTitles", e.toString());
    }
}

    public void addTitlesrfcmalo(String title, String subtitle, String date){
        try{

            paragraph = new Paragraph();
            addChild(new Paragraph(title, fTitle));
            addChild(new Paragraph(subtitle, frfcmaloText));
            addChild(new Paragraph(date, fsubTitle));

            paragraph.setSpacingAfter(30);
            document.add(paragraph);
        }
        catch (Exception e){
            Log.e("addTitles", e.toString());
        }
    }

public  void addParagraph(String text){

    try{
        paragraph = new Paragraph(text, fsolText);
        //paragraph.setSpacingAfter(5000);
        paragraph.setSpacingBefore(500);
       // paragraph.setAbsolutePosition(200f, 0f);
        document.add(paragraph);
    }
    catch (Exception e){
        Log.e("addparagraph", e.toString());
    }

}

private void addChild(Paragraph childparagraph){
childparagraph.setAlignment(Element.ALIGN_CENTER);
paragraph.add(childparagraph);
    }


    public void viewpdf(){
        Intent intent = new Intent(context, viewpdf.class);
        intent.putExtra("path", pdffile.getAbsolutePath());
        intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    public void addImgNameRS () {
        try{

            Drawable d = context.getResources().getDrawable(R.drawable.ram);
            BitmapDrawable bitDw = ((BitmapDrawable) d);
            Bitmap bmp = bitDw.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image image = Image.getInstance(stream.toByteArray());
            image.setSpacingBefore(5);
            image.setSpacingAfter(5);
            image.setAbsolutePosition(0f, 0f);
            image.scaleToFit(200,200);
            image.setAlignment(Element.ALIGN_CENTER);
            document.add(image);
        }catch (Exception e){
            Log.e("addImgName ", e.toString());
        }
    }

    public void addImgNamelv () {
        try{

            Drawable d = context.getResources().getDrawable(R.drawable.ave);
            BitmapDrawable bitDw = ((BitmapDrawable) d);
            Bitmap bmp = bitDw.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image image = Image.getInstance(stream.toByteArray());
            image.setSpacingBefore(5);
            image.setSpacingAfter(5);
            image.setAbsolutePosition(50f, 0f);
            image.scaleToFit(150,150);
            image.setAlignment(Element.ALIGN_CENTER);
            document.add(image);
        }catch (Exception e){
            Log.e("addImgName ", e.toString());
        }
    }

}

