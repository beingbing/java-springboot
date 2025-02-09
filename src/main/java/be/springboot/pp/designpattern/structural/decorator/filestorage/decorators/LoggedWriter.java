package be.springboot.pp.designpattern.structural.decorator.filestorage.decorators;

import be.springboot.pp.designpattern.structural.decorator.filestorage.basewriter.DataWriter;

public class LoggedWriter extends DataWriterDecorator {
    public LoggedWriter(DataWriter writer) {
        super(writer);
    }

    @Override
    public void writeData(String data) {
        System.out.println("📜 Logging Data: " + data);
        super.writeData(data);
    }
}
