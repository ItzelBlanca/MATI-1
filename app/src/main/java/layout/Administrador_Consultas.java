package layout;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cj.mati.R;

import java.util.ArrayList;

public class Administrador_Consultas extends Fragment {

    String filtro = "";

    FloatingActionButton btn_query, btn_clear;
    TextView tvFiltro;
    View vista;


    public Administrador_Consultas() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista =  inflater.inflate(R.layout.fragment_administrador__consultas, container, false);
        Fragment fragment = new administrador_tienda_recycle();
        if(getArguments() != null){
            Bundle b = new Bundle();
            b.putString("USER", getArguments().getString("USER"));
            fragment.setArguments(b);
        }
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_tiendas2,fragment).commit();
        loadControls();
        tvFiltro.setText("GENERAL");
        return vista;
    }

    private void loadControls() {
        tvFiltro = (TextView) vista.findViewById(R.id.tv_Filtro);
        btn_query = (FloatingActionButton) vista.findViewById(R.id.btn_query);
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fitro();
            }
        });
        btn_clear = (FloatingActionButton) vista.findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvFiltro.setText("GENERAL");
                Fragment fragment = new administrador_tienda_recycle();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_tiendas2,fragment).commit();
            }
        });
    }

    private void fitro() {
        filtro = "";
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Tipo de Busqueda:")
                .setItems(R.array.qTipo, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //0 = Lugar, 1 = Giro
                        if(which == 0){
                            filtro += "LUGAR";
                            selectEstado();
                        }else{
                            filtro += "GIRO";
                            selectGiro();
                        }
                    }
                });
        builder.create();
        builder.show();
    }

    private void selectEstado() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Seleccione Estado:")
                .setItems(R.array.Estados, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        selectMunicipio();                    }
                });
        builder.create();
        builder.show();
    }

    private void selectMunicipio() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Seleccione Municipio:")
                .setItems(R.array.Municipios, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        selectColonia();                    }
                });
        builder.create();
        builder.show();
    }

    private void selectColonia() {
        final ArrayList<String> colonias = new ArrayList<>();

        colonias.add("Rincon de Dolores");
        colonias.add("Centro");
        colonias.add("La mangana");
        colonias.add("La morita");
        colonias.add("La fabrica");
        colonias.add("El zapotito");

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Seleccione Colonia:")
                .setItems(R.array.Colonias1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        filtro = colonias.get(which);
                        Fragment fragment = new administrador_tienda_recycle();
                        Bundle b = new Bundle();
                        b.putString("FILTRO", filtro);
                        fragment.setArguments(b);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_tiendas2,fragment).commit();
                    }
                });
        builder.create();
        builder.show();
    }

    private void selectGiro() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Selecciona el giro:")
                .setItems(R.array.qGiros, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                filtro+="abarrotes";
                                tvFiltro.setText("Abarrotes");
                                break;
                            case 1:
                                filtro+="zapateria";
                                tvFiltro.setText("Zapaterias");
                                break;
                        }

                        Fragment fragment = new administrador_tienda_recycle();
                        Bundle b = new Bundle();
                        b.putString("FILTRO", filtro);
                        fragment.setArguments(b);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_tiendas2,fragment).commit();
                    }
                });
        builder.create();
        builder.show();
    }

}
