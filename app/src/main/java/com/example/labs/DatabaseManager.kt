import android.content.ContentValues
import android.content.Context
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DatabaseManager(context: Context) {
    private val dbHelper = MyDatabaseHelper(context)

    fun insertData(name: String, date: String) {
        val db = dbHelper.writableDatabase
        val values = ContentValues()
        values.put(MyDatabaseHelper.COLUMN_NAME, name)
        values.put(MyDatabaseHelper.COLUMN_DATE, date)
        db.insert(MyDatabaseHelper.TABLE_NAME, null, values)
    }

    fun insertData(name: String) {
        val date = generateCurrentDate()
        val db = dbHelper.writableDatabase
        val values = ContentValues()
        values.put(MyDatabaseHelper.COLUMN_NAME, name)
        values.put(MyDatabaseHelper.COLUMN_DATE, date)
        db.insert(MyDatabaseHelper.TABLE_NAME, null, values)
    }

    fun insertDataAtStart(count: Int){
        dbHelper.deleteAllData(dbHelper.writableDatabase)
        for (i in 1..count) {
            insertData("Fake$i Full$i Name$i", generateCurrentDate())
        }
    }

    fun getAllGroupmates(): List<Groupmate> {
        val groupmates = mutableListOf<Groupmate>()
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            MyDatabaseHelper.TABLE_NAME,
            arrayOf(MyDatabaseHelper.COLUMN_ID, MyDatabaseHelper.COLUMN_NAME, MyDatabaseHelper.COLUMN_DATE),
            null, null, null, null, null
        )

        if (cursor.moveToFirst()) {
            do {
                val groupmate = Groupmate(
                    cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_DATE))
                )
                groupmates.add(groupmate)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return groupmates
    }

    fun generateCurrentDate(): String{
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        return formatter.format(date)
    }

    fun getLastRecordId(): Int? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            MyDatabaseHelper.TABLE_NAME,
            arrayOf(MyDatabaseHelper.COLUMN_ID),
            null, null, null, null,
            "${MyDatabaseHelper.COLUMN_ID} DESC",
            "1"
        )

        var lastId: Int? = null
        if (cursor.moveToFirst()) {
            lastId = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_ID))
        }
        cursor.close()
        return lastId
    }

    fun updateLastRecord(name: String): Boolean {
        val lastId = getLastRecordId() ?: return false
        val db = dbHelper.writableDatabase
        val values = ContentValues()
        val date = generateCurrentDate()
        values.put(MyDatabaseHelper.COLUMN_NAME, name)
        values.put(MyDatabaseHelper.COLUMN_DATE, date)

        val rowsAffected = db.update(
            MyDatabaseHelper.TABLE_NAME,
            values,
            "${MyDatabaseHelper.COLUMN_ID} = ?",
            arrayOf(lastId.toString())
        )
        return rowsAffected > 0
    }
}

data class Groupmate(val id: Int, val name: String, val date: String)