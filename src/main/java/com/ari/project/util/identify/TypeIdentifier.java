package com.ari.project.util.identify;

import com.ari.project.util.identify.type.TypeFile;
import com.ari.project.util.identify.type.TypeJSON;
import com.ari.project.util.identify.type.TypeXML;

public abstract class TypeIdentifier {
    private static boolean identifyXML(String entry){
        TypeFile typeFile = new TypeXML();
        return typeFile.validateSchema(entry);
    }
    private static boolean identifyJSON(String entry){
        TypeFile typeFile = new TypeJSON();
        return typeFile.validateSchema(entry);
    }
    public static String identifyType(String entry){
        if(TypeIdentifier.identifyJSON(entry)){
            return "json";
        }
        else if (TypeIdentifier.identifyXML(entry)){
            return "xml";
        }
        else {
            return "txt";
        }
    }
}
