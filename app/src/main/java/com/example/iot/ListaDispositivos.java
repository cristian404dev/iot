package com.example.iot;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class ListaDispositivos extends ListActivity {
    private BluetoothAdapter meuBluetoothAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> arrayBT = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        meuBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Set<BluetoothDevice> dispositivosPareados = meuBluetoothAdapter.getBondedDevices();
        if(!dispositivosPareados.isEmpty()) {
            for(BluetoothDevice dispositivo : dispositivosPareados) {
                String nomeBT = dispositivo.getName();
                String macBT = dispositivo.getAddress();
                arrayBT.add(nomeBT + "\n" + macBT);
            }
        }
        setListAdapter(arrayBT);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String informacaoGeral = ((TextView) v).getText().toString();
        String nomeDispositivo = informacaoGeral.substring(0, informacaoGeral.length()-18);
        String enderecoMAC = informacaoGeral.substring(informacaoGeral.length()-17);
        Toast.makeText(getApplicationContext(),
                "Info: " + nomeDispositivo + "\nEndereço MAC: " + enderecoMAC,
                Toast.LENGTH_LONG).show();
        Intent retornaMAC = new Intent();
        retornaMAC.putExtra("nome_dispositivo", informacaoGeral);
        retornaMAC.putExtra("endereco_MAC", enderecoMAC);
        setResult(RESULT_OK, retornaMAC);
        finish();
    }
}