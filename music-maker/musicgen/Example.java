package musicgen;


import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class Example {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
