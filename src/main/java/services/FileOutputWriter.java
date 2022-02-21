package services;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.BowlingGameException;
import interfaces.OutputWriter;

public class FileOutputWriter implements OutputWriter<StringBuilder>{

	private static final Logger logger = LogManager.getLogger(FileOutputWriter.class);
	
	private String filePath;
	
	public FileOutputWriter(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public void write(StringBuilder game) {
		try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(filePath))) {
			bw.write(game.toString());
		} catch (IOException e) {
			logger.error(String.format("Exception: %s. Message: %s. Cause: %s. ", e.getClass(), e.getMessage(), e.getCause()));
			throw new BowlingGameException(String.format("Error trying to write the output at the file: %s.", filePath));
		} catch (InvalidPathException e) {
			logger.error(String.format("Exception: %s. Message: %s. Cause: %s. ", e.getClass(), e.getMessage(), e.getCause()));
			throw new BowlingGameException(String.format("Error creating the file with path: %s.", filePath));
		}
	}

}
