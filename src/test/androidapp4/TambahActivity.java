package test.androidapp4;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TambahActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tambah);

		final DbHandler db = new DbHandler(this);
		final EditText editNim = (EditText) findViewById(R.id.editNim);
		final EditText editNama = (EditText) findViewById(R.id.editNama);

		Button btnTambah = (Button) findViewById(R.id.buttonTambah);
		btnTambah.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODOAuto-generated method stub
				String nim = editNim.getText().toString();
				String nama = editNama.getText().toString();
				db.addMahasiswa(new Mahasiswa(nim, nama));
				editNim.setText("");
				editNama.setText("");
				try {
					Class<?> c = Class
							.forName("emha.android.aplikasi8.MainActivity");
					Intent i = new Intent(TambahActivity.this, c);
					startActivity(i);
				} catch (ClassNotFoundException e) {
					// TODOAuto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tambah, menu);
		return true;
	}

}
