package json;

import com.google.gson.Gson;

import java.util.List;

public abstract class JsonReader<T> {

    private Gson gson;
    private Class<T> aClass;

}
