package be.springboot.pp.designpattern.structural.decorator.filestorage.decorators;

import be.springboot.pp.designpattern.structural.decorator.filestorage.basewriter.DataWriter;

import java.util.Base64;

public class EncryptedWriter extends DataWriterDecorator {
    public EncryptedWriter(DataWriter writer) {
        super(writer);
    }

    @Override
    public void writeData(String data) {
        String encryptedData = Base64.getEncoder().encodeToString(data.getBytes());
        System.out.println("🔒 Encrypting Data: " + encryptedData);
        super.writeData(encryptedData);
    }
}
