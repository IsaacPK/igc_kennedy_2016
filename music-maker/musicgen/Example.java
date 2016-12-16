package musicgen;


import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
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
		
	// generateOptions (myRandom);
		
		
		playNote (mc, generateOptions (60,myRandom) );
		
		System.out.println(myRandom);
	
		
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
		System.out.println("done");
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
	
	