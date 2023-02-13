package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public interface JavaGrep {
    /**
     * Top Level search workflow
     *
     * @throws java.io.IOException
     */

    void process() throws IOException;
    /**
     * Traverse a given directory and return alL files
     *
     * @param rootDir input directory
     * @return files under the rootDir
     */

    List<File> listFiles(String rootDir);
    /**
     * Read a file and return all the Lines
     * Explain FileReader, BufferedReader, and character encoding
     *
     * @param inputFile file to be read
     * @return Lines
     * @throws IllegalArgumentException If a given inputFile is not a FIle
     */

    List<String> readLines(File inputFile);
    /**
     * Check if a line contains the regex pattern (passed by user)
     *
     * @param line Input string
     * @return true if there is a match
     */

    boolean containsPattern(String line);

    boolean containPattern(String line);

    /**
     * Write Lines to a file
     * Explore: FileOutputStream, OutputStreamwriter, and Bufferedwriter
     *
     * @param lines matched Line
     * @throws IOException if write failed
     */

    void writeToFile(List<String> lines) throws IOException;

    String getRootPath();

    void setRootPath(String rootPath);

    String getRégex();

    String getRegex();

    void setRegex(String regex);

    String getOutFile();

    void setOutFile(String outFile);
}