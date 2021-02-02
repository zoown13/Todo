package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Priority;
import com.amplifyframework.datastore.generated.model.Todo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());

            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException failure) {
            Log.e("Tutorial", "Could not initialize Amplify", failure);
        }

        Amplify.DataStore.observe(Todo.class,
                started -> Log.i("Tutorial", "Observation began."),
                change -> Log.i("Tutorial", change.item().toString()),
                failure -> Log.e("Tutorial", "Observation failed.", failure),
                () -> Log.i("Tutorial", "Observation complete.")
        );

        //query the item from DataStore
        /*Amplify.DataStore.query(
                Todo.class,
                todos -> {
                    while(todos.hasNext()){
                        Todo todo = todos.next();

                        Log.i("Tutorial", "==== Todo ====");
                        Log.i("Tutorial", "Name: " + todo.getName());

                        if(todo.getPriority() != null){
                            Log.i("Tutorial", "Priority: " + todo.getPriority().toString());
                        }//getPriority

                        if(todo.getDescription() != null){
                            Log.i("Tutorial", "Description: " + todo.getDescription());
                        }//getDescription
                    }//while
                },//todos
                failure -> Log.e("Tutorial", "Could not query DataStore", failure)
        );*/


        //create an item to save
        /*Todo item = Todo.builder()
                .name("Finish quarterly taxes")
                .priority(Priority.HIGH)
                .description("Taxes are due for the quarter next week")
                .build();*/

        //save the item to DataStore
        /*Amplify.DataStore.save(
                item,
                success -> Log.i("Tutorial", "Saved item "+success.item().getName()),
                error -> Log.e("Tutorial", "Could not save the item to DataStore", error)
        );*/
    }
}