package com.RPE;

import java.io.*;

import gate.*;
import gate.util.*;
import gate.util.persistence.PersistenceManager;

public class Annie  {

  private CorpusController annieController;
  
  public void initAnnie() throws GateException, IOException {
    // load the ANNIE application from the saved state in plugins/ANNIE
    File gateHome = Gate.getGateHome();
    
    File annieGapp = new File(gateHome, "ANNIEResumeParser.gapp");
    annieController = (CorpusController) PersistenceManager.loadObjectFromFile(annieGapp);
  }

  public void setCorpus(Corpus corpus) {
    annieController.setCorpus(corpus);
  }

  public void execute() throws GateException {
    Out.prln("Running processing engine...");
    annieController.execute();
    Out.prln("...processing engine complete");
  }
}
 
