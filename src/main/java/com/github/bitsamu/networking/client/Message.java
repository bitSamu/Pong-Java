package com.github.bitsamu.networking.client;

import java.io.InputStream;
import java.io.OutputStream;

public interface Message {
    void read(InputStream in);
    void write(OutputStream out);
}
