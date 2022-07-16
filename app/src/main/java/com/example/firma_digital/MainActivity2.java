package com.example.firma_digital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.firma_digital.AdapterRecord;
import com.example.firma_digital.Constants;
import com.example.firma_digital.MainActivity;
import com.example.firma_digital.MyDbHelper;
import com.example.firma_digital.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity2 extends AppCompatActivity {

    //Views
    private FloatingActionButton addRecordBtn;

    //RecyclerView
    private RecyclerView recordsRv;

    //DB Helper
    private MyDbHelper dbHelper;

    //Action Bar
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Inicializar Vista
        addRecordBtn = findViewById(R.id.addRecordBtn);
        recordsRv = findViewById(R.id.recordsRv);
        //Inicializamos db helper Clase
        dbHelper = new MyDbHelper(this);

        //Inicializacion ActionBar
        actionBar = getSupportActionBar();
        actionBar.setTitle("Registros");

        // Cargando Registros
        loadRecords();

        // Click para Iniciar a añadir y grabar en la activity
        addRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Iniciar la Activity
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
            }
        });
    }

    private void loadRecords(){
        AdapterRecord adapterRecord = new AdapterRecord(MainActivity2.this,
                dbHelper.getAllRecords(Constants.C_ADDED_TIMESTAMP + " DESC"));

        recordsRv.setAdapter(adapterRecord);

        //Establecer el numero de Registros
        actionBar.setSubtitle("Total: "+dbHelper.getRecordsCount());
    }

    private void searchRecords(String query){
        AdapterRecord adapterRecord = new AdapterRecord(MainActivity2.this,
                dbHelper.searchRecords(query));

        recordsRv.setAdapter(adapterRecord);

    }

    @Override
    protected void onResume(){
        super.onResume();
        loadRecords();// Refresca o actualiza la lista de registros
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate menu
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //searchView


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        //maneja elementos del menu
        return super.onOptionsItemSelected(item);
    }
}
