package com.krf.entity.test4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Output extends Filter {

    private List<String> outputTxt = new ArrayList<>();
    public Output(File file, Pipe input) {
        super(input, null);
    }

    @Override
    public void transform() throws IOException {

        while (input.hashNextLine()) {
            outputTxt.add(input.readerLine());
        }

        input.closeReader();
    }

    public List<String> getOutputTxt() {
        return outputTxt;
    }

}