package ch.cardini.steven.semantic_web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class Converter {
  
  public static final File INPUT_FILE = new File("animals.rdf");
  public static final File OUTPUT_FILE = new File("animals.ttl");
  
  public enum Format {
    RDF("RDF/XML-ABBREV"), TTL("TTL"), N3("N3"), NT("N-TRIPLE");
    
    private String name;

    Format(String name) {
      this.name = name;
    }
    
    @Override
    public String toString() {
      return name;
    }
  }
  
  public static void main(String[] args) {
    
    Model model = ModelFactory.createDefaultModel();
    
    // load input file
    File inputFile = INPUT_FILE;
    Format inputFormat = getFileFormat(inputFile);
    try {
      InputStream in = new FileInputStream(INPUT_FILE);
      model.read(in, "anm", inputFormat.toString());
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    // write output file
    File outputFile = OUTPUT_FILE;
    Format outputFormat = getFileFormat(outputFile);
    try {
      OutputStream out = new FileOutputStream(outputFile);
      model.write(out, outputFormat.toString());
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
  }
  
  static Format getFileFormat(File file) {
    int i = file.getName().lastIndexOf('.');
    if (i > 0) {
        String extension = file.getName().substring(i+1);
        switch (extension.toLowerCase()) {
        case "rdf":
          return Format.RDF;
        case "ttl":
          return Format.TTL;
        case "n3":
          return Format.N3;
        case "nt":
          return Format.NT;
        }
    }
    
    throw new IllegalArgumentException("The specified file has no valid file extension!");
  }
  
}
