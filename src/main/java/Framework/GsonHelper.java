package Framework;

import com.google.common.base.Preconditions;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;

public class GsonHelper {

    public static GsonHelper helper = null;
    private Gson gson;


    private GsonHelper(){

    }


    public Gson getGson() {

        return  gson != null ? gson : new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    }

    public static GsonHelper getHelper() {

        return helper != null ? helper : new GsonHelper();

    }


    public JsonArray getJsonArray(String key , String json ){

        JsonObject object = JsonParser.parseString( json ).getAsJsonObject()  ;
        JsonArray array = object.getAsJsonArray( key );
        return array;
    }

    public JsonElement getJsonObject(String key, String json ){
        Preconditions.checkNotNull( json );
        JsonObject object = JsonParser.parseString( json ).getAsJsonObject()  ;
        if ( object.get( key ) instanceof JsonArray ){
            return object.get( key ).getAsJsonArray();
        } else if (  object.get( key ) instanceof JsonObject ){
            return object.get( key );
        } else if( object.get( key ) instanceof JsonPrimitive){
            return object.getAsJsonPrimitive( key );
        }

        return null;
    }


     protected Map<String, Object> deSerialise(String file) {

        Type dc = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, Object> caps = getGson().fromJson(readJson(file), dc);
        return caps;

    }


     protected JsonReader readJson(String file) {
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader( FrameworkConstants.RESOURCE_PATH + File.separator + file ));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return reader;
    }


}

