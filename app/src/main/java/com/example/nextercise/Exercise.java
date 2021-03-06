package com.example.nextercise;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("Exercise")
public class Exercise extends ParseObject {
    public static final String KEY_OBJECTID = "objectId";
    public static final String KEY_EXERCISENAME = "exerciseName";
    public static final String KEY_EXERCISEDESCRIPTION = "exerciseDescription";
    public static final String KEY_EXERCISEIMAGE = "exerciseImg";
    public static final String KEY_VIDEOURL = "videoURL";
    public static final String KEY_EXERCISEINSTRUCTIONS = "exerciseInstructions";
    public static final String KEY_EXERCISEID = "exerciseId";

    private String objectId;
    private String exerciseName;
    private String exerciseDescription;
    private ParseFile exerciseImage;
    private String videoUrl;
    private String exerciseInstructions;
    private int exerciseId;

    public Exercise(){}
    public String getObjectId() {
        return getString(KEY_OBJECTID);
    }
    public void setObjectId(String objectId) {
        put(KEY_OBJECTID, objectId);
    }

    public String getExerciseName() {
        return getString(KEY_EXERCISENAME);
    }
    public void setExerciseName(String exerciseName) {
        put(KEY_EXERCISENAME, exerciseName);
    }

    public String getExerciseDescription() {
        return getString(KEY_EXERCISEDESCRIPTION);
    }
    public void setExerciseDescription(String exerciseDescription) { put(KEY_EXERCISEDESCRIPTION, exerciseDescription);}

    public ParseFile getExerciseImage() {
        return getParseFile(KEY_EXERCISEIMAGE);
    }
    public void setKeyExerciseImage(ParseFile parseFile) {
        put(KEY_EXERCISEIMAGE, parseFile);
    }

    public String getVideoURL() {
        return getString(KEY_VIDEOURL);
    }
    public void setVideoURL(String videoURL) {
        put(KEY_VIDEOURL, videoURL);
    }

    public String getExerciseInstructions() {
        return getString(KEY_EXERCISEINSTRUCTIONS);
    }
    public void setExerciseInstructions(String exerciseInstructions) { put(KEY_EXERCISEINSTRUCTIONS, exerciseInstructions); }

    public int getExerciseId() {
        return getInt(KEY_EXERCISEID);
    }
    public void setExerciseId(Integer exerciseId) { put(KEY_EXERCISEID, exerciseId); }
}
