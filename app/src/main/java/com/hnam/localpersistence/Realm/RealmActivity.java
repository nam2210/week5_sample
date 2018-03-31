package com.hnam.localpersistence.Realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hnam.localpersistence.R;
import com.hnam.localpersistence.Realm.model.Person;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmActivity extends AppCompatActivity {
    private static final String TAG = RealmActivity.class.getSimpleName();
    private Realm realm;
    private LinearLayout rootLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
        rootLayout = findViewById(R.id.container);
        rootLayout.removeAllViews();

        // Create the Realm instance
        realm = Realm.getDefaultInstance();
        basicCRUD(realm);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void showStatus(String txt) {
        Log.i(TAG, txt);
        TextView tv = new TextView(this);
        tv.setText(txt);
        rootLayout.addView(tv);
    }

    private void basicCRUD(Realm realm){
        showStatus("Perform basic Create/Read/Update/Delete (CRUD) operations...");

        // All writes must be wrapped in a transaction to facilitate safe multi threading
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Add a person
                Person person = realm.createObject(Person.class);
                person.setId(1);
                person.setName("Young Person");
                person.setAge(14);

            }
        });

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Add a person
                Person person = realm.createObject(Person.class);
                person.setId(1);
                person.setName("Young Husband");
                person.setAge(20);

            }
        });


        // Find the first person (no query conditions) and read a field
        final Person person = realm.where(Person.class)
                .equalTo("age", 20)
                .findFirst();
        showStatus(person.getName() + ":" + person.getAge());

        // Update person in a transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                person.setName("Drink Water");
                person.setAge(99);
                showStatus(person.getName() + " got older: " + person.getAge());
            }
        });


        // Delete one persons
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                person.deleteFromRealm();
                RealmResults<Person> persons = realm.where(Person.class).findAll();
                showStatus("number of person: " + String.valueOf(persons.size()));
            }
        });

        // Delete all persons
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(Person.class);
                RealmResults<Person> persons = realm.where(Person.class).findAll();
                showStatus("number of person: " + String.valueOf(persons.size()));
            }
        });


    }



}
