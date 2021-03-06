package com.hribol.bromium.cli;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.hribol.bromium.cli.commands.*;
import org.apache.commons.io.IOUtils;
import org.docopt.Docopt;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static com.hribol.bromium.cli.Main.Commands.*;

/**
 * Created by hvrigazov on 14.03.17.
 */
public class Main {

    public static class Commands {
        public static final String INIT = "init";
        public static final String RECORD = "record";
        public static final String REPLAY = "replay";
        public static final String UPDATE = "update";
        public static final String VERSION = "version";
    }

    private static Injector injector;

    public static void main(String[] args) {
        try {
            InputStream inputStream = Main.class.getResourceAsStream("/cli-specification.txt");
            String doc = IOUtils.toString(inputStream);
            Docopt docopt = new Docopt(doc);
            Map<String, Object> opts = docopt
                    .withVersion("bromium 0.1")
                    .withExit(false)
                    .parse(args);

            System.out.println(opts);
            Map<String, Supplier<Command>> commandToHandler = getCommands();
            String selectedCommand = commandToHandler
                    .keySet()
                    .stream()
                    .filter(command -> opts.get(command).equals(true))
                    .findAny()
                    // we know that a command must be present because otherwise docopt would have stopped
                    .get();

            injector = Guice.createInjector(new DefaultModule(selectedCommand, opts));
            commandToHandler.get(selectedCommand).get().run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Map<String, Supplier<Command>> getCommands() {
        Map<String, Supplier<Command>> map = new HashMap<>();
        map.put(INIT, () -> injector.getInstance(InitCommand.class));
        map.put(RECORD, () -> injector.getInstance(RecordCommand.class));
        map.put(REPLAY, () -> injector.getInstance(ReplayCommand.class));
        map.put(UPDATE, () -> injector.getInstance(UpdateCommand.class));
        map.put(VERSION, () -> injector.getInstance(VersionCommand.class));
        return map;
    }

}
