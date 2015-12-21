import java.io.*;

class helpLoader
{
	public helpLoader(String str) throws IOException
    {
        String fileName = str;
        String[] commands = {"cmd", "/c", "start", "\"DummyTitle\"",fileName};
        Runtime.getRuntime().exec(commands);
    }
}