// Name: Saasha Mor
// Date: 04/12/2018
// Section: AR
// TA: Alissa Adornato

import java.util.*;

public class GuitarString {
	private Queue<Double> ringBuffer;
	private Random rand;
	
	public static final double ENERGY_DECAY_FACTOR = 0.996;
	
	// pre:  frequency should be more than zero and desired
	//		 capacity of ring buffer should be more than two
	//		 otherwise throws IllegalArgumentException
	// post: Constructs a guitar string with given frequency.
	//		 Adds 0.0 to ring buffer to fill it to desired capacity
	// parameters needed:
	//			frequency: a guitar string is created with this frequency
	public GuitarString(double frequency) {
		rand = new Random();
		ringBuffer = new LinkedList<Double>();
		int capacity = (int)Math.round(StdAudio.SAMPLE_RATE / frequency);
		if((frequency <= 0) || (capacity < 2)) {
			throw new IllegalArgumentException();
		}
		for(int i = 0; i < capacity; i++) {
			ringBuffer.add(0.0);
		}
	}
	
	// pre:  length of array should be more than 2 else throws IllegalArgumentException
	// post: Constructs a guitar string by copying all elements
	// 		 from the given set of values to the ring buffer
	// parameters needed:
	//	init- values which need to be copied to the ring buffer
	public GuitarString(double[] init) {
		rand = new Random();
		ringBuffer = new LinkedList<Double>();
		int capacity = init.length;
		if(capacity < 2) {
			throw new IllegalArgumentException("Array length = " + capacity);
		}
		for(int i = 0; i < capacity; i++) {
			ringBuffer.add(init[i]);
		}
	}
	
	// post: Replaces all elements in the ring buffer with
	// 		 random values between -0.5 and 0.5
	public void pluck() {
		for(int i = 0; i < ringBuffer.size(); i++) {
			ringBuffer.remove();
			ringBuffer.add(rand.nextDouble() - 0.5);
		}
	}
	
	//post: Adds the average of the first two values multiplied
	// 		with the energy decay factor to the ring buffer and deletes
	// 		the first element of the ring Buffer
	public void tic() {
		ringBuffer.add(ENERGY_DECAY_FACTOR * (ringBuffer.remove()+ ringBuffer.peek()) / 2);
	}
	
	//post: returns the element that is currently at the front of the ring buffer
	public double sample() {
		return ringBuffer.peek();
	}
}