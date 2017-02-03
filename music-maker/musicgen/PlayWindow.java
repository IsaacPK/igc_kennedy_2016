// Jasmine



// Concepts borrowed from:
//http://www3.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html

package musicgen;

import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;  // Using AWT event classes and listener interfaces
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
 
// An AWT program inherits from the top-level container java.awt.Frame
public class PlayWindow extends Frame {
   private Label lblCount, lblRecreate;    // Declare a Label component 
   private TextField tfCount; // Declare a TextField component 
   private Button btnCount, btnRecreate;   // Declare a Button component
   private int count = 0;     // Counter's value
 
   // Constructor to setup GUI components and event handlers
   public PlayWindow () {
      setLayout(new FlowLayout());
         // "super" Frame (a Container) sets its layout to FlowLayout, which arranges
         // the components from left-to-right, and flow to next row from top-to-bottom.
 
      lblCount = new Label("New Music!");  // construct the Label component
      add(lblCount);                    // "super" Frame adds Label
      
      btnCount = new Button("New!");   // construct the Button component
      add(btnCount);   // "super" Frame adds Button
      btnCount.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evt) {
             MakeNew();
          }
       });
      
      lblRecreate = new Label ("Read from file");
      add(lblRecreate);
 
    //  tfCount = new TextField("0", 10); // construct the TextField component
    // tfCount.setEditable(false);       // set to read-only
    //  add(tfCount);                     // "super" Frame adds TextField
 
      
      
      btnRecreate = new Button("Play");
      add(btnRecreate);
      btnRecreate.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evt) {
             ReadPlay();
          }
       });
 
  
      
     
 
      setTitle("Play Window");  // "super" Frame sets its title
      setSize(300, 100);        // "super" Frame sets its initial window size
 
 
      setVisible(true);         // "super" Frame shows
 
    
   }
   
  
   
   public void ReadPlay() {
	   Synthesizer synth;
		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		final MidiChannel[] mc = synth.getChannels();
		Instrument[] instr = synth.getDefaultSoundbank().getInstruments();
		synth.loadInstrument(instr[90]);
		
		
		
		 // The name of the file to open.
       String fileName = "musicgen.txt";

       // This will reference one line at a time
       String line = null;

       try {
           // FileReader reads text files in the default encoding.
           FileReader fileReader = 
               new FileReader(fileName);

           // Always wrap FileReader in BufferedReader.
           BufferedReader bufferedReader = 
               new BufferedReader(fileReader);

           while((line = bufferedReader.readLine()) != null) {
           	
           	int result = Integer.parseInt(line);
           	
           	playNote(mc,result);
           }   

           // Always close files.
           bufferedReader.close();         
       }
       catch(FileNotFoundException ex) {
           System.out.println(
               "Unable to open file '" + 
               fileName + "'");                
       }
       catch(IOException ex) {
           System.out.println(
               "Error reading file '" 
               + fileName + "'");                  
           // Or we could just do this: 
           // ex.printStackTrace();
       }

	
		
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }

	private static void playNote(MidiChannel[] channel, int noteNum) {
		
		channel[5].noteOn(noteNum, 200);
		
		try {
			TimeUnit.MILLISECONDS.sleep(400);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		channel[5].allNotesOff();
	//	System.out.println("done");
		
	}
	
	private void MakeNew (){
		
		Synthesizer synth;
		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		final MidiChannel[] mc = synth.getChannels();
		Instrument[] instr = synth.getDefaultSoundbank().getInstruments();
		synth.loadInstrument(instr[90]);
		
		int myRandom = getRandom();
		
		int counter = 0;
		
		PrintWriter writer;
		try {
			writer = new PrintWriter("musicgen.txt", "UTF-8");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			return;
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			return;
		}
		   
		
		while (counter < 10){
			
		
			int myNote = generateOptions (65 ,myRandom);
			playNote (mc, myNote );
		
			myRandom = getRandom();
		
			System.out.println(myNote);
		
			System.out.println (myRandom);
		
			myRandom = getRandom();
		
			int myNote2 = generateOptions (myNote, myRandom);
		
			playNote (mc, myNote2);
		
			System.out.println(myNote2);
		
			System.out.println(myRandom);
	
			counter++;
			
			writer.println(myNote);
		    writer.println(myNote2);
		
		}
		
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writer.close();
		
		
	}
	private static int getRandom() {
		   
		  

		Random randomGenerator = new Random();

		int randomInt = randomGenerator.nextInt(100);
		   
	
		int mynotevalue= randomInt % 5;
	
		return mynotevalue;
	}

	private static int generateOptions(int note, int randomNumber){
	 	int[] tempOptions = new int[6]; 
	 		
	 		tempOptions[0] = note + 7;
	 		tempOptions[1] = note + 3;
	 		tempOptions[2] = note + 4;
	 		tempOptions[3] = note - 7;
	 		tempOptions[4] = note - 3;
	 		tempOptions[5] = note - 4;
	 
	 		return tempOptions [randomNumber];
	
	 	}

}
