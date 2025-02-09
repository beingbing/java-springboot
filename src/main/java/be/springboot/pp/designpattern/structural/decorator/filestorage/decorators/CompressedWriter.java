package be.springboot.pp.designpattern.structural.decorator.filestorage.decorators;

import be.springboot.pp.designpattern.structural.decorator.filestorage.basewriter.DataWriter;

import java.util.zip.Deflater;

public class CompressedWriter extends DataWriterDecorator {
    public CompressedWriter(DataWriter writer) {
        super(writer);
    }

    @Override
    public void writeData(String data) {
        byte[] compressedData = compress(data);
        System.out.println("🗜 Compressing Data: " + new String(compressedData));
        super.writeData(new String(compressedData));
    }

    private byte[] compress(String data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data.getBytes());
        deflater.finish();
        byte[] compressed = new byte[1024];
        int compressedSize = deflater.deflate(compressed);
        deflater.end();
        return java.util.Arrays.copyOf(compressed, compressedSize);
    }
}
