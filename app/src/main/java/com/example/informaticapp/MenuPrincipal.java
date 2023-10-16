package com.example.informaticapp;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.google.firebase.auth.FirebaseAuth;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MenuPrincipal extends AppCompatActivity {

    private ImageView imageRepaso;
    private ImageView imagenAtras;
    private ImageView imageQuiz;
    private Button btnCerrarSesion; // Agregar el botón de cierre de sesión

    private FirebaseAuth mAuth; // Agregar la instancia de Firebase Auth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        imageRepaso = findViewById(R.id.imageView);
        imageQuiz = findViewById(R.id.imageView1);

        btnCerrarSesion = findViewById(R.id.btnCerrarSesion); // Obtener referencia al botón

        // Configurar el clic del botón de cierre de sesión
        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un diálogo de confirmación
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuPrincipal.this);
                builder.setTitle("Confirmación");
                builder.setMessage("¿Estás seguro de que quieres cerrar la sesión?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Cerrar la sesión actual
                        mAuth.signOut();

                        // Redirigir al usuario a la pantalla de inicio de sesión
                        Intent intent = new Intent(MenuPrincipal.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Cierra la actividad actual
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // No hacer nada si el usuario elige "No"
                    }
                });

                // Mostrar el diálogo
                builder.show();
            }
        });

        imageQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipal.this, MenuQuiz.class);
                startActivity(intent);
            }
        });
        imageRepaso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipal.this, MenuRepaso.class);
                startActivity(intent);
            }
        });
    }

}