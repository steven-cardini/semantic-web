package ch.cardini.steven.semantic_web;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDF;

public class AnimalModelGenerator {
  
  private final static String BASE = "http://www.my-namespace.ch/animals#";

  public static void main(String[] args) {
    
    // initialize ontology model
    OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
    model.setNsPrefix("anm", BASE);
    
    // create animal classes
    OntClass animal = model.createClass(BASE + "animal");
    OntClass mammal = model.createClass(BASE + "mammal");
    OntClass whale = model.createClass(BASE + "whale");
    OntClass bear = model.createClass(BASE + "bear");
    OntClass cat = model.createClass(BASE + "cat");
    OntClass fish = model.createClass(BASE + "fish");
    
    // create feature resources
    OntClass feature = model.createClass(BASE + "feature");
    OntResource vertebra = model.createOntResource(BASE + "vertebra");
    OntResource fur = model.createOntResource(BASE + "fur");
    vertebra.addProperty(RDF.type, feature);
    fur.addProperty(RDF.type, feature);
    
    // create environment resources
    OntClass environment = model.createClass(BASE + "environment");
    OntResource water = model.createOntResource(BASE + "water");
    water.addProperty(RDF.type, environment);
    
    // create properties
    OntProperty has = model.createOntProperty(BASE + "has");
    OntProperty livesIn = model.createOntProperty(BASE + "livesIn");
    
    // set superclasses
    mammal.setSuperClass(animal);
    whale.setSuperClass(mammal);
    bear.setSuperClass(mammal);
    cat.setSuperClass(mammal);
    fish.setSuperClass(animal);
    
    // set properties
    mammal.setPropertyValue(has, vertebra);
    cat.setPropertyValue(has, fur);
    bear.setPropertyValue(has, fur);
    whale.setPropertyValue(livesIn, water);
    fish.setPropertyValue(livesIn, water);
    
    // print out the generated model
    model.write(System.out);

  }
  
}
