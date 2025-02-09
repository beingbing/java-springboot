package be.springboot.pp.designpattern.structural.decorator.filestorage;

import be.springboot.pp.designpattern.structural.decorator.filestorage.basewriter.DataWriter;
import be.springboot.pp.designpattern.structural.decorator.filestorage.basewriter.FileDataWriter;
import be.springboot.pp.designpattern.structural.decorator.filestorage.decorators.CompressedWriter;
import be.springboot.pp.designpattern.structural.decorator.filestorage.decorators.EncryptedWriter;
import be.springboot.pp.designpattern.structural.decorator.filestorage.decorators.LoggedWriter;

public class Tester {

    public static void main(String[] args) {
        String filename = "src/main/java/be/springboot/pp/designpattern/structural/decorator/filestorage/secure_data.txt";

        // Base Writer: Writes directly to file
        DataWriter fileWriter = new FileDataWriter(filename);

        // Step 1: Add Encryption
        DataWriter encryptedWriter = new EncryptedWriter(fileWriter);

        // Step 2: Add Compression on top of Encryption
        DataWriter compressedEncryptedWriter = new CompressedWriter(encryptedWriter);

        // Step 3: Add Logging on top of Compression + Encryption
        DataWriter fullyDecoratedWriter = new LoggedWriter(compressedEncryptedWriter);

        // Writing Secure Data
        fullyDecoratedWriter.writeData("Sensitive Transaction Details");

        // Writing Normal Log Data (Without Encryption or Compression)
        DataWriter loggedFileWriter = new LoggedWriter(new FileDataWriter(filename));
        loggedFileWriter.writeData("This is a simple log entry.");
    }
}
