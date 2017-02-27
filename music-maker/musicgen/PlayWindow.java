// Jasmine




// Concepts borrowed from:
//http://www3.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html

package musicgen;

import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;  // Using AWT event classes and listener interfaces
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.awt.Color;
 
// An AWT program inherits from the top-level container java.awt.Frame
public class PlayWindow extends Frame implements WindowListener {
   private Label lblCount, lblRecreate;    // Declare a Label component 
   private TextField tfCount, tfPlayback; // Declare a TextField component 
   private Button btnCount, btnRecreate, btnNew, btnfilefind;   // Declare a Button component
   private int count = 0;     // Counter's value
   private JComboBox box;
   
  JPanel controlPanel= new JPanel();
  private JFrame mainFrame;
 
   // Returns an ImageIcon, or null if the path was invalid. 
   private static ImageIcon createImageIcon(String path,
     String description) {
	   
   
       return new ImageIcon("maxresdefault.jpg", "cat in toilet roll");
      
   }
   
   private static String [] filename = {"Added files here"};
   
   // Constructor to setup GUI components and event handlers
   public PlayWindow () {
	   
	      
	      
      setLayout(new FlowLayout());
         // "super" Frame (a Container) sets its layout to FlowLayout, which arranges
         // the components from left-to-right, and flow to next row from top-to-bottom.
      
      Panel Panel1 = new Panel();
      add (Panel1);
 
      lblCount = new Label("New Music!");  // construct the Label component
      Panel1.add(lblCount);                    // "super" Frame adds Label
      
      btnCount = new Button("New!");   // construct the Button component
      Panel1.add(btnCount);   // "super" Frame adds Button
      btnCount.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evt) {
             MakeNew();
          }
       });
      
      lblRecreate = new Label ("Read from file");
      Panel1.add(lblRecreate);
 

 
      
      
      btnRecreate = new Button("Play");
      Panel1.add(btnRecreate);
      btnRecreate.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evt) {
             ReadPlay();
          }
       });
       
      Panel Panel2 = new Panel();
      add(Panel2);
      
      tfCount = new TextField(3); // construct the TextField component
      Panel2.add(tfCount);                     // "super" Frame adds TextField;
      
 
      btnNew = new Button ("new folder");
      Panel2.add(btnNew);
      btnNew.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evt) {
             newFile();
             filename [0] =  tfCount.getText();
          }
       });
      
      box = new JComboBox (filename);
      Panel2.add(box);
     
      
      btnfilefind = new Button ("find file");
      Panel2.add(btnfilefind);
      btnfilefind.addActionListener(new ActionListener() {
          @Override
         public void actionPerformed(ActionEvent evt) {
           findfile();
         }
     });
   
      
      
      
      ImageIcon icon = createImageIcon ("maxresdefault.png", "");
     JLabel label1 = new JLabel("", icon, JLabel.CENTER);
     this.add(label1);
      
     addWindowListener(this);
     
      this.setBackground(new Color (126,171, 166));
      setTitle("Play Window");  // "super" Frame sets its title
      setSize(350, 400);        // "super" Frame sets its initial window size
   
      setVisible(true);         // "super" Frame shows
 
    
   
   
  
   
	// TODO Auto-generated method stub
	return;
}
   


    public void findfile () {
	
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
   String fileName = (String) box.getSelectedItem();

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


    public void newFile () {
	
	String filename = tfCount.getText() + ".txt";
     tfCount.setText("");  // Clear input TextField

    try {
    	BufferedWriter writer = new BufferedWriter (new FileWriter (filename) );
    	
    	writer.close();
    	
    } catch (IOException e) {
    	
    	e.printStackTrace();
    }

{
		
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
			writer = new PrintWriter(filename, "UTF-8");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			return;
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			return;
		}
		
		int lastnote = 65;
		
		while (counter < 10){
			
		
			int myNote = generateOptions (lastnote ,myRandom);
			while ( myNote < 30 || myNote > 80 ) {
				
			  myNote = generateOptions (lastnote, getRandom());
			}
			playNote (mc, myNote );
		
			myRandom = getRandom();
		
			System.out.println(myNote);
		
			System.out.println (myRandom);

	
			counter++;
			
			lastnote = myNote;
			
			writer.println(myNote);
		
		
		}
		
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writer.close();
		
		
	}
    box.insertItemAt(filename, 1);
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
		
		int lastnote = 65;
		
		while (counter < 25){
			
		
			int myNote = generateOptions (lastnote ,myRandom);
			while ( myNote < 30 || myNote > 80 ) {
				
			  myNote = generateOptions (lastnote, getRandom());
			}
			playNote (mc, myNote );
		
			myRandom = getRandom();
		
			System.out.println(myNote);
		
			System.out.println (myRandom);

	
			counter++;
			
			lastnote = myNote;
			
			writer.println(myNote);
		
		
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
	
	   @Override
	public void windowClosing(WindowEvent evt) {
	      System.exit(0);  // Terminate the program
	   }
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
