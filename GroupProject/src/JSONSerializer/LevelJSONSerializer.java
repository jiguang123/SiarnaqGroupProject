package JSONSerializer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import sixesWildEntity.Level;
import sixesWildEntity.Puzzle;

public class LevelJSONSerializer {

    private String fileName;

    public LevelJSONSerializer(String fName) {
        fileName = fName;
    }

    public void saveLevels(ArrayList<Level> levels) throws JSONException, IOException {
        // build an array in JSON
        JSONArray array = new JSONArray();
        for (Level l : levels)
            array.put(l.toJSON());

        // write the file to disk
        Writer writer = null;
        try {
            OutputStream out = new FileOutputStream(fileName);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null)
                writer.close();
        }
    }
    
    public ArrayList<Level> loadLevels() throws IOException, JSONException {
        ArrayList<Level> levels = new ArrayList<Level>();
        BufferedReader reader = null;
        try {
            // open and read the file into a StringBuilder
            InputStream in = new FileInputStream(fileName);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                // line breaks are omitted and irrelevant
                jsonString.append(line);
            }
            
            // parse the JSON using JSONTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            // build the array of levels from JSONObjects
            for (int i = 0; i < array.length(); i++) {
            	//levels.add(new Puzzle(array.getJSONObject(i));
            }
        } catch (FileNotFoundException e) {
            //ignore this one, since it happens when we start fresh
        	//when no levels to read
        } finally {
            if (reader != null)
                reader.close();
        }
		return levels;
    }
    
    public void saveSingleLevel(Level level) {
    	
    }
    
    public Level loadSingelLevel() {
    	return null;
    }
}
