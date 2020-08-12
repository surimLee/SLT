package com.example.slt_ver2;

import android.bluetooth.BluetoothAdapter;
import android.os.Build;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;
import android.bluetooth.BluetoothAdapter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import com.example.slt_ver2.R;
import com.example.slt_ver2.TranslationFragment;
import com.example.slt_ver2.ListViewFragment;
import com.example.slt_ver2.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.slt_ver2.TranslationFragment.handler;

public class MainActivity extends AppCompatActivity {
    static BluetoothService bluetoothService = null;
    private BluetoothAdapter mBluetoothAdapter = null;

    private Socket socket;  //소켓생성
    static BufferedReader in;      //서버로부터 온 데이터를 읽는다.
    static PrintWriter out;        //서버에 데이터를 전송한다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavigationview_main);
        bottomNavigationView.setSelectedItemId(R.id.navigation_translator);
        getFragmentManager().beginTransaction().replace(R.id.layout_container, new TranslationFragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@androidx.annotation.NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_translator:
                        getFragmentManager().beginTransaction().replace(R.id.layout_container, new TranslationFragment()).commit();
                        return true;
                    case R.id.navigation_list:
                        getFragmentManager().beginTransaction().replace(R.id.layout_container, new ListFragment()).commit();
                        return true;
                    case R.id.navigation_setting:
                        getFragmentManager().beginTransaction().replace(R.id.layout_container, new SettingFragment()).commit();
                        return true;
                }
                return false;
            }
        });

        Thread main_socket = new Thread() {
            public void run() { //스레드 실행구문
                try {
                    //소켓을 생성하고 입출력 스트립을 소켓에 연결한다.
                    socket = new Socket("115.85.173.148", 8888); //소켓생성
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true); //데이터를 전송시 stream 형태로 변환하여 전송한다.
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //데이터 수신시 stream을 받아들인다.

                } catch (
                        IOException e) {
                    e.printStackTrace();
                }
            }
        };
        main_socket.start();

        if(bluetoothService == null) {
            bluetoothService = new BluetoothService(this, handler);
        }
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


    }

}
