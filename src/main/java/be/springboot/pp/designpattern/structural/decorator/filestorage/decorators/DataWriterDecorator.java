package be.springboot.pp.designpattern.structural.decorator.filestorage.decorators;

import be.springboot.pp.designpattern.structural.decorator.filestorage.basewriter.DataWriter;

public class DataWriterDecorator implements DataWriter {
    protected DataWriter wrappedWriter;

    public DataWriterDecorator(DataWriter writer) {
        this.wrappedWriter = writer;
    }

    @Override
    public void writeData(String data) {
        wrappedWriter.writeData(data); // Delegate writing
    }
}
