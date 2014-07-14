package org.kie.config.cli.command.impl;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.jboss.weld.environment.se.WeldContainer;
import org.jboss.weld.literal.NamedLiteral;
import org.kie.config.cli.CliContext;
import org.kie.config.cli.command.CliCommand;
import org.uberfire.io.IOService;

public class PushGitRepositoryCliCommand implements CliCommand {

    @Override
    public String getName() {
        return "push-git-repo";
    }

    @Override
    public String execute(CliContext context) {

        WeldContainer container = context.getContainer();

        IOService ioService = container.instance().select(IOService.class, new NamedLiteral("configIO")).get();

        String upstream = context.getParameter("git-upstream");
        String gitlocal = context.getParameter("git-local");

        if (upstream == null || gitlocal == null) {
            return "No upstream ("+upstream+") or no local ("+gitlocal+") git repository info available";
        }

        ioService.getFileSystem(URI.create(gitlocal+"?push="+upstream));
        return "Pushed successfully";
    }
}
