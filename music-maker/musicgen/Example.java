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
		
		playNote(mc, 60);
		
		playNote(mc, 64);
		
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
	
	
	private static int getRandom {
	   
	   int counter = 0;

		while (counter < 100) {

			Random randomGenerator = new Random();

			int randomInt = randomGenerator.nextInt(100);
			   
			int Startnote= 70;
		
			int mynotevalue= Startnote + randomInt % 60;
		
			mc[5].noteOn(mynotevalue,400);
			
			counter++; 			
		}
		
		return mynotevalue;
	}
	
}
