package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1",
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {

    @Parameters(index = "0", description = "path to first file")
    private String filePath1;

    @Parameters(index = "1", description = "path to second file")
    private String filePath2;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format [default: stylish")
    private String format;

    @Override
    public Integer call() {
        var model = Differ.generate(filePath1, filePath2, format);
        System.out.println(model);
        return 0;
    }

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }
}
