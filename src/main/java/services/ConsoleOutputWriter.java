package services;

import interfaces.OutputWriter;

public class ConsoleOutputWriter implements OutputWriter<StringBuilder> {

	@Override
	public void write(StringBuilder game) {
		System.out.print(game);
	}
}
