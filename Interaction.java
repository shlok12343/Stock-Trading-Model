package controller;

/**
 * An interaction with the user consists of some input to send the program
 * and some output to expect.  We represent it as an object that takes in two
 * StringBuilders and produces the intended effects on them
 */
public interface Interaction {
  /**
   * Applies this interaction to the given StringBuilders which represent input
   * and output streams.
   * The method is expected to append the simulated user input to the 'in' StringBuilder and
   * the expected output to the 'out' StringBuilder. This method facilitates the testing of
   * interaction-based systems by predefining input/output sequences.
   *
   * @param in  the StringBuilder that simulates the input stream to the system
   * @param out the StringBuilder that collects the output from the system as a response
   *            to the input
   */
  void apply(StringBuilder in, StringBuilder out);
}
