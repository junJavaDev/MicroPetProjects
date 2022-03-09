package ru.junjavadev.filemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;

import ru.junjavadev.filemanager.comparators.SortByName;

public class MainActivity extends AppCompatActivity {
//    private TextView tvPermission;
//    private Button btnPermission;
    private static final int PERMISSION_STORAGE = 101;
    public static Entry HOME_ENTRY = new Entry(Environment.getExternalStorageDirectory().toPath());
    ArrayList<Entry> entries = new ArrayList<>();
    FmFileVisitor fileVisitor = new FmFileVisitor(new SortByName());
    static Path pathSource;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();   // Разобраться с этой ошибкой
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        if (!PermissionUtils.hasPermissions(getContext())) {
            PermissionUtils.requestPermissions(MainActivity.this, PERMISSION_STORAGE);
            onStop();
            onDestroy();
            onStart();
        }



//        tvPermission = findViewById(R.id.tvPermission);
//        btnPermission = findViewById(R.id.btnPermission);
//
//        if (PermissionUtils.hasPermissions(this)) {
//            tvPermission.setText("Разрешение получено");
//        } else {
//            tvPermission.setText("Разрешение не предоставлено");
//        }
//        btnPermission.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//        if (PermissionUtils.hasPermissions(MainActivity.this)) return;
//        PermissionUtils.requestPermissions(MainActivity.this, PERMISSION_STORAGE);
//        }});


        pathSource = HOME_ENTRY.getEntryPath();    // pathSource = Paths.get("/storage/emulated/0");


        // начальная инициализация списка

        RecyclerView recyclerView = findViewById(R.id.entry_list);
        // определяем слушателя нажатия элемента в списке
        EntryAdapter.OnEntryClickListener entryClickListener = new EntryAdapter.OnEntryClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onEntryClick(Entry entry, int position) {
//                Toast.makeText(getApplicationContext(), "Был выбран пункт " + entry.getEntryName(),
//                Toast.LENGTH_SHORT).show();
                if (Files.isDirectory(entry.getEntryPath())) {
                    if (pathSource == entry.getEntryPath()) {
                        pathSource = pathSource.getParent();
                    } else {
                        pathSource = pathSource.resolve(entry.getEntryPath());
                    }
                    entries.clear();
                    entries.addAll(fileVisitor.visitDirectory(pathSource, fileVisitor));
                    Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();

                } else {
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromFile(entry.getEntryPath().toFile()));
//                    intent.setType("*/*");
//                    startActivity(intent);
                    // создаём новое намерение
                    Intent intent = Intents.openFile(entry, getContext());

                    // устанавливаем флаг для того, чтобы дать внешнему приложению пользоваться нашим FileProvider
//                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                    // генерируем URI, я определил полномочие как ID приложения в манифесте, последний параметр это файл, который я хочу открыть
//                    Uri uri = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID, entry.getEntryPath().toFile());



                    // подтвердите, что устройство может открыть этот файл!
                    PackageManager pm = getContext().getPackageManager();
                    if (intent.resolveActivity(pm) != null) {
                        startActivity(intent);
                    }

//                    Intent intent = new Intent("android.intent.action.VIEW");
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    intent.putExtra("oneshot", 0);
//                    intent.putExtra("configchange", 0);
//                    Uri uri = Uri.fromFile(entry.getEntryPath().toFile());
//                    intent.setDataAndType(uri, "audio/*");
//                    startActivity(intent);
                }
            }

        };
        // создаем адаптер
        EntryAdapter adapter = new EntryAdapter(this, entries, entryClickListener);
        //установка адаптера на список recyclerView
        recyclerView.setAdapter(adapter);
        entries.addAll(fileVisitor.visitDirectory(pathSource, fileVisitor));

    }

    private Context getContext() {
        return this;
    }


//    Context getActivity() {
//        return this;
//    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == PERMISSION_STORAGE) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                if (PermissionUtils.hasPermissions(this)) {
//                    tvPermission.setText("Разрешение получено");
//                } else {
//                    tvPermission.setText("Разрешение не предоставлено");
//                }
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//            if (requestCode == PERMISSION_STORAGE) {
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    tvPermission.setText("Разрешение получено");
//                } else {
//                    tvPermission.setText("Разрешение не предоставлено");
//                }
//            }
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }



}