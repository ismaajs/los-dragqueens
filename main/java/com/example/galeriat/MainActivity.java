package com.example.galeriat;
import static com.example.galeriat.R.id.buscar;
import static com.example.galeriat.R.id.nav_attch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ListView listView2;
    ImageView visor;

    int ima1[]={R.drawable.cerdo,R.drawable.gatoo};
    int ima2[]={R.drawable.pro,R.drawable.conejo};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //instanciar vista
        listView = findViewById(R.id.imageness);
        listView2 = findViewById(R.id.imagenes1);
        //instanciar elementos que contiene la lista mediante SetAdapter
        listView.setAdapter(new carpetas(this,ima1,ima2));
        listView2.setAdapter(new carpetas(this,ima2,ima1));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(view.getContext(),listaImagenes.class);
                String resultado=String.valueOf(i);
                intent.putExtra("resultado",resultado);
                startActivity(intent);
            }
        });
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(view.getContext(),subCarpeta.class);
                String resultado=String.valueOf(i);
                intent.putExtra("resultado",resultado);
                startActivity(intent);
            }
        });

    }

    public void actividad4(View vie){
        Intent inten = new Intent(vie.getContext(),prueba.class);
        startActivity(inten);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.appbas_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.nav_attch:
                Toast.makeText(this,"Foto",Toast.LENGTH_SHORT).show();

                setContentView(R.layout.activity_prueba);
                visor = findViewById(R.id.guardarFoto);
                camara();
                break;
            case R.id.archivos:
                Toast.makeText(this,"Archivos",Toast.LENGTH_SHORT).show();
                break;
            case R.id.buscar:
                Toast.makeText(this,"Editar",Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    private void camara(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent, 1);

        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            visor.setImageBitmap(imgBitmap);
        }
    }
}