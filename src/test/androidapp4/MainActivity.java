package test.androidapp4;

import java.util.List;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

	String dataMahasiswa[] = null;
	DbHandler db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);

		db = new DbHandler(this);

		Log.d("Insert: ", "inserting...");
		db.addMahasiswa(new Mahasiswa("001", "Harun Al Rasyid"));
		db.addMahasiswa(new Mahasiswa("002", "Ali bin Yahya"));
		db.addMahasiswa(new Mahasiswa("003", "Ahmad"));

		Log.d("Reading: ", "Reading all...");
		List<Mahasiswa> mhs = db.getAllMhs();

		dataMahasiswa = new String[mhs.size()];

		int i = 0;
		for (Mahasiswa m : mhs) {
			String log = "Nim: " + m.getNim() + ",Nama: " + m.getNama();
			Log.d("Name: ", log);
			dataMahasiswa[i] = m.getNim() + " - " + m.getNama();
			i++;
		}

		setListAdapter(new ArrayAdapter<Object>(this,
				android.R.layout.simple_list_item_1, dataMahasiswa));
		registerForContextMenu(getListView());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Action");
		menu.add("Tambah");
		menu.add("Hapus");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.equals("Tambah")) {
			try {
				Class<?> c = Class.forName("test.androidapp4.TambahActivity");
				Intent i = new Intent(MainActivity.this, c);
				startActivity(i);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (item.equals("Hapus")) {
			// delete ????
		}

		return true;
	}

}
