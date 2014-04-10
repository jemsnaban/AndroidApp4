package test.androidapp4;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "univ";
	private static final String TABLE_MHS = "Mahasiswa";
	private static final String KEY_NIM = "nim";
	private static final String KEY_NAMA = "nama";

	public DbHandler(Context context) {
		// TODO Auto-generated constructor stub
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String query = "CREATE TABLE " + TABLE_MHS + "(" + KEY_NIM
				+ " TEXT PRIMARY KEY," + KEY_NAMA + " TEXT)";
		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MHS);
		// Create tables again
		onCreate(db);
	}

	public void addMahasiswa(Mahasiswa mhs) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NIM, mhs.getNim());
		values.put(KEY_NAMA, mhs.getNama());
		// Inserting Row
		db.insert(TABLE_MHS, null, values);
		db.close();
	}

	public Mahasiswa getMhs(String nim) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_MHS, new String[] { KEY_NIM, KEY_NAMA },
				KEY_NIM + "=?", new String[] { nim }, null, null, null, null);

		if (cursor != null)
			cursor.moveToFirst();
		Mahasiswa mhs = new Mahasiswa(cursor.getString(0), cursor.getString(1));

		return mhs;
	}

	public List<Mahasiswa> getAllMhs() {
		List<Mahasiswa> mhsList = new ArrayList<Mahasiswa>();
		String query_select_siswa = "SELECT * FROM " + TABLE_MHS;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query_select_siswa, null);
		if (cursor.moveToFirst()) {
			do {
				Mahasiswa mhs = new Mahasiswa(cursor.getString(0),
						cursor.getString(1));
				mhsList.add(mhs);
			} while (cursor.moveToNext());
		}
		return mhsList;
	}
	
	public void deleteMhs(Mahasiswa mhs) {
		SQLiteDatabase db = this.getWritableDatabase(); 
		db.delete(TABLE_MHS, KEY_NIM+ " = ?", new String[] { mhs.getNim()}); 
		db.close();
	}

}
